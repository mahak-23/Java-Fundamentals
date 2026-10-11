import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileStorageDemo {
    public static void main(String[] args) {
        InMemoryObjectStore objectStore = new InMemoryObjectStore();
        MetadataRepository metadata = new MetadataRepository();
        FileStorageService service = new FileStorageService(objectStore, metadata);

        String fileId = service.upload(
                "USER-7",
                "hello.txt",
                "Hello, file storage!".getBytes(StandardCharsets.UTF_8));

        byte[] downloaded = service.download("USER-7", fileId);
        System.out.println("Downloaded: " + new String(downloaded, StandardCharsets.UTF_8));
        System.out.println("Checksum: " + metadata.find(fileId).checksum);

        try {
            service.download("USER-8", fileId);
        } catch (SecurityException exception) {
            System.out.println("Other user blocked: " + exception.getMessage());
        }
    }

    static final class FileStorageService {
        private final InMemoryObjectStore objectStore;
        private final MetadataRepository metadata;
        private int nextId = 1;

        FileStorageService(InMemoryObjectStore objectStore, MetadataRepository metadata) {
            this.objectStore = objectStore;
            this.metadata = metadata;
        }

        synchronized String upload(String ownerId, String fileName, byte[] content) {
            String fileId = "FILE-" + nextId++;
            String objectKey = ownerId + "/" + fileId;
            String checksum = sha256(content);

            objectStore.put(objectKey, content);
            metadata.save(new FileRecord(fileId, ownerId, fileName, objectKey, checksum));
            return fileId;
        }

        byte[] download(String requesterId, String fileId) {
            FileRecord record = metadata.find(fileId);
            if (record == null) {
                throw new IllegalArgumentException("File not found: " + fileId);
            }
            if (!record.ownerId.equals(requesterId)) {
                throw new SecurityException("Requester does not own this file");
            }

            byte[] content = objectStore.get(record.objectKey);
            if (!sha256(content).equals(record.checksum)) {
                throw new IllegalStateException("File checksum does not match metadata");
            }
            return content;
        }

        private String sha256(byte[] content) {
            try {
                byte[] digest = MessageDigest.getInstance("SHA-256").digest(content);
                StringBuilder hex = new StringBuilder();
                for (byte value : digest) {
                    hex.append(String.format("%02x", value & 0xff));
                }
                return hex.toString();
            } catch (NoSuchAlgorithmException exception) {
                throw new IllegalStateException("SHA-256 is unavailable", exception);
            }
        }
    }

    static final class InMemoryObjectStore {
        private final Map<String, byte[]> objects = new HashMap<>();

        synchronized void put(String key, byte[] content) {
            objects.put(key, Arrays.copyOf(content, content.length));
        }

        synchronized byte[] get(String key) {
            byte[] content = objects.get(key);
            if (content == null) {
                throw new IllegalArgumentException("Object not found: " + key);
            }
            return Arrays.copyOf(content, content.length);
        }
    }

    static final class MetadataRepository {
        private final Map<String, FileRecord> records = new HashMap<>();

        synchronized void save(FileRecord record) {
            records.put(record.fileId, record);
        }

        synchronized FileRecord find(String fileId) {
            return records.get(fileId);
        }
    }

    static final class FileRecord {
        final String fileId;
        final String ownerId;
        final String fileName;
        final String objectKey;
        final String checksum;

        FileRecord(String fileId, String ownerId, String fileName,
                   String objectKey, String checksum) {
            this.fileId = fileId;
            this.ownerId = ownerId;
            this.fileName = fileName;
            this.objectKey = objectKey;
            this.checksum = checksum;
        }
    }
}

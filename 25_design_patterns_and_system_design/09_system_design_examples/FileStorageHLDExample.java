import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FileMetadata {
    private final String fileId;
    private final String fileName;
    private final long sizeInBytes;
    private final String contentType;
    private final List<String> chunkIds;

    public FileMetadata(String fileId, String fileName, long sizeInBytes, String contentType, List<String> chunkIds) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.sizeInBytes = sizeInBytes;
        this.contentType = contentType;
        this.chunkIds = new ArrayList<>(chunkIds);
    }

    public String getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public String getContentType() {
        return contentType;
    }

    public List<String> getChunkIds() {
        return new ArrayList<>(chunkIds);
    }
}

class SimpleObjectStore {
    private final Map<String, byte[]> chunks = new HashMap<>();

    public void putChunk(String chunkId, byte[] data) {
        chunks.put(chunkId, data);
    }

    public byte[] getChunk(String chunkId) {
        return chunks.get(chunkId);
    }
}

class FileStorageService {
    private final SimpleObjectStore objectStore = new SimpleObjectStore();
    private final Map<String, FileMetadata> metadataStore = new HashMap<>();
    private final int chunkSize = 4 * 1024;

    public FileMetadata uploadFile(String fileId, String fileName, byte[] fileData, String contentType) {
        List<String> chunkIds = new ArrayList<>();

        for (int i = 0; i < fileData.length; i += chunkSize) {
            int end = Math.min(i + chunkSize, fileData.length);
            byte[] chunk = java.util.Arrays.copyOfRange(fileData, i, end);
            String chunkId = fileId + "_chunk_" + (chunkIds.size() + 1);
            objectStore.putChunk(chunkId, chunk);
            chunkIds.add(chunkId);
        }

        FileMetadata metadata = new FileMetadata(fileId, fileName, fileData.length, contentType, chunkIds);
        metadataStore.put(fileId, metadata);

        System.out.println("Uploaded file: " + fileName + " with " + chunkIds.size() + " chunks");
        return metadata;
    }

    public byte[] downloadFile(String fileId) {
        FileMetadata metadata = metadataStore.get(fileId);
        if (metadata == null) {
            throw new IllegalArgumentException("File not found: " + fileId);
        }

        List<byte[]> chunks = new ArrayList<>();
        for (String chunkId : metadata.getChunkIds()) {
            byte[] chunk = objectStore.getChunk(chunkId);
            if (chunk == null) {
                throw new IllegalStateException("Missing chunk for file: " + fileId + " chunk: " + chunkId);
            }
            chunks.add(chunk);
        }

        int totalSize = 0;
        for (byte[] chunk : chunks) {
            totalSize += chunk.length;
        }

        byte[] combined = new byte[totalSize];
        int offset = 0;
        for (byte[] chunk : chunks) {
            System.arraycopy(chunk, 0, combined, offset, chunk.length);
            offset += chunk.length;
        }

        System.out.println("Downloaded file: " + metadata.getFileName() + " size=" + combined.length);
        return combined;
    }
}

public class FileStorageHLDExample {
    public static void main(String[] args) {
        FileStorageService service = new FileStorageService();

        String fileId = "file_101";
        String fileName = "report.pdf";
        byte[] payload = "PDF content: this is a sample file for object storage design".getBytes();

        FileMetadata uploaded = service.uploadFile(fileId, fileName, payload, "application/pdf");
        byte[] downloaded = service.downloadFile(fileId);

        System.out.println("Stored metadata: " + uploaded.getFileName() + ", chunks=" + uploaded.getChunkIds().size());
        System.out.println("Downloaded content: " + new String(downloaded));

        // HLD explanation:
        // - Actual files are stored in object storage as chunks.
        // - Metadata DB tracks file info, chunk IDs, and ownership.
        // - Download reconstructs the file from the stored chunks.
        // - In production, a CDN and signed URLs are added for scale and security.
    }
}

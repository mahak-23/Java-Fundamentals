import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoStreamingPipelineDemo {
    public static void main(String[] args) {
        System.out.println("=== Upload + transcoding workflow ===");
        VideoUploadService uploadService = new VideoUploadService();
        String videoId = uploadService.upload("user-42", "travel-reel.mp4");
        uploadService.startTranscoding(videoId);

        System.out.println("\n=== Recommendation candidate generation ===\n");
        User user = new User("user-42", List.of("travel", "food", "technology"));
        RecommendationService recommendationService = new RecommendationService();
        List<String> candidates = recommendationService.generateCandidates(user);
        System.out.println("Candidates for " + user.userId + ": " + candidates);

        System.out.println("\n=== AI scoring pipeline ===");
        AIRecommendationScorer scorer = new AIRecommendationScorer();
        Map<String, Double> scores = scorer.scoreCandidates(user, candidates);
        scores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry.getKey() + " -> score=" + String.format("%.2f", entry.getValue())));
    }

    static final class VideoUploadService {
        private final Map<String, List<String>> transcodedVariants = new HashMap<>();

        String upload(String userId, String fileName) {
            String videoId = "video-" + System.currentTimeMillis();
            System.out.println("Upload accepted for user=" + userId + ", file=" + fileName + ", videoId=" + videoId);
            transcodedVariants.put(videoId, new ArrayList<>());
            return videoId;
        }

        void startTranscoding(String videoId) {
            List<String> variants = List.of("360p", "720p", "1080p");
            transcodedVariants.put(videoId, new ArrayList<>(variants));
            System.out.println("Queued transcoding job for " + videoId + " -> " + variants);
            for (String variant : variants) {
                System.out.println("  generated segment manifest for " + variant);
            }
        }
    }

    static final class User {
        final String userId;
        final List<String> interests;

        User(String userId, List<String> interests) {
            this.userId = userId;
            this.interests = interests;
        }
    }

    static final class RecommendationService {
        private final Map<String, List<String>> genreMap = Map.of(
                "travel", List.of("video-101", "video-204", "video-772"),
                "food", List.of("video-204", "video-500"),
                "technology", List.of("video-301", "video-204", "video-772")
        );

        List<String> generateCandidates(User user) {
            List<String> candidates = new ArrayList<>();
            for (String interest : user.interests) {
                candidates.addAll(genreMap.getOrDefault(interest, List.of()));
            }
            candidates.sort(Comparator.naturalOrder());
            return new ArrayList<>(List.copyOf(candidates.stream().distinct().toList()));
        }
    }

    static final class AIRecommendationScorer {
        Map<String, Double> scoreCandidates(User user, List<String> candidates) {
            Map<String, Double> scores = new HashMap<>();
            for (String candidate : candidates) {
                double score = 0.25 + (candidate.length() % 5) * 0.12;
                for (String interest : user.interests) {
                    if (candidate.contains("204") && interest.equals("travel")) {
                        score += 0.35;
                    }
                    if (candidate.contains("301") && interest.equals("technology")) {
                        score += 0.30;
                    }
                    if (candidate.contains("500") && interest.equals("food")) {
                        score += 0.25;
                    }
                }
                scores.put(candidate, score);
            }
            return scores;
        }
    }
}

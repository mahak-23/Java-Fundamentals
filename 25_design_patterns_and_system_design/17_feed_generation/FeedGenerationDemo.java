import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FeedGenerationDemo {
    public static void main(String[] args) {
        FollowGraph follows = new FollowGraph();
        follows.follow("USER-B", "CREATOR-A");
        follows.follow("USER-B", "CREATOR-C");

        PostStore posts = new PostStore();
        posts.add(new Post("POST-1", "CREATOR-A", "First update", 100));
        posts.add(new Post("POST-2", "CREATOR-C", "New photo", 300));
        posts.add(new Post("POST-3", "CREATOR-D", "Unfollowed creator", 200));

        FeedService feedService = new FeedService(follows, posts);
        System.out.println("Feed for USER-B:");
        for (Post post : feedService.buildFeed("USER-B", 10)) {
            System.out.println(post.postId + " from " + post.authorId + ": " + post.text);
        }
    }

    static final class Post {
        final String postId;
        final String authorId;
        final String text;
        final long createdAt;

        Post(String postId, String authorId, String text, long createdAt) {
            this.postId = postId;
            this.authorId = authorId;
            this.text = text;
            this.createdAt = createdAt;
        }
    }

    static final class FollowGraph {
        private final Map<String, Set<String>> followedAuthorsByUser = new HashMap<>();

        void follow(String userId, String authorId) {
            followedAuthorsByUser
                    .computeIfAbsent(userId, ignored -> new HashSet<>())
                    .add(authorId);
        }

        Set<String> getFollowedAuthors(String userId) {
            return followedAuthorsByUser.getOrDefault(userId, Set.of());
        }
    }

    static final class PostStore {
        private final List<Post> posts = new ArrayList<>();

        void add(Post post) {
            posts.add(post);
        }

        List<Post> getPostsByAuthors(Set<String> authorIds) {
            List<Post> matches = new ArrayList<>();
            for (Post post : posts) {
                if (authorIds.contains(post.authorId)) {
                    matches.add(post);
                }
            }
            return matches;
        }
    }

    static final class FeedService {
        private final FollowGraph follows;
        private final PostStore posts;

        FeedService(FollowGraph follows, PostStore posts) {
            this.follows = follows;
            this.posts = posts;
        }

        List<Post> buildFeed(String userId, int pageSize) {
            List<Post> candidates = posts.getPostsByAuthors(follows.getFollowedAuthors(userId));
            candidates.sort(Comparator.comparingLong((Post post) -> post.createdAt).reversed());
            return new ArrayList<>(candidates.subList(0, Math.min(pageSize, candidates.size())));
        }
    }
}

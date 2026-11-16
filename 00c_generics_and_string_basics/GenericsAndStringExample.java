import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericsAndStringExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");

        System.out.println("Names: " + names);

        String a = "Java";
        String b = "Java";
        String c = new String("Java");

        System.out.println("a == b: " + (a == b));
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println("a == c: " + (a == c));
        System.out.println("a.equals(c): " + a.equals(c));

        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 90);
        scores.put("Bob", 88);
        System.out.println("Alice score: " + scores.get("Alice"));

        StringBuilder builder = new StringBuilder();
        builder.append("Hello");
        builder.append(" Java");
        System.out.println("Builder: " + builder);

        String result = identity("Learning");
        System.out.println("Generic identity: " + result);
    }

    public static <T> T identity(T value) {
        return value;
    }
}

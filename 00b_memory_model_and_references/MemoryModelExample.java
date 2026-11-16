public class MemoryModelExample {
    public static void main(String[] args) {
        int primitive = 10;
        int copy = primitive;
        copy = 30;

        System.out.println("primitive = " + primitive); // 10
        System.out.println("copy = " + copy); // 30

        StringBuilder builder = new StringBuilder("Java");
        modifyBuilder(builder);
        System.out.println("After modifyBuilder: " + builder.toString()); // Java!

        String s1 = new String("Java");
        String s2 = new String("Java");

        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("s1.equals(s2): " + s1.equals(s2));

        StringBuilder builder2 = new StringBuilder("Hello");
        builder2.append(" World");
        System.out.println("builder2 = " + builder2);
    }

    static void modifyBuilder(StringBuilder builder) {
        builder.append("!");
    }
}

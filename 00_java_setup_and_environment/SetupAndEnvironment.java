public class SetupAndEnvironment {
    public static void main(String[] args) {
        System.out.println("Java setup is working.");
        System.out.println("JDK is installed and the program is running.");

        String javaVersion = System.getProperty("java.version");
        String osName = System.getProperty("os.name");

        System.out.println("Java version: " + javaVersion);
        System.out.println("Operating system: " + osName);
    }
}

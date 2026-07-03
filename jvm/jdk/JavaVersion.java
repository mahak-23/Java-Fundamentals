package jvm.jdk;

public class JavaVersion {
    public static void main(String args[]){
        System.out.println("--------------Java System Properties--------------");
        String javaVersion = System.getProperty("java.version");
        System.out.println("Java Version: " + javaVersion);

        String javaVendor = System.getProperty("java.vendor");
        System.out.println("Java Vendor: " + javaVendor);

        String javaHome = System.getProperty("java.home");
        System.out.println("Java Home: " + javaHome);

        String osName = System.getProperty("os.name");
        System.out.println("Operating System: " + osName);

        String userName = System.getProperty("user.name");
        System.out.println("User Name: " + userName);

        String userDir = System.getProperty("user.dir");
        System.out.println("User Directory: " + userDir);

        System.out.println("--------------End of Java System Properties--------------");
    }
}
package study20200821;

public class SystemProperty {
    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.home"));
//        System.out.println(System.getProperty("MAVEN_HOME"));
        System.getProperties().forEach((k,v) -> System.out.println(k+"="+v));
    }
}

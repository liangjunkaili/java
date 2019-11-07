package manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SecurityManagerTest {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(System.getSecurityManager());
        FileInputStream fileInputStream = new FileInputStream("D:\\test.txt");
        System.out.println(System.getProperty("liangjun"));
    }
}

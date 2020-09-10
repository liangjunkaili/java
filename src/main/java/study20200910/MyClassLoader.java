package study20200910;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) {
        String fileName = "D:\\code\\java\\target\\classes\\study20200910\\"+name+".class";
        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            while ((len = fis.read()) != -1) {
                bos.write(len);
            }
            byte[] data = bos.toByteArray();
            fis.close();
            bos.close();
            // 调用defineClass 把 class加载到内存
            return defineClass(name,data,0,data.length);
        }catch (Exception e){

        }
        return null;
    }

    public static void main(String[] args) {
        ClassLoader classLoader = new MyClassLoader();
        try {
            Class c = classLoader.loadClass("study20200910.SortStudy");
            System.out.println(c.getName());
            System.out.println(c.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

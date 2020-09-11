package study20200910;

import java.io.*;

/**
 * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) {
        String fileName = "D:\\code\\java\\target\\classes"+name+".class";
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

    public static void main(String[] args) throws Exception {
//        ClassLoader classLoader = new MyClassLoader();
//        try {
//            Class c = classLoader.loadClass("study20200910.SortStudy");
//            System.out.println(c.getName());
//            System.out.println(c.getClassLoader());
//            System.out.println(c.getClassLoader().getParent());
//            System.out.println(c.getClassLoader().getParent().getParent());
//            System.out.println(System.getProperty("java.class.path"));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        ClassLoader classLoader1 = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is==null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                } catch (IOException e) {
                    throw  new ClassNotFoundException(name);
                }
            }
        };
        Object obj = classLoader1.loadClass("study20200910.MyClassLoader").newInstance();
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(MyClassLoader.class.getClassLoader());
        System.out.println(obj instanceof MyClassLoader);
    }
}

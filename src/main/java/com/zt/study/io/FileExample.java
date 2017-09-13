package com.zt.study.io;

import org.junit.Test;

import java.io.*;
import java.util.Date;

/**
 * @Descriprion: TODO
 * @author: zhangxiaohua
 * @create 2017/9/13 19:45
 **/
public class FileExample implements Serializable{
    public static void main(String[] args) {
        File file = new File("F:\\workpace\\idea\\study\\NettyDemo\\src\\main\\java\\com\\zt\\study\\io\\test.txt");
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.getPath());
    }

    /**
     * 读取文件的字节数
     */
    @Test
    public void countFileByteLength() throws Exception {
//        File file = new File("F:\\workpace\\idea\\study\\NettyDemo\\src\\main\\java\\com\\zt\\study\\io\\test.txt");
        File file = new File("C:\\Users\\zhang\\Pictures\\test.png");
        if(!file.exists()){
            return;
        }
        int count = 0;
        InputStream inputStream = null;
        inputStream = new FileInputStream(file);
        int temp = 0;
        while ((temp = inputStream.read() ) != -1){
            System.out.println(temp);
            count++;
        }
        System.out.println(count);
        inputStream.close();
    }

    /**
     * 复制文件
     */
    @Test
    public void copyFileOneByOne() throws Exception{
        long begain = new Date().getTime();
        File file = new File("C:\\Users\\zhang\\Pictures\\test.png");
        File file2 = new File("C:\\Users\\zhang\\Pictures\\test2.png");
        if(file2.exists()){
            file2.delete();
        }
        int number = 0;
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        while ((number = fileInputStream.read()) != -1){
            fileOutputStream.write(number);
        }

        System.out.println("所用时间："+(new Date().getTime()-begain));

        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     * 复制文件
     * @throws Exception
     */
    @Test
    public void copyFileByBuffer() throws Exception{
        long begain = new Date().getTime();
        File file = new File("C:\\Users\\zhang\\Pictures\\test.png");
        File file2 = new File("C:\\Users\\zhang\\Pictures\\test2.png");
        if(file2.exists()){
            file2.delete();
        }
        int number = 0;
        byte[] buffer = new byte[1024];
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        while ((number = fileInputStream.read(buffer)) != -1){
            fileOutputStream.write(buffer);
        }

        System.out.println("所用时间："+(new Date().getTime()-begain));

        fileInputStream.close();
        fileOutputStream.close();
    }


    /**
     * 对象流的输入输出
     * @throws Exception
     */
    @Test
    public void objectStream() throws Exception{
        File file = new File("F:\\workpace\\idea\\study\\NettyDemo\\src\\main\\java\\com\\zt\\study\\io\\test.txt");
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
        People people = new People();
        people.setName("mike");
        people.setAge(15);
        people.setPhone("18829292713");
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(people);
        objectOutputStream.flush();
        objectInputStream = new ObjectInputStream(new FileInputStream(file));
        System.out.println(objectInputStream.readObject());

        objectInputStream.close();
        objectOutputStream.close();;





    }

    /**
     * people类，用于测试对象流,内部类，不仅内部类要实现Serializable，其所属的类也要实现
     */
    class People implements Serializable{
        private String name;
        private Integer age;
        private String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "People{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }
}

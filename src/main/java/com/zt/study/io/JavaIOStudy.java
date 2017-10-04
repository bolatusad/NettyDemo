package com.zt.study.io;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 19:30 2017/10/3
 */
public class JavaIOStudy {

    public File file = new File("test.txt");

    @Test
    public void testByteArrayInputStream() throws FileNotFoundException {
        byte[] buffer = new byte[1024];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
//        byteArrayInputStream.
        PrintStream printStream = new PrintStream(file);
        printStream.println("hello,hello");
    }


    @Test
    public void testFile() throws IOException {
//        File file = new File("F:\\");
//        String[] folders;
//        folders = file.list();
//        for (String folder : folders){
//            System.out.println(folder);
//        }

        File file = new File("F:"+File.separator+"study"+File.separator+"test.txt");
        if(!file.exists()){
            file.createNewFile();
        }
    }

    @Test
    public void testFileOutputStream() throws IOException {
        File file = new File("F:"+File.separator+"study"+File.separator+"test.txt");
        if(!file.exists()){
            file.createNewFile();
        }
//        OutputStream outputStream = new FileOutputStream(file);//这种方式是进行覆盖
        OutputStream outputStream = new FileOutputStream(file,true);//这种方式是进行追加
        String str = "hello Java IO Java IO";
        outputStream.write(str.getBytes());//字节流要转化成字节数组进行输出
        outputStream.close();
    }

    @Test
    public void testFileInputStream() throws IOException {
        File file = new File("F:"+File.separator+"study"+File.separator+"test.txt");
        InputStream inputStream = new FileInputStream(file);
        int length = (int) file.length();
        System.out.println(length);
        byte[] bytes = new byte[length];
        inputStream.read(bytes);
        System.out.println(Arrays.toString(bytes));
        System.out.println(new String(bytes));
        inputStream.close();
    }

    @Test
    public void testFileWriter() throws IOException {
        File file = new File("F:"+File.separator+"study"+File.separator+"test.txt");
//        Writer writer = new FileWriter(file);//这个是覆盖
        Writer writer = new FileWriter(file,true);//这个是追加
        writer.write("hello world！");
        writer.close();
    }

    @Test
    public void testFileReader() throws IOException {
        File file = new File("F:"+File.separator+"study"+File.separator+"test.txt");
        Reader reader = new FileReader(file);
        char[] chars = new char[(int) file.length()];
        reader.read(chars);
        System.out.println(Arrays.toString(chars));
        System.out.println(new String(chars));
        reader.close();
    }

    @Test
    public void testByteArrayStream() throws IOException {
        String str = "hello ByteArrayStream!";
        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        int readIndex = 0;
        while ((readIndex = inputStream.read()) != -1){
            outputStream.write(Character.toUpperCase(readIndex));
        }
        inputStream.close();
        outputStream.close();
        System.out.println(outputStream.toString());
    }

    @Test
    public void testBufferReader() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = bufferedReader.readLine();
        System.out.println(inputStr);
    }

}

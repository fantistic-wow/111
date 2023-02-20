import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Xor {
    private static final int numOfEncAndDec = 0x99;
    private static int dataOfFile = 0;

    public static void main(String[] args) {

        File initial = new File("待加密文件.TXT"); //
        File encrypt = new File("加密后文件.TXT"); //
        File decrypt = new File("解密后文件.TXT"); //

        //创建文件
        try {
            initial.createNewFile();
            encrypt.createNewFile();
            decrypt.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //对文件写入内容
            writeFile(initial,decrypt);
            //加密
            EncFile(initial, encrypt);
            //解密
            DecFile(encrypt,decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(File initial,File decrypt) throws Exception{
        if(!initial.exists()){
            System.out.println("The file does not exist");
            return;
        }
        if(!decrypt.exists()){
            System.out.println("The file does not exist");
            decrypt.createNewFile();
        }

        //IO流写入
        FileOutputStream f1 = new FileOutputStream(initial);
        FileOutputStream f2 = new FileOutputStream(decrypt);
        System.out.print("Please enter what needs to be encrypted:");
        String in = new Scanner(System.in).nextLine();
        f1.write(in.getBytes(StandardCharsets.UTF_8));
        f2.write("对被加密文件的解密结果为：".getBytes(StandardCharsets.UTF_8));
        f1.flush();
        f1.close();
        f2.flush();
        f2.close();
    }

    private static void EncFile(File initial, File encrypt) throws Exception {
        if(!initial.exists()){//判断是否有此文件
            System.out.println("The file does not exist");
            return;
        }
        if(!encrypt.exists()){
            System.out.println("The file does not exist");
            encrypt.createNewFile();
        }
        FileInputStream fis  = new FileInputStream(initial);
        FileOutputStream fos = new FileOutputStream(encrypt);
        while ((dataOfFile = fis.read()) > -1) {
            fos.write(dataOfFile^numOfEncAndDec);//对文件进行加密，将文件和秘钥进行异或操作。
        }

        fis.close();
        fos.flush();
        fos.close();
    }

    private static void DecFile(File encrypt, File decrypt) throws Exception {

        if(!encrypt.exists()){
            System.out.println("The file does not exist");
            return;
        }
        if(!decrypt.exists()){
            System.out.println("The file does not exist");
            decrypt.createNewFile();
        }

        FileInputStream fi  = new FileInputStream(encrypt);//读取加密文件
        FileOutputStream fj = new FileOutputStream(decrypt,true);
        while ((dataOfFile = fi.read()) > -1) {
            fj.write(dataOfFile^numOfEncAndDec);//再次进行异操作，提取文件
        }
        //文档关闭
        fi.close();
        fj.flush();
        fj.close();
    }
}

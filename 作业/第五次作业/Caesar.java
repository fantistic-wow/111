import java.io.IOException;
import java.util.Scanner;

public class Caesar {
    static int ASCII;
    static char[] messageArray=null;
    static int i = 0;
    static int key=0;
    public static void main(String[] args) throws IOException {
        int choice=-1;
        int flag=0;
        while(flag==0) {
            menu();
            Scanner in2=new Scanner(System.in);
            choice=in2.nextInt();
            switch (choice) {
                case 1:
                    go(choice);
                    break;
                case 2:
                    go(choice);
                    break;
                case 3:
                    flag++;
                    break;
                default:
                    System.out.println("Error！");
                    break;
            }
        }
        System.out.println("Thanks！！");
    }
    public static void menu() {
        System.out.println();
        System.out.println();
        System.out.println("*************CaesarCipher*************");
        System.out.println("    "+"1.encipher"+"      "+"2. decipher"+"        "+"3.Exit");
        System.out.println("**************************************");
        System.out.print("Please choose:\n");
    }
    public static void go(int choice) {
        boolean flag=false;
        do {
            flag=false;
            if(choice==1)
                System.out.print("Please enter what needs to be encrypted:");
            else
                System.out.print("Please enter the ciphertext:");
            Scanner in1=new Scanner(System.in);
            String message=in1.nextLine();
            System.out.print("Please enter the key:");
            Scanner in2=new Scanner(System.in);
            message=message.replaceAll("\\s*", "");
            try
            {
                key=in2.nextInt();
            }catch (Exception e) {
                flag=true;
                System.out.println("The key must be digital! Please re-enter...");
            }
            if(flag!=true) {
                messageArray=message.toCharArray();
                for(i=0; i < messageArray.length; i++) {
                    ASCII=Integer.valueOf(messageArray[i]);
                    if(ASCII<65||(ASCII>90&&ASCII<97)||ASCII>122) {
                        flag=true;
                        System.out.println("Please enter English! Please try again...");
                        break;
                    }
                    else
                        switch (choice) {
                            case 1:
                                encode();
                                break;
                            case 2:
                                decode();
                                break;
                            default:
                                System.out.println("Error！");
                                break;
                        }
                }
            }
        }while(flag);
        System.out.print("The ciphertext is:");
        for(int i = 0; i < messageArray.length; i++)
            System.out.print(messageArray[i]);
    }
    public static void encode() {
        if(ASCII>=97&&ASCII<=122)//小写字母
            messageArray[i]=(char) ((ASCII+key-97)%26+97);
        else //大写字母
            messageArray[i]=(char) ((ASCII+key-65)%26+65);
    }
    public static void decode() {
        if(ASCII>=97&&ASCII<=122)//小写字母
            messageArray[i]=(char) ((ASCII-97+26-key)%26+97);
        else //大写字母
            messageArray[i]=(char) ((ASCII-65+26-key)%26+65);
    }
}

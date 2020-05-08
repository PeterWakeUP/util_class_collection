package code62;

public class InviteCodeUtils {

    private static final char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z' };

    private static int len = str.length;

    public static String toStr(long num) {
        if(num<len){
            return ""+str[(int)num];
        }
        int a = (int)(num%len);
        long b = num / len;
        char e = str[a];
        if(b >= len){
            return ""+toStr(b)+e;
        }else{
            return ""+str[(int)b]+e;
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<200000; i++){
            long num = System.nanoTime();
            String enCode =toStr(num);
            System.out.println("短Id是："+enCode);
        }

    }
}

package wkhtmltopdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author ah
 * @date 2019/5/14 16:25
 */
public class HtmlToPdfInterceptor extends Thread {
    private InputStream is;

    public HtmlToPdfInterceptor(InputStream is){
        this.is = is;
    }

    @Override
    public void run(){
        try{
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line.toString()); //输出内容
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


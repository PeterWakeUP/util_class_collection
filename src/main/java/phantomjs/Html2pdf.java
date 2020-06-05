package phantomjs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 转换html为pdf
 *
 * @author xiongfan
 *
 */
public class Html2pdf {

    public static String parseHtml2Pdf(String url) throws IOException {
        System.out.println(url);
        //执行phantomjs 生成js
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("F:\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe F:\\phantomjs-2.1.1-windows\\html2pdfjs\\html2pdf.js "+url);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbf = new StringBuffer();
        String tmp = "";
        while ((tmp = br.readLine()) != null) {
            sbf.append(tmp);
        }
        String resultstr = sbf.toString();
        String[] arr = resultstr.split("\\$");
        String result = "";
        for(String s : arr){
            if(s.endsWith("pdf"))result = s;
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        parseHtml2Pdf("http://www.baidu.com");
    }
}

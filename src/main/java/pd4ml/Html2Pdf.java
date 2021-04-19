package pd4ml;

import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import java.awt.*;
import java.io.*;

public class Html2Pdf {
    public static void main(String[] args) {
        String f = "F:\\_project\\_test_project\\util_class_collection\\src\\main\\resources\\test2.html";
        generatePDF(f);

    }

    public static void generatePDF(String filePath) {
        try {
            File output = new File("D:/pd4ml.pdf");
            java.io.FileOutputStream fos = new java.io.FileOutputStream(output);
            String html = readFile(filePath);
            StringReader strReader = new StringReader(html);
            PD4ML pd4ml = new PD4ML();
            pd4ml.setPageInsets(new Insets(5, 5, 5, 5));
            pd4ml.setHtmlWidth(900);
            pd4ml.setPageSize(PD4Constants.A4);  // A4竖向显示  pd4ml.changePageOrientation(PD4Constants.A4)横向显示
            pd4ml.useTTF("java:fonts", true);
            pd4ml.setDefaultTTFs("SimHei", "YouYuan", "SimSun");  //fonts.jar的配置文件中的值，用于中文乱码
            pd4ml.enableDebugInfo();
            pd4ml.render(strReader, fos);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String readFile(String filePathAndName) {
        String fileContent = "";
        try {
            File f = new File(filePathAndName);
            if(f.isFile()&&f.exists()){
                InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
                BufferedReader reader=new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    //将读取到的字符拼接
                    fileContent += line;
                }
                read.close();
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        return fileContent;
    }
}

package wkhtmltopdf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author ah
 * @date 2019/5/14 16:15
 */
public class HtmlToPdf {
    private static Logger logger = LoggerFactory.getLogger(HtmlToPdf.class);

    // wkhtmltopdf在系统中的路径
    private static String toPdfTool ="F:\\wkhtmltopdf\\bin\\wkhtmltopdf.exe";


    /**
     * html转pdf
     *
     * @param srcPath
     *            html路径，可以是硬盘上的路径，也可以是网络路径
     * @param destPath
     *            pdf保存路径
     * @return 转换成功返回true
     */
    public static boolean convert(String srcPath, String destPath) {
        File file = new File(destPath);
        File parent = file.getParentFile();
        // 如果pdf保存路径不存在，则创建路径
        if (!parent.exists()) {
            parent.mkdirs();
        }
        StringBuilder cmd = new StringBuilder();
        cmd.append(toPdfTool);
        cmd.append(" ");
        cmd.append(srcPath);
        cmd.append(" ");
        cmd.append(destPath);
        logger.info(cmd.toString());
        boolean result = true;
        try {
            Process proc = Runtime.getRuntime().exec(cmd.toString());
            HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
            HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
            error.start();
            output.start();
            proc.waitFor();
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        HtmlToPdf.convert("F:/_project/51_mall/html/index.html", "D:/mall.pdf");
    }
}


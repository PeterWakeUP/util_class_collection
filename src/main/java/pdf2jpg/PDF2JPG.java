package pdf2jpg;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class PDF2JPG {

    public static void main(String args[]){
        pdfbox();
    }


    static void pdfbox()  {
        PDDocument doc = null;
        ByteArrayOutputStream os = null;
        InputStream stream = null;

        OutputStream out = null;

        /*InputStream is = null;
        OutputStream responseOut = null;*/
        try{
            long start = System.currentTimeMillis();
            //pdf路径
            stream = new FileInputStream(new File("D:/zzz/pdf.pdf"));
            // 加载解析PDF文件
            doc = PDDocument.load(stream);
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            PDPageTree pages = doc.getPages();
            int pageCount = pages.getCount();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 200);
                os = new ByteArrayOutputStream();
                ImageIO.write(bim, "jpg", os);
                byte[] datas = os.toByteArray();

                //jpg文件转出路径
                out = new FileOutputStream("D:\\zzz\\" + i + ".jpg");
                out.write(datas);

                //通过response输出流
               /* is = new ByteArrayInputStream(datas);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) > 0) {
                    responseOut.write(buffer, 0, len);
                }
                responseOut.flush();*/

            }
            long end = System.currentTimeMillis();
            long time = (end - start);
            System.out.println("pdf转jpg耗时： " + time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (doc != null) {
                try {
                    doc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            /*if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (responseOut != null) {
                try {
                    responseOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
        }

    }

}

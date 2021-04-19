package pdfaddpage.itext;

/**
 * @description
 *	测试类
 * @author TianwYam
 * @date 2019年12月28日下午9:11:47
 */
public class PdfUtilsTest {


    public static void main(String args[]) {

        String orgPdfPath = "D:/Input.pdf" ;
        String outputPdfPath = "D:/1.pdf" ;
        PdfUtils.addPageNum(orgPdfPath, outputPdfPath);
    }

}



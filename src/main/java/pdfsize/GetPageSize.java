package pdfsize;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.PdfGraphicsUnit;
import com.spire.pdf.graphics.PdfUnitConvertor;

public class GetPageSize {

    public static void main(String[] args) {
        //加载PDF测试文档
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile("D:/zzz/pdf.pdf");

        //获取第一页
        PdfPageBase page = pdf.getPages().get(0);

        //获取第一页页面宽度、高度
        float pointWidth = (float) page.getSize().getWidth();
        float pointHeight = (float) page.getSize().getHeight();

        //将度量单位转换为厘米
        PdfUnitConvertor unitCvtr = new PdfUnitConvertor();
        float centimeterWidth = unitCvtr.convertUnits(pointWidth, PdfGraphicsUnit.Point, PdfGraphicsUnit.Centimeter);
        float centimeterHeight = unitCvtr.convertUnits(pointHeight, PdfGraphicsUnit.Point, PdfGraphicsUnit.Centimeter);
        float pxWidth = unitCvtr.convertUnits(pointWidth, PdfGraphicsUnit.Point, PdfGraphicsUnit.Pixel);
        float pxHeight = unitCvtr.convertUnits(pointHeight, PdfGraphicsUnit.Point, PdfGraphicsUnit.Pixel);

        //输出页面大小
        System.out.println("该PDF的页面大小为(宽: " + pointWidth + "pt, 高: " + pointHeight + "pt).");
        System.out.println("该PDF的页面大小为(宽: " + centimeterWidth + "cm, 高: " + centimeterHeight + "cm.)");
        System.out.println("该PDF的页面大小为(宽: " + pxWidth + "px, 高: " + pxHeight + "px.)");

        double ppi = Math.sqrt((Math.pow(pxWidth, 2) + Math.pow(pxHeight, 2))) / 14.32; //A4纸14.32英寸
        System.out.println("ppi：" + ppi);
    }
}

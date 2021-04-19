package pdfaddpage;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.automaticfields.PdfCompositeField;
import com.spire.pdf.automaticfields.PdfPageCountField;
import com.spire.pdf.automaticfields.PdfPageNumberField;
import com.spire.pdf.graphics.*;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AddPageNumber {

    public static void main(String[] args) throws Exception {
        //加载PDF
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromStream(new FileInputStream(new File("D://Input.pdf")));

        //设置页边距
        PdfUnitConvertor unitCvtr = new PdfUnitConvertor();
        PdfMargins margin = new PdfMargins();
        margin.setTop(unitCvtr.convertUnits(1.54f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setBottom(unitCvtr.convertUnits(1f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setLeft(unitCvtr.convertUnits(3.17f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setRight(unitCvtr.convertUnits(3.17f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));

        //调用AddPageNumber方法添加页码
        AddPageNumber(pdf, margin);

        //保存文档
        pdf.saveToStream(new FileOutputStream(new File("D://1.pdf")));
    }

    /**
     * @param document PDF文档
     * @param margin   PDF文档页面的边距
     */
    public static void AddPageNumber(PdfDocument document, PdfMargins margin) {
        int pageCount = document.getPages().getCount();
        for (int i = 0; i < pageCount; i++) {
            PdfBrush brush = PdfBrushes.getBlack();
            PdfTrueTypeFont font = new PdfTrueTypeFont(new Font("Arial Unicode MS", Font.PLAIN, 14), true);
            PdfStringFormat format = new PdfStringFormat(PdfTextAlignment.Right);
            int x = (int) (document.getPages().get(i).getCanvas().getClientSize().getWidth() - margin.getRight());
            int y = (int) (document.getPages().get(i).getCanvas().getClientSize().getHeight() - margin.getBottom());
            Rectangle bounds = new Rectangle(x, y, 50, 20);
            PdfPageNumberField field = new PdfPageNumberField();
            field.setFont(font);
            field.setBrush(brush);
            field.setStringFormat(format);
            field.setBounds(bounds);
            field.draw(document.getPages().get(i).getCanvas());
        }
    }
}

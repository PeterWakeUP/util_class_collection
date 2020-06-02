package itext;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MakePdf {
    public static void main(String[] args) throws DocumentException, IOException {
        //创建文件
        Rectangle pageSize = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight());
        pageSize.rotate();
        Document document = new Document(pageSize);
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:/test4.pdf"));
        //打开文件
        document.open();

        //中文显示
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(base, 18);

        Paragraph paragraph = new Paragraph("电子档案移交与接收登记表", font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        // 4列的表.
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(10f); // 前间距
        table.setSpacingAfter(10f); // 后间距

        List<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = {3f, 2f, 1.5f, 3f};
        table.setWidths(columnWidths);

        //表格字体
        Font fontTable = new Font(base, 14);
        float paddingLeft = 30f, rowHeight = 30f;

        String[] tag = {"交接工作名称", "内容描述"};
        for (int i = 0; i < tag.length; i++) {
            //行1、2
            PdfPCell cells1[] = new PdfPCell[4];
            PdfPRow row1 = new PdfPRow(cells1);
            cells1[0] = new PdfPCell(new Paragraph(tag[i], fontTable));
            cells1[0].setPaddingLeft(paddingLeft);
            cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
            cells1[0].setMinimumHeight(rowHeight);
            cells1[1] = new PdfPCell(new Paragraph("", fontTable));
            cells1[1].setColspan(3);
            listRow.add(row1);
        }

        String[][] tag3 = {{"移交电子档案数量", "移交数据量"}, {"载体起止顺序号", "移交载体类型、规格"}};
        for (int i = 0; i < tag3.length; i++) {
            //行3、4
            PdfPCell cells3[] = new PdfPCell[4];
            PdfPRow row3 = new PdfPRow(cells3);
            cells3[0] = new PdfPCell(new Paragraph(tag3[i][0], fontTable));
            cells3[0].setPaddingLeft(paddingLeft);
            cells3[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
            cells3[0].setMinimumHeight(rowHeight);
            cells3[1] = new PdfPCell(new Paragraph("", fontTable));
            cells3[2] = new PdfPCell(new Paragraph(tag3[i][1], fontTable));
            cells3[2].setVerticalAlignment(Element.ALIGN_MIDDLE);
            cells3[3] = new PdfPCell(new Paragraph("", fontTable));
            listRow.add(row3);
        }

        //行5
        PdfPCell cells5[] = new PdfPCell[4];
        PdfPRow row5 = new PdfPRow(cells5);
        cells5[0] = new PdfPCell(new Paragraph("检验内容", fontTable));
        cells5[0].setPaddingLeft(paddingLeft);
        cells5[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
        cells5[0].setRowspan(2);
        cells5[0].setMinimumHeight(2 * rowHeight);
        cells5[1] = new PdfPCell(new Paragraph("单位名称", fontTable));
        cells5[1].setMinimumHeight(rowHeight);
        cells5[1].setHorizontalAlignment(Element.ALIGN_CENTER);
        cells5[1].setVerticalAlignment(Element.ALIGN_MIDDLE);
        cells5[1].setColspan(3);
        listRow.add(row5);

        //行6
        PdfPCell cells6[] = new PdfPCell[4];
        PdfPRow row6 = new PdfPRow(cells6);
        cells6[1] = new PdfPCell(new Paragraph("移交单位：" + "罗湖区**单位", fontTable));
        cells6[1].setColspan(2);
        cells6[3] = new PdfPCell(new Paragraph("接收单位：" + "罗湖区档案馆", fontTable));
        listRow.add(row6);

        //剩余行
        String[] tagElse = {"准确性检验", "完整性检验", "可用性检验", "安全性检验", "载体外观检验", "填表人(签名)", "审核人(签名)", "单位(印章)"};
        String dateFormat = "           年          月            日";
        for (int i = 0; i < tagElse.length; i++) {
            PdfPCell cells[] = new PdfPCell[4];
            PdfPRow row = new PdfPRow(cells);
            cells[0] = new PdfPCell(new Paragraph(tagElse[i], fontTable));
            cells[0].setPaddingLeft(paddingLeft);
            cells[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
            cells[0].setMinimumHeight(2 * rowHeight);
            if (i >= 5) {
                cells[1] = new PdfPCell(new Paragraph(dateFormat, fontTable));
                cells[1].setHorizontalAlignment(Element.ALIGN_CENTER);
                cells[1].setVerticalAlignment(Element.ALIGN_MIDDLE);
                cells[1].setColspan(2);
                cells[3] = new PdfPCell(new Paragraph(dateFormat, fontTable));
                cells[3].setHorizontalAlignment(Element.ALIGN_CENTER);
                cells[3].setVerticalAlignment(Element.ALIGN_MIDDLE);
            } else {
                cells[1] = new PdfPCell(new Paragraph("", fontTable));
                cells[1].setColspan(2);
                cells[3] = new PdfPCell(new Paragraph("", fontTable));
            }
            listRow.add(row);
        }

        document.add(table);//把表格添加到文件中
        document.close();//关闭文档
        writer.close();//关闭书写器
    }
}

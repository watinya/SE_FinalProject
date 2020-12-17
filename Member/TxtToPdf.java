package Member;

import java.awt.Font;
import java.io.*;

import java.security.DigestException;

import javax.swing.text.Document;

public class TxtToPdf {
    private static final String FONT = "C:\\Windows\\Fonts\\simhei.ttf";
    public static void text2pdf(String text, String pdf) throws DocumentException, IOException {
        Document document = new Document();
        OutputStream os = new FileOutputStream(new File(pdf));
        PdfWriter.getInstance(document, os);
        document.open();
        //方法一：使用Windows系統字型(TrueType)
        BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(text)), "GBK");
        BufferedReader bufferedReader = new BufferedReader(isr);
String str = "";
        while ((str = bufferedReader.readLine()) != null) {
            document.add(new Paragraph(str, font));
        }
        document.close();
    }
    public static void main(String[] args) throws Exception {
        String PDFTIMEDIR = "F:/pdf/";
        String text = PDFTIMEDIR + "1.txt";
        String pdf = PDFTIMEDIR + "1.txt.pdf";
        text2pdf(text, pdf);
    }
}
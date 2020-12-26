package Member;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class OutputPdf {
	//Windows 內建文字新: 細明體
	private static final String FONT = "c:\\windows\\fonts\\mingliu.ttc,1";
	public OutputPdf(String txtLocation, String pdfLocation) {
	   try {
		   //建立檔案
		   Document document = new Document();
		   //寫入檔案位置
		   PdfWriter.getInstance(document, new FileOutputStream(pdfLocation));
		   document.open();
		   
		   //使用文字
		   BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		   
		   Font firstLine = new Font(baseFont);
		   firstLine.setSize(38);
		   firstLine.setStyle(1);
		   
		   Font titleFont = new Font(baseFont);
		   titleFont.setSize(30);
		   titleFont.setStyle(1);
		   
		   Font subjectTitle = new Font(baseFont);
		   subjectTitle.setSize(27);
		   subjectTitle.setStyle(1);
		   
	       Font font = new Font(baseFont);
	       font.setSize(20);
	       font.setStyle(0);
	       //讀出 txt
	       InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(txtLocation)), "UTF-8");
	       BufferedReader bufferedReader = new BufferedReader(isr);
	       //寫入 pdf
	       String str = "";
	       str = bufferedReader.readLine();
	       document.add(new Paragraph(str, firstLine));
	       str = bufferedReader.readLine();
	       document.add(new Paragraph(str, titleFont));
	       str = bufferedReader.readLine();
	       document.add(new Paragraph(str, font));
	       str = bufferedReader.readLine();
	       document.add(new Paragraph(str, subjectTitle));
	        
	       while ((str = bufferedReader.readLine()) != null) {
	           document.add(new Paragraph(str, font));
	       }
		   bufferedReader.close();
		   document.close();
	   }catch(Exception e) {
		   System.err.println(e);
	   }
   }
}//end class
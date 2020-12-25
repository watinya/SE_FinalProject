package Member;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class OutputPdf {
   public OutputPdf(String content, String saveLocation) {
	   try {
		   //建立檔案
		   Document document = new Document();
		   //寫入檔案位置
		   PdfWriter.getInstance(document, new FileOutputStream(saveLocation));
		   document.open();
		   //段落內容
		   Paragraph para = new Paragraph(content);
		   //加入內容
		   document.add(para);
		   
		   document.close();
	   }catch(Exception e) {
		   System.err.println(e);
	   }
   }
}//end class
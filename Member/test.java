package Member;

import java.io.File;
import java.io.FileInputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JFileChooser;

public class test {
	public static void main(String[] args) {
		// 建立列印作業
		JFileChooser fileChooser = new JFileChooser(); 
		//int state = fileChooser.showOpenDialog(null);
		//if (state == JFileChooser.APPROVE_OPTION) {
			// 獲取選擇的檔案
			File file = new File("data\\studentAccount.txt");
					//fileChooser.getSelectedFile();
			
// 構建列印請求屬性集
			HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
// 設定列印格式,因為未確定型別,所以選擇autosense
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
// 查詢所有的可用的列印服務
			PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
// 定位預設的列印服務
			PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
// 顯示列印對話方塊
			PrintService service = ServiceUI.printDialog(null, 200, 200, printService, defaultService, flavor, pras);
			if (service != null) {
				try {
					DocPrintJob job = service.createPrintJob(); // 建立列印作業
					FileInputStream fis = new FileInputStream(file); // 構造待列印的檔案流
					DocAttributeSet das = new HashDocAttributeSet();
					Doc doc = new SimpleDoc(fis, flavor, das);
					job.print(doc, pras);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
	public static boolean printPdf(String pdfPath){
		try{
		Runtime.getRuntime().exec("cmd.exe /C start acrord32 /P /h " + pdfPath);
		return true;
		}catch(Exception e){
		e.printStackTrace();
		return false;
		}
	}
	
}


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
		// �إߦC�L�@�~
		JFileChooser fileChooser = new JFileChooser(); 
		//int state = fileChooser.showOpenDialog(null);
		//if (state == JFileChooser.APPROVE_OPTION) {
			// �����ܪ��ɮ�
			File file = new File("data\\studentAccount.txt");
					//fileChooser.getSelectedFile();
			
// �c�ئC�L�ШD�ݩʶ�
			HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
// �]�w�C�L�榡,�]�����T�w���O,�ҥH���autosense
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
// �d�ߩҦ����i�Ϊ��C�L�A��
			PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
// �w��w�]���C�L�A��
			PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
// ��ܦC�L��ܤ��
			PrintService service = ServiceUI.printDialog(null, 200, 200, printService, defaultService, flavor, pras);
			if (service != null) {
				try {
					DocPrintJob job = service.createPrintJob(); // �إߦC�L�@�~
					FileInputStream fis = new FileInputStream(file); // �c�y�ݦC�L���ɮ׬y
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


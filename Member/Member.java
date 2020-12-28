package Member;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Member {
	public String id;
	public String password;
	public String name;

	public Member(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}
	//更改密碼method
	public static boolean changePassword(String account, String oldPassword, String newPassword){
		String file;
		//找檔案路徑
		switch (account.length()) {
		case 9:
			file = "data\\account\\studentAccount.txt";
			break;
		case 5:
			file = "data\\account\\teacherAccount.txt";
			break;
		case 4:
			file = "data\\account\\administratorAccount.txt";
			break;
		default:
			file = "";
			return false;
		}
		try {
			FileInputStream fr = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fr, "utf8"));
			String writeText="";
			while (br.ready()) {
				String temp = br.readLine();
				String[] info = temp.split(" ");
	
				if (info[0].equals(account) && info[1].equals(oldPassword)) {
					temp = "";
					info[1] = newPassword;
					for(int i = 0; i < info.length; i++) 
						temp += info[i] + " ";
				}	
				writeText += temp+"\n";
			}
			br.close();
			FileOutputStream writerStream = new FileOutputStream(file);    
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8")); 
			writer.write(writeText);
			writer.close();
		}catch(Exception e) {
			System.err.println(e);
		}
		return true;
	}
	
	//列出課程同學
	public static boolean outputClassmate(String year, String subjectName)  {
		//建檔案路徑
		String dataLocation = "data\\course\\" + year + "\\" + subjectName + ".txt";
		try {
			outputClassmate(dataLocation);
		}catch (Exception e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
	private static boolean outputClassmate(String dataLocation) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		String line,id,name,fileContent = "";
		//跳過第一行
		reader.readLine();
		//抓出每個學生學號與名字
		while((line = reader.readLine()) != null) {
			id = line.split(" ")[0];
			name = line.split(" ")[1];
			fileContent = fileContent.concat(id +"  "+ name +"\n");
		}
		reader.close();
		JOptionPane.showMessageDialog(null, fileContent, "選課名單", JOptionPane.PLAIN_MESSAGE);
		return true;
	}
	
	//列出課程學生成績
	public static void getCourseStudentScore(String year, String subjectName, DefaultTableModel tableM)  {
		//建檔案路徑
		String dataLocation = "data\\course\\" + year + "\\" + subjectName + ".txt";
		try {
			getCourseStudentScore(dataLocation, tableM);
		}catch(FileNotFoundException e) {}
		catch(IOException e) {
			System.err.println(e);
		}	
	}
	private static void getCourseStudentScore(String dataLocation, DefaultTableModel tableM) throws IOException {
		File f = new File(dataLocation);
		Member.cleanTable(tableM);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		String line, id, name, score;
		//跳過第一行
		reader.readLine();
		//抓出每個學生學號與名字
		while((line = reader.readLine()) != null) {
			id = line.split(" ")[0];
			name = line.split(" ")[1];
			score = line.split(" ")[2];
			
			Object[] temp = {id, name, score};
			tableM.addRow(temp);
		}
		reader.close();
	}

	//列出學期課程清單
	public static boolean outputCourseList(String year, DefaultTableModel tableM) {
		String dataLocation = "data\\course\\" + year;
		try {
			createCourseList(dataLocation, tableM, year);
		}catch(IOException e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
	private static void createCourseList(String dataLocation, DefaultTableModel tableM, String year) throws IOException {
		File f = new File(dataLocation);
		File[] fileList = f.listFiles();
		cleanTable(tableM);
		ListBtn btn = new ListBtn();
		//讀取每個課程第一行內容
		for(int i=0; i<fileList.length; i++) {
			InputStreamReader read = new InputStreamReader(new FileInputStream(fileList[i]), "utf-8");
			BufferedReader reader = new BufferedReader(read);
			String[] line = reader.readLine().split(" ");
			
			Object[] temp = {line[0], line[1], line[2], line[3], line[4], btn};
			tableM.addRow(temp);
			reader.close();
		}
		
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String subject = (String) tableM.getValueAt(ListBtnMouseListener.getRow(), 1);
				outputClassmate(year, subject);
			}
		});
	}
	
	//寫入成績
	public static boolean writeScore(String semester, String course, DefaultTableModel tableM){
		String dataLocation = "data\\course\\" + semester + "\\" + course + ".txt";
		try{
			writeScore(dataLocation, tableM);
		}catch(IOException e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
    private static void writeScore(String dataLocation, DefaultTableModel tableM) throws IOException {
		FileInputStream fr = new FileInputStream(dataLocation);
		BufferedReader br = new BufferedReader(new InputStreamReader(fr, "utf8"));
		String title = br.readLine();
		String writeText = title + "\n";
		int count = 0;
		while (br.ready()) {
			String temp = br.readLine();
			String[] info = temp.split(" ");
			String id = (String) tableM.getValueAt(count, 0);
			String score = (String) tableM.getValueAt(count++, 2);

			if (!info[0].equals(id) || !info[2].equals(score)) {
				temp = "";
				info[2] = score;
				for(int i = 0; i < info.length; i++) 
					temp += info[i] + " ";
			}
			writeText += temp + "\n";
		}
		br.close();
		FileOutputStream writerStream = new FileOutputStream(dataLocation);    
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8")); 
		writer.write(writeText);
		writer.close(); 
    }

	// 清空表單method
	public static void cleanTable(DefaultTableModel table) {
		while (table.getRowCount() > 0)
			table.removeRow(0);
	}
}// end class



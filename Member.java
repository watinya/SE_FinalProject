package Member;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.awt.Container;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Member {
	public String id;
	public String password;
	public String name;

	public Member() {
	}

	public Member(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}
	//���K�X
	public void changePassword(char[] newPassword) {
		try {
			//���ɮ׸��|
			switch (id.length()) {
			case 9:
				setNewPassword("data\\studentAccount.txt", new String(newPassword));
				break;
			case 5:
				setNewPassword("data\\teacherAccount.txt", new String(newPassword));
				break;
			case 4:
				setNewPassword("data\\administratorAccount.txt", new String(newPassword));
				break;
			default:
				JOptionPane.showMessageDialog(null, "ID���פ����T");
			}
		} catch (IOException e) {
			System.out.println("changePassword Error");
		}
	}
	private void setNewPassword(String dataLocation, String newPassword) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		String line, user, fileContent = "";
		//�M��b�����
		while ((line = reader.readLine()) != null) {
			user = line.split(" ")[0];
			if (user.equals(id)) {
				fileContent = fileContent.concat(id + " " + newPassword + " " + name +"\n");
			}else {
				fileContent = fileContent.concat(line + "\n");
			}
		}
		reader.close();

		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
		BufferedWriter writer = new BufferedWriter(write);
		//�g�J��s�K�X���
		writer.write(fileContent);
		writer.close();
	}
	//�C�X�ҵ{�P��
	public void outputClassmate(String year, String subjectName)  {
		//���ɮ׸��|
		String dataLocation = "data\\course\\" + year + "\\" + subjectName + ".txt";
		try {
			outputClassmate(dataLocation);
		}catch (IOException e) {
			System.out.println("outputClassmate Error");
		}	
	}
	private void outputClassmate(String dataLocation) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		String line,id,name,fileContent = "";
		//���L�Ĥ@��
		reader.readLine();
		//��X�C�Ӿǥ;Ǹ��P�W�r
		while((line = reader.readLine()) != null) {
			id = line.split(" ")[0];
			name = line.split(" ")[1];
			fileContent = fileContent.concat(id +"  "+ name +"\n");
		}
		reader.close();
		JOptionPane.showMessageDialog(null, fileContent, "��ҦW��", JOptionPane.PLAIN_MESSAGE);
	}
	//�C�X�Ǵ��ҵ{�M��
	public void outputCourseList(String year) {
		String dataLocation = "data\\course\\" + year;
		try {
			createCourseList(dataLocation);
		}catch(IOException e) {
			System.out.println("outputCourseList Error");
		}
	}
	private void createCourseList(String dataLocation) throws IOException {
		File f = new File(dataLocation);
		File[] fileList = f.listFiles();
		//Ū���C�ӽҵ{�Ĥ@�椺�e
		String[][] fileContent = new String[fileList.length][5];
		for(int i=0; i<fileList.length; i++) {
			InputStreamReader read = new InputStreamReader(new FileInputStream(fileList[i]), "utf-8");
			BufferedReader reader = new BufferedReader(read);
			String[] line = reader.readLine().split(" ");
			fileContent[i] = line;
		}
		//�������
		new CourseList(fileContent);
	}
	
}// end class



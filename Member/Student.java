package Member;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Student extends Member{	

	private String inYear ;
	private ArrayList<Subject> subjects = new ArrayList<Subject>();
	//�إ߱b�����ݩ�
	public Student(String id, char[] password, String name) {
		//������e�ϥΪ�:�b��,�K�X,�ϥΪ̦W��
		super(id,new String(password),name);
		//�إ߿�׽ҵ{
		try {
			createCourse("data\\students\\"+ id +"\\��׽ҵ{.txt");	
		}catch(IOException e) {
			System.out.println("�إ߿�׽ҵ{ Error");
		}	
	}
	//�إ߿�׽ҵ{
	private void createCourse(String dataLoaction) throws IOException {
			File f = new File(dataLoaction);
			InputStreamReader reade = new InputStreamReader(new FileInputStream(f),"utf-8");
			BufferedReader reader = new BufferedReader(reade);
			String line;
			while((line = reader.readLine()) != null) {
				subjects.add(new Subject(line.split(" ")[0],line.split(" ")[1]));
			}
			reader.close();
	}
	//�d�ݩҦ����Z
	public void getScore() {
		String content = "";
		for(int i=0; i<subjects.size(); i++) {
			content = content.concat(getScore(subjects.get(i).getYear(),subjects.get(i).getName()) +"\n");
		}
		JOptionPane.showMessageDialog(null, content, "�ҵ{���Z", JOptionPane.PLAIN_MESSAGE);
	}
	//��X�C�즨�Z
	private String getScore(String year, String subject) {
		for(int i=0; i<subjects.size(); i++) {
			if(subjects.get(i).getYear().equals(year) &&
			   subjects.get(i).getName().equals(subject)) {
				for(int j=0; j<subjects.get(i).getSubjectStudents().size(); j++) {
					if(subjects.get(i).getSubjectStudents().get(j).getId().equals(super.id)){
						String content = String.format("%s%5s%3s",subject,"���Z:",subjects.get(i).getSubjectStudents().get(j).getScore());
						return content;
						//JOptionPane.showMessageDialog(null, content, "�ҵ{���Z", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		}
		return null;
	}
}

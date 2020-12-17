package Member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Teacher extends Member{
	//�Ҧ����ɽҵ{
	public ArrayList<Subject> subjects = new ArrayList<Subject>();
	//�إ߱b�����ݩ�
	public Teacher(String id, char[] password, String name){
		//������e�ϥΪ�:�b��,�K�X,�ϥΪ̦W��
		super(id,new String(password),name);
		//�إ߫��ɽҵ{
		try {
		createCourse("data\\teachers\\"+ name +"\\���ɽҵ{.txt");	
		}catch(IOException e) {
			System.out.println("�إ߫��ɽҵ{ Error");
		}	
	}
	//�إ߫��ɽҵ{
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
	//��J�ǥͦ��Z
	public void setScore(String subject,String studentId, String score){
		try {
			File f = new File("data\\course\\"+findSubject(subject).getYear()+"\\"+ subject +".txt");
			InputStreamReader reade = new InputStreamReader(new FileInputStream(f),"utf-8");
			BufferedReader reader = new BufferedReader(reade);
			String line,id,fileContent = "";
			//�Ĥ@�椣�ʧ@
			fileContent = fileContent.concat(reader.readLine() + "\n");
			while((line = reader.readLine()) != null) {
				id = line.split(" ")[0];
				//�g�J���Z
				if(id.equals(studentId)) {
					fileContent = fileContent.concat(id +" "+ line.split(" ")[1] +" "+ score +"\n");
				}else {
					fileContent = fileContent.concat(line +"\n");
				}
			}
			reader.close();
			//�g�J��s���
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
		}catch(IOException e) {
			System.out.println("��J�ǥͦ��Z Error");
		}
	}
	private Subject findSubject(String subject) {
		for(int i=0; i<subjects.size(); i++) {
			if(subject.equals(subjects.get(i).getName())){
				return subjects.get(i);
			}
		}
		return null;
	}
}//end class

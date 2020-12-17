import java.io.*;
import javax.swing.JOptionPane;

public class addUser {
	private String id = "";
	private String password = "";
	private String name = "";

	public addUser(String id, char[] pw, String name) {
		this.id = id;
		this.password = new String(pw);
		this.name = name;
		try {
			actionPerformed();
		} catch (IOException e) {
			System.out.println("addUser Error");
		}
	}

	private void actionPerformed() throws IOException {
		switch (this.id.length()) {
		case 9:
			writerToData("data\\studentAccount.txt");
			break;

		case 5:
			writerToData("data\\teacherAccount.txt");
			break;

		case 4:
			writerToData("data\\administratorAccount.txt");
			break;
		default:
			JOptionPane.showMessageDialog(null, "ID���פ����T");
		}

	}

	private void writerToData(String location) throws IOException {
		//���}�����ϥΪ̤��
		File f = new File(location);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f),"utf-8");    
		BufferedReader reader = new BufferedReader(read);    
		
		boolean check = true;
		String line,user;
		String fileContent = "";
		//�ˬdID�O�_����
		while ((line = reader.readLine()) != null) {
			user = line.split(" ")[0];
			//�ϥΪ̤w�b���W�椺���Xwhile�åB��������ʧ@
			if(this.id.equals(user)) {
				JOptionPane.showMessageDialog(null, "ID�w�Q�ϥ�");
				check = false;
				break;
			}
			fileContent = fileContent.concat(line + "\n");
		}
		reader.close();
		
		if(check) {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"utf-8");
			BufferedWriter writer=new BufferedWriter(write);
			writer.write(fileContent + this.id + " " + this.password + " " + this.name);
			writer.close();
		}
	}

}

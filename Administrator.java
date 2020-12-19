package Member;

import javax.swing.JOptionPane;
import java.io.*;

public class Administrator extends Member {
	//�إ߰��ݩ�
	public Administrator(String id, char[] password, String name) {
		super(id,new String(password),name);
	}
	//���o�ϥΪ̲M��
	public String[][] getUserList() {
		String[][] data = new String[100][100];
		try {
		//�إ��ɮק���m
		File f = new File("data\\account");
		File[] fileList = f.listFiles();
		
		//�̧Ƕ}���ɮק��������
		int count = 0;//�Τ�p��
		for(int i=0; i<fileList.length; i++) {
			InputStreamReader reade = new InputStreamReader(new FileInputStream(fileList[i]),"utf-8");
			BufferedReader reader = new BufferedReader(reade);
			String line;
			//�̧Ǭ����Τ�
			while((line = reader.readLine()) != null) {
				data[count][0] = line.split(" ")[0];
				data[count][1] = line.split(" ")[1];
				data[count][2] = line.split(" ")[2];
				data[count][3] = line.split(" ")[3];
				count ++;
			}
			reader.close();
		}
		}catch(IOException e) {
			System.out.println("���o�ϥΪ̲M�� Error");
		}
		return data;
	}
	//�s�W�ϥΪ�
	public void addUser(String id,String password, String name, String type) {
		//�إ߷s�Τ��Ʀr��
		String newUser = id +" "+ password +" "+ name +" "+ type ;
		try {
			//����ɮ׸��|
			switch (id.length()){
			case 9:
				addUser("data\\account\\studentAccount.txt", newUser, id);
				break;
			case 5:
				addUser("data\\account\\teacherAccount.txt", newUser, id);
				break;
			case 4:
				addUser("data\\account\\administratorAccount.txt", newUser, id);
				break;
			default:
				JOptionPane.showMessageDialog(null, "�b�����פ����T");
			}
		}catch(IOException e) {
			System.out.println("�s�W�ϥΪ� Error");
		}
	}
	//����s�W�Τ�
	private void addUser(String dataLocation, String newUser, String id) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f),"utf-8");    
		BufferedReader reader = new BufferedReader(read); 
		String line,user,fileContent = "";
		boolean check = true;
		//�ˬdID�O�_����
		while ((line = reader.readLine()) != null) {
			user = line.split(" ")[0];
			//�ϥΪ̤w�b���W�椺���Xwhile�åB��������ʧ@
			if(id.equals(user)) {
				JOptionPane.showMessageDialog(null, "�b���w�Q�ϥ�");
				check = false;
				break;
			}
			fileContent = fileContent.concat(line + "\n");
		}
		reader.close();
		//�S���ƥ[�J�s�Τ�
		if(check) {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"utf-8");
			BufferedWriter writer=new BufferedWriter(write);
			writer.write(fileContent + newUser);
			writer.close();
			JOptionPane.showMessageDialog(null, "�s�W���\");
		}
		/*
		RandomAccessFile file = new RandomAccessFile(dataLocation, "rw");
		//�g�J���̫�@��
		file.seek(file.length());
		file.write(newUser.getBytes());
		file.close();
		*/
	}
	
	//�R���ϥΪ�
	public void removeUser(String id) {
		try {
			//����ɮ׸��|
			switch (id.length()){
			case 9:
				removeUser("data\\account\\studentAccount.txt",id);
				break;
			case 5:
				removeUser("data\\account\\teacherAccount.txt",id);
				break;
			case 4:
				removeUser("data\\account\\administratorAccount.txt",id);
				break;
			default:
				JOptionPane.showMessageDialog(null, "�b�����פ����T");
			}
		}catch(IOException e) {
			System.out.println("�s�W�ϥΪ� Error");
		}
	}
	//����R���Τ�
	private void removeUser(String dataLocation, String id) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(reade);
		String line,fileContent = "";
		//��X�Τ�
		while((line = reader.readLine()) != null) {
			if(line.split(" ")[0].equals(id)){
				//���L�n�R�Τᤣ������
			}else {
				//��l����
				fileContent = fileContent.concat(line+"\n");
			}
		}
		reader.close();
		//���s�g�J�s����
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
		BufferedWriter writer = new BufferedWriter(write);
		writer.write(fileContent);
		writer.close();
	}

	//���o�Τ���
	public String getUserInformation(String id) {
		String dataLocation = null;
		try {
			//����ɮ׸��|
			switch (id.length()){
			case 9:
				dataLocation = "data\\account\\studentAccount.txt";
				break;
			case 5:
				dataLocation = "data\\account\\teacherAccount.txt";
				break;
			case 4:
				dataLocation = "data\\account\\administratorAccount.txt";
				break;
			default:
				JOptionPane.showMessageDialog(null, "�b�����פ����T");
			}
			if(dataLocation != null) {
				//��X�Τ���
				File f = new File(dataLocation);
				InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
				BufferedReader reader = new BufferedReader(reade);
				String line;
				while((line = reader.readLine()) != null) {
					if(id.equals(line.split(" ")[0])) {
						return line;
					}
				}
				JOptionPane.showMessageDialog(null, "�b�����s�b");
			}
		}catch(IOException e) {
			System.out.println("���o���ܧ�Τ���   Error");
		}
		return null;
	}
	//�ܧ�Τ���
	public void changeUserInformation(String id, String newId, String newPassword, String newName, String newType) {
		try {
			String newData = newId +" "+ newPassword +" "+ newName +" "+ newType;
			//����ɮ׸��|
			switch (id.length()){
			case 9:
				changeUserInformation("data\\account\\studentAccount.txt", id, newData);
				break;
			case 5:
				changeUserInformation("data\\account\\teacherAccount.txt", id, newData);
				break;
			case 4:
				changeUserInformation("data\\account\\administratorAccount.txt", id, newData);
				break;
			default:
				JOptionPane.showMessageDialog(null, "�b�����פ����T");
			}
		}catch(IOException e) {
			System.out.println("�s�W�ϥΪ� Error");
		}
	}
	//����Τ����ܧ�
	private void changeUserInformation(String dataLocation, String id, String newData) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(reade);
		String line,fileContent = "";
		//��X�n�ܧ󪺥Τ���
		while((line = reader.readLine()) != null) {
			if(line.split(" ")[0].equals(id)){
				//������s���
				fileContent = fileContent.concat(newData+"\n");
			}else {
				//������L�Τ�
				fileContent = fileContent.concat(line+"\n");
			}
		}
		reader.close();
		//���s�g�J��s�᪺���
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
		BufferedWriter writer = new BufferedWriter(write);
		writer.write(fileContent);
		writer.close();
	}
	
	//���o�ǥ͸�T
	public String[][] getStudentsInformation() {
		try {
			String[][] data = null;
			File f = new File("data\\students");
			File[] listFile = f.listFiles();
			//System.out.println(listFile[0]);
			for(int i=0; i<listFile.length; i++) {
				f = new File(listFile[0]+"\\�ǥ͸�T.txt");
				InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
				BufferedReader reader = new BufferedReader(reade);
				
			}
		}catch(IOException e) {
			System.out.println("���o�ǥ͸�T Error");
		}
		
		
		return data;
	}

}//end class

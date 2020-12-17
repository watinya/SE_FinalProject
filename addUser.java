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
			JOptionPane.showMessageDialog(null, "ID長度不正確");
		}

	}

	private void writerToData(String location) throws IOException {
		//打開對應使用者文件
		File f = new File(location);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f),"utf-8");    
		BufferedReader reader = new BufferedReader(read);    
		
		boolean check = true;
		String line,user;
		String fileContent = "";
		//檢查ID是否重複
		while ((line = reader.readLine()) != null) {
			user = line.split(" ")[0];
			//使用者已在文件名單內跳出while並且不做對文件動作
			if(this.id.equals(user)) {
				JOptionPane.showMessageDialog(null, "ID已被使用");
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

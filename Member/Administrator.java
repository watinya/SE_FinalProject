package Member;

import javax.swing.JOptionPane;
import java.io.*;

public class Administrator extends Member {
	//建立基本屬性
	public Administrator(String id, char[] password, String name) {
		super(id,new String(password),name);
	}
	//取得使用者清單
	public String[][] getUserList() {
		String[][] data = new String[100][100];
		try {
		//建立檔案夾位置
		File f = new File("data\\account");
		File[] fileList = f.listFiles();
		
		//依序開啟檔案夾內的資料
		int count = 0;//用戶計數
		for(int i=0; i<fileList.length; i++) {
			InputStreamReader reade = new InputStreamReader(new FileInputStream(fileList[i]),"utf-8");
			BufferedReader reader = new BufferedReader(reade);
			String line;
			//依序紀錄用戶
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
			System.out.println("取得使用者清單 Error");
		}
		return data;
	}
	//新增使用者
	public void addUser(String id,String password, String name, String type) {
		//建立新用戶資料字串
		String newUser = id +" "+ password +" "+ name +" "+ type ;
		try {
			//選擇檔案路徑
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
				JOptionPane.showMessageDialog(null, "帳號長度不正確");
			}
		}catch(IOException e) {
			System.out.println("新增使用者 Error");
		}
	}
	//執行新增用戶
	private void addUser(String dataLocation, String newUser, String id) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f),"utf-8");    
		BufferedReader reader = new BufferedReader(read); 
		String line,user,fileContent = "";
		boolean check = true;
		//檢查ID是否重複
		while ((line = reader.readLine()) != null) {
			user = line.split(" ")[0];
			//使用者已在文件名單內跳出while並且不做對文件動作
			if(id.equals(user)) {
				JOptionPane.showMessageDialog(null, "帳號已被使用");
				check = false;
				break;
			}
			fileContent = fileContent.concat(line + "\n");
		}
		reader.close();
		//沒重複加入新用戶
		if(check) {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"utf-8");
			BufferedWriter writer=new BufferedWriter(write);
			writer.write(fileContent + newUser);
			writer.close();
			JOptionPane.showMessageDialog(null, "新增成功");
		}
		/*
		RandomAccessFile file = new RandomAccessFile(dataLocation, "rw");
		//寫入文件最後一行
		file.seek(file.length());
		file.write(newUser.getBytes());
		file.close();
		*/
	}
	
	//刪除使用者
	public void removeUser(String id) {
		try {
			//選擇檔案路徑
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
				JOptionPane.showMessageDialog(null, "帳號長度不正確");
			}
		}catch(IOException e) {
			System.out.println("新增使用者 Error");
		}
	}
	//執行刪除用戶
	private void removeUser(String dataLocation, String id) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(reade);
		String line,fileContent = "";
		//找出用戶
		while((line = reader.readLine()) != null) {
			if(line.split(" ")[0].equals(id)){
				//跳過要刪用戶不做紀錄
			}else {
				//其餘紀錄
				fileContent = fileContent.concat(line+"\n");
			}
		}
		reader.close();
		//重新寫入新紀錄
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
		BufferedWriter writer = new BufferedWriter(write);
		writer.write(fileContent);
		writer.close();
	}

	//取得用戶資料
	public String getUserInformation(String id) {
		String dataLocation = null;
		try {
			//選擇檔案路徑
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
				JOptionPane.showMessageDialog(null, "帳號長度不正確");
			}
			if(dataLocation != null) {
				//找出用戶資料
				File f = new File(dataLocation);
				InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
				BufferedReader reader = new BufferedReader(reade);
				String line;
				while((line = reader.readLine()) != null) {
					if(id.equals(line.split(" ")[0])) {
						return line;
					}
				}
				JOptionPane.showMessageDialog(null, "帳號不存在");
			}
		}catch(IOException e) {
			System.out.println("取得欲變更用戶資料   Error");
		}
		return null;
	}
	//變更用戶資料
	public void changeUserInformation(String id, String newId, String newPassword, String newName, String newType) {
		try {
			String newData = newId +" "+ newPassword +" "+ newName +" "+ newType;
			//選擇檔案路徑
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
				JOptionPane.showMessageDialog(null, "帳號長度不正確");
			}
		}catch(IOException e) {
			System.out.println("新增使用者 Error");
		}
	}
	//執行用戶資料變更
	private void changeUserInformation(String dataLocation, String id, String newData) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(reade);
		String line,fileContent = "";
		//找出要變更的用戶資料
		while((line = reader.readLine()) != null) {
			if(line.split(" ")[0].equals(id)){
				//紀錄更新資料
				fileContent = fileContent.concat(newData+"\n");
			}else {
				//紀錄其他用戶
				fileContent = fileContent.concat(line+"\n");
			}
		}
		reader.close();
		//重新寫入更新後的資料
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
		BufferedWriter writer = new BufferedWriter(write);
		writer.write(fileContent);
		writer.close();
	}
	
	//取得學生資訊
	public String[][] getStudentsInformation() {
		try {
			String[][] data = null;
			File f = new File("data\\students");
			File[] listFile = f.listFiles();
			//System.out.println(listFile[0]);
			for(int i=0; i<listFile.length; i++) {
				f = new File(listFile[0]+"\\學生資訊.txt");
				InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
				BufferedReader reader = new BufferedReader(reade);
				
			}
		}catch(IOException e) {
			System.out.println("取得學生資訊 Error");
		}
		
		
		return data;
	}

}//end class
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
				if(type.equals("學生")) {
					addUser("data\\account\\studentAccount.txt", newUser, id);
					break;
				}
				JOptionPane.showMessageDialog(null, "帳號長度或類型錯誤");
				break;
			case 5:
				if(type.equals("教授")) {
					addUser("data\\account\\teacherAccount.txt", newUser, id);
					break;
				}
				JOptionPane.showMessageDialog(null, "帳號長度或類型錯誤");
				break;
			case 4:
				if(type.equals("管理員")) {
					addUser("data\\account\\administratorAccount.txt", newUser, id);
					break;
				}
				JOptionPane.showMessageDialog(null, "帳號長度或類型錯誤");
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
	public boolean changeUserInformation(String id, String newId, String newPassword, String newName, String newType) {
		try {
			String newData = newId +" "+ newPassword +" "+ newName +" "+ newType;
			//選擇檔案路徑
			switch (newId.length()){
			case 9:
				if(!newType.equals("學生")) {
					JOptionPane.showMessageDialog(null, "帳號長度或類型錯誤");
					break;
				}
				changeUserInformation("data\\account\\studentAccount.txt", id, newData);
				return true;
			case 5:
				if(!newType.equals("教授")) {
					JOptionPane.showMessageDialog(null, "帳號長度或類型錯誤");
					break;
				}
				changeUserInformation("data\\account\\teacherAccount.txt", id, newData);
				return true;
			case 4:
				if(!newType.equals("管理員")) {
					JOptionPane.showMessageDialog(null, "帳號長度或類型錯誤");
					break;
				}
				changeUserInformation("data\\account\\administratorAccount.txt", id, newData);
				return true;
			default:
				JOptionPane.showMessageDialog(null, "帳號長度不正確");
				return false;
			}
		}catch(IOException e) {
			System.out.println("變更使用者資料 Error");
		}
		return false;
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
		JOptionPane.showMessageDialog(null, "變更完成");
	}
	//--------------------------------------------------------------------------------------
	// 取得全部學生資訊清單
	public String[][] getAllStudentsInformationList() {
		try {
			// 建立資料料夾位置
			File f = new File("data\\students");
			// 建立每個文件位置
			File[] listFile = f.listFiles();
			// 讀取每個學生資訊
			String[][] studentsInformationData = new String[listFile.length][4];
			for (int i = 0; i < listFile.length; i++) {
				f = new File(listFile[i] + "\\學生資訊.txt");
				InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
				BufferedReader reader = new BufferedReader(reade);
				String line = reader.readLine();
				String[] lineData = line.split(" ");
				studentsInformationData[i] = lineData;
			}
			return studentsInformationData;

		} catch (IOException e) {
			System.out.println("取得學生資訊 Error");
		}
		// no work
		return null;
	}

	// 檢查學生存在
	private boolean checkStudentExist(String id) {
		// 檢查 學生是否存在
		boolean check = false;
		File f = new File("data\\students");
		File[] checkData = f.listFiles();
		f = new File("data\\students\\" + id);
		for (int i = 0; i < checkData.length; i++) {
			if (checkData[i].equals(f)) {
				check = true;
			}
		}
		return check;
	}

	// 新增學生資訊
	public void addStudent(String id, String password, String name, String startSchool) {
		try {
			// 學生還未建立過
			if (!checkStudentExist(id)) {
				// 建立 學生檔案夾
				File f = new File("data\\students\\" + id);
				f.mkdir();
				// 建立 選修課程 檔案
				f = new File("data\\students\\" + id + "\\選修課程.txt");
				f.createNewFile();
				// 建立 學生資訊 檔案
				f = new File("data\\students\\" + id + "\\學生資訊.txt");
				f.createNewFile();
				// 訊息寫入
				OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
				BufferedWriter writer = new BufferedWriter(write);
				String data = id + " " + password + " " + name + " " + startSchool;
				writer.write(data);
				writer.close();
				//JOptionPane.showMessageDialog(null, "新增完成");
			} else {
				JOptionPane.showMessageDialog(null, "學生已存在");
			}
		} catch (IOException e) {
			System.out.println("新增學生資訊Error");
		}
	}

	// 刪除學生資訊
	public void removeStudent(String id) {
		// 學生存在
		if (checkStudentExist(id)) {
			// 宣告 要刪除檔案
			File f = new File("data\\students\\" + id);
			deleteFile(f);
			JOptionPane.showMessageDialog(null, "刪除完成");
		} else {
			JOptionPane.showMessageDialog(null, "學生不存在");
		}
	}

	// 執行檔案刪除
	private void deleteFile(File file) {
		// 判斷路徑是否存在
		if (file.exists()) {
			// 測試此抽象路徑名錶示的檔案是否是一個標準檔案
			if (file.isFile()) {
				file.delete();
			} else {// 不是檔案
					// 儲存 路徑下的所有的檔案和資料夾到listFiles陣列中
				File[] listFiles = file.listFiles();
				// 檔案路徑下所有檔案和資料夾的絕對路徑
				for (File file2 : listFiles) {
					// 遞迴作用:由外到內先一層一層刪除裡面的檔案 再從最內層 反過來刪除資料夾
					deleteFile(file2);
				}
			}
			file.delete();
		} else {
			System.out.println("該file路徑不存在！！");
		}
	}

	// 取得學生資訊
	public String getStudentInformation(String id) {
		String data = null;
		if (checkStudentExist(id)) {
			try {
				File f = new File("data\\students\\" + id + "\\學生資訊.txt");
				InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "UTF-8");
				BufferedReader reader = new BufferedReader(reade);
				data = reader.readLine();
				return data;
			} catch (IOException e) {
				System.out.println("取得學生資訊Error");
			}
		} else {
			JOptionPane.showMessageDialog(null, "學生不存在");
		}
		return data;
	}

	// 修改學生資訊
	public void changeStudentInformation(String id, String newId, String newPassword, String newName,
			String newStartSchool) {
		try {
			/*
			 * File oldFile = new File("data\\students\\"+ id); File f = new
			 * File("data\\students\\"+ newId); oldFile.renameTo(f);
			 */
			// 建立新學生資料夾
			addStudent(newId, newPassword, newName, newStartSchool);
			copyFolder("data\\students\\" + id, "data\\students\\" + newId);
			// 建立學生資料
			File f = new File("data\\students\\" + newId + "\\學生資訊.txt");
			// 建立新內容
			InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "UTF-8");
			BufferedReader reader = new BufferedReader(reade);
			String line, fileContent = "";
			// 捨棄舊資訊
			reader.readLine();
			// 加入新資訊
			fileContent = fileContent.concat(newId + " " + newPassword + " " + newName + " " + newStartSchool + "\n");
			// 加入原有選修課
			while ((line = reader.readLine()) != null) {
				fileContent = fileContent.concat(line + "\n");
			}
			// 寫入新資料
			reader.close();
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
			// 刪除舊檔案
			deleteFile(new File("data\\students\\" + id));
			//new File("data\\students\\" + id).delete();
			JOptionPane.showMessageDialog(null, "修改完成");
		} catch (IOException e) {
			System.out.println("修改學生資訊Error");
		}
	}
	//複製整個資料夾內容
	private void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果資料夾不存在則建立新資料夾
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子資料夾
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("複製整個資料夾內容操作出錯");
			e.printStackTrace();
		}
	}
	//-------------------------------------------------------------------------------------
	//檢查課程是否存在
	private boolean checkSubjectExist(String year, String subject) {
		// 檢查 學年是否存在
		File f = new File("data\\course");
		File[] checkYearList = f.listFiles();
		f = new File("data\\course\\" + year);
		for (int i = 0; i < checkYearList.length; i++) {
			//檢查 學年內有沒有開課
			if (checkYearList[i].equals(f)) {
				File[] checkSubjectList = checkYearList[i].listFiles();
				f = new File("data\\course\\" + year +"\\"+subject+".txt");
				for(int j=0; j<checkSubjectList.length; j++) {
					if(checkSubjectList[j].equals(f)) {
						//有課程
						return true;
					}
				}
			}
		}
		//沒有課程
		return false;
	}
	//新增課程資訊
	
	
}// end class
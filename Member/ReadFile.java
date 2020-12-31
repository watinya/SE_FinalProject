package Member;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadFile {
	private String fileContent = "";
	public ReadFile(File file) {
		try {
			InputStreamReader reade = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader reader = new BufferedReader(reade);
			String line;
			while((line = reader.readLine()) != null) {
				fileContent = fileContent.concat(line + "\n");
			}
			reader.close();
		}catch(Exception e) {
			System.err.println(e);
		}
	}
	public String toString() {
		return fileContent;
	}
}

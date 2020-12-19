import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Member.ListBtnEditor;
import Member.ListBtnMouseListener;
import Member.ListBtnRender;
import Member.Member;
import Member.Teacher;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class writeScoreFrame extends JFrame implements ActionListener {
	private JLabel Jlb_semester = new JLabel("學期：");
	private JComboBox<String> jcb_semester;
	private JLabel Jlb_course = new JLabel("課程：");
	private JComboBox<String> jcb_course;
	private JButton Jbtn_confirm = new JButton("確認");
	
	private JTable jt;
	private DefaultTableModel tableM;
	private Teacher user;
	private static String dataLocation;
	
    public writeScoreFrame(Teacher user)
    {
        super("高燕大課程平台 輸入成績");
        Container c = getContentPane();
        c.setLayout(null);
                
        //設定學期標籤大小位置及顯示字型
        Jlb_semester.setLocation(135,13);
        Jlb_semester.setSize(77,40);
        Jlb_semester.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_semester);
        
        //設定學期下拉式選單大小位置及顯示字型
        File fileSemester = new File("data\\course");
    	String[] directoriesSemester = fileSemester.list(new FilenameFilter() {
    	  @Override
    	  public boolean accept(File current, String name) {
    	    return new File(current, name).isDirectory();
    	  }
    	});
    	jcb_semester = new JComboBox(new String[] {"請選擇"});
    	for(int i = 0; i < directoriesSemester.length; i++) {
    		jcb_semester.addItem(directoriesSemester[i]);
    	}
    	jcb_semester.setLocation(209, 19);
    	jcb_semester.setSize(134, 31);
    	jcb_semester.setFont(new Font("微軟正黑體",Font.BOLD,22));
    	jcb_semester.addItemListener(new ItemListener() {
    		@Override
    		public void itemStateChanged(ItemEvent e) {
    			File fileCourse = new File("data\\teachers\\" + user.id + "\\指導課程.txt");
    	        String selectedSemester = (String) jcb_semester.getSelectedItem();
    	        jcb_course.removeAllItems();
    			try {
    				InputStreamReader read = new InputStreamReader(new FileInputStream(fileCourse), "utf-8");
    				BufferedReader reader = new BufferedReader(read);
    				String line, semester, name= "";
    				//抓出每個課程學期與名稱
    				while((line = reader.readLine()) != null) {
    					semester = line.split(" ")[0];
    					name = line.split(" ")[1];
    					if(semester.equals(selectedSemester)) {
    						jcb_course.addItem(name);
    					}
    				}
    				reader.close();
    			} catch (UnsupportedEncodingException | FileNotFoundException ee) {ee.printStackTrace();
    			} catch (IOException ee) {ee.printStackTrace();
    			}
    		}
    	});
    	c.add(jcb_semester);
        
        //設定課程標籤大小位置及顯示字型
        Jlb_course.setLocation(357,13);
        Jlb_course.setSize(77,38);
        Jlb_course.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_course);
        
        //設定課程下拉式選單大小位置及顯示字型
        jcb_course = new JComboBox<String>();
        jcb_course.setBounds(431, 19, 242, 31);
        jcb_course.setFont(new Font("微軟正黑體",Font.BOLD,22));
        jcb_course.addItemListener(new ItemListener() {
    		@Override
    		public void itemStateChanged(ItemEvent e) {
    			outputClassmate((String) jcb_semester.getSelectedItem(), (String) jcb_course.getSelectedItem(), tableM);
    		}
    	});
		getContentPane().add(jcb_course);
		
    	
        
        //表格內容
    	String[] columns = { "學號", "姓名", "成績"};
		tableM = new DefaultTableModel(null, columns) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		if (column < 2)
					return false;
				else
					return true;
			}// 表格不允許被編輯
	    };
	    JTable courseTable = new JTable(tableM) ;
	    //表格標題大小
	    JTableHeader head = courseTable.getTableHeader();
	    head.setFont(new Font("微軟正黑體", Font.BOLD, 26));
	    head.setPreferredSize(new Dimension(head.getWidth(), 32));
	    //表格大小
	    courseTable.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
	    courseTable.setRowHeight(28);
	    
		JScrollPane coursePane = new JScrollPane(courseTable);
		TableColumn column;
		for (int i = 0; i < columns.length; i++) {
			column = courseTable.getColumnModel().getColumn(i);
			column.setPreferredWidth(100);
		}
		coursePane.setLocation(14, 66);
		coursePane.setSize(766, 831);
		c.add(coursePane);
		
		Jbtn_confirm.setBounds(321, 910, 127, 42);
		getContentPane().add(Jbtn_confirm);
		Jbtn_confirm.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		Jbtn_confirm.addActionListener(this);
		
        //設定視窗
        setSize(800, 1000);
        setLocationRelativeTo(null);//視窗置中
        //setLocation(300,200);
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_confirm) {
        	try {
				writeScore(tableM);
				JOptionPane.showMessageDialog(new JFrame(), "成績已變更", "成績輸入", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e1) {e1.printStackTrace();}
        }
    }
    
    //寫入成績
    static void writeScore(DefaultTableModel tableM) throws IOException {
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
    
    //列出課程同學
  	public static void outputClassmate(String year, String subjectName, DefaultTableModel tableM)  {
  		//建檔案路徑
  		dataLocation = "data\\course\\" + year + "\\" + subjectName + ".txt";
  		try {
  			outputClassmate(dataLocation, tableM);
  		}catch (IOException e) {
  			System.out.println("outputClassmate Error");
  		}	
  	}
  	private static void outputClassmate(String dataLocation, DefaultTableModel tableM) throws IOException {
  		File f = new File(dataLocation);
  		cleanTable(tableM);
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
  	// 清空表單method
 	static void cleanTable(DefaultTableModel table) {
 		while (table.getRowCount() > 0)
 			table.removeRow(0);
 	}
}

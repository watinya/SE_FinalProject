package Member;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class CourseList {
	JTable jt;

	public CourseList(String[][] fileContent) {
		JFrame frame = new JFrame("課表");
		Container c = frame.getContentPane();
		
		//建立表格
		String[] columns = { "開課代號", "課程名稱", "學分數", "科目型態", "授課教師"};
		jt = new JTable(fileContent, columns);
		jt.setPreferredScrollableViewportSize(new Dimension(400,300));
	    TableColumn column=jt.getColumnModel().getColumn(0);
	    //表格寬度
	    column.setPreferredWidth(80);
	    column=jt.getColumnModel().getColumn(1);
	    column.setPreferredWidth(200);
	    column=jt.getColumnModel().getColumn(2);
	    column.setPreferredWidth(65);
	    column=jt.getColumnModel().getColumn(3);
	    column.setPreferredWidth(80);
	    column=jt.getColumnModel().getColumn(4);
	    column.setPreferredWidth(80);
		c.add(new JScrollPane(jt), BorderLayout.CENTER);


		frame.setSize(1000, 800);
		frame.setLocationRelativeTo(null);// 視窗置中
		frame.setResizable(false);// 視窗放大按鈕無效
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

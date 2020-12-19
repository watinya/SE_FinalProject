

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class CourseListFrame {
	JTable jt;

	public CourseListFrame(String[][] fileContent) {
		JFrame frame = new JFrame("�Ҫ�");
		Container c = frame.getContentPane();
		
		//�إߪ��
		String[] columns = { "�}�ҥN��", "�ҵ{�W��", "�Ǥ���", "��ث��A", "�½ұЮv"};
		jt = new JTable(fileContent, columns);
		jt.setPreferredScrollableViewportSize(new Dimension(400,300));
	    //���e��
	    TableColumn column=jt.getColumnModel().getColumn(0);
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


		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);// �����m��
		frame.setResizable(false);// ������j���s�L��
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Member.ListBtnEditor;
import Member.ListBtnRender;
import Member.Member;

import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class searchCourseFrame extends JFrame implements ActionListener {
	private JLabel Jlb_semester = new JLabel("學期：");
	JComboBox jcb_time;
	private JTable jt;
	private DefaultTableModel tableM;
	
    public searchCourseFrame()
    {
        super("高燕大課程平台 開課查詢");
        Container c = getContentPane();
        c.setLayout(null);
                
        //設定Jlb_semester大小位置及顯示字型
        Jlb_semester.setLocation(469,13);
        Jlb_semester.setSize(94,46);
        Jlb_semester.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_semester);
        
        //設定下拉式選單大小位置及顯示字型
        File file = new File("data\\course");
    	String[] directories = file.list(new FilenameFilter() {
    	  @Override
    	  public boolean accept(File current, String name) {
    	    return new File(current, name).isDirectory();
    	  }
    	});
    	jcb_time = new JComboBox(directories);
        jcb_time.setLocation(560, 22);
        jcb_time.setSize(130, 36);
        jcb_time.setFont(new Font("微軟正黑體",Font.BOLD,22));
        jcb_time.addActionListener(this);
        c.add(jcb_time);
        
        //表格內容
    	String[] columns = { "開課代號", "課程名稱", "學分數", "科目型態", "授課教師", "選修學生清單"};
		tableM = new DefaultTableModel(null, columns) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		if (column < 6)
					return false;
				else
					return true;
			}// 表格不允許被編輯
	    };
	    JTable courseTable = new JTable(tableM) ;
	    courseTable.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
	    
	    courseTable.getColumnModel().getColumn(5).setCellRenderer(new ListBtnRender());
		courseTable.getColumnModel().getColumn(5).setCellEditor(new ListBtnEditor());

		JScrollPane coursePane = new JScrollPane(courseTable);
		TableColumn column;
		for (int i = 0; i < columns.length; i++) {
			column = courseTable.getColumnModel().getColumn(i);
			if (i == 1) {
				column.setPreferredWidth(300); //second column is bigger
			} else {
				column.setPreferredWidth(20);
			}
		}
		coursePane.setLocation(14, 72);
		coursePane.setSize(1166, 680);
		c.add(coursePane);
		
        //設定視窗
        setSize(1200, 800);
        setLocationRelativeTo(null);//視窗置中
        //setLocation(300,200);
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jcb_time) {
			String selectedSemester = (String) jcb_time.getSelectedItem();
			Member.outputCourseList(selectedSemester, tableM);
        }
    }
    
}

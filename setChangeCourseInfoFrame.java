import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Member.Administrator;

public class setChangeCourseInfoFrame extends JFrame implements ActionListener {
	private JLabel Jlb_title = new JLabel("請輸入要修改的課程資訊");
	private JLabel Jlb_time = new JLabel("學期：");
    private JLabel Jlb_subject = new JLabel("名稱：");
    private JTextField jTime = new JTextField(5);
    private JTextField jSubject = new JTextField(20);
    private JButton Jbtn_confirm = new JButton("確認");
	public Administrator user;;

	public setChangeCourseInfoFrame(Administrator user) {
		super("修改課程資訊");
        Container c = getContentPane();
        this.user = user;
        c.setLayout(null);
        
        //設定標題標籤大小位置及顯示字型
        Jlb_title.setLocation(51,13);
        Jlb_title.setSize(335,47);
        Jlb_title.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_title);
        
        //設定學期標籤大小位置及顯示字型
        Jlb_time.setLocation(73,62);
        Jlb_time.setSize(100,40);
        Jlb_time.setFont(new Font("微軟正黑體", Font.BOLD, 28));
        c.add(Jlb_time);
        //設定學期輸入框大小位置及顯示字型
        jTime.setLocation(161,66);
        jTime.setSize(209,40);
        jTime.setFont(new Font("微軟正黑體", Font.BOLD,20));
        c.add(jTime);
        
        //設定課程名稱標籤大小位置及顯示字型
        Jlb_subject.setLocation(73,113);
        Jlb_subject.setSize(100,40);
        Jlb_subject.setFont(new Font("微軟正黑體", Font.BOLD, 28));
        c.add(Jlb_subject);
        //設定課程名稱輸入框大小位置及顯示字型
        jSubject.setLocation(161,118);
        jSubject.setSize(209,38);
        jSubject.setFont(new Font("微軟正黑體", Font.BOLD,20));
        c.add(jSubject);
        
        //設定新增按鈕大小位置及顯示字型
        Jbtn_confirm.setLocation(171,170);
        Jbtn_confirm.setSize(100,32);
        Jbtn_confirm.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
       
        //設定視窗
        setSize(450, 250);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Jbtn_confirm) {
			String year = jTime.getText();
			String subject = jSubject.getText();
			if(year.equals("") || subject.equals("")) {
				JOptionPane.showMessageDialog(null, "請輸入課程資訊");
			}
			else {
				String courseInfo = user.getSubjectInformation(year, subject);
				new changeCourseInfoFrame(user, courseInfo);
				this.dispose();
			}
		}
	}
}
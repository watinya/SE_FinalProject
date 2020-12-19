import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableColumn;

import Member.Administrator;

public class UserListFrame implements ActionListener{
	Administrator user;
	JFrame f;
	JTable jt;
	
	public UserListFrame(Administrator user) {
		this.user = user;
		//設定框架
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		f = new JFrame("用戶清單");
		f.setSize(500,400);
		f.setLocationRelativeTo(null);
		Container cp = f.getContentPane();
		
		//建立表格內容
		String[] columns = {"帳號","密碼","名稱","使用者類型"};
		jt = new JTable(user.getUserList(),columns);
		//表格寬度
		TableColumn column=jt.getColumnModel().getColumn(0);
	    column.setPreferredWidth(80);
	    column=jt.getColumnModel().getColumn(1);
	    column.setPreferredWidth(80);
	    column=jt.getColumnModel().getColumn(2);
	    column.setPreferredWidth(50);
	    column=jt.getColumnModel().getColumn(3);
	    column.setPreferredWidth(50);
		cp.add(new JScrollPane(jt), BorderLayout.CENTER);
		
		//增加按鈕
		JPanel panel = new JPanel(new GridLayout(4,1));
		JButton btn = new JButton("重新整理");
		btn.addActionListener(this);
		panel.add(btn);
		btn = new JButton("新增帳戶");
		btn.addActionListener(this);
		panel.add(btn);
		btn = new JButton("修改帳戶");
		btn.addActionListener(this);
		panel.add(btn);
		btn = new JButton("刪除帳戶");
		btn.addActionListener(this);
		panel.add(btn);
		cp.add(panel,BorderLayout.SOUTH);
		f.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("重新整理")) {
			f.dispose();
			new UserListFrame(user);
		}
		if(cmd.equals("新增帳戶")) {
			new AddUserFrame(user);
		}
		if(cmd.equals("修改帳戶")) {
			new SetChangeUserIdFrame(user);
		}
		if(cmd.equals("刪除帳戶")) {
			new SetRemoveUserIdFrame(user);
		}
	}
}//end class
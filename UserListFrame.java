

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
		//�]�w�ج[
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		f = new JFrame("�Τ�M��");
		f.setSize(500,400);
		f.setLocationRelativeTo(null);
		Container cp = f.getContentPane();
		
		//�إߪ�椺�e
		String[] columns = {"�b��","�K�X","�W��","�ϥΪ�����"};
		jt = new JTable(user.getUserList(),columns);
		//���e��
		TableColumn column=jt.getColumnModel().getColumn(0);
	    column.setPreferredWidth(80);
	    column=jt.getColumnModel().getColumn(1);
	    column.setPreferredWidth(80);
	    column=jt.getColumnModel().getColumn(2);
	    column.setPreferredWidth(50);
	    column=jt.getColumnModel().getColumn(3);
	    column.setPreferredWidth(50);
		cp.add(new JScrollPane(jt), BorderLayout.CENTER);
		
		//�W�[���s
		JPanel panel = new JPanel(new GridLayout(4,1));
		JButton btn = new JButton("���s��z");
		btn.addActionListener(this);
		panel.add(btn);
		btn = new JButton("�s�W�b��");
		btn.addActionListener(this);
		panel.add(btn);
		btn = new JButton("�ק�b��");
		btn.addActionListener(this);
		panel.add(btn);
		btn = new JButton("�R���b��");
		btn.addActionListener(this);
		panel.add(btn);
		cp.add(panel,BorderLayout.SOUTH);
		f.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("���s��z")) {
			f.dispose();
			new UserListFrame(user);
		}
		if(cmd.equals("�s�W�b��")) {
			new AddUserFrame(user);
		}
		if(cmd.equals("�ק�b��")) {
			new SetChangeUserIdFrame(user);
		}
		if(cmd.equals("�R���b��")) {
			new SetRemoveUserIdFrame(user);
		}
	}
}//end class




import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Member.Administrator;

public class SetChangeUserIdFrame implements ActionListener {
	Administrator user;
	JFrame f;
	//�ŧi��J��
    JTextField userId = new JTextField(20);

	public SetChangeUserIdFrame(Administrator user) {
		this.user = user;
		//�]�w�ج[
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//JDialog.setDefaultLookAndFeelDecorated(true);
		f = new JFrame("��J�ק�b��");
		f.setSize(450, 250);
		f.setLocationRelativeTo(null);//�����m��
		f.setResizable(false);//������j���s�L��
		Container cp = f.getContentPane();
		cp.setLayout(null);
		
		//�إ߼���
		//���� �ק�b��
		JLabel lb = new JLabel("�ק�b��: ");
		lb.setLocation(25,40);
		lb.setSize(140,47);
		lb.setFont(new Font("�L�n������", Font.BOLD, 30));
		cp.add(lb);
        
        //�إ߿�J��
        //��J�� userId
        userId.setLocation(175,50);
        userId.setSize(220,35);
        userId.setFont(new Font("�L�n������", Font.BOLD, 16));
		cp.add(userId);
		
		//�إ߫��s
		JButton check = new JButton("�T�{");
		JButton reset = new JButton("���m");
		//���s �T�{
		check.setLocation(60,120);
		check.setSize(137,58);
		check.setFont(new Font("�L�n������",Font.BOLD,22));
		check.addActionListener(this);
        cp.add(check);
        //���s ���m
        reset.setLocation(230,120);
        reset.setSize(137,58);
        reset.setFont(new Font("�L�n������",Font.BOLD,22));
        reset.addActionListener(this);
        cp.add(reset);
		
		//�Ұ�
		f.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("�T�{")) {
			String userInformation = user.getUserInformation(userId.getText());
			if(userInformation == null) {
				new SetChangeUserIdFrame(user);
				f.dispose();
			}else {
				new ChangeUserInformationFrame(user, userInformation);
				f.dispose();
			}
		}
		if(cmd.equals("���m")) {
			new SetChangeUserIdFrame(user);
			f.dispose();
		}
		
	}
}
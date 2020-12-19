


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Member.Administrator;

public class SetRemoveUserIdFrame implements ActionListener {
	Administrator user;
	JFrame f;
	//�ŧi��J��
    JTextField userId = new JTextField(20);

	public SetRemoveUserIdFrame(Administrator user) {
		this.user = user;
		//�]�w�ج[
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//JDialog.setDefaultLookAndFeelDecorated(true);
		f = new JFrame("��J�R���b��");
		f.setSize(450, 250);
		f.setLocationRelativeTo(null);//�����m��
		f.setResizable(false);//������j���s�L��
		Container cp = f.getContentPane();
		cp.setLayout(null);
		
		//�إ߼���
		//���� �R���b��
		JLabel lb = new JLabel("�R���b��: ");
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
		JButton remove = new JButton("�R��");
		JButton reset = new JButton("���m");
		//���s �R��
		remove.setLocation(60,120);
		remove.setSize(137,58);
		remove.setFont(new Font("�L�n������",Font.BOLD,22));
		remove.addActionListener(this);
        cp.add(remove);
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
		if(cmd.equals("�R��")) {
			String userInformation = user.getUserInformation(userId.getText());
			if(userInformation == null) {
				new SetRemoveUserIdFrame(user);
				f.dispose();
			}else {
				user.removeUser(userInformation.split(" ")[0]);
				JOptionPane.showMessageDialog(null, "�����R��");
				f.dispose();
			}
		}
		if(cmd.equals("���m")) {
			new SetRemoveUserIdFrame(user);
			f.dispose();
		}
		
	}
}


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Member.Administrator;

public class AddUserFrame implements ActionListener {
	Administrator user;
	JFrame f;
	//�ŧi��J��
    JTextField newId = new JTextField(20);
    JTextField newPw = new JTextField(20);
    JTextField newName = new JTextField(20);
    JTextField newType = new JTextField(20);
    
	public AddUserFrame(Administrator user) {
		this.user = user;
		//�]�w�ج[
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//JDialog.setDefaultLookAndFeelDecorated(true);
		f = new JFrame("�s�W�Τ�");
		f.setSize(1000, 600);
		f.setLocationRelativeTo(null);//�����m��
		f.setResizable(false);//������j���s�L��
		Container cp = f.getContentPane();
		cp.setLayout(null);
		
		//�إ߼���
		//���� �b��
		JLabel lb = new JLabel("�b��: ");
		lb.setLocation(343,105);
		lb.setSize(120,47);
		lb.setFont(new Font("�L�n������", Font.BOLD, 30));
		cp.add(lb);
		//���� �K�X
		lb = new JLabel("�K�X: ");
		lb.setLocation(343,165);
        lb.setSize(120,47);
        lb.setFont(new Font("�L�n������", Font.BOLD, 30));
        cp.add(lb);
        //���� �ϥΪ̦W��
        lb = new JLabel("�ϥΪ̦W��: ");
        lb.setLocation(255,225);
        lb.setSize(185,47);
        lb.setFont(new Font("�L�n������", Font.BOLD,30));
        cp.add(lb);
        //���� ����
        lb = new JLabel("����: ");
		lb.setLocation(343,285);
        lb.setSize(120,47);
        lb.setFont(new Font("�L�n������", Font.BOLD, 30));
        cp.add(lb);
        
        //�إ߿�J��
        //��J�� newId
        newId.setLocation(436,114);
		newId.setSize(220,35);
		newId.setFont(new Font("�L�n������", Font.BOLD, 16));
		cp.add(newId);
		//��J�� newPw 
		newPw.setLocation(436,174);
		newPw.setSize(220,35);
		newPw.setFont(new Font("�L�n������", Font.BOLD, 16));
		cp.add(newPw);
		//��J�� newName
		newName.setLocation(436,234);
		newName.setSize(220,35);
		newName.setFont(new Font("�L�n������", Font.BOLD, 16));
		cp.add(newName);
		//��J�� newType
		newType.setLocation(436,294);
		newType.setSize(220,35);
		newType.setFont(new Font("�L�n������", Font.BOLD, 16));
		cp.add(newType);
		
		//�إ߫��s
		JButton add = new JButton("�s�W");
		JButton reset = new JButton("���m");
		//���s �s�W
		add.setLocation(354,350);
		add.setSize(137,58);
		add.setFont(new Font("�L�n������",Font.BOLD,22));
		add.addActionListener(this);
        cp.add(add);
        //���s ���m
        reset.setLocation(505,350);
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
		if(cmd.equals("�s�W")) {
			user.addUser(newId.getText(), newPw.getText(), newName.getText(), newType.getText());
			new AddUserFrame(user);
			f.dispose();
		}
		if(cmd.equals("���m")) {
			new AddUserFrame(user);
			f.dispose();
		}
		
	}
}



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Member.Administrator;

public class ChangeUserInformationFrame implements ActionListener {
	Administrator user;
	String userInformation;
	JFrame f;
	//�ŧi��J��
    JTextField newId = new JTextField(20);
    JTextField newPw = new JTextField(20);
    JTextField newName = new JTextField(20);
    JTextField newType = new JTextField(20);
    
	public ChangeUserInformationFrame(Administrator user, String userInformation) {
		this.user = user;
		this.userInformation = userInformation;
		//�]�w�ج[
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//JDialog.setDefaultLookAndFeelDecorated(true);
		f = new JFrame("�ק�Τ��T");
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
		newId.setText(userInformation.split(" ")[0]);
		cp.add(newId);
		//��J�� newPw 
		newPw.setLocation(436,174);
		newPw.setSize(220,35);
		newPw.setFont(new Font("�L�n������", Font.BOLD, 16));
		newPw.setText(userInformation.split(" ")[1]);
		cp.add(newPw);
		//��J�� newName
		newName.setLocation(436,234);
		newName.setSize(220,35);
		newName.setFont(new Font("�L�n������", Font.BOLD, 16));
		newName.setText(userInformation.split(" ")[2]);
		cp.add(newName);
		//��J�� newType
		newType.setLocation(436,294);
		newType.setSize(220,35);
		newType.setFont(new Font("�L�n������", Font.BOLD, 16));
		newType.setText(userInformation.split(" ")[3]);
		cp.add(newType);
		
		//�إ߫��s
		JButton change = new JButton("�ܧ�");
		JButton reset = new JButton("���m");
		//���s �ܧ�
		change.setLocation(354,350);
		change.setSize(137,58);
		change.setFont(new Font("�L�n������",Font.BOLD,22));
		change.addActionListener(this);
        cp.add(change);
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
		if(cmd.equals("�ܧ�")) {
			//user.addUser(newId.getText(), newPw.getText(), newName.getText(), newType.getText());
			user.changeUserInformation(userInformation.split(" ")[0], newId.getText(), newPw.getText(), newName.getText(), newType.getText());
			JOptionPane.showMessageDialog(null, "�ܧ󧹦�");
			f.dispose();
		}
		if(cmd.equals("���m")) {
			new ChangeUserInformationFrame(user, userInformation);
			f.dispose();
		}
		
	}
}
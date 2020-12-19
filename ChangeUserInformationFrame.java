

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Member.Administrator;

public class ChangeUserInformationFrame implements ActionListener {
	Administrator user;
	String userInformation;
	JFrame f;
	//宣告輸入欄
    JTextField newId = new JTextField(20);
    JTextField newPw = new JTextField(20);
    JTextField newName = new JTextField(20);
    JTextField newType = new JTextField(20);
    
	public ChangeUserInformationFrame(Administrator user, String userInformation) {
		this.user = user;
		this.userInformation = userInformation;
		//設定框架
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//JDialog.setDefaultLookAndFeelDecorated(true);
		f = new JFrame("修改用戶資訊");
		f.setSize(1000, 600);
		f.setLocationRelativeTo(null);//視窗置中
		f.setResizable(false);//視窗放大按鈕無效
		Container cp = f.getContentPane();
		cp.setLayout(null);
		
		//建立標籤
		//標籤 帳號
		JLabel lb = new JLabel("帳號: ");
		lb.setLocation(343,105);
		lb.setSize(120,47);
		lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		cp.add(lb);
		//標籤 密碼
		lb = new JLabel("密碼: ");
		lb.setLocation(343,165);
        lb.setSize(120,47);
        lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        cp.add(lb);
        //標籤 使用者名稱
        lb = new JLabel("使用者名稱: ");
        lb.setLocation(255,225);
        lb.setSize(185,47);
        lb.setFont(new Font("微軟正黑體", Font.BOLD,30));
        cp.add(lb);
        //標籤 類型
        lb = new JLabel("類型: ");
		lb.setLocation(343,285);
        lb.setSize(120,47);
        lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        cp.add(lb);
        
        //建立輸入欄
        //輸入欄 newId
        newId.setLocation(436,114);
		newId.setSize(220,35);
		newId.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		newId.setText(userInformation.split(" ")[0]);
		cp.add(newId);
		//輸入欄 newPw 
		newPw.setLocation(436,174);
		newPw.setSize(220,35);
		newPw.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		newPw.setText(userInformation.split(" ")[1]);
		cp.add(newPw);
		//輸入欄 newName
		newName.setLocation(436,234);
		newName.setSize(220,35);
		newName.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		newName.setText(userInformation.split(" ")[2]);
		cp.add(newName);
		//輸入欄 newType
		newType.setLocation(436,294);
		newType.setSize(220,35);
		newType.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		newType.setText(userInformation.split(" ")[3]);
		cp.add(newType);
		
		//建立按鈕
		JButton change = new JButton("變更");
		JButton reset = new JButton("重置");
		//按鈕 變更
		change.setLocation(354,350);
		change.setSize(137,58);
		change.setFont(new Font("微軟正黑體",Font.BOLD,22));
		change.addActionListener(this);
        cp.add(change);
        //按鈕 重置
        reset.setLocation(505,350);
        reset.setSize(137,58);
        reset.setFont(new Font("微軟正黑體",Font.BOLD,22));
        reset.addActionListener(this);
        cp.add(reset);
		
		//啟動
		f.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("變更")) {
			//user.addUser(newId.getText(), newPw.getText(), newName.getText(), newType.getText());
			user.changeUserInformation(userInformation.split(" ")[0], newId.getText(), newPw.getText(), newName.getText(), newType.getText());
			JOptionPane.showMessageDialog(null, "變更完成");
			f.dispose();
		}
		if(cmd.equals("重置")) {
			new ChangeUserInformationFrame(user, userInformation);
			f.dispose();
		}
		
	}
}
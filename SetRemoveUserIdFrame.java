


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Member.Administrator;

public class SetRemoveUserIdFrame implements ActionListener {
	Administrator user;
	JFrame f;
	//宣告輸入欄
    JTextField userId = new JTextField(20);

	public SetRemoveUserIdFrame(Administrator user) {
		this.user = user;
		//設定框架
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//JDialog.setDefaultLookAndFeelDecorated(true);
		f = new JFrame("輸入刪除帳號");
		f.setSize(450, 250);
		f.setLocationRelativeTo(null);//視窗置中
		f.setResizable(false);//視窗放大按鈕無效
		Container cp = f.getContentPane();
		cp.setLayout(null);
		
		//建立標籤
		//標籤 刪除帳號
		JLabel lb = new JLabel("刪除帳號: ");
		lb.setLocation(25,40);
		lb.setSize(140,47);
		lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		cp.add(lb);
        
        //建立輸入欄
        //輸入欄 userId
        userId.setLocation(175,50);
        userId.setSize(220,35);
        userId.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		cp.add(userId);
		
		//建立按鈕
		JButton remove = new JButton("刪除");
		JButton reset = new JButton("重置");
		//按鈕 刪除
		remove.setLocation(60,120);
		remove.setSize(137,58);
		remove.setFont(new Font("微軟正黑體",Font.BOLD,22));
		remove.addActionListener(this);
        cp.add(remove);
        //按鈕 重置
        reset.setLocation(230,120);
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
		if(cmd.equals("刪除")) {
			String userInformation = user.getUserInformation(userId.getText());
			if(userInformation == null) {
				new SetRemoveUserIdFrame(user);
				f.dispose();
			}else {
				user.removeUser(userInformation.split(" ")[0]);
				JOptionPane.showMessageDialog(null, "完成刪除");
				f.dispose();
			}
		}
		if(cmd.equals("重置")) {
			new SetRemoveUserIdFrame(user);
			f.dispose();
		}
		
	}
}
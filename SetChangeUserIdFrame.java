import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Member.Administrator;

public class SetChangeUserIdFrame implements ActionListener {
	Administrator user;
	JFrame f;
	//宣告輸入欄
    JTextField userId = new JTextField(20);

	public SetChangeUserIdFrame(Administrator user) {
		this.user = user;
		//設定框架
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//JDialog.setDefaultLookAndFeelDecorated(true);
		f = new JFrame("輸入修改帳號");
		f.setSize(450, 250);
		f.setLocationRelativeTo(null);//視窗置中
		f.setResizable(false);//視窗放大按鈕無效
		Container cp = f.getContentPane();
		cp.setLayout(null);
		
		//建立標籤
		//標籤 修改帳號
		JLabel lb = new JLabel("修改帳號: ");
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
		JButton check = new JButton("確認");
		JButton reset = new JButton("重置");
		//按鈕 確認
		check.setLocation(60,120);
		check.setSize(137,58);
		check.setFont(new Font("微軟正黑體",Font.BOLD,22));
		check.addActionListener(this);
        cp.add(check);
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
		if(cmd.equals("確認")) {
			String userInformation = user.getUserInformation(userId.getText());
			if(userInformation == null) {
				new SetChangeUserIdFrame(user);
				f.dispose();
			}else {
				new ChangeUserInformationFrame(user, userInformation);
				f.dispose();
			}
		}
		if(cmd.equals("重置")) {
			new SetChangeUserIdFrame(user);
			f.dispose();
		}
		
	}
}
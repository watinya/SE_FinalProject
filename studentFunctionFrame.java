import java.awt.*;
import javax.swing.*;

import Member.Student;

import java.awt.event.*;

public class studentFunctionFrame extends JFrame implements ActionListener {
	private JButton Jbtn_Course = new JButton("�}�Ҭd��");
    private JButton Jbtn_Score = new JButton("���Z�d��");
    private JButton Jbtn_printTranscript = new JButton("�C�L���Z��");
    private JButton Jbtn_ChangePW = new JButton("�ק�K�X");
    private Student user;
    public studentFunctionFrame(Student user)
    {
        super("���P�j�ҵ{���x �ǥͥ\��");
        this.user = user;
        Container c = getContentPane();
        c.setLayout(null);
                 
        //�]�w�}�Ҭd��(�M��B���e�B�ǥͲM��)���s�j�p��m����ܦr��
        Jbtn_Course.setLocation(64,148);
        Jbtn_Course.setSize(218,127);
        Jbtn_Course.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_Course.addActionListener(this);
        c.add(Jbtn_Course);
       
        //�]�w���Z�d�߫��s�j�p��m����ܦr��
        Jbtn_Score.setLocation(346,148);
        Jbtn_Score.setSize(218,127);
        Jbtn_Score.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_Score.addActionListener(this);
        c.add(Jbtn_Score);
        
        //�]�w�C�L���Z����s�j�p��m����ܦr��
        Jbtn_printTranscript.setLocation(628,148);
        Jbtn_printTranscript.setSize(218,127);
        Jbtn_printTranscript.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_printTranscript.addActionListener(this);
        c.add(Jbtn_printTranscript);
        
        //�]�w�ק�K�X���s�j�p��m����ܦr��
        Jbtn_ChangePW.setLocation(910,148);
        Jbtn_ChangePW.setSize(218,127);
        Jbtn_ChangePW.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_ChangePW.addActionListener(this);
        c.add(Jbtn_ChangePW);
       
        //�]�w����
        setSize(1200, 800);
        setLocationRelativeTo(null);//�����m��
        //setLocation(300,200);
        setResizable(false);//������j���s�L��
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_ChangePW) {
        	
			new changePasswordFrame();
        }
        else if(e.getSource() == Jbtn_Score){
        	user.getScore();
        }
    }
}

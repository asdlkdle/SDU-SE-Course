package Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/*
ÿ���Ծ�25����Ŀ
15����ѡ��*ÿ��2��=30��
5����ѡ��ÿ��4��=20�֣�ѡ��ȫ��2�֣�
5��������*ÿ��10��=50��
 */
public class MakePapers extends JFrame {
    public static void main(String[] args) {
        new MakePapers();
    }
    public MakePapers(){
        setTitle("���ʽѡ��");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,400);
        setLocation(450,320);
        init();
        addListener();
    }

    Font font = new Font("����",Font.BOLD,27);
    Font font1 = new Font("����",Font.BOLD,17);

    JPanel jPanel = new JPanel(null);
    JLabel jLabel = new JLabel("��ѡ�����ʽ��");
    JButton humButton = new JButton("�˹����");
    JButton autoButton = new JButton("�Զ����");
    JButton backBut = new JButton("����");

    public void beautify() {
        jPanel.setOpaque(false);
        ImageIcon ig = new ImageIcon("/IDEA/�γ����/src/images/login.png");
        JLabel imgLabel = new JLabel(ig);
        this.getLayeredPane().add(imgLabel,new Integer(Integer.MIN_VALUE));
        imgLabel.setBounds(0,0,ig.getIconWidth(),ig.getIconHeight());

        jLabel.setForeground(Color.BLACK);
        jLabel.setFont(font);
        humButton.setFont(font1);
        humButton.setBackground(Color.CYAN);
        autoButton.setFont(font1);
        autoButton.setBackground(Color.CYAN);
        backBut.setFont(font1);
        backBut.setBackground(Color.CYAN);
    }


    public void init(){
        beautify();

        jLabel.setBounds(100,40,300,90);
        humButton.setBounds(100,160,120,25);
        autoButton.setBounds(250,160,120,25);
        backBut.setBounds(340,300,120,25);

        jPanel.add(jLabel);
        jPanel.add(humButton);
        jPanel.add(autoButton);
        jPanel.add(backBut);
        setContentPane(jPanel);
        jPanel.updateUI();
    }

    public void addListener(){
        backBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TeacherGuiChoose();
                dispose();
            }
        });

        humButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HumMakePaper();
                dispose();
            }
        });

        autoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AutoMakePaper();
                dispose();
            }
        });
    }




}

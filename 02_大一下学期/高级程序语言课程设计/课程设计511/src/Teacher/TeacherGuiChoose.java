package Teacher;

import Student.AnswerConn;
import User.DBConnection;
import User.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TeacherGuiChoose extends JFrame {

    public static void main(String[] args) {
        TeacherGuiChoose teacherGuiChoose = new TeacherGuiChoose();
    }
    public TeacherGuiChoose(){
        setTitle("��ʦ�ɹ���¼");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(450,320);

        init();
        addListener();

    }

    Font font = new Font("����",Font.BOLD,27);
    Font font1 = new Font("����",Font.BOLD,17);
    Font font2 = new Font("����",Font.BOLD,14);

    JPanel pan=new JPanel(null);
    public static JLabel welcomeLabel = Login.nameLabel;
    JButton database=new JButton("ά�����");
    JButton makepapers=new JButton("���Ծ�");
    JButton check=new JButton("�����Ծ�") ;
    JButton grades=new JButton("ͳ�Ƴɼ�");
    JButton back = new JButton("���ص�¼");

    public void beautify() {
        pan.setOpaque(false);
        ImageIcon ig = new ImageIcon("/IDEA/�γ����/src/images/login.png");
        JLabel imgLabel = new JLabel(ig);
        this.getLayeredPane().add(imgLabel,new Integer(Integer.MIN_VALUE));
        imgLabel.setBounds(0,0,ig.getIconWidth(),ig.getIconHeight());

        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setFont(font1);
        database.setFont(font1);
        database.setBackground(Color.CYAN);
        makepapers.setFont(font1);
        makepapers.setBackground(Color.CYAN);
        check.setFont(font1);
        check.setBackground(Color.CYAN);
        grades.setFont(font1);
        grades.setBackground(Color.CYAN);
        back.setFont(font1);
        back.setBackground(Color.CYAN);

    }


    private void init(){
        beautify();

        welcomeLabel.setBounds(30,30,200,25);
        database.setLocation(70,95);
        database.setSize(120,30);
        makepapers.setLocation(280,95);
        makepapers.setSize(120,30);
        check.setLocation(70,195);
        check.setSize(120,30);
        grades.setLocation(280,195);
        grades.setSize(120,30);
        back.setBounds(350,290,120,30);

        pan.add(welcomeLabel);
        pan.add(database);
        pan.add(makepapers);
        pan.add(check);
        pan.add(grades);
        pan.add(back);
        setContentPane(pan);
        pan.updateUI();

    }

    private void addListener(){
        database.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpholdQuestion();
                dispose();
            }
        });

        makepapers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MakePapers();
                dispose();
            }
        });

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new TeacherMarkChoose();
                    dispose();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        grades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeacherGetScore getScore = null;
                try {
                        getScore = new TeacherGetScore();
                        dispose();
                        getScore.setVisible(true);
                } catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(null, "����ѧ��δ����Ծ��޷�ͳ�Ƴɼ�",
                            "��ʾ",2);
//                    throwables.printStackTrace();
                }

            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }








}

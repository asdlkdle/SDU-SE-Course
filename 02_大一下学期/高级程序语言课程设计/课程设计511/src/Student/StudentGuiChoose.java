package Student;

import Teacher.PaperConn;
import User.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentGuiChoose extends JFrame {
    public static void main(String[] args) {
        new StudentGuiChoose().setVisible(true);
    }

    public StudentGuiChoose(){
        super("ѧ���ɹ���¼");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(450,320);
        init();
        addListener();
    }

    Font font = new Font("����",Font.BOLD,27);
    Font font1 = new Font("����",Font.BOLD,17);
    Font font2 = new Font("����",Font.BOLD,14);

    JPanel pan = new JPanel(null);
    public static JLabel welcomeLabel = Login.nameLabel;
    String name = StudentGuiChoose.welcomeLabel.getText().split(" ")[1];
    JButton exam = new JButton("��ʼ����");
    JButton checkGrade = new JButton("�ҵĳɼ�");
    JButton back = new JButton("���ص�¼");

    private PaperConn ppConn = null;
    private Connection conn = null;
    private Statement stmt = null;
    private AnswerConn answerConn = null;

    public void beautify() {
        pan.setOpaque(false);
        ImageIcon ig = new ImageIcon("/IDEA/�γ����/src/images/login.png");
        JLabel imgLabel = new JLabel(ig);
        this.getLayeredPane().add(imgLabel,new Integer(Integer.MIN_VALUE));
        imgLabel.setBounds(0,0,ig.getIconWidth(),ig.getIconHeight());

        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setFont(font1);
        exam.setFont(font1);
        exam.setBackground(Color.CYAN);
        checkGrade.setFont(font1);
        checkGrade.setBackground(Color.CYAN);
        back.setFont(font1);
        back.setBackground(Color.CYAN);

    }


    private void init(){
        beautify();
        welcomeLabel.setBounds(50,50,200,25);
        exam.setBounds(50,110,150,30);
        checkGrade.setBounds(260,110,150,30);
        back.setBounds(165,270,150,30);
        pan.add(welcomeLabel);
        pan.add(checkGrade);
        pan.add(exam);
        pan.add(back);
        setContentPane(pan);
        pan.updateUI();
    }



    public boolean isHasFinish() throws SQLException {
        answerConn = new AnswerConn();
        conn = answerConn.getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT * FROM studentanswer."+name;
        Boolean result = true;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            conn.close();
        }catch(SQLSyntaxErrorException e){
            result = false;
            JOptionPane.showMessageDialog(null, "����δ��ɣ�������ɿ���",
                    "��ʾ",2);
        }
        return result;
    }

    public boolean isHasFinishEar() throws SQLException {
        answerConn = new AnswerConn();
        conn = answerConn.getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT * FROM studentanswer."+name;
        Boolean result = true;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            conn.close();
        }catch(SQLSyntaxErrorException e){
            result = false;
        }
        return result;
    }

    public boolean isHasQue() throws SQLException {
        ppConn = new PaperConn();
        conn = ppConn.getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT * FROM papers.paper1";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next())return true;
        else return false;

    }



    private void addListener(){
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        exam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isHasQue()) {
                        if (!isHasFinishEar()) {
                            new MultiExam().start();
                            dispose();
                        }else {
                            JOptionPane.showMessageDialog(null, "�����Ѿ��������޷��ٴο���",
                                    "��ʾ",2);
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "��ǰû���Ծ���ȴ���ʦ����",
                                "��ʾ",2);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        checkGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isHasFinish()) {
                        new GetScore();
                        dispose();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }



}


class MultiExam extends Thread{
    public void run(){
        try {
            new BeginExam().setVisible(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
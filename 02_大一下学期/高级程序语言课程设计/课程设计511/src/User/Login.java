package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Teacher.*;
import Student.*;



public class Login extends JFrame {
    DBConnection dbConn = new DBConnection();
    Connection conn = dbConn.getConnection();
    Socket socket;



    public Login() throws IOException {

        super("��¼");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,400);
        setLocation(450,320);
        this.init();
        this.addListener();
        new MultyLoginSer();
    }

    Font font = new Font("�����п�",Font.BOLD,27);
    Font font1 = new Font("����",Font.BOLD,17);
    Font font2 = new Font("����",Font.BOLD,14);

    JPanel jPanel = new JPanel(null);
    public static JLabel nameLabel = new JLabel();
    JLabel jLabelId = new JLabel("�˺ţ�");
    JLabel jLabelPass = new JLabel("���룺");
    JTextField fieldId = new JTextField(10);
    JPasswordField fieldPass = new JPasswordField(10);
    JLabel title = new JLabel("��¼");
    JButton button = new JButton("��¼");
    ButtonGroup btnGroup = new ButtonGroup();
    JRadioButton teaButton = new JRadioButton("������ʦ");
    JRadioButton stuButton = new JRadioButton("����ѧ��");
    JButton buttonReg = new JButton("ע��");


    public void beautify() {
        jPanel.setOpaque(false);
        ImageIcon ig = new ImageIcon("/IDEA/�γ����/src/images/login.png");
        JLabel imgLabel = new JLabel(ig);
        this.getLayeredPane().add(imgLabel,new Integer(Integer.MIN_VALUE));
        imgLabel.setBounds(0,0,ig.getIconWidth(),ig.getIconHeight());

        title.setForeground(Color.BLACK);
        jLabelId.setForeground(Color.BLACK);
        jLabelPass.setForeground(Color.BLACK);
        title.setFont(font);
        jLabelId.setFont(font1);
        jLabelPass.setFont(font1);
        fieldId.setFont(font1);
        fieldPass.setFont(font1);
        stuButton.setFont(font2);
        stuButton.setBackground(Color.CYAN);
        teaButton.setFont(font2);
        teaButton.setBackground(Color.CYAN);
        button.setFont(font1);
        button.setBackground(Color.CYAN);
        buttonReg.setFont(font1);
        buttonReg.setBackground(Color.CYAN);
    }

    private void init() {
        beautify();

        title.setBounds(200, 60, 90, 25);
        jLabelId.setBounds(30, 130, 90, 25);
        jLabelPass.setBounds(30, 154, 90, 25);
        fieldId.setBounds(95, 130, 300, 25);
        fieldPass.setBounds(95, 154, 300, 25);
        fieldPass.setEchoChar('*');
        button.setBounds(100, 280, 90, 25);
        buttonReg.setBounds(300, 280, 90, 25);
        teaButton.setBounds(95, 205, 90, 25);
        stuButton.setBounds(300, 205, 90, 25);

        btnGroup.add(teaButton);
        btnGroup.add(stuButton);
        jPanel.add(title);
        jPanel.add(jLabelId);
        jPanel.add(jLabelPass);
        jPanel.add(fieldId);
        jPanel.add(fieldPass);
        jPanel.add(button);
        jPanel.add(buttonReg);
        jPanel.add(stuButton);
        jPanel.add(teaButton);
        setContentPane(jPanel);
        jPanel.updateUI();
//        this.add(jPanel);

    }


    private void addListener(){
        //ע�ᰴť
        buttonReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Register();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        //��¼��ť
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getUserName();
                    send();
                    fieldId.setText("");
                    fieldPass.setText("");
                    btnGroup.clearSelection();
                } catch (IOException | SQLException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }

    public void getUserName() throws SQLException {
        String isTeacher = null;
        if (teaButton.isSelected())isTeacher = "1";
        if (stuButton.isSelected())isTeacher = "0";
        if (fieldId.getText().equals("")||fieldPass.getText().equals("")||
                isTeacher==null){
        }else {
            dbConn = new DBConnection();
            conn = dbConn.getConnection();
            Statement stmt = conn.createStatement();
            String id = fieldId.getText();
            String sql = "SELECT * FROM user.new_table WHERE id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (rs.getInt(5) == 1) nameLabel.setText("��ӭ�㣬 " + rs.getString(2) + " ��ʦ��");
                else nameLabel.setText("��ӭ�㣬ѧ�� " + rs.getString(2) + " ��");
            }
        }
    }

    public void send() throws IOException {
        InetAddress ia = InetAddress.getLocalHost();
        socket = new Socket(ia.getHostAddress(),6666);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        PrintWriter pw = new PrintWriter(bw,true);
        String isTeacher = null;
        if (teaButton.isSelected())isTeacher = "1";
        if (stuButton.isSelected())isTeacher = "0";

        if (fieldId.getText().equals("")||fieldPass.getText().equals("")||
                isTeacher==null){
            JOptionPane.showMessageDialog(null, "�뽫��¼��Ϣ��д������",
                    "��Ϣ", 0);
        }else {
            String send = fieldId.getText() + " " + fieldPass.getText() + " " + isTeacher;
            pw.println(send);
            int judge = Integer.parseInt(br.readLine());
            if (judge == 1 && isTeacher.equals("1")) {
                new TeacherGuiChoose();
            } else if (judge == 1 && isTeacher.equals("0")) {
                new StudentGuiChoose().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "û���ҵ��û�",
                        "��Ϣ", 0);
            }
        }
    }




    public static void main(String[] args) throws IOException {
        new Login();
    }

}

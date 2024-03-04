package Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewPaper extends JFrame{
    public ViewPaper(){
        setTitle("�Ծ�Ԥ��");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700,800);
        setLocation(350,50);
        init();
        addListener();

    }

    Font font1 = new Font("����",Font.BOLD,17);

    JPanel jPanel = new JPanel(null);
    JLabel idLabel = new JLabel("��Ŀ��ţ�");
    JLabel typeLabel = new JLabel("���ͣ�");
    JLabel difficultLabel = new JLabel("�Ѷ�ϵ����");
    JLabel questionLabel = new JLabel("��Ŀ��");
    JLabel answerLabel = new JLabel("�𰸣�");
    ButtonGroup btg = new ButtonGroup();
    JRadioButton singleRadButton = new JRadioButton("��ѡ");
    JRadioButton multiRadButton = new JRadioButton("��ѡ");
    JRadioButton subjectiveRadButton = new JRadioButton("������");
    JTextField idField = new JTextField();
    JTextField difficultField = new JTextField();
    JScrollPane quePan = new JScrollPane();
    JScrollPane ansPan = new JScrollPane();
    JTextArea questionArea = new JTextArea();
    JTextArea answerArea = new JTextArea();
    JButton removeBut = new JButton("�Ƴ��Ծ�");
    JButton saveBut = new JButton("�����Ծ�");
    JButton checkBut = new JButton("��ѯ��Ŀ");
    JButton delAllBut = new JButton("ɾ�������Ծ�");
    JButton firstBut = new JButton("��һ��");
    JButton lastBut = new JButton("���һ��");
    JButton nextBut = new JButton("��һ��");
    JButton preBut =  new JButton("��һ��");
    JButton back = new JButton("����");

    private PaperConn ppConn = null;
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;

    public void beautify() {
        jPanel.setOpaque(false);
        ImageIcon ig = new ImageIcon("/IDEA/�γ����/src/images/1.jpg");
        JLabel imgLabel = new JLabel(ig);
        this.getLayeredPane().add(imgLabel,new Integer(Integer.MIN_VALUE));
        imgLabel.setBounds(0,0,ig.getIconWidth(),ig.getIconHeight());

        idLabel.setForeground(Color.BLACK);
        idLabel.setFont(font1);
        typeLabel.setFont(font1);
        typeLabel.setBackground(Color.CYAN);
        difficultLabel.setFont(font1);
        difficultLabel.setBackground(Color.CYAN);
        questionLabel.setFont(font1);
        questionLabel.setBackground(Color.CYAN);
        answerLabel.setFont(font1);
        answerLabel.setBackground(Color.CYAN);

        singleRadButton.setFont(font1);
        multiRadButton.setFont(font1);
        subjectiveRadButton.setFont(font1);
        idField.setFont(font1);
        difficultField.setFont(font1);
        questionArea.setFont(font1);
        answerArea.setFont(font1);
        removeBut.setFont(font1);
        removeBut.setBackground(Color.CYAN);
        delAllBut.setFont(font1);
        delAllBut.setBackground(Color.YELLOW);
        saveBut.setFont(font1);
        saveBut.setBackground(Color.YELLOW);
        checkBut.setFont(font1);
        checkBut.setBackground(Color.CYAN);
        firstBut.setFont(font1);
        firstBut.setBackground(Color.GREEN);
        lastBut.setFont(font1);
        lastBut.setBackground(Color.GREEN);
        nextBut.setFont(font1);
        nextBut.setBackground(Color.GREEN);
        preBut.setFont(font1);
        preBut.setBackground(Color.GREEN);
        back.setFont(font1);
        back.setBackground(Color.GREEN);

    }

    private void init(){
        beautify();

        idLabel.setBounds(50,20,90,25);
        idField.setBounds(160,20,90,25);
        typeLabel.setBounds(50,50,90,25);
        btg.add(singleRadButton);
        btg.add(multiRadButton);
        btg.add(subjectiveRadButton);
        singleRadButton.setBounds(160,50,90,25);
        multiRadButton.setBounds(260,50,90,25);
        subjectiveRadButton.setBounds(360,50,90,25);
        difficultLabel.setBounds(50,100,90,25);
        difficultField.setBounds(160,100,300,25);
        questionLabel.setBounds(50,150,90,25);
        quePan.setBounds(160,150,300,150);
        quePan.setViewportView(questionArea);
        answerLabel.setBounds(50,350,90,25);
        ansPan.setBounds(160,350,300,150);
        ansPan.setViewportView(answerArea);
        checkBut.setBounds(150,550,120,25);
        removeBut.setBounds(370,550,120,25);
        saveBut.setBounds(150,600,120,25);
        delAllBut.setBounds(340,600,180,25);
        firstBut.setBounds(60,650,120,25);
        preBut.setBounds(210,650,120,25);
        nextBut.setBounds(360,650,120,25);
        lastBut.setBounds(510,650,120,25);
        back.setBounds(510,700,120,25);


        jPanel.add(idField);
        jPanel.add(idLabel);
        jPanel.add(typeLabel);
        jPanel.add(singleRadButton);
        jPanel.add(multiRadButton);
        jPanel.add(subjectiveRadButton);
        jPanel.add(difficultField);
        jPanel.add(difficultLabel);
        jPanel.add(quePan);
        jPanel.add(questionLabel);
        jPanel.add(answerLabel);
        jPanel.add(ansPan);
        jPanel.add(checkBut);
        jPanel.add(removeBut);
        jPanel.add(saveBut);
        jPanel.add(delAllBut);
        jPanel.add(firstBut);
        jPanel.add(preBut);
        jPanel.add(nextBut);
        jPanel.add(lastBut);
        jPanel.add(back);
        setContentPane(jPanel);
        jPanel.updateUI();

    }

    public void back(){
        new HumMakePaper();
    }

    private void addListener(){
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back();
                dispose();
            }
        });

        saveBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateId();
                JOptionPane.showMessageDialog(null, "�Ծ��ѱ��棡","��ʾ",1);
                idField.setText("");
                difficultField.setText("");
                questionArea.setText("");
                answerArea.setText("");
                btg.clearSelection();
            }
        });

        checkBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idField.getText().equals("")) {
                    idField.setText("");
                    difficultField.setText("");
                    questionArea.setText("");
                    answerArea.setText("");
                    btg.clearSelection();
                    check();
                }else {
                    haveNumCheck();
                }
            }
        });

        firstBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                difficultField.setText("");
                questionArea.setText("");
                answerArea.setText("");
                btg.clearSelection();
                check();
            }
        });

        lastBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                difficultField.setText("");
                questionArea.setText("");
                answerArea.setText("");
                btg.clearSelection();
                last();
            }
        });

        nextBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(idField.getText().equals("")||difficultField.getText().equals("")||questionArea.getText().equals("")
                        ||answerArea.getText().equals(""))
                        &&(singleRadButton.isSelected()==true||multiRadButton.isSelected()==true
                        ||subjectiveRadButton.isSelected()==true)){
                    nextQue();
                }else{
                    JOptionPane.showMessageDialog(null, "��ǰ����Ŀ���޷�������һ���ѯ","��ʾ",0);
                }

            }
        });

        preBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(idField.getText().equals("")||difficultField.getText().equals("")||questionArea.getText().equals("")
                        ||answerArea.getText().equals(""))
                        &&(singleRadButton.isSelected()==true||multiRadButton.isSelected()==true
                        ||subjectiveRadButton.isSelected()==true)){
                    preQue();
                }else{
                    JOptionPane.showMessageDialog(null, "��ǰ����Ŀ���޷�������һ���ѯ","��ʾ",0);
                }
            }
        });

        removeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(idField.getText().equals("")||difficultField.getText().equals("")||questionArea.getText().equals("")
                        ||answerArea.getText().equals(""))
                        &&(singleRadButton.isSelected()==true||multiRadButton.isSelected()==true
                        ||subjectiveRadButton.isSelected()==true)){
                    delQue();
                }else{
                    JOptionPane.showMessageDialog(null, "��Ŀ��Ϣ��ȫ���޷������Ƴ��Ծ�","��ʾ",0);
                }
            }
        });

        delAllBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "ȷ��ɾ�������Ծ���?", "���ٴ�ȷ��", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    delAllQuestion();
                    JOptionPane.showMessageDialog(null, "ɾ���ɹ���","��ʾ",1);
                    idField.setText("");
                    difficultField.setText("");
                    questionArea.setText("");
                    answerArea.setText("");
                    btg.clearSelection();
                }
            }
        });

    }

    public void check(){
        ppConn = new PaperConn();

        String sql = "SELECT * FROM papers.paper1";
        try {
            conn = ppConn.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()){
                int id = rs.getInt(1);
                int type  = rs.getInt(2);
                int difficult = rs.getInt(3);
                String question = rs.getString(4);
                String answer = rs.getString(5);
                idField.setText(String.valueOf(id));
                if(type==1)singleRadButton.setSelected(true);
                else if (type==2)multiRadButton.setSelected(true);
                else if(type==3)subjectiveRadButton.setSelected(true);
                difficultField.setText(String.valueOf(difficult));
                questionArea.setText(question);
                answerArea.setText(answer);
            }else{
                JOptionPane.showMessageDialog(null, "�����Ծ��л�û����Ŀ��","��ʾ",2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(stmt!= null)
                    stmt.close();
                if(conn!= null)
                    conn.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void last(){
        ppConn = new PaperConn();

        String sql = "SELECT * FROM papers.paper1";
        try {
            conn = ppConn.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt(1);
                int type  = rs.getInt(2);
                int difficult = rs.getInt(3);
                String question = rs.getString(4);
                String answer = rs.getString(5);
                idField.setText(String.valueOf(id));
                if(type==1)singleRadButton.setSelected(true);
                else if (type==2)multiRadButton.setSelected(true);
                else if(type==3)subjectiveRadButton.setSelected(true);
                difficultField.setText(String.valueOf(difficult));
                questionArea.setText(question);
                answerArea.setText(answer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(stmt!= null)
                    stmt.close();
                if(conn!= null)
                    conn.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void nextQue(){
        ppConn = new PaperConn();

        String sql = "SELECT * FROM papers.paper1";
        try {
            conn = ppConn.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int now = Integer.parseInt(idField.getText());
            while(rs.next()){
                if(rs.getInt(1)==now)break;
            }
            if (rs.next()){
                int id = rs.getInt(1);
                int type  = rs.getInt(2);
                int difficult = rs.getInt(3);
                String question = rs.getString(4);
                String answer = rs.getString(5);
                idField.setText(String.valueOf(id));
                if(type==1)singleRadButton.setSelected(true);
                else if (type==2)multiRadButton.setSelected(true);
                else if(type==3)subjectiveRadButton.setSelected(true);
                difficultField.setText(String.valueOf(difficult));
                questionArea.setText(question);
                answerArea.setText(answer);
            }else{
                JOptionPane.showMessageDialog(null, "�Ѿ������һ����","��ʾ",2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(stmt!= null)
                    stmt.close();
                if(conn!= null)
                    conn.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void preQue(){
        ppConn = new PaperConn();

        try {
            conn = ppConn.getConnection();
            stmt = conn.createStatement();
            String now = idField.getText();
            int pre = Integer.parseInt(now)-1;
            now = String.valueOf(pre);
            String sql1 = "select * from papers.paper1 where id="+now;
            ResultSet rs = stmt.executeQuery(sql1);
            while(!rs.next()&&pre!=0){//ע��Ҫ���ϲ������㣬���򵽵�һ����Ҳ����ǰ�ƶ�
                pre--;
                now = String.valueOf(pre);
                sql1 = "select * from papers.paper1 where id="+now;
                rs = stmt.executeQuery(sql1);
            }
            ResultSet rs1 = stmt.executeQuery(sql1);
            if (rs1.next()){
                int id = rs1.getInt(1);
                int type  = rs1.getInt(2);
                int difficult = rs1.getInt(3);
                String question = rs1.getString(4);
                String answer = rs1.getString(5);
                idField.setText(String.valueOf(id));
                if(type==1)singleRadButton.setSelected(true);
                else if (type==2)multiRadButton.setSelected(true);
                else if(type==3)subjectiveRadButton.setSelected(true);
                difficultField.setText(String.valueOf(difficult));
                questionArea.setText(question);
                answerArea.setText(answer);
            }else{
                JOptionPane.showMessageDialog(null, "�Ѿ��ǵ�һ����Ŀ��","��ʾ",2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(stmt!= null)
                    stmt.close();
                if(conn!= null)
                    conn.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void haveNumCheck(){
        ppConn = new PaperConn();

        try {
            conn = ppConn.getConnection();
            stmt = conn.createStatement();
            int now = Integer.parseInt(idField.getText());
            String sql1 = "select * from papers.paper1 where id="+now;
            ResultSet rs = stmt.executeQuery(sql1);
            idField.setText("");
            difficultField.setText("");
            questionArea.setText("");
            answerArea.setText("");
            btg.clearSelection();
            if (rs.next()){
                int id = rs.getInt(1);
                int type  = rs.getInt(2);
                int difficult = rs.getInt(3);
                String question = rs.getString(4);
                String answer = rs.getString(5);
                idField.setText(String.valueOf(id));
                if(type==1)singleRadButton.setSelected(true);
                else if (type==2)multiRadButton.setSelected(true);
                else if(type==3)subjectiveRadButton.setSelected(true);
                difficultField.setText(String.valueOf(difficult));
                questionArea.setText(question);
                answerArea.setText(answer);
            }else{
                JOptionPane.showMessageDialog(null, "�Ծ��п���û�������Ŀ��","��ʾ",0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(stmt!= null)
                    stmt.close();
                if(conn!= null)
                    conn.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void delQue(){
        ppConn = new PaperConn();

        try {
            conn = ppConn.getConnection();
            stmt = conn.createStatement();
            String sql = "DELETE FROM papers.paper1 WHERE ID ="+idField.getText();
            int n = JOptionPane.showConfirmDialog(null, "ȷ�ϴ��Ծ����Ƴ���?", "���ٴ�ȷ��",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                stmt.executeUpdate(sql);
                idField.setText("");
                difficultField.setText("");
                questionArea.setText("");
                answerArea.setText("");
                btg.clearSelection();
                JOptionPane.showMessageDialog(null, "�ɹ����Ծ����Ƴ���","��ʾ",1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(stmt!= null)
                    stmt.close();
                if(conn!= null)
                    conn.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void delAllQuestion() {
        ppConn = new PaperConn();
        try {
            conn = ppConn.getConnection();
            stmt = conn.createStatement();
            for (int id=1;id<51;id++){
                String sql = "SELECT * FROM papers.paper1 WHERE id="+id;
                rs = stmt.executeQuery(sql);
                if (rs.next()){
                    String sql1 = "DELETE FROM papers.paper1 WHERE id ="+id;
                    stmt.executeUpdate(sql1);
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(stmt!= null)
                    stmt.close();
                if(conn!= null)
                    conn.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

    }

    public void updateId(){
        ppConn = new PaperConn();

        try {
            conn = ppConn.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from papers.paper1";
            ResultSet rs = stmt.executeQuery(sql);
            for (int i=1;rs.next();i++){
                Statement stmt1 = conn.createStatement();
                String sql1 = "UPDATE papers.paper1 SET id="+i+" WHERE id="+rs.getInt(1);
                stmt1.executeUpdate(sql1);
                stmt1.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(stmt!= null)
                    stmt.close();
                if(conn!= null)
                    conn.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        new ViewPaper();
    }
}

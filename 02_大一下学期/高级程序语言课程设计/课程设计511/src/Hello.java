import javax.swing.*;
import java.awt.*;

public class Hello extends JFrame {
    public Hello(){
        //�Դ�frame�Ļ�������
        super("��ʼ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,500);
        setExtendedState(6);
        //����frame
        init();
        beautify();
        addListener();
    }
//��Ҫ��Ԫ��
    Font font = new Font("�����п�",Font.BOLD,40);
    Font font1 = new Font("����",Font.BOLD,60);
    JPanel jPanel = new JPanel(null);
    String words = "��ӭʹ��JExam�����������ܿ���ϵͳ";
    JLabel helloLabel = new JLabel(words);
    JLabel jExamLabel = new JLabel("JExam");
    JLabel picLabel = new JLabel();


    //��Ԫ�ص������Լ����뱳��ͼƬ
    public void beautify() {
        jPanel.setOpaque(false);
        ImageIcon ig = new ImageIcon("/IDEA/�γ����/src/images/hello.jpg");
        JLabel imgLabel = new JLabel(ig);
        this.getLayeredPane().add(imgLabel,new Integer(Integer.MIN_VALUE));
        imgLabel.setBounds(0,0,ig.getIconWidth(),ig.getIconHeight());
        helloLabel.setFont(font);
        picLabel.setIcon(new ImageIcon("/IDEA/�γ����/src/images/hello1.jpg"));
        jExamLabel.setFont(font1);
    }
    //��Ԫ�ؼ�������
    public void init(){
        helloLabel.setBounds(355,120,900,200);
        picLabel.setBounds(400,50,100,100);
        jExamLabel.setBounds(600,50,200,100);
        jPanel.add(helloLabel);
        jPanel.add(picLabel);
        jPanel.add(jExamLabel);
        setContentPane(jPanel);
        jPanel.updateUI();
    }
    //��Ӽ�����
    public void addListener(){

    }
//����
    public static void main(String[] args) {
        new Hello().setVisible(true);
    }
}

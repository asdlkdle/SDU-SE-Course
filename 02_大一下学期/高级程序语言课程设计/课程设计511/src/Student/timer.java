package Student;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class timer{

    public static JLabel jl0 = new JLabel();
    public boolean testend=true;
    private ScheduledThreadPoolExecutor scheduled;

    public void run(){
        String a = new gettime().gettime();
        scheduled = new ScheduledThreadPoolExecutor(2);
        timer(a,jl0);
    }

    private Date String2Date(String dateStr,JLabel jLabel) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            jLabel.setText("");
            throw new IllegalArgumentException("");
        }
    }

    /*����ʱ*/
    public void timer(String dateStr,JLabel jLabel) {
        Date end = String2Date(dateStr,jLabel);
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long time = (end.getTime() - 1 - System.currentTimeMillis()) / 1000;
                if (time <= 0) {
                    stopTimer();
                    {
                        jLabel.setText("���Խ�������ȴ���ʦ����");
                        testend=false;//ʱ�������testend=false
                    }return;
                }
                long hour = time / 3600;
                long minute = (time - hour * 3600) / 60;
                long seconds = time - hour * 3600 - minute * 60;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("���뿼�Խ�������").append(hour).append("ʱ ").append(minute).append("�� ").
                        append(seconds).append("�� ");

                jLabel.setText(stringBuilder.toString());
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /*ֹͣ��ʱ��*/
    public void stopTimer() {
        if (scheduled != null) {
            scheduled.shutdownNow();
            scheduled = null;
        }
    }
    /* ���� ʵ�ֽ���Ŀ��� GUI */
    public timer() {
        run();
    }
    /* �����װ�� */

    public static JLabel getLabel(){
        return jl0;
    }
}


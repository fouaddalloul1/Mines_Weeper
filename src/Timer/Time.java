package Timer;

import Game_logic.Rules;

import javax.swing.*;
import java.awt.*;


public class Time extends Thread implements java.io.Serializable{

    public static JLabel timeLabel=new JLabel("");

    public static JLabel getTimeLabel() {
        return timeLabel;
    }
    public static boolean suspendFlag;

    synchronized public  void mySuspend(){
        suspendFlag=true;
    }
    public static boolean resumeDone=false;
    synchronized public void myResume(){
        resumeDone=true;
        suspendFlag=false;
        notifyAll();
    }
    public static void setTimeLabel(JLabel timeLabel) {
        Time.timeLabel = timeLabel;
    }
    public static int number =0;
    public static int tenSec = 0;
    public static int minute = 0;
    public static int maxSec=0;
    public static int maxMin=0;
    public static boolean stop;
    public static void resetTime(){
        Time.number=0;
        Time.tenSec=0;
        Time.maxSec=0;
        Time.minute=0;
        Time.maxMin=0;
        Time.maxMin=0;
    }
    @Override
    public void run() {
            stop = false;
        while(!Thread.currentThread().isInterrupted()&&!stop){
            timeLabel.setText(Integer.toString(maxMin)+""+Integer.toString(minute)+":"+Integer.toString(maxSec)+""+ Integer.toString(tenSec));
            number++;
            tenSec++;
            if(tenSec==10){
                tenSec=0;
                maxSec++;
            }
            if(number==60){
                maxSec=0;
                number =0 ;
                minute++;
            }
            if(minute==9){
                minute=0;
                maxMin++;
            }
            if(minute==9) Rules.gameOver();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                timeLabel.setText(e.getMessage());
                timeLabel.setForeground(Color.white);
//                stop=true;
            }

            synchronized (this){
                while (suspendFlag){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
            if(resumeDone)         try {
                resumeDone=false;
                this.sleep(1300);
            } catch (InterruptedException e) {
                System.out.printf("resumeDone exception");
            }

        }
    }
}

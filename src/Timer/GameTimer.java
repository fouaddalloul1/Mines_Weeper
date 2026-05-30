package Timer;

import Game_logic.Rules;

import javax.swing.*;
import java.awt.*;

public class GameTimer extends Thread implements java.io.Serializable{
    static public  boolean stop=false;
    static public JLabel timeLabel=new JLabel("");

    static public  JLabel getTimeLabel() {
        return timeLabel;
    }

    static public void setTimeLabel(JLabel timeLabel) {
        Time.timeLabel = timeLabel;
    }
    static public  int curTime =0;
    static public boolean suspendFlag;

    public static int maxTime;

    public static int getMaxTime() {
        return maxTime;
    }

    public static void setMaxTime(int maxTime) {
        GameTimer.maxTime = maxTime;
    }

    static public  void resetTime(){
        curTime=maxTime;
    }

    synchronized public  void mySuspend(){
        suspendFlag=true;
    }
    public boolean resumeDone=false;
     synchronized  public void myResume(){
        resumeDone=true;
         suspendFlag=false;
        notifyAll();
    }
    public void run(){

        stop = false;
        curTime=maxTime;


            while(!Thread.currentThread().isInterrupted()&&!stop){
                curTime--;
                if(curTime==-1){
                    resetTime();
                    Rules.swapPlayer();
                }
                timeLabel.setText(Integer.toString(curTime));


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

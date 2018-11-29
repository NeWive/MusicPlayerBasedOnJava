import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MusicControl extends Thread{
    private static int STARTMODE = 0;
    private static int PAUSEMODE = 1;
    private static int STOPMODE = 2;
    private static int currentMode = STOPMODE;

    Thread playerThread = null;
    String path = null;
    Player player = null;

    @Override
    public void run(){
        play();
    }
    public MusicControl(){

    }
    public MusicControl(String path){
        this.path = path;
        try {
            player = new Player(new FileInputStream(new File(path)));
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void playMode(){
        synchronized (this){
            if(currentMode == STOPMODE){
                playerThread = new Thread(this,"MusicPlayer");//初始化线程
                playerThread.setPriority(Thread.MAX_PRIORITY);//设置优先级
                currentMode = STARTMODE;
                playerThread.start();
            }else if(currentMode == PAUSEMODE){
                currentMode = STARTMODE;
                this.notifyAll();
            }else{
                return;
            }
        }
    }
    public boolean setPause(){//已经暂停返回true
        synchronized (this){
            if(currentMode == STARTMODE){
                currentMode = PAUSEMODE;
            }
            return currentMode == PAUSEMODE;
        }
    }
    public boolean setResume(){
        synchronized (this){
            if(currentMode == PAUSEMODE){
                currentMode = STARTMODE;
                this.notifyAll();
            }
            return currentMode == STARTMODE;
        }
    }
    public void setStop(){
        synchronized (this){
            currentMode = STOPMODE;
            this.notifyAll();
        }
    }
    public void play(){
        while(currentMode != STOPMODE){
            try {
                if(!player.play(1)){
                    break;
                }
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
            synchronized (this){
                while(currentMode == PAUSEMODE){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }
        setClose();
    }
    public void setClose(){
        synchronized (this){
            currentMode = STOPMODE;
        }
        player.close();
    }
    public static void main(String[] args){
        MusicControl musicControl = new MusicControl("C:\\Users\\dell\\Desktop\\PacificRim.mp3");
        musicControl.playMode();
        for(int i = 0; i < 10000000; i++){
            System.out.println(i);
        }
        musicControl.setPause();
        for(int i = 0; i < 1000; i++){
            System.out.println(i);
        }
        musicControl.setResume();
    }
}

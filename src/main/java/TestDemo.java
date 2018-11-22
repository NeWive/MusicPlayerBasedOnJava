import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestDemo {
    private static int currentProgress = 20;
    public static void main(String[] args) {
        JFrame f=new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final Container contentpane=f.getContentPane();
        final JProgressBar jProgressBar = new JProgressBar();
        jProgressBar.setMinimum(0);
        jProgressBar.setMaximum(100);
        jProgressBar.setValue(currentProgress);
        jProgressBar.setStringPainted(true);
        jProgressBar.setSize(1000, 2);
        jProgressBar.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                System.out.println(jProgressBar.getPercentComplete());
            }
        });
        contentpane.add(jProgressBar);
        f.setVisible(true);
        f.pack();
        Timer count = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentProgress > 100){

                }else{
                    currentProgress++;
                    jProgressBar.setValue(currentProgress);
                }

            }
        });
        count.start();
    }
}
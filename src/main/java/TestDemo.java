import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class TestDemo {

    public static void main(String[] args) {
        JFrame f=new JFrame();
        Container contentpane=f.getContentPane();
        f.setLayout(new GridLayout(1, 2));
        String[] s=new String[]{"日本","英国","法国","中国","美国"};
        Vector v=new Vector();
        v.addElement("nokia 8850");
        v.addElement("nokia 8250");
        v.addElement("notorola v8088");
        v.addElement("motorola v3688");
        v.addElement("panasonic GD92");
        v.addElement("其他");

        JList jList=new JList(s);
        jList.setBorder(BorderFactory.createTitledBorder("您最喜欢到哪个国家玩呢"));

        JList jList2=new JList(v);
        jList2.setBorder(BorderFactory.createTitledBorder("你最喜欢哪部手机呢"));
        contentpane.add(new JScrollPane(jList));
        contentpane.add(new JScrollPane(jList2));
        contentpane.add(jList2);
//        f.pack();
        f.setSize(1024,680);
//        f.show();
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });
    }
}
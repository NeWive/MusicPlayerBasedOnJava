import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class TestDemo extends JFrame {

    public TestDemo() {
        Vector<String> songsLists = new Vector<String>();
        songsLists.add("1");
        songsLists.add("1");
        songsLists.add("1");
        songsLists.add("1");
        songsLists.add("1");
        Container container = this.getContentPane();
        JScrollPane jScrollPane = new JScrollPane();

        JList lists = new JList(songsLists);
        jScrollPane.add(lists);

        container.add(jScrollPane);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(1024,680);
    }

    public static void main(String[] args) {
        new TestDemo();
    }

}
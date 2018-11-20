import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class playerDesktop implements FocusListener {
    String hintText = "请输入你要搜索的歌曲";
    Vector<String> songsLists = new Vector<String>();

    public playerDesktop() {
        songsLists.add("本地歌单");
        songsLists.add("云歌单1");
        songsLists.add("云歌单2");
    }


    public void mainLayout() {
        JFrame mainLayout = new JFrame("桥某人的机型复读机");//主界面

        Container mainContainer = mainLayout.getContentPane();//获取容器
        mainContainer.setBackground(Color.GRAY);
        mainContainer.setLayout(new BorderLayout());//边界布局

        //获取顶部JPanel
        JPanel top = new JPanel(new GridLayout(1,3));
        top.setBackground(Color.BLACK);

        //左侧
        //设置图标
        Icon icon = new ImageIcon("E:/player/timg.jpg");
        ((ImageIcon) icon).setImage(((ImageIcon) icon).getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT ));//设置图标大小
        //设置标题
        JLabel topic = new JLabel("滑稽播放器——滑天下之大稽");
        topic.setFont(new Font("宋体",Font.ITALIC, 18));
        topic.setForeground(Color.YELLOW);
        topic.setIcon(icon);
        topic.setHorizontalAlignment(SwingConstants.CENTER);
        topic.setOpaque(true);
        topic.setBackground(Color.black);
        top.add(topic);

        //设置搜索框
        Icon searchIcon = new ImageIcon("E:/player/timg1.jpg");
        Insets inserts = new Insets(0,10,0,0 );
        JTextField searchArea = new JTextField("",20);
        searchArea.setText(hintText);
        searchArea.setMargin(inserts);
        searchArea.setName("searchArea");
        searchArea.addFocusListener(this);//添加监听事件
        searchArea.setBackground(Color.YELLOW);
        JButton search = new JButton("Search");
        search.setSize(50,50);
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //提交
            }
        });
        search.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {

            }
        });
        top.add(searchArea);
        top.add(search);

        mainContainer.add(BorderLayout.NORTH,top);


        //设置右侧界面
        JPanel songsBox = new JPanel(new GridLayout(2,1));
        songsBox.setPreferredSize(new Dimension(1024/3, 500));

        //歌单
        JPanel listBox = new JPanel(new BorderLayout());

        //歌单标题
        JLabel listTopic = new JLabel("所有歌单");
        listTopic.setHorizontalAlignment(SwingConstants.LEFT);
        listTopic.setPreferredSize(new Dimension(1020/3, 50));
        Icon listIcon = new ImageIcon("E:/player/timg1.jpg");
        ((ImageIcon) listIcon).setImage(((ImageIcon) listIcon).getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        listTopic.setIcon(listIcon);
        listBox.add(BorderLayout.NORTH,listTopic);

        //歌单内容
        JScrollPane list = new JScrollPane();
        JList lists = new JList(songsLists);
        list.add(lists);
        list.setPreferredSize(new Dimension(1020/3, 400));
        listBox.add(BorderLayout.CENTER,list);

        songsBox.add(listBox);
        mainContainer.add(BorderLayout.EAST,songsBox);

        //设置主框架属性
        mainLayout.setTitle("滑稽播放器——滑天下之大稽");
        mainLayout.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainLayout.setSize(1024, 680);
        mainLayout.setVisible(true);
        mainLayout.setLocationRelativeTo(null);//设置屏幕中心出现
    }


    //为文本框添加获取聚焦事件监听
    public void focusGained(FocusEvent e) {
        JTextField jTextField = (JTextField)e.getSource();

        if(jTextField.getText().equals("请输入你要搜索的歌曲")){
            jTextField.setText("");
            jTextField.setForeground(Color.BLACK);
        }
    }
    //为文本框添加失去聚焦事件监听
    public void focusLost(FocusEvent e){
        JTextField jTextField = (JTextField)e.getSource();

        if(jTextField.getText().equals("")){
            jTextField.setText("输入在当前歌单要搜索的歌曲");
            jTextField.setForeground(Color.BLACK);
        }
    }
    public void submitSearch(){

    }

}


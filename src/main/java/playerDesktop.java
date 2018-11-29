import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import static javax.swing.BorderFactory.createLineBorder;

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
        JTextField searchArea = new JTextField(null,20);
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
        JPanel rightBox = new JPanel(new GridLayout(2,1));
        rightBox.setPreferredSize(new Dimension(1024/3, 500));

        //歌单
        JList lists = new JList(songsLists);
        lists.setBorder(BorderFactory.createTitledBorder("所有歌单"));//设置标题
        //后端完成修改参数
        JScrollPane listsContent = new JScrollPane(lists);
        //
        JPanel listsBox = new JPanel(new BorderLayout());
        listsBox.add(BorderLayout.CENTER, listsContent);
        //翻页
        JButton prePage_list = new JButton("上一页");
        JButton nextPage_list = new JButton("下一页");
        JPanel tempPage_list = new JPanel(new GridLayout(1,2));
        tempPage_list.add(prePage_list);
        tempPage_list.add(nextPage_list);
        listsBox.add(BorderLayout.SOUTH, tempPage_list);
        rightBox.add(listsBox);

        //歌曲
        JList songs = new JList(songsLists);
        songs.setBorder(BorderFactory.createTitledBorder("当前歌单中的歌曲"));//设置标题
        //后端完成修改参数
        JScrollPane songsContent = new JScrollPane(songs);
        //
        JPanel songsBox = new JPanel(new BorderLayout());
        songsBox.add(BorderLayout.CENTER, songsContent);
        //翻页
        JButton prePage_songs = new JButton("上一页");
        JButton nextPage_songs = new JButton("下一页");
        JPanel tempPage_songs = new JPanel(new GridLayout(1,2));
        tempPage_songs.add(prePage_songs);
        tempPage_songs.add(nextPage_songs);
        songsBox.add(BorderLayout.SOUTH, tempPage_songs);
        rightBox.add(songsBox);

        mainContainer.add(BorderLayout.EAST,rightBox);

        //设置播放界面
        JPanel playerIndex = new JPanel(new BorderLayout());
        playerIndex.setBackground(Color.orange);
        mainContainer.add(BorderLayout.CENTER,playerIndex);

        //播放界面底部
        JPanel playerIndexBottom = new JPanel(null);
        playerIndexBottom.setBackground(Color.black);
        playerIndexBottom.setPreferredSize(new Dimension(700,50));
        playerIndex.add(BorderLayout.SOUTH, playerIndexBottom);

        //后端完成后修改
        String songImagePath = "E:/player/timg1.jpg";
        //
        Icon songImage = new ImageIcon(songImagePath);
        JLabel songImage_label = new JLabel("");
        ((ImageIcon) songImage).setImage(((ImageIcon) songImage).getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        songImage_label.setIcon(songImage);
        songImage_label.setBounds(15,0,50,50);
        songImage_label.setBorder(createLineBorder(Color.red, 1,true ));
        playerIndexBottom.add(songImage_label);

        //歌名
        //后端完成后修改,添加函数
        String songName = "滑稽12312312312321123";
        JLabel songNameTag = new JLabel(songName);
        songNameTag.setFont(new Font("宋体", Font.BOLD, 15));
        songNameTag.setForeground(Color.white);
        songNameTag.setBounds(80,5, 200,20 );
        playerIndexBottom.add(songNameTag);

        //添加播放进度条
        //后端完成后修改
        int min = 0;
        int max = 1000;
        JLabel showProcess = new JLabel(Integer.toString(200)+"/"+Integer.toString(max));
        showProcess.setBounds(80, 26, 100, 13);
        showProcess.setFont(new Font("宋体", Font.BOLD, 14));
        showProcess.setForeground(Color.white);
        playerIndexBottom.add(showProcess);
        //
        JProgressBar process = new JProgressBar();
        process.setMinimum(min);
        process.setMaximum(max);
        process.setValue(200);
        process.setStringPainted(true);
        process.setBounds(80,38,300,5);
        playerIndexBottom.add(process);

        //添加按钮
        JButton start = new JButton();
        start.setBounds(350, 15, 30, 30);

        JButton next = new JButton();
        start.setBounds(400, 15,30 ,30 );

        JButton previous = new JButton();
        start.setBounds(300,15,30 , 30);

        JButton stop = new JButton();
        start.setBounds(450, 15,30 ,30 );

        playerIndexBottom.add(previous);
        playerIndexBottom.add(start);
        playerIndexBottom.add(next);
        playerIndexBottom.add(stop);

        //设置主框架属性
        mainLayout.setTitle("滑稽播放器——滑天下之大稽");
        mainLayout.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        mainLayout.setSize(1024, 680);
        mainLayout.pack();
        mainLayout.setVisible(true);
        mainLayout.setLocationRelativeTo(null);//设置屏幕中心出现
    }


    //为文本框添加获取聚焦事件监听
    public void focusGained(FocusEvent e) {
        JTextField jTextField = (JTextField)e.getSource();

        if(jTextField.getText().equals("输入在当前歌单要搜索的歌曲")){
            jTextField.setText(null);
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


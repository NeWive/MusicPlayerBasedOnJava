import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.text.AttributedCharacterIterator;

import static javax.swing.BorderFactory.createLineBorder;

public class playerDesktop implements FocusListener {
    private static String hintText = "请输入你要搜索的歌曲";
    private static DefaultListModel<SongList> songsLists = new DefaultListModel<>();
    private static DefaultListModel<Song> songs = new DefaultListModel<>();
    private static MusicControl musicControl = null;
    private static int curruntPlaying = 0;
    private static JPanel musicInfoTable = new JPanel();
    JLabel currentSongName = new JLabel();
    int currentTime = 0;
    int max = 0;//歌曲的长度
    JPanel songListsTable = null;
    JPanel songsTable = null;
    Song currentSong = null;//当前歌曲
    SongList currentSongList = null;//当前歌单
    int processFlag = 0;//判断播放状态
    JProgressBar process = new JProgressBar();//进度条
    JLabel showProcess = new JLabel();
    JPanel infoTable = new JPanel();//歌曲信息
    BackgroundPanel backgroundPanel = null;//主界面

    //初始化
    public playerDesktop() throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {//测试用
        songs.addElement(new Song("pacific rim", 1, "213231", "C:\\Users\\dell\\Desktop\\PacificRim.mp3", "Ramin Djawadi"));
        songsLists.addElement(new SongList("metal music", "2018-12-03", "newive", "E:\\player\\tim1g.jpg"));
        currentSong = songs.elementAt(0);
        currentSongList = songsLists.getElementAt(0);
        //测试用
        curruntPlaying = 0;
        musicControl = new MusicControl(songs.getElementAt(0).getPath());
        songListsTable = creaatePage(songsLists);
        songsTable = creaatePage(songs);
        max = currentSong.getLength();
//        initMusicInfoTable(currentSong);
        initListInfoTable(songsLists.getElementAt(0));
        infoTable.setBounds(0, 80, 500, 400);
        infoTable.setOpaque(false);
        infoTable.setLayout(null);
    }
    public void initInfoJLabel(JLabel jLabel){//修改主界面的信息标签属性
        jLabel.setForeground(Color.BLACK);
        jLabel.setFont(new Font("宋体", Font.BOLD, 18));
    }
    //修改主界面信息，切换歌单信息
    public void initListInfoTable(SongList songList){
        infoTable.removeAll();
        JLabel listName = new JLabel("歌单：" + songList.getSheetName());
        JLabel listOwner = new JLabel("创建者：" + songList.getOwner());
        JLabel listCreatetime = new JLabel("创建时间：" + songList.getCreateDate());
        initInfoJLabel(listName);
        initInfoJLabel(listOwner);
        initInfoJLabel(listCreatetime);
        listName.setBounds(0,50, 300, 20);
        listOwner.setBounds(0, 90, 300, 20);
        listCreatetime.setBounds(0, 110, 300, 20);
        infoTable.add(listName);
        infoTable.add(listOwner);
        infoTable.add(listCreatetime);
        ImageIcon imageIcon = new ImageIcon(songList.getCoverPath());
        Graphics g = new Graphics() {
            @Override
            public Graphics create() {
                return null;
            }

            @Override
            public void translate(int x, int y) {

            }

            @Override
            public Color getColor() {
                return null;
            }

            @Override
            public void setColor(Color c) {

            }

            @Override
            public void setPaintMode() {

            }

            @Override
            public void setXORMode(Color c1) {

            }

            @Override
            public Font getFont() {
                return null;
            }

            @Override
            public void setFont(Font font) {

            }

            @Override
            public FontMetrics getFontMetrics(Font f) {
                return null;
            }

            @Override
            public Rectangle getClipBounds() {
                return null;
            }

            @Override
            public void clipRect(int x, int y, int width, int height) {

            }

            @Override
            public void setClip(int x, int y, int width, int height) {

            }

            @Override
            public Shape getClip() {
                return null;
            }

            @Override
            public void setClip(Shape clip) {

            }

            @Override
            public void copyArea(int x, int y, int width, int height, int dx, int dy) {

            }

            @Override
            public void drawLine(int x1, int y1, int x2, int y2) {

            }

            @Override
            public void fillRect(int x, int y, int width, int height) {

            }

            @Override
            public void clearRect(int x, int y, int width, int height) {

            }

            @Override
            public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

            }

            @Override
            public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

            }

            @Override
            public void drawOval(int x, int y, int width, int height) {

            }

            @Override
            public void fillOval(int x, int y, int width, int height) {

            }

            @Override
            public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

            }

            @Override
            public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

            }

            @Override
            public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {

            }

            @Override
            public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {

            }

            @Override
            public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {

            }

            @Override
            public void drawString(String str, int x, int y) {

            }

            @Override
            public void drawString(AttributedCharacterIterator iterator, int x, int y) {

            }

            @Override
            public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
                return false;
            }

            @Override
            public void dispose() {

            }
        };
        g.drawImage(imageIcon.getImage(), 0, 0, backgroundPanel.width, backgroundPanel.height,null);
        backgroundPanel.paintComponent(g);
    }
    //修改主界面信息，切换歌曲信息
    public void initMusicInfoTable(Song song){
        infoTable.removeAll();
        JLabel musicName = new JLabel("<<" + song.getMusicName() + ">>");
        JLabel musicArtist = new JLabel(song.getArtist());
        JLabel musicList = new JLabel("歌单名称: " + songsLists.getElementAt(curruntPlaying).getSheetName());
        JLabel musicOwner = new JLabel("创建者: " + songsLists.getElementAt(curruntPlaying).getOwner());
        JLabel musicPath = new JLabel("路径: " + song.getPath());
        JLabel musicMD5 = new JLabel("MD5: " + song.getMd5());
        musicPath.setForeground(Color.BLACK);
        musicPath.setFont(new Font("黑体", Font.BOLD, 12));
        musicMD5.setForeground(Color.BLACK);
        musicMD5.setFont(new Font("黑体", Font.BOLD, 12));
        initInfoJLabel(musicName);
        initInfoJLabel(musicArtist);
        initInfoJLabel(musicList);
        initInfoJLabel(musicOwner);
        musicName.setBounds(0, 50, 300, 20);
        musicArtist.setBounds(0, 70, 300, 20);
        musicList.setBounds(0, 90, 300, 20);
        musicOwner.setBounds(0, 110, 300, 20);
        musicPath.setBounds(0, 150, 300, 20);
        musicMD5.setBounds(0, 170, 300, 20);
        infoTable.add(musicName);
        infoTable.add(musicArtist);
        infoTable.add(musicList);
        infoTable.add(musicOwner);
        infoTable.add(musicPath);
        infoTable.add(musicMD5);
        infoTable.repaint();
        infoTable.revalidate();
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

    //添加本地选项卡
    public JPanel creaatePage(DefaultListModel defaultListModel){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        JList songsList = new JList(defaultListModel);
        JScrollPane songsContent = new JScrollPane(songsList);
        jPanel.add(BorderLayout.CENTER, songsContent);
        //翻页
        JPanel tempPage_songs = new JPanel(new GridLayout(1,2));
        jPanel.add(BorderLayout.SOUTH, tempPage_songs);
        return jPanel;
    }

    //更新歌曲
    public void refreshSongs(JTabbedPane jTabbedPane, int selected){
//        songs.addElement(new Song("qweewq", 1, "csaa", "aweq"));
        songsTable = creaatePage(songs);
    }

    //更新歌单
    public void refreshList(JTabbedPane jTabbedPane, int selected){
//        songsLists.addElement(new SongList("123", "123", "newive", "12123"));
        songListsTable = creaatePage(songsLists);
    }
    //初始化进度条
    public void initProcessBar(JProgressBar jProgressBar, JLabel jLabel){
        jLabel.setText(Integer.toString(currentTime)+"/"+Integer.toString(currentSong.getLength()));
        jLabel.setBounds(260, 25, 200, 13);
        jLabel.setFont(new Font("宋体", Font.BOLD, 10));
        jLabel.setForeground(Color.white);

        jProgressBar.setMinimum(0);
        jProgressBar.setMaximum(max);
        jProgressBar.setValue(currentTime);
        jProgressBar.setStringPainted(true);
        jProgressBar.setBounds(260,40,400,5);
    }

    public void changeButton(String flag, JButton jButton, ImageIcon icon_temp, ImageIcon icon){//播放按钮判定
        System.out.println(flag);
        if(flag.equals("pause")) {
            processFlag = 1;
            musicControl.playMode();
            return;
        }else if(flag.equals("begin") || flag.equals("resume")){
            processFlag = 1;
            jButton.setIcon(icon_temp);
            jButton.setText("pause");
            musicControl.playMode();
            return;
        }
    }

    public void refreshSongName(JLabel jLabel){//更新歌曲名称
        jLabel.setText(currentSong.getMusicName());
    }
    //初始化顶部左侧图标
    public void initTopLeftIcon(Icon icon, JLabel topic){
        ((ImageIcon) icon).setImage(((ImageIcon) icon).getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT ));//设置图标大小
        topic.setFont(new Font("宋体",Font.ITALIC, 18));
        topic.setForeground(Color.YELLOW);
        topic.setIcon(icon);
        topic.setHorizontalAlignment(SwingConstants.CENTER);
        topic.setOpaque(true);
        topic.setBackground(Color.black);
    }
    //初始化搜索框
    public void initTopRightSearchArea(JPanel temp_search){
        temp_search.setLayout(null);
        Icon searchIcon = new ImageIcon("E:/player/timg1.jpg");
        Insets inserts = new Insets(0,10,0,0 );
        JTextField searchArea = new JTextField(null,20);
        searchArea.setText(hintText);
        searchArea.setMargin(inserts);
        searchArea.setName("searchArea");
        searchArea.addFocusListener(this);//添加监听事件
        searchArea.setBackground(Color.YELLOW);
        searchArea.setBounds(30, 10, 140, 25);
        searchArea.setHorizontalAlignment(SwingConstants.CENTER);
        JButton search = new JButton("Search");
        search.setSize(50,50);
        search.setBounds(170, 10, 100, 25);
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //提交
            }
        });
        search.setHorizontalAlignment(SwingConstants.CENTER);
        search.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {

            }
        });
        temp_search.add(searchArea);
        temp_search.add(search);
    }
    //初始化选项卡目录
    public void initMenu(final JTabbedPane jTabbedPane){
        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String title = jTabbedPane.getTitleAt(jTabbedPane.getSelectedIndex());
                if(title.equals("本地歌曲")){
                    refreshSongs(jTabbedPane, jTabbedPane.getSelectedIndex());
                }else if(title.equals("本地歌单")){
                    refreshList(jTabbedPane, jTabbedPane.getSelectedIndex());
                }
            }
        });
        jTabbedPane.addTab("本地歌曲", songsTable);
        jTabbedPane.addTab("本地歌单", songListsTable);
        jTabbedPane.setSelectedIndex(0);
    }
    //初始化控制界面
    public void initControlArea(JPanel controlArea, JPanel playerIndex){
        controlArea.setBackground(Color.BLACK);
//        controlArea.setOpaque(false);
        controlArea.setLayout(null);
        controlArea.setSize(playerIndex.getWidth(), 80);
        controlArea.setPreferredSize(new Dimension(playerIndex.getWidth(), 60));
//        controlArea.setOpaque(false);
    }

    public void mainLayout() throws InterruptedException {
        JFrame mainLayout = new JFrame("桥某人的机型复读机");//主界面

        Container mainContainer = mainLayout.getContentPane();//获取容器
        mainContainer.setBackground(Color.GRAY);
        mainContainer.setLayout(new BorderLayout());//边界布局

        //获取顶部JPanel
        JPanel top = new JPanel(new GridLayout(1,2));
        top.setBackground(Color.BLACK);

        //左侧
        //设置图标
        Icon icon = new ImageIcon("E:/player/timg.jpg");
        //设置标题
        JLabel topic = new JLabel("滑稽播放器——滑天下之大稽");
        initTopLeftIcon(icon, topic);
        top.add(topic);

        //设置搜索框
        JPanel temp_search = new JPanel();
        initTopRightSearchArea(temp_search);
        top.add(temp_search);

        mainContainer.add(BorderLayout.NORTH,top);

        //主界面选项卡
        final JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        initMenu(jTabbedPane);
        mainContainer.add(BorderLayout.WEST,jTabbedPane);

        //播放控制
        JPanel playerIndex = new JPanel();
        playerIndex.setLayout(new BorderLayout());
        mainContainer.add(BorderLayout.CENTER, playerIndex);

        //添加按钮
        JPanel controlArea = new JPanel();
        initControlArea(controlArea, playerIndex);
        playerIndex.add(BorderLayout.SOUTH, controlArea);

        final ImageIcon icon2 = new ImageIcon("C:\\Users\\dell\\Desktop\\壁纸\\Alicization_Dividing_by_Kagura-Kurosaki.jpg");
        final ImageIcon icon_temp = new ImageIcon("C:\\Users\\dell\\Desktop\\壁纸\\Alice_Schuberg_--_Sword_Art_Online_Alicization_by.jpg");
        final JButton start = new JButton(icon2);
        start.setText("begin");
        start.setBounds(65, 5, 50, 50);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton = (JButton) e.getSource();
                if(jButton.getText().equals("resume")){
                    ((JButton) e.getSource()).setIcon(icon_temp);
                    ((JButton) e.getSource()).setText("pause");
                    musicControl.playMode();
                    processFlag = 1;
                }else if(jButton.getText().equals("pause")){
                    ((JButton) e.getSource()).setIcon(icon2);
                    ((JButton) e.getSource()).setText("resume");
                    musicControl.setPause();
                    processFlag = 0;
                }else if(jButton.getText().equals("begin")){
                    processFlag = 1;
                    musicControl.playMode();
                    ((JButton) e.getSource()).setText("pause");
                    ((JButton) e.getSource()).setIcon(icon_temp);
                }
            }
        });
        controlArea.add(start);

        final ImageIcon icon1 = new ImageIcon("C:\\Users\\dell\\Desktop\\壁纸\\Alice_Schuberg_--_Sword_Art_Online_Alicization_by.jpg");
        final JButton pre = new JButton(icon1);
        pre.setText("pre");
        pre.setBounds(10, 5, 50, 50);
        pre.addActionListener(new ActionListener() {  //上一首
            @Override
            public void actionPerformed(ActionEvent e) {
                if(curruntPlaying == 0){
                    JOptionPane.showMessageDialog(null, "当前已处于第一首歌", "Ops :(", JOptionPane.WARNING_MESSAGE);
                }else{
                    musicControl.setClose();
                    musicControl.setPath(songs.getElementAt(--curruntPlaying).getPath());
                    musicControl.playMode();
                    String flag = start.getText();
                    changeButton(flag, start, icon_temp, icon2);
                }
            }
        });
        controlArea.add(pre);

        ImageIcon icon3 = new ImageIcon("C:\\Users\\dell\\Desktop\\壁纸\\Alice_Schuberg_--_Sword_Art_Online_Alicization_by.jpg");
        JButton next = new JButton(icon3);
        next.setBounds(120, 5, 50, 50);
        next.addActionListener(new ActionListener() {//下一首
            @Override
            public void actionPerformed(ActionEvent e) {
                if(curruntPlaying == (songsLists.getSize() - 1)){
                    JOptionPane.showMessageDialog(null, "当前已处于最后一首歌", "Ops :(", JOptionPane.WARNING_MESSAGE);
                }else{
                    musicControl.setClose();
                    musicControl.setPath(songs.getElementAt(++curruntPlaying).getPath());
                    musicControl.playMode();
                    String flag = start.getText();
                    changeButton(flag, start, icon_temp, icon2);
                }
            }

        });
        controlArea.add(BorderLayout.WEST, next);

        String songImagePath = "E:/player/timg1.jpg";
        Icon songImage = new ImageIcon(songImagePath);
        JLabel songImage_label = new JLabel("");
        ((ImageIcon) songImage).setImage(((ImageIcon) songImage).getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        songImage_label.setIcon(songImage);
        songImage_label.setBounds(200,5,50,50);
        songImage_label.setBorder(createLineBorder(Color.red, 1,true ));
        controlArea.add(songImage_label);

        currentSongName.setFont(new Font("宋体", Font.BOLD, 14));
        currentSongName.setForeground(Color.BLACK);

        refreshSongName(currentSongName);//调用刷新currentSongName

        currentSongName.setBounds(260, -5, 250, 50);
        controlArea.add(currentSongName);

        //添加进度条
        //添加播放进度条
        showProcess.setForeground(Color.BLACK);
        controlArea.add(showProcess);

        initProcessBar(process, showProcess);
        controlArea.add(process);

        //设置中部歌曲信息
        musicInfoTable.setLayout(null);

        //设置主框架属性
        mainLayout.setTitle("滑稽播放器——滑天下之大稽");
        mainLayout.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainLayout.setSize(1024, 680);
//        mainLayout.pack();
        mainLayout.setVisible(true);
        mainLayout.setLocationRelativeTo(null);//设置屏幕中心出现

        //为进度条添加事件
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(processFlag);
                if(processFlag == 1){
                    currentTime += 1;
                    process.setValue(currentTime);
                    if(currentTime == currentSong.getLength()){
                        process.setValue(0);
                    }
                    showProcess.setText(Integer.toString(currentTime));
                }else{
                    return;
                }
            }
        }).start();

        //背景图片
        backgroundPanel = new BackgroundPanel("prototype_by_experimentnr7-d2zfi7u.png", playerIndex.getWidth(), playerIndex.getHeight());

        backgroundPanel.add(infoTable);
        playerIndex.add(BorderLayout.CENTER, backgroundPanel);
        Thread.sleep(1000);
    }

}

class BackgroundPanel extends JPanel{
    String path = null;
    int width;
    int height;
    public BackgroundPanel(String path, int width, int height){
        this.path = path;
        this.width = width;
        this.height = height;
        this.setLayout(null);
    }
    public BackgroundPanel(){

    }
    public void setPath(String path){
        this.path = path;
    }
    public String getPath(){
        return path;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon(path);
        g.drawImage(imageIcon.getImage(), 0, 0, this.width, this.height,null);
    }
}


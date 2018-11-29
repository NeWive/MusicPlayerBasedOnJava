import java.sql.*;
import java.util.Vector;


public class javaConnectSQLite {
    private static String class_name = "org.sqlite.JDBC";
    private static String DBURL = "jdbc:sqlite:E:/player/music_info_database/music_info.db";
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet result = null;
    private static  String sql = null;
    private static PreparedStatement preparedStatement = null;
    private static boolean check = false;
    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(class_name);
        return DriverManager.getConnection(DBURL);
    }

    public static Vector<Song> getAllSongs() {//查询所有歌曲
        sql = "select * from music";
        Vector<Song> songs = new Vector<Song>();
        try {
            connection = createConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            System.out.println("success");
            while(result.next()){
                Song song_temp = new Song(result.getInt("musicId"),result.getString("musicname"),result.getInt("sheetid"),result.getString("md5"),result.getString("path"));
                songs.add(song_temp);
            }
            System.out.println("拉取歌单成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("拉取歌单失败");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接库失败");
        }
        try {
            statement.close();
            connection.close();
            result.close();
            System.out.println("Close Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to close the sources");
        }
        return songs;
    }
    public static Vector<SongList> getSongLists(){//查询歌单

        sql = "select * from sheet";
        Vector<SongList> songLists = new Vector<SongList>();
        try {
            connection = createConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            System.out.println("拉取歌单成功");
            while(result.next()){
                SongList songList_temp = new SongList(result.getInt("sheetid"), result.getString("sheetname"), result.getString("createDate"), result.getString("owner"), result.getString("coverpath"));
                songLists.add(songList_temp);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("创建歌单失败");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("关闭资源失败");
                e.printStackTrace();
            }

        }
        return songLists;
    }
    public static Vector<Song> getSongsBySheetId(int id){//根据歌单编号查找
        Vector<Song> songs = new Vector<Song>();
        try {
            connection = createConnection();
            sql = "select * from music where sheetid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
            System.out.println("拉取歌曲成功");
            while(result.next()){
                Song song_temp = new Song(result.getInt("musicId"),result.getString("musicname"),result.getInt("sheetid"),result.getString("md5"),result.getString("path"));
                songs.add(song_temp);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("连接数据库失败");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                result.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return songs;
    }
    public static Vector<Song> searchSong(String musicname) throws SQLException {//查找音乐
        Vector<Song> songs = null;
        try {
            connection = createConnection();
            sql = "select * from music where musicname = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, musicname);
            result = preparedStatement.executeQuery();
            while(result.next()){
                songs.add(new Song(result.getInt("musicId"), result.getString("musicname"), result.getInt("sheetid"), result.getString("md5"), result.getString("path")));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
            preparedStatement.close();
        }
        return songs;
    }
    public static boolean checkIfSame(Song song_1, Song song_2){//查重子部份
        if(song_1.getPath().equals(song_2.getPath())){
            return true;
        }else{
            return false;
        }
    }
    public static boolean checkExist(Song song) throws SQLException {//查找相同的音乐
        Vector<Song> songs = searchSong(song.getMusicName());
        boolean check = false;
        for(Song song_temp: songs){
            check = checkIfSame(song, song_temp);
            if(check == true){
                break;
            }
        }
        return check;
    }
    public static boolean addMusic(Song song) throws SQLException {//增加音乐
        if(checkExist(song)){
            try {
                connection = createConnection();
                sql = "insert into music (musicname,sheetid,md5,path) values(?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, song.getMusicName());
                preparedStatement.setInt(2, song.getSheetId());
                preparedStatement.setString(3, song.getMd5());
                preparedStatement.setString(4, song.getPath());
                check = preparedStatement.execute();
            } catch (ClassNotFoundException e) {
                System.out.println("连接数据库失败");
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("关闭资源失败");
                    e.printStackTrace();
                }
            }
            return !check;
        }else {
            return true;
        }
    }
    public static boolean deleteMusic(Song song){
        boolean check = false;
        try {
            connection = createConnection();
            sql = "delete from music where musicname = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, song.getMusicName());
            check = preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return !check;

    }
    public static boolean deleteMusicList(SongList songList, int commond){//只删除歌单

        try {
            connection = createConnection();
            sql = "delete from sheet where sheetname = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, songList.getSheetName());
            check = preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(commond == 1){
                sql = "delete from music where sheetid = ?";
                try {
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, songList.getSheetId());
                    preparedStatement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return !check;
    }
    public static boolean addMusicList(SongList songList){
        try {
            connection = createConnection();
            sql = "insert into sheet (sheetname,createDate,owner,coverpath) values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, songList.getSheetName());
            preparedStatement.setString(2, songList.getCreateDate());
            preparedStatement.setString(3, songList.getOwner());
            preparedStatement.setString(4, songList.getCoverPath());
            check = preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return !check;
    }
}

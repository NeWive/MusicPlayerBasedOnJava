import org.junit.Test;

import java.sql.SQLException;
import java.util.Vector;

public class javaConnectSQLiteTest {
    @Test
    public void getSongs() {
        Vector<Song> songs = javaConnectSQLite.getAllSongs();
        for(Song song_temp: songs){
            System.out.println(song_temp.toString());
        }
    }
    @Test
    public void  getSongsList(){
        Vector<SongList> songLists = javaConnectSQLite.getSongLists();
        for(SongList songList_temp: songLists){
            System.out.println(songList_temp.toString());
        }
    }
    @Test
    public void getSongsBySheetId(){
        Vector<Song> songs = javaConnectSQLite.getSongsBySheetId(1);
        for(Song song_temp: songs){
            System.out.println(song_temp.toString());
        }
    }
    @Test
    public void addMusic() throws SQLException {
        boolean check = javaConnectSQLite.addMusic(new Song("311",1,"12321","das"));
        System.out.println(check ? "success":"false");
    }
    @Test
    public void deleteMusic(){
        Song song = new Song();
        song.setMusicName("glory");
        boolean check = javaConnectSQLite.deleteMusic(song);
        System.out.println(check ? "success":"false");
    }
    @Test
    public void deleteMusicList(){
        SongList songList = new SongList();
        songList.setSheetId(2);
        songList.setSheetName("asd");
        boolean check = javaConnectSQLite.deleteMusicList(songList, 1);
        System.out.println(check ? "success":"false");
    }
    @Test
    public void addSongList(){
        SongList songList = new SongList("qwe","321","newive","123");
        boolean check = javaConnectSQLite.addMusicList(songList);
        System.out.println(check ? "success":"false");
    }
}
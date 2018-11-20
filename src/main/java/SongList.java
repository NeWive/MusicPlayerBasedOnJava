import java.util.Arrays;

public class SongList {
    private String number;
    private Song[] songs;
    private String createTime;
    private String name;

    public SongList() {
    }

    public SongList(String number, Song[] songs, String createTime, String name) {
        this.name = name;
        this.songs = songs;
        this.createTime = createTime;
        this.number = number;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public Song[] getSongs() {
        return songs;
    }

    public void setSongs(Song[] songs) {
        this.songs = songs;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "SongList{" +
                "number='" + number + '\'' +
                ", songs=" + Arrays.toString(songs) +
                ", createTime='" + createTime + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

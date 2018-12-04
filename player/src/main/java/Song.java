public class Song {
    private int musicId;
    private String musicName;
    private int sheetId = 0;
    private String md5;
    private String path;
    private String artist;
    private int length = 0;
    public Song(){

    }
    public Song(int musicId, String musicName, int sheetId, String md5, String path, String artist) {
        this.musicId = musicId;
        this.musicName = musicName;
        this.sheetId = sheetId;
        this.md5 = md5;
        this.path = path;
    }
    public Song(String musicName, int sheetId, String md5, String path, String artist) {
        this.musicName = musicName;
        this.sheetId = sheetId;
        this.md5 = md5;
        this.path = path;
    }
    public void setArtist(String artist){
        this.artist = artist;
    }
    public String getArtist(){
        return artist;
    }
    public void setLength(int length){
        this.length = length;
    }
    public int getLength(){
        return length;
    }
    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "歌曲名称：" + musicName;
    }
}

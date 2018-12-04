public class SongList {
    private int sheetId;
    private String sheetName;
    private String createDate;
    private String owner;
    private String coverPath;

    public SongList(int sheetId, String sheetName, String createDate, String owner, String coverPath) {
        this.sheetId = sheetId;
        this.sheetName = sheetName;
        this.createDate = createDate;
        this.owner = owner;
        this.coverPath = coverPath;
    }
    public SongList(String sheetName, String createDate, String owner, String coverPath) {
        this.sheetName = sheetName;
        this.createDate = createDate;
        this.owner = owner;
        this.coverPath = coverPath;
    }

    public SongList(){

    }
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    @Override
    public String toString() {
        return "歌单名称：" + sheetName;
    }
}

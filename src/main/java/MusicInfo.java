import org.apache.commons.codec.digest.DigestUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class MusicInfo {
    public static HashMap<String, String> captureMusicInfo(String path) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {//获取歌曲的信息标签保存在hashmap内
        HashMap<String, String> musicInfo = new HashMap<String, String>();
        AudioFile file = null;
        file = AudioFileIO.read(new File(path));
        Tag tag = file.getTag();
        String md5 = DigestUtils.md5Hex(new FileInputStream(path));
        musicInfo.put("musicName",tag.getFirst(FieldKey.TITLE));
        musicInfo.put("md5",md5);
        return musicInfo;
    }
    public static Song createSong(File file) throws ReadOnlyFileException, IOException, TagException, InvalidAudioFrameException, CannotReadException { //选择歌单，默认为0
        String path = file.getAbsolutePath();
        HashMap<String, String> musicInfo = captureMusicInfo(path);
        MP3File f = (MP3File) AudioFileIO.read(new File("C:\\Users\\dell\\Desktop\\PacificRim.mp3"));
        MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
        int leng = audioHeader.getTrackLength();
        Song song = new Song();
        song.setMusicName(musicInfo.get("musicName"));
        song.setMd5(musicInfo.get("md5"));
        song.setPath(path);
        song.setLength(leng);
        return song;
    }
}

package musicplayer.service;

import musicplayer.entity.Song;
import musicplayer.entity.UserSong;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface SongService {
	
	
    public List <Song> getSongs();

    public List <Song> getSongsByGenre(Long genre);


    public void saveSong(Song theSong) throws IOException;

    public Song getSong(Long theId) throws Throwable;

    public List <UserSong> getMySongs();

    public void downLoadSong(Long songId) throws IOException;



}

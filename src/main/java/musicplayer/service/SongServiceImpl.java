package musicplayer.service;

import musicplayer.entity.*;
import musicplayer.repository.SongGenreRepository;
import musicplayer.repository.SongRepository;
import musicplayer.repository.UserSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    /**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private UserSongRepository userSongRepository;


    @Autowired
    UserService userService;

    @Autowired
    ServletContext context;
    @Autowired
    HttpServletResponse response;
    @Autowired
    private SongGenreRepository songGenreRepository;

    @Transactional
    public List <Song> getSongs() {
        return songRepository.findAll();
    }
    @Transactional
    public List <Song> getSongsByGenre(Long genre) {
        SongGenre songGenre=songGenreRepository.findById(genre);
        if(songGenre==null){
            return songRepository.findAll();
        } else {
            return songRepository.findByGenre(songGenre);
        }
    }

    @Transactional
    public void saveSong(Song theSong) throws IOException {
        MultipartFile multipartFile = theSong.getSongFile();
        if (multipartFile != null || !multipartFile.isEmpty()) {
            String relativeWebPath = "/resources/songs/";
            String fileName = context.getRealPath("/") + relativeWebPath+ multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(fileName));
        }
        theSong.setFileName(multipartFile.getOriginalFilename());
        theSong.setDownloadCount(0);
        songRepository.save(theSong);
    }

    @Transactional
    public Song getSong(Long id) throws Throwable {
    	
        return songRepository.findById(id);
        
    }
    @Transactional
    public List <UserSong> getMySongs() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user=this.userService.findByUsername(authentication.getName());
        return userSongRepository.findByUser(user);
    }
    @Transactional
    public void downLoadSong(Long songId) throws IOException {

        Song song=songRepository.findById(songId);

        //Update downLoad Count
        int currentDownloadCount=song.getDownloadCount();
        song.setDownloadCount(currentDownloadCount+1);
        songRepository.save(song);

        //Download song
        String relativeWebPath = "/resources/songs/";
        String fileName = context.getRealPath("/") + relativeWebPath+ song.getFileName();
        File downloadFile = new File(fileName);

        FileInputStream inputStream = new FileInputStream(downloadFile);
        String mimeType = context.getMimeType(fileName);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
    }

}

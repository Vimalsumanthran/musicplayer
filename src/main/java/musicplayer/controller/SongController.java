package musicplayer.controller;

import musicplayer.entity.*;
import musicplayer.exception.ResourceNotFoundException;
import musicplayer.repository.SongRepository;
import musicplayer.service.CartService;
import musicplayer.service.SongGenreService;
import musicplayer.service.SongService;
import musicplayer.service.UserService;
import musicplayer.validator.songValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongGenreService songGenreService;
    @Autowired
    private CartService cartService;

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @Autowired
    private SongRepository songRepository;
    @Autowired
    ServletContext context;

    @Autowired
    songValidator songValidator;

    /**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;



    @GetMapping("/list")
    public String listSongs(@RequestParam(name = "genre",required=false) Integer genreId,Model theModel) {
        List < Song > theSongs=new ArrayList<>();
        if (genreId != null){
            Long theGenreId = new Long(genreId);
            theSongs = this.songService.getSongsByGenre(theGenreId);
        } else {
            theSongs = this.songService.getSongs();
        }
        theModel.addAttribute("filterGenre", genreId);
        theModel.addAttribute("songs", theSongs);
        return "list-songs";
    }

    @PostMapping("/saveSong")
    public String saveSong(@ModelAttribute("song") Song theSong, BindingResult bindingResult, Model theModel) throws IOException {

        songValidator.validate(theSong,bindingResult);
        if (bindingResult.hasErrors()) {
            return "song-form";
        }
        this.songService.saveSong(theSong);
        return "redirect:/song/list";
    }

    @ModelAttribute("genreDetailsList")
    public List<SongGenre> getGenreDetailsList()
    {
        List<SongGenre> genreDetailsList = this.songGenreService.getGenres();

        return genreDetailsList;
    }

    @GetMapping("/my-songs")
    public String listMySongs(Model theModel) {

        List <UserSong> theSongs = this.songService.getMySongs();
        theModel.addAttribute("songs", theSongs);
        return "my-songs";
    }

    @GetMapping("/download")
    public void downloadSong(@RequestParam("songId") Long theId, HttpServletResponse response) throws IOException {
        this.songService.downLoadSong(theId);
    }

    @ModelAttribute("loggedUser")
    public User getLoggedUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user=this.userService.findByUsername(authentication.getName());
        return user;
    }

}

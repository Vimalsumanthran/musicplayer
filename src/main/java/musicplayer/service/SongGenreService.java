package musicplayer.service;

import musicplayer.entity.MasterGender;
import musicplayer.entity.SongGenre;
import musicplayer.repository.MasterGenderRepository;
import musicplayer.repository.SongGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SongGenreService {

    @Autowired
    private SongGenreRepository songGenreRepository;
    @Transactional
    public List<SongGenre> getGenres() {
        return songGenreRepository.findAll();
    }
}

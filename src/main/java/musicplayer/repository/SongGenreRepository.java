package musicplayer.repository;

import musicplayer.entity.Song;
import musicplayer.entity.SongGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("songGenreRepository")
public interface SongGenreRepository extends JpaRepository<SongGenre, Integer> {
    SongGenre findById(Long id);
}

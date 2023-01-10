package musicplayer.repository;

import musicplayer.entity.Role;
import musicplayer.entity.Song;
import musicplayer.entity.SongGenre;
import musicplayer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("songRepository")
public interface SongRepository extends JpaRepository<Song, Integer> {

    Song findById(Long id);

    List<Song> findByGenre(SongGenre genre);

}

package musicplayer.repository;

import musicplayer.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userSongRepository")
public interface UserSongRepository extends JpaRepository<UserSong, Integer> {
    List<UserSong> findByUser(User user);

    @Query(value = "SELECT c FROM UserSong c WHERE c.song = ?1 AND c.user=?2")
    UserSong getUserSong(Song song, User user);
}

package musicplayer.repository;

import musicplayer.entity.MasterGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("masterGenderRepository")
public interface MasterGenderRepository extends JpaRepository<MasterGender, Integer> {

}
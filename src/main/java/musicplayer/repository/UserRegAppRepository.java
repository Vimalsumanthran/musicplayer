package musicplayer.repository;

import musicplayer.entity.UserRegApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository("userRegAppRepository")
public interface UserRegAppRepository extends JpaRepository<UserRegApp, Integer> {
    List<UserRegApp> findByStatus(int status);
    UserRegApp findById(Long id);
    @Transactional
    @Modifying
    @Query("update UserRegApp c set c.status = :status where c.id = :applicationId")
    int updateApplicationStatus(@Param("status") int status,@Param("applicationId") Long applicationId);

}

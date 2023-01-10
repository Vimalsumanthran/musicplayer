package musicplayer.repository;

import musicplayer.entity.Cart;
import musicplayer.entity.User;
import musicplayer.entity.UserRegApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByIsActive(boolean isActive);

	User findByUsername(String username);
	@Transactional
	@Modifying
	@Query("update User c set c.isActive = :status where c.id = :id")
	int activateUser(@Param("status") boolean status, @Param("id") Long id);

	@Query(value = "SELECT u FROM User u WHERE u.isActive = ?1 AND u.isUser=true")
	List<User> getActiveUsers(boolean isActive);

	@Query(value = "SELECT u FROM User u WHERE u.isActive = ?1 AND u.isAdmin=true")
	List<User> getActiveAdmins(boolean isActive);
}
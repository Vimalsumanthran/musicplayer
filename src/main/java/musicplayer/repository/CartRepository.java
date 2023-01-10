package musicplayer.repository;

import musicplayer.entity.Cart;
import musicplayer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cartRepository")
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart  findByIsActive(boolean isActive);

    @Query(value = "SELECT c FROM Cart c WHERE c.isActive = ?1 AND c.user=?2")
    Cart getActiveCart(boolean isActive, User user);

}

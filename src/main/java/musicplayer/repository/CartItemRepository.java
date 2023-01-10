package musicplayer.repository;

import musicplayer.entity.Cart;
import musicplayer.entity.CartItem;
import musicplayer.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cartItemRepository")
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByCart(Cart cart);

    void deleteById(Long id);

    @Query(value = "SELECT c FROM CartItem c WHERE c.song = ?1 AND c.cart=?2")
    CartItem getCartItem(Song song, Cart cart);

}

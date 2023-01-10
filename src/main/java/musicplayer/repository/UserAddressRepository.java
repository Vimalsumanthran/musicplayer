package musicplayer.repository;

import musicplayer.entity.User;
import musicplayer.entity.UserAddress;
import musicplayer.entity.UserRegApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userAddressRepository")
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {

    List<UserAddress> findByUser(User user);

    UserAddress findById(Long id);

}

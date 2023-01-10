package musicplayer.repository;

import musicplayer.entity.PurchaseRequest;
import musicplayer.entity.Song;
import musicplayer.entity.UserRegApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("purchaseRequestRepository")
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Integer> {

    List<PurchaseRequest> findByStatus(int status);
    PurchaseRequest findById(Long id);

}

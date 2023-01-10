package musicplayer.entity;

import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "user_id",referencedColumnName="id")
    @ManyToOne(targetEntity=User.class,cascade = CascadeType.MERGE)
    private User user;

    @OneToOne(mappedBy = "cart")
    private PurchaseRequest purchaseRequest;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItem;

}

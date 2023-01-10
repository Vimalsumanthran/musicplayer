package musicplayer.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @JoinColumn(name = "cart_id",referencedColumnName="id")
    @ManyToOne(targetEntity=Cart.class,cascade = CascadeType.MERGE)
    private Cart cart;

    @JoinColumn(name = "song_id",referencedColumnName="id")
    @ManyToOne(targetEntity=Song.class,cascade = CascadeType.MERGE)
    private Song song;
}

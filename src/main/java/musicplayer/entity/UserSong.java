package musicplayer.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "user_song")
public class UserSong {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @JoinColumn(name = "user_id",referencedColumnName="id")
    @ManyToOne(targetEntity=User.class,cascade = CascadeType.MERGE)
    private User user;

    @JoinColumn(name = "song_id",referencedColumnName="id")
    @ManyToOne(targetEntity=Song.class,cascade = CascadeType.MERGE)
    private Song song;


}

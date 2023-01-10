package musicplayer.entity;

import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "genre_id",referencedColumnName="id")
    @ManyToOne(targetEntity=SongGenre.class,cascade = CascadeType.MERGE)
    private SongGenre genre;

    @Column(name = "song_name")
    private String songName;


    @Column(name = "artist")
    private String artist;

    @OneToMany(mappedBy = "song")
    private List<CartItem> cartItem;

    @OneToMany(mappedBy = "song")
    private List<UserSong> userSong;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SongGenre getGenre() {
        return genre;
    }

    public void setGenre(SongGenre genre) {
        this.genre = genre;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Column(name = "minutes")
    private String minutes;

    @Column(name = "seconds")
    private String seconds;


    @Column(name = "price")
    private Long price;

    @Column(name = "file_name")
    private String fileName;

    public MultipartFile getSongFile() {
        return songFile;
    }

    public void setSongFile(MultipartFile songFile) {
        this.songFile = songFile;
    }

    @Column(name = "download_count")
    private int downloadCount;

    @Transient
    private MultipartFile songFile;

}

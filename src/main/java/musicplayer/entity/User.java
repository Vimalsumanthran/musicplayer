package musicplayer.entity;


import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public MasterGender getGender() {
        return gender;
    }

    public void setGender(MasterGender gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<UserAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<UserAddress> addresses) {
        this.addresses = addresses;
    }

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @JoinColumn(name = "gender_id",referencedColumnName="id")
    @ManyToOne(targetEntity=MasterGender.class,cascade = CascadeType.MERGE)
    private MasterGender gender;

    @Column(name = "is_active")
    private boolean isActive;

    public boolean getIsUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public UserRegApp getUserRegApp() {
        return userRegApp;
    }

    public void setUserRegApp(UserRegApp userRegApp) {
        this.userRegApp = userRegApp;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public List<UserSong> getUserSong() {
        return userSong;
    }

    public void setUserSong(List<UserSong> userSong) {
        this.userSong = userSong;
    }

    @Column(name = "is_user")
    private boolean isUser=true;

    @Column(name = "is_admin")
    private boolean isAdmin=false;

    @Column(name = "is_super_admin")
    private boolean isSuperAdmin=false;

    @Column(name = "is_blocked")
    private boolean isBlocked=false;

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @OneToMany(mappedBy = "user")
    private List<UserAddress> addresses;

    @OneToOne(mappedBy = "user")
    private UserRegApp userRegApp;

    @OneToMany(mappedBy = "user")
    private List<Cart> cart;

    @OneToMany(mappedBy = "user")
    private List<UserSong> userSong;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }






}

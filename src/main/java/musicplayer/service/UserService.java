package musicplayer.service;

import musicplayer.entity.User;
import musicplayer.entity.UserAddress;
import musicplayer.form.UserForm;

import java.util.List;

public interface UserService {
    void save(UserForm user);

    void update(UserForm user);
    public List<User> getUsers();

    public List<UserAddress> getUserAddress(User user);

    User findByUsername(String username);

    public User getUser() throws Throwable;

    public List<User> getActiveUsers();
    public List<User> getActiveAdmins();

    public void setAsAdmin(Long id);

    public void RemoveAdmin(Long id);
    public void handleUserBlock(Long id,boolean isBlocked);


}
package musicplayer.service;

import musicplayer.entity.*;

import musicplayer.form.UserForm;
import musicplayer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserAddressRepository userAddressRepository;
    @Autowired
    private UserRegAppRepository userRegAppRepository;

    @Autowired
    private MasterGenderRepository masterGenderRepository;

    @Override
    public void save(UserForm user) {
    	
        user.getUser().setPassword(bCryptPasswordEncoder.encode(user.getUser().getPassword()));
        user.getUser().setRoles(new HashSet<Role>(roleRepository.findByName("ROLE_USER")));
        userRepository.save(user.getUser());
        user.getUserAddress1().setUser(user.getUser());
        user.getUserAddress2().setUser(user.getUser());
        userAddressRepository.save(user.getUserAddress1());
        userAddressRepository.save(user.getUserAddress2());
        UserRegApp userRegApp=new UserRegApp();
        userRegApp.setUser(user.getUser());
        userRegApp.setStatus(1);
        userRegAppRepository.save(userRegApp);
    }
    @Override
    public void update(UserForm user) {

        Long userId = user.getUser().getId();
        User updateUser= userRepository.findById(userId).get();
        updateUser.setFirstName(user.getUser().getFirstName());
        updateUser.setLastName(user.getUser().getLastName());
        updateUser.setEmail(user.getUser().getEmail());
        int userGenderId=user.getUser().getGender().getId();
        MasterGender updatedGender= masterGenderRepository.findById(userGenderId).get();
        updateUser.setGender(updatedGender);
        userRepository.save(updateUser);

        Long address1=user.getUserAddress1().getId();
        UserAddress userAddress1= userAddressRepository.findById(address1);
        userAddress1.setAddressLine1(user.getUserAddress1().getAddressLine1());
        userAddress1.setAddressLine2(user.getUserAddress1().getAddressLine2());
        userAddress1.setCity(user.getUserAddress1().getCity());
        userAddress1.setState(user.getUserAddress1().getState());
        userAddress1.setZipCode(user.getUserAddress1().getZipCode());
        userAddressRepository.save(userAddress1);

        Long address2=user.getUserAddress2().getId();
        UserAddress userAddress2= userAddressRepository.findById(address2);
        userAddress2.setAddressLine1(user.getUserAddress2().getAddressLine1());
        userAddress2.setAddressLine2(user.getUserAddress2().getAddressLine2());
        userAddress2.setCity(user.getUserAddress2().getCity());
        userAddress2.setState(user.getUserAddress2().getState());
        userAddress2.setZipCode(user.getUserAddress2().getZipCode());
        userAddressRepository.save(userAddress2);

    }


    @Transactional
    public List<User> getUsers() {
        return userRepository.findByIsActive(true);
    }

    @Transactional
    public List<User> getActiveUsers() {

        return userRepository.getActiveUsers(true);
    }

    @Transactional
    public List<User> getActiveAdmins() {

        return userRepository.getActiveAdmins(true);
    }

    @Override
    public void setAsAdmin(Long id) {
        User user= userRepository.findById(id).get();
        Set<Role> userRoles=user.getRoles();
        userRoles.add(roleRepository.findById(2L).get());
        user.setRoles(userRoles);
        user.setIsAdmin(true);
        userRepository.save(user);
    }
    @Override
    public void RemoveAdmin(Long id) {
        User user= userRepository.findById(id).get();
        Set<Role> userRoles=user.getRoles();

        Iterator<Role> itr = userRoles.iterator();
        while(itr.hasNext()){
            Role rootRole = itr.next();
            if(rootRole.getName().equals("ROLE_ADMIN")){
                itr.remove();
            }
        }

        user.setRoles(userRoles);
        user.setIsAdmin(false);
        userRepository.save(user);
    }
    @Override
    public void handleUserBlock(Long id,boolean isBlocked) {
        User user= userRepository.findById(id).get();
        user.setIsBlocked(isBlocked);
        userRepository.save(user);
    }



    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User getUser() throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =this.findByUsername(authentication.getName());
        return user;
    }

    @Transactional
    public List<UserAddress> getUserAddress(User user) {
        return userAddressRepository.findByUser(user);
    }



}
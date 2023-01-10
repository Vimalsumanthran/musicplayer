package musicplayer.service;

import musicplayer.entity.UserRegApp;
import musicplayer.repository.UserRegAppRepository;
import musicplayer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRegAppService {

    @Autowired
    private UserRegAppRepository userRegAppRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List <UserRegApp> getUserRegAppsByStatus(int status) {

        return userRegAppRepository.findByStatus(1);

    }
    @Transactional
    public void updateUserStatus(int status,Long applicationId) {
        userRegAppRepository.updateApplicationStatus(status,applicationId);
        Long userId= userRegAppRepository.findById(applicationId).getUser().getId();
        if(status==2) {
            userRepository.activateUser(true, userId);
        }
    }


}

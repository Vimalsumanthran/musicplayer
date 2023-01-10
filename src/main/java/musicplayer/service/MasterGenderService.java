package musicplayer.service;

import musicplayer.entity.MasterGender;
import musicplayer.repository.MasterGenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MasterGenderService {

    @Autowired
    private MasterGenderRepository masterGenderRepository;
    @Transactional
    public List<MasterGender> getGenders() {
        return masterGenderRepository.findAll();
    }
}

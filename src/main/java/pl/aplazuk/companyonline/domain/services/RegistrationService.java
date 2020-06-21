package pl.aplazuk.companyonline.domain.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aplazuk.companyonline.domain.dtos.RegistrationDataDTO;
import pl.aplazuk.companyonline.domain.entities.user.User;
import pl.aplazuk.companyonline.domain.repositories.UserRepository;

@Service
@Transactional @Slf4j
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegistrationDataDTO data){
        log.debug("Dane do rejestracji: {}", data);
        User user = new User();
        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setActive(true);
        user.setEmail(data.getEmail());
        user.setRole("ROLE_USER");
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        log.debug("Uzyskany obiekt użytkownika: {}", user);

        userRepository.save(user);
    }



}

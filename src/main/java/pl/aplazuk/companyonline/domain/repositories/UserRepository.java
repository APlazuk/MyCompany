package pl.aplazuk.companyonline.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.aplazuk.companyonline.domain.entities.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUsername(String username);
}

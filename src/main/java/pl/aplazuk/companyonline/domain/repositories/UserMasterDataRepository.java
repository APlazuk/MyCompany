package pl.aplazuk.companyonline.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.aplazuk.companyonline.domain.entities.user.UserMasterData;

@Repository
public interface UserMasterDataRepository extends JpaRepository<UserMasterData,Long> {
}

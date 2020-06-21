package pl.aplazuk.companyonline.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.aplazuk.companyonline.domain.entities.document.DocumentDetails;

@Repository
public interface DocumentDetailsRepository extends JpaRepository<DocumentDetails, Long> {

}

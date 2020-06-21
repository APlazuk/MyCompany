package pl.aplazuk.companyonline.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.aplazuk.companyonline.domain.entities.document.DocumentType;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

    DocumentType findOneByName(String name);
}

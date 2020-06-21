package pl.aplazuk.companyonline.domain.services.interfaces;

import pl.aplazuk.companyonline.domain.dtos.DocumentAdaptationDTO;
import pl.aplazuk.companyonline.domain.dtos.FileDTO;

import java.util.List;
import java.util.Optional;

public interface DocumentAdaptationService {

    void addDocument(DocumentAdaptationDTO document);

    void addFile(FileDTO uploadedFile);

    List<FileDTO> yourFiles();

    Optional<FileDTO> getFileById(Long fileId);

    void deleteFile(Long id);
}

package pl.aplazuk.companyonline.domain.converters;

import pl.aplazuk.companyonline.domain.dtos.FileDTO;
import pl.aplazuk.companyonline.domain.entities.document.File;

public class FileConverter {
    public static FileDTO toFileDTO(File file) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setId(file.getId());
        fileDTO.setName(file.getName());
        fileDTO.setContent(file.getContent());
        fileDTO.setContentType(file.getContentType());
        fileDTO.setCreatedOn(file.getCreatedOn());

        return fileDTO;
    }
}

package pl.aplazuk.companyonline.domain.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileDTO {

    private long id;
    private String name;
    private String contentType;
    private byte [] content;
    private LocalDateTime createdOn;


}

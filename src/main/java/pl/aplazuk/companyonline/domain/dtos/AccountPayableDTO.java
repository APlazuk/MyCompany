package pl.aplazuk.companyonline.domain.dtos;

import lombok.Data;
import org.apache.bval.extras.constraints.checkdigit.IBAN;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class AccountPayableDTO {


    private Long id;
    @NotBlank
    private String companyName;
    private Integer clientNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotBlank
    private String documentNumber;
    @NotNull
    private Double amount;
    @IBAN
    private String IBAN;
    private String status;
}

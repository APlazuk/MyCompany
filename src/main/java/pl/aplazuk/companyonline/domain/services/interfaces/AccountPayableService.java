package pl.aplazuk.companyonline.domain.services.interfaces;

import pl.aplazuk.companyonline.domain.dtos.AccountPayableDTO;

import java.util.List;

public interface AccountPayableService {

     void editCreditInvoice(AccountPayableDTO accountPayableDTO);

    List<AccountPayableDTO> yourCreditInvoices();

    AccountPayableDTO getDocumentById(Long id);

}

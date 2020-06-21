package pl.aplazuk.companyonline.domain.services.interfaces;



import pl.aplazuk.companyonline.domain.dtos.AccountReceivableDTO;

import java.util.List;

public interface AccountReceivableService {

    void editVendorInvoice(AccountReceivableDTO accountReceivableDTO);

    List<AccountReceivableDTO> yourVendorInvoices();

    AccountReceivableDTO getDocumentById(Long id);
}

package pl.aplazuk.companyonline.domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.aplazuk.companyonline.domain.dtos.AccountReceivableDTO;
import pl.aplazuk.companyonline.domain.dtos.DocumentAdaptationDTO;
import pl.aplazuk.companyonline.domain.entities.document.DocumentStatus;
import pl.aplazuk.companyonline.domain.entities.document.DocumentType;
import pl.aplazuk.companyonline.domain.repositories.DocumentTypeRepository;
import pl.aplazuk.companyonline.domain.services.interfaces.AccountReceivableService;
import pl.aplazuk.companyonline.domain.services.interfaces.DocumentAdaptationService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ar")
public class AccountReceivableController {

    private final AccountReceivableService accountReceivableService;
    private final DocumentAdaptationService documentAdaptationService;
    private final DocumentTypeRepository documentTypeRepository;

    public AccountReceivableController(AccountReceivableService accountReceivableService, DocumentAdaptationService documentAdaptationService, DocumentTypeRepository documentTypeRepository) {
        this.accountReceivableService = accountReceivableService;
        this.documentAdaptationService = documentAdaptationService;
        this.documentTypeRepository = documentTypeRepository;
    }


    @ModelAttribute("vendorInvoices")
    public List<AccountReceivableDTO> vendorInvoices() {
        return accountReceivableService.yourVendorInvoices();
    }

    @GetMapping
    public String prepareMainPage() {
        return "invoices/AR/mainAR";
    }

    @GetMapping("/addDocument")
    public String addNewDocument(Model model) {
        model.addAttribute("document", new DocumentAdaptationDTO());

        return "invoices/AR/formAddInvoiceAR";
    }

    @PostMapping("/addDocument")
    public String distributeNewDocument(@Valid @ModelAttribute("document") DocumentAdaptationDTO document, BindingResult result) {
        if (result.hasErrors()) {
            return "invoices/AR/formAddInvoiceAR";
        }
        documentAdaptationService.addDocument(document);

        return "redirect:/ar";
    }


    @GetMapping("/edit/{id}")
    public String editDocument(Model model, @PathVariable Long id) {
        AccountReceivableDTO editedDocument = accountReceivableService.getDocumentById(id);
        model.addAttribute("editedDocument", editedDocument);

        return "invoices/AR/formEditInvoiceAR";
    }

    @PostMapping("/edit/{id}")
    public String distributeEditedDocument(@Valid @ModelAttribute("editedDocument") AccountReceivableDTO editedDocument, BindingResult result) {
        if (result.hasErrors()) {
            return "invoices/AR/formEditInvoiceAR";
        }
        accountReceivableService.editVendorInvoice(editedDocument);

        return "redirect:/ar";
    }

    @ModelAttribute("statuses")
    public List<DocumentStatus> statuses() {
        return Arrays.asList(DocumentStatus.values());
    }

    @ModelAttribute("types")
    public List<DocumentType> getType() {
        return documentTypeRepository.findAll();
    }

}
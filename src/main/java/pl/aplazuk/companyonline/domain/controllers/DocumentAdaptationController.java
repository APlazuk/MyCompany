package pl.aplazuk.companyonline.domain.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.aplazuk.companyonline.domain.dtos.DocumentAdaptationDTO;


import pl.aplazuk.companyonline.domain.dtos.FileDTO;
import pl.aplazuk.companyonline.domain.entities.document.DocumentStatus;
import pl.aplazuk.companyonline.domain.entities.document.DocumentType;
import pl.aplazuk.companyonline.domain.entities.document.File;
import pl.aplazuk.companyonline.domain.repositories.DocumentTypeRepository;
import pl.aplazuk.companyonline.domain.repositories.FileRepository;
import pl.aplazuk.companyonline.domain.services.interfaces.DocumentAdaptationService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/unspecified-document")
public class DocumentAdaptationController {


    private final DocumentAdaptationService documentAdaptationService;
    private final DocumentTypeRepository documentTypeRepository;
    private final FileRepository fileRepository;

    public DocumentAdaptationController(DocumentAdaptationService documentAdaptationService, DocumentTypeRepository documentTypeRepository, FileRepository fileRepository) {
        this.documentAdaptationService = documentAdaptationService;
        this.documentTypeRepository = documentTypeRepository;
        this.fileRepository = fileRepository;
    }

    @ModelAttribute("yourFiles")
    public List<FileDTO> yourFiles(){
       return documentAdaptationService.yourFiles();
    }

    @GetMapping("")
    public String prepareMainPage(){
        return "documentAdaptation/main";
    }


    @GetMapping("/addDocument/{fileId}")
    public String addNewDocument(Model model, @PathVariable Long fileId) {
        Model document = model.addAttribute("document", new DocumentAdaptationDTO());

        return "documentAdaptation/form";
        //jak przekazać atrybuty z modelu?
    }

    @GetMapping("/addDocument/{fileId}/file")
    public ResponseEntity<?> getFile(Model model, @PathVariable Long fileId) throws IOException {
        Model document = model.addAttribute("document", new DocumentAdaptationDTO());

        Optional<FileDTO> optionalFile = documentAdaptationService.getFileById(fileId);

        if(optionalFile.isPresent()) {
            FileDTO file = optionalFile.get();
            //klasa javowa która reprezentuje zasób zbudowany z tablicy byteów
            ByteArrayResource resource = new ByteArrayResource(file.getContent());
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.valueOf(file.getContentType()))
                    .header("Content-Disposition", "filename =" + file.getName())
                    .body(resource);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addDocument")
    public String distributeNewDocument(@Valid @ModelAttribute("document") DocumentAdaptationDTO document, BindingResult result) {
        if (result.hasErrors()) {
            return "documentAdaptation/form";
        }
        log.debug("Zapisujemy dokument: {}",document);
        documentAdaptationService.addDocument(document);

        return "redirect:/";
    }

    @GetMapping ("/confirm/delete/{id}")
    public String deleteFile(@PathVariable long id, Model model){

        File fileToDelete = fileRepository.findFileById(id);
        log.debug("Wywołano plik o id:{}", fileRepository.findFileById(id));

        model.addAttribute("fileToDelete",fileToDelete);

        return "documentAdaptation/delete";
    }

    @RequestMapping("/delete/{id}")
    public String deleteFile(@PathVariable long id) {
        documentAdaptationService.deleteFile(id);
        log.debug("Usunięto plik o id: {}", id);

        return "redirect:/";
    }

    @ModelAttribute("types")
    public List<DocumentType> getType() {
        return documentTypeRepository.findAll();
    }

    @ModelAttribute("statuses")
    public List<DocumentStatus> statuses() {
       return Arrays.asList(DocumentStatus.values());
    }
}

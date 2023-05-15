package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.apiModel.request.CredentialRequest;
import com.udacity.jwdnd.course1.cloudstorage.apiModel.request.NoteRequest;
import com.udacity.jwdnd.course1.cloudstorage.apiModel.response.CredentialResponse;
import com.udacity.jwdnd.course1.cloudstorage.apiModel.response.NoteResponse;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileManagementService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final FileManagementService fileManagementService;

    public HomeController(NoteService noteService, CredentialService credentialService, FileManagementService fileManagementService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileManagementService = fileManagementService;
    }

    @GetMapping()
    public ModelAndView homePageView(Authentication authentication, Model model){
        if(authentication == null || !authentication.isAuthenticated())
            return new ModelAndView("redirect:/login");

        List<Note> notes = noteService.getUserNotes(authentication);

        List<Credential> credentials = credentialService.getUserCredentials(authentication);
        List<File> files = fileManagementService.getUserFiles(authentication);

        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);
        model.addAttribute("files", files);

        return new ModelAndView("home");
    }


    @PostMapping("add-note")
    public ModelAndView addNoteAction(Authentication authentication, @ModelAttribute NoteRequest noteRequest, Model model){
        NoteResponse noteResponse = null;
        if(noteRequest.getNoteId() != null)
            noteResponse = noteService.updateNote(authentication, noteRequest);
        else
            noteResponse = noteService.addNote(authentication, noteRequest);

        model.addAttribute("noteResponse", noteResponse);
        return new ModelAndView("redirect:/home");
    }

    @PostMapping("add-credential")
    public ModelAndView addCredentialAction(Authentication authentication, @ModelAttribute CredentialRequest credentialRequest, Model model){
        CredentialResponse credentialResponse = null;

        if(credentialRequest.getCredentialId() != null)
            credentialResponse = credentialService.updateCredential(authentication, credentialRequest);
        else
            credentialResponse = credentialService.addCredential(authentication, credentialRequest);

        model.addAttribute("credentialResponse", credentialResponse);
        return new ModelAndView("redirect:/home");
    }

    @PostMapping( value ="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView uploadFile(Authentication authentication, @RequestPart("file") MultipartFile file){
        logger.info("File name: " + file.getOriginalFilename());
        fileManagementService.uploadFile(authentication, file);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("note/{id}/delete")
    public ModelAndView deleteNote(Authentication authentication, @PathVariable Integer id){
        noteService.deleteNote(authentication, id);

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("credential/{id}/delete")
    public ModelAndView deleteCredential(Authentication authentication, @PathVariable Integer id){
        credentialService.deleteCredential(authentication, id);

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("file/{id}/delete")
    public ModelAndView deleteFile(Authentication authentication, @PathVariable Integer id){
        fileManagementService.deleteFile(authentication, id);

        return new ModelAndView("redirect:/home");
    }



}

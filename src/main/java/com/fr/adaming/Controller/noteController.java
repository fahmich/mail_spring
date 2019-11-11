package com.fr.adaming.Controller;


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.fr.adaming.dbRepo.NoteRepository;
import com.fr.adaming.dbRepo.NotebookRepository;
import com.fr.adaming.dto.Mapper;
import com.fr.adaming.dto.NoteViewModel;
import com.fr.adaming.model.Note;
import com.fr.adaming.model.Notebook;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/*
Requests can be tested using the built in HTTP Rest Client. Use the
examples found in 'noteit.http' file.
 */

@RestController
@RequestMapping("/api/notes")
@CrossOrigin("*")
public class noteController {
    private NoteRepository noteRepository;
    private NotebookRepository notebookRepository;
    private Mapper mapper;

    public noteController(NoteRepository noteRepository, NotebookRepository notebookRepository, Mapper mapper) {
        this.noteRepository = noteRepository;
        this.notebookRepository = notebookRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<NoteViewModel> all() {
        List<Note> notes = this.noteRepository.findAll();

        // map from entity to view model
        List<NoteViewModel> notesViewModel = notes.stream()
                .map(note -> this.mapper.convertToNoteViewModel(note))
                .collect(Collectors.toList());

        return notesViewModel;
    }

    @GetMapping("/byId/{id}")
    public NoteViewModel byId(@PathVariable Long id) {
        Note note = this.noteRepository.findById(id).orElse(null);

        if (note == null) {
            throw new EntityNotFoundException();
        }

        NoteViewModel noteViewModel = this.mapper.convertToNoteViewModel(note);

        return noteViewModel;
    }

    @GetMapping("/byNotebook/{notebookId}")
    public List<NoteViewModel> byNotebook(@PathVariable Long notebookId) {
        List<Note> notes = new ArrayList<>();

        Optional<Notebook> notebook = this.notebookRepository.findById(notebookId);
        if (notebook.isPresent()) {
            notes = this.noteRepository.findAllByNotebook(notebook.get());
        }

        // map to note view model
        List<NoteViewModel> notesViewModel = notes.stream()
                .map(note -> this.mapper.convertToNoteViewModel(note))
                .collect(Collectors.toList());

        return notesViewModel;
    }

    @PostMapping
    public Note save(@RequestBody NoteViewModel noteCreateViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Note noteEntity = this.mapper.convertToNoteEntity(noteCreateViewModel);

        // save note instance to db
        this.noteRepository.save(noteEntity);

        return noteEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.noteRepository.deleteById(id);
    }
}
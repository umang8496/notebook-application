package com.app.notebook.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.notebook.Mapper;
import com.app.notebook.model.Note;
import com.app.notebook.model.Notebook;
import com.app.notebook.service.NoteService;
import com.app.notebook.service.NotebookService;
import com.app.notebook.viewmodel.NoteViewModel;

@RestController
@RequestMapping("/api/v1/notes")
@CrossOrigin
public class NoteController {
	private NoteService noteService;
	private NotebookService notebookService;
	private Mapper mapper;

	public NoteController(NoteService noteService, NotebookService notebookService, Mapper mapper) {
		this.noteService = noteService;
		this.notebookService = notebookService;
		this.mapper = mapper;
	}

	@GetMapping("/all")
	public List<NoteViewModel> fetchAll() {
		List<Note> listOfNote = this.noteService.fetchAll();
		List<NoteViewModel> listOfNotesViewModel = listOfNote.stream()
				.map(note -> this.mapper.convertToNoteViewModel(note))
				.collect(Collectors.toList());
		return listOfNotesViewModel;
	}

	@GetMapping("/count")
	public Long count() {
		return this.noteService.count();
	}

	@GetMapping("/byId/{id}")
	public NoteViewModel findNoteById(@PathVariable String id) {
		if (id == null) {
			throw new EntityNotFoundException();
		}
		
		Note note = this.noteService.getNoteById(id);
		if (note == null) {
			throw new EntityNotFoundException();
		}
		
		NoteViewModel noteViewModel = this.mapper.convertToNoteViewModel(note);
		return noteViewModel;
	}

	@GetMapping("/byNotebook/{notebookId}")
	public List<NoteViewModel> byNotebook(@PathVariable String notebookId) {
		List<Note> listOfnote = new ArrayList<Note>();
		Optional<Notebook> notebook = this.notebookService.findById(notebookId);
		if (notebook.isPresent()) {
			listOfnote = this.noteService.findAllByNotebook(notebook.get());
		}

		List<NoteViewModel> listOfNoteViewModel = listOfnote.stream()
				.map(note -> this.mapper.convertToNoteViewModel(note))
				.collect(Collectors.toList());
		return listOfNoteViewModel;
	}

	@PostMapping
	public Note save(@RequestBody NoteViewModel noteCreateViewModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidationException();
		}
		
		Note noteEntity = this.mapper.convertToNoteEntity(noteCreateViewModel);
		this.noteService.saveNote(noteEntity);
		return noteEntity;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		if (id == null) {
			throw new EntityNotFoundException();
		}
		
		this.noteService.deleteById(id);
	}
}

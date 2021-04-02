package com.app.notebook.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.notebook.model.Note;
import com.app.notebook.service.NoteService;

@RestController
@RequestMapping("/api/v1/notes")
@CrossOrigin
public class NoteController {
	@Autowired
	private NoteService noteService;

	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}

	@GetMapping("/all")
	public List<Note> fetchAll() {
		return this.noteService.fetchAll();
	}

	@GetMapping("/count")
	public Long count() {
		return this.noteService.count();
	}
	
	@GetMapping("/byId/{id}")
	public Note findNoteById(@PathVariable String id) {
		if (id == null) {
			throw new EntityNotFoundException();
		} else {
			Note note = this.noteService.getNoteById(id);
			if (note == null) {
				throw new EntityNotFoundException();
			}
			return  note;
		}
	}
}

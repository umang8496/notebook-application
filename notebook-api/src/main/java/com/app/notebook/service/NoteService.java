package com.app.notebook.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.notebook.model.Note;
import com.app.notebook.model.Notebook;
import com.app.notebook.repository.NoteRepository;

@Service
public class NoteService {
	@Autowired
	private NoteRepository noteRepository;

	public NoteService(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	public List<Note> fetchAll() {
		return this.noteRepository.findAll();
	}

	public Long count() {
		return this.noteRepository.count();
	}

	public void saveNote(Note note) {
		this.noteRepository.save(note);
	}

	public void saveAllNotes(List<Note> notes) {
		this.noteRepository.saveAll(notes);
	}
	
	public Note getNoteById(String id) {
		Optional<Note> note = this.noteRepository.findById(UUID.fromString(id));
		return note.get();
	}
	
	public void deleteById(String id) {
		this.noteRepository.deleteById(UUID.fromString(id));
	}
	
	public List<Note> findAllByNotebook(Notebook notebook) {
		return noteRepository.findAllByNotebook(notebook);
	}
}

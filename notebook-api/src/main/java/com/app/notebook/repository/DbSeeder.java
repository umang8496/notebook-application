package com.app.notebook.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.app.notebook.model.Note;
import com.app.notebook.model.Notebook;

@Component
@ConditionalOnProperty(name = "notebook.db.recreate", havingValue = "true")
public class DbSeeder implements CommandLineRunner {
	private NotebookRepository notebookRepository;
	private NoteRepository noteRepository;

	public DbSeeder(NotebookRepository notebookRepository, NoteRepository noteRepository) {
		this.notebookRepository = notebookRepository;
		this.noteRepository = noteRepository;
	}

	@Override
	public void run(String... args) {
		System.out.println("Initializing database");
		
		// Remove all existing entities
		this.notebookRepository.deleteAll();
		this.noteRepository.deleteAll();

		// Save a default notebook
		Notebook defaultNotebook = new Notebook("Default");
		this.notebookRepository.save(defaultNotebook);

		Notebook quotesNotebook = new Notebook("Quotes");
		this.notebookRepository.save(quotesNotebook);

		// Save the welcome note
		Note note = new Note("Hello", "Welcome to Notebook App", defaultNotebook);
		this.noteRepository.save(note);

		// Save a quote note
		Note quoteNote = new Note("Latin Quote", "Carpe Diem", quotesNotebook);
		this.noteRepository.save(quoteNote);

		System.out.println("Initialized database");
	}
}

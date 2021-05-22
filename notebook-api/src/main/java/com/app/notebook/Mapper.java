package com.app.notebook;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.app.notebook.model.Note;
import com.app.notebook.model.Notebook;
import com.app.notebook.repository.NotebookRepository;
import com.app.notebook.viewmodel.NoteViewModel;
import com.app.notebook.viewmodel.NotebookViewModel;

@Component
public class Mapper {
	private NotebookRepository notebookRepository;

	public Mapper(NotebookRepository notebookRepository) {
		this.notebookRepository = notebookRepository;
	}

	public NoteViewModel convertToNoteViewModel(Note entity) {
		NoteViewModel viewModel = new NoteViewModel();
		viewModel.setTitle(entity.getNoteName());
		viewModel.setId(entity.getNoteId().toString());
		viewModel.setLastModifiedOn(entity.getLastModifiedOn());
		viewModel.setText(entity.getNoteContent());
		viewModel.setNotebookId(entity.getNotebook().getNotebookId().toString());

		return viewModel;
	}

	public Note convertToNoteEntity(NoteViewModel noteViewModel) {
		UUID noteId = null;
		if (noteViewModel.getId() != null) {
			noteId = UUID.fromString(noteViewModel.getId());
		} else {
			noteId = UUID. randomUUID();
		}
		
		Notebook notebook = this.notebookRepository.findById(UUID.fromString(noteViewModel.getNotebookId())).get();
		Note entity = new Note(noteId, noteViewModel.getTitle(), noteViewModel.getText(), notebook);
		return entity;
	}

	public NotebookViewModel convertToNotebookViewModel(Notebook entity) {
		NotebookViewModel viewModel = new NotebookViewModel();
		viewModel.setId(entity.getNotebookId().toString());
		viewModel.setName(entity.getNotebookName());
		viewModel.setNbNotes(entity.getNotes().size());

		return viewModel;
	}

	public Notebook convertToNotebookEntity(NotebookViewModel notebookViewModel) {
		UUID notebookId = null;
		if (notebookViewModel.getId() != null) {
			notebookId = UUID.fromString(notebookViewModel.getId());
		} else {
			notebookId = UUID. randomUUID();
		}
		
		Notebook entity = new Notebook(notebookId, notebookViewModel.getName());
		return entity;
	}
}

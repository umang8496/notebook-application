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

	public Note convertToNoteEntity(NoteViewModel viewModel) {
		Notebook notebook = this.notebookRepository.findById(UUID.fromString(viewModel.getNotebookId())).get();
		Note entity = new Note(UUID.fromString(viewModel.getId()), viewModel.getTitle(), viewModel.getText(), notebook);

		return entity;
	}

	public NotebookViewModel convertToNotebookViewModel(Notebook entity) {
		NotebookViewModel viewModel = new NotebookViewModel();
		viewModel.setId(entity.getNotebookId().toString());
		viewModel.setName(entity.getNotebookName());
		viewModel.setNbNotes(entity.getNotes().size());

		return viewModel;
	}

	public Notebook convertToNotebookEntity(NotebookViewModel viewModel) {
		Notebook entity = new Notebook(UUID.fromString(viewModel.getId()), viewModel.getName());
		return entity;
	}
}

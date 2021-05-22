package com.app.notebook.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.notebook.model.Notebook;
import com.app.notebook.repository.NotebookRepository;

@Service
public class NotebookService {
	@Autowired
	private NotebookRepository notebookRepository;

	public NotebookService(NotebookRepository notebookRepository) {
		this.notebookRepository = notebookRepository;
	}

	public List<Notebook> fetchAll() {
		return notebookRepository.findAll();
	}

	public Long count() {
		return notebookRepository.count();
	}

	public void saveNotebook(Notebook notebook) {
		this.notebookRepository.save(notebook);
	}
	
	public Optional<Notebook> findById(String notebookId) {
		return notebookRepository.findById(UUID.fromString(notebookId));
	}
	
	public void deleteById(String id) {
		this.notebookRepository.deleteById(UUID.fromString(id));
	}
}

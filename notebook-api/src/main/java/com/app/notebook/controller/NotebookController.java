package com.app.notebook.controller;

import java.util.List;
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
import com.app.notebook.model.Notebook;
import com.app.notebook.service.NotebookService;
import com.app.notebook.viewmodel.NotebookViewModel;

@RestController
@RequestMapping("/api/v1/notebooks")
@CrossOrigin
public class NotebookController {
	private NotebookService notebookService;
	private Mapper mapper;

	public NotebookController(NotebookService notebookService, Mapper mapper) {
		this.notebookService = notebookService;
		this.mapper = mapper;
	}

	@GetMapping("/all")
	public List<NotebookViewModel> fetchAll() {
		List<Notebook> listOfNotebook = this.notebookService.fetchAll();
		List<NotebookViewModel> listOfNotebookViewModel = listOfNotebook.stream()
				.map(notebook -> this.mapper.convertToNotebookViewModel(notebook))
				.collect(Collectors.toList());
		return listOfNotebookViewModel;
	}

	@GetMapping("/count")
	public Long count() {
		return this.notebookService.count();
	}
	
	@PostMapping
	public Notebook save(@RequestBody NotebookViewModel notebookViewModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidationException();
		}

		Notebook notebookEntity = this.mapper.convertToNotebookEntity(notebookViewModel);
		this.notebookService.saveNotebook(notebookEntity);
		return notebookEntity;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		if (id == null) {
			throw new EntityNotFoundException();
		}
		
		this.notebookService.deleteById(id);
	}
}

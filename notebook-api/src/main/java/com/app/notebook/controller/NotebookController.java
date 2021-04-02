package com.app.notebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.notebook.model.Notebook;
import com.app.notebook.service.NotebookService;

@RestController
@RequestMapping("/api/v1/notebooks")
@CrossOrigin
public class NotebookController {
	@Autowired
	private NotebookService notebookService;

	public NotebookController(NotebookService notebookService) {
		this.notebookService = notebookService;
	}

	@GetMapping("/all")
	public List<Notebook> fetchAll() {
		return this.notebookService.fetchAll();
	}

	@GetMapping("/count")
	public Long count() {
		return this.notebookService.count();
	}
}

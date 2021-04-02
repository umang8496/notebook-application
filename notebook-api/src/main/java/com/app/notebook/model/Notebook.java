package com.app.notebook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Notebook")
public class Notebook {
	@Id
	@Column(name = "notebookId", nullable = false)
	private UUID notebookId;

	@Column(name = "notebookName", nullable = false, length = 64)
	private String notebookName;

	@Column(name = "notes")
	@OneToMany(mappedBy = "notebook", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Note> notes;

	public Notebook() {
		this.notebookId = UUID.randomUUID();
		this.notes = new ArrayList<>();
	}

	public Notebook(String notebookName) {
		this();
		this.notebookName = notebookName;
	}

	public Notebook(UUID notebookId, String notebookName) {
		this();
		if (notebookId == null) {
			this.notebookId = UUID.randomUUID();
		} else {
			this.notebookId = notebookId;
		}
		this.notebookName = notebookName;
	}

	public UUID getNotebookId() {
		return this.notebookId;
	}

	public String getNotebookName() {
		return this.notebookName;
	}

	public void setNotebookName(String notebookName) {
		this.notebookName = notebookName;
	}

	public List<Note> getNotes() {
		return this.notes;
	}

	public int getNumberOfNotes() {
		return this.notes.size();
	}
}

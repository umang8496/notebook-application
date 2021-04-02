package com.app.notebook.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Note")
public class Note {
	@Id
	@Column(name = "noteId", nullable = false)
	private UUID noteId;

	@Column(name = "noteName", nullable = false, length = 64)
	private String noteName;

	@Column(name = "noteContent", nullable = false, length = 256)
	private String noteContent;

	@ManyToOne(fetch = FetchType.EAGER)
	private Notebook notebook;

	@Column(name = "lastModifiedOn")
	private Date lastModifiedOn;

	public Note() {
		this.noteId = UUID.randomUUID();
		this.lastModifiedOn = new Date();
	}

	public Note(String noteName, String noteContent, Notebook notebook) {
		this.noteId = UUID.randomUUID();
		this.lastModifiedOn = new Date();
		this.noteName = noteName;
		this.notebook = notebook;
		this.noteContent = noteContent;
	}

	public Note(UUID noteId, String noteName, String noteContent, Notebook notebook) {
		this.noteId = noteId;
		this.lastModifiedOn = new Date();
		this.noteName = noteName;
		this.notebook = notebook;
		this.noteContent = noteContent;
	}

	public UUID getNoteId() {
		return this.noteId;
	}

	public String getNoteName() {
		return this.noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public String getNoteContent() {
		return this.noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public Notebook getNotebook() {
		return this.notebook;
	}

	public String getNotebookId() {
		return this.notebook.getNotebookId().toString();
	}

	public Date getLastModifiedOn() {
		return this.lastModifiedOn;
	}

	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
}

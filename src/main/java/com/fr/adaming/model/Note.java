package com.fr.adaming.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String text;
	private Date lastModifiedOn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference 
	private Notebook notebook;

	
	public Note() {
		super();
	}


	public Note(Long id, String title, String text, Date lastModifiedOn, Notebook notebook) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.lastModifiedOn = lastModifiedOn;
		this.notebook = notebook;
	}
	public Long getId() {
		return id;
	}


	public Note(String title, String text, Notebook notebook) {
	this.title = title;
		this.text = text;
		this.notebook = notebook;
	}

	public Note(Long id, String title, String text, Notebook notebook) {
		this(title, text, notebook);
		if (id != null) {
			this.id = id;
		}
	}
	

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}

	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

	public Notebook getNotebook() {
		return notebook;
	}

	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}

}

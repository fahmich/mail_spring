package com.fr.adaming.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Notebook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Note> notes;

	
	public Notebook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notebook(Long id, String name, List<Note> notes) {
		super();
		this.id = id;
		this.name = name;
		this.notes = notes;
	}
	  public Notebook(Long id, String name) {
	        this();
	        if (id != null) {
	            this.id = id;
	        }
	        this.name = name;
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public int nbNotes() {
		return this.notes.size();

	}

}

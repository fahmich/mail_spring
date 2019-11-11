package com.fr.adaming.dto;

import javax.validation.constraints.NotNull;

public class NotebookViewModel {
	private Long id;
	@NotNull
	private String name;
	private int nbNotes;

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

	public int getNbNotes() {
		return nbNotes;
	}

	public void setNbNotes(int nbNotes) {
		this.nbNotes = nbNotes;
	}

}

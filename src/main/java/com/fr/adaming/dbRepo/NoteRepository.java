package com.fr.adaming.dbRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.model.Note;
import com.fr.adaming.model.Notebook;
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	List<Note> findAllByNotebook(Notebook notebook);

}

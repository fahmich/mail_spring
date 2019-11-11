package com.fr.adaming.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.fr.adaming.dbRepo.NotebookRepository;
import com.fr.adaming.dto.Mapper;
import com.fr.adaming.dto.NotebookViewModel;
import com.fr.adaming.model.Notebook;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin("*")
public class NotebookController {
	private NotebookRepository notebookRepository;
	private Mapper mapper;

	public NotebookController(NotebookRepository notebookRepository, Mapper mapper) {
		this.notebookRepository = notebookRepository;
		this.mapper = mapper;
	}

	@GetMapping("/all")
	public List<Notebook> all() {
		List<Notebook> allCategories = this.notebookRepository.findAll();
		return allCategories;
	}

	@PostMapping("/create")
	public Notebook save(@RequestBody NotebookViewModel notebookViewModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidationException();
		}

		Notebook notebookEntity = this.mapper.convertToNotebookEntity(notebookViewModel);

		// save notebookEntity instance to db
		this.notebookRepository.save(notebookEntity);

		return notebookEntity;
	}

	@DeleteMapping("/{id}")
	public List<Notebook> delete(@PathVariable Long id) {
		this.notebookRepository.deleteById(id);
		List<Notebook> allCategories = this.notebookRepository.findAll();
		return allCategories;
	}

	@GetMapping("/getByname/{name}")
	public List<Notebook> getListeDesNoteBooksByName(@PathVariable String name) {
		List<Notebook> allnoteBooks = this.notebookRepository.getnotebookbynamed(name);
		return allnoteBooks;
	}
}

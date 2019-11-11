package com.fr.adaming.dbRepo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.model.Notebook;


@Repository
public interface NotebookRepository extends JpaRepository<Notebook, Long> {
	@Query(value="SELECT * FROM notebook where name like %:name%",nativeQuery=true)
	List<Notebook> getnotebookbynamed(@Param("name") String name);

}

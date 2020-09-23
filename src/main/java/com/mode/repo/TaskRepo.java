package com.mode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mode.entities.Task;

public interface TaskRepo extends JpaRepository<Task, Long>{
	
	@Query("select t from Task t where t.user.id_user like :x")
	public List<Task> findTasksByIdUser(@Param("x") Long id_user);

	
	@Query("select t from Task t where t.label like :x and t.user.id_user like :y")
	public List<Task> chercher(@Param("x")String string, @Param("y") Long idUser); 



}

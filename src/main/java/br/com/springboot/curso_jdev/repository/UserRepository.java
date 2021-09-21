package br.com.springboot.curso_jdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso_jdev.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	@Query(value="select u from User u where upper(trim(u.name)) like %?1%")
	List<User> getByName(String name);

}

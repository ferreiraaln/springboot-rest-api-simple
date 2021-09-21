package br.com.springboot.curso_jdev.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev.dto.UserDTO;
import br.com.springboot.curso_jdev.exception.UserException;
import br.com.springboot.curso_jdev.model.User;
import br.com.springboot.curso_jdev.services.UserService;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
  
    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO userDTO) {
    	User userResponse =  this.userService.create(userDTO.transformToObject());
		return new ResponseEntity<>(userDTO.transformToDTO(userResponse), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
    	return ResponseEntity.ok(this.userService.findAll());
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<Optional<UserDTO>> getByID(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.userService.getById(id));
	}
    
    @GetMapping("/name/{name}")
    public ResponseEntity<List<UserDTO>> getByName(@PathVariable("name") String name){
    	List<UserDTO> user = userService.getByName(name.toUpperCase());
    	return ResponseEntity.ok(user);
    }
    
	@DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
    	this.userService.deleteById(id);
    	return "Usuário " + id + " deletado.";
    }
    
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ExceptionResponse> handleException(UserException userException) {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse("Erro ao salvar usuário.")
        );
    }
    
}

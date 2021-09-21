package br.com.springboot.curso_jdev.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import br.com.springboot.curso_jdev.model.User;

public class UserDTO {

	@NotBlank(message="{user.name.not.blank}")
	private String name;
	
	@PositiveOrZero(message ="{user.age.positiveorzero}")
	@Min(value = 18, message = "{user.age.min}")
	private int age;
	
	public User transformToObject(){
	  return new User(name, age);
	}
	
	public UserDTO transformToDTO(User user){
		this.name = user.getName();
		this.age = user.getAge();
		return this;
	}
	
	public List<UserDTO> transformListUserToListUserDTO(List<User> user){
		List<UserDTO> userDTO = new ArrayList<>();
		for(User u : user) {
			UserDTO uDTO = new UserDTO();
			uDTO.transformToDTO(u);
			userDTO.add(uDTO);
		}
		
		return userDTO;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}

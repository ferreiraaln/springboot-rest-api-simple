package br.com.springboot.curso_jdev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.curso_jdev.dto.UserDTO;
import br.com.springboot.curso_jdev.exception.UserException;
import br.com.springboot.curso_jdev.model.User;
import br.com.springboot.curso_jdev.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	private List<UserDTO> userDTO;
	
	public User create(User user) {
		try {
			return this.userRepository.save(user);
		} catch (UserException userException) {
			throw userException;
		}
	}
	
	public List<User> findAll() {
		try {
			return this.userRepository.findAll();
		} catch (UserException userException) {
			throw userException;
		}
	}
	
	public List<UserDTO> getByName(String name) {
		try {
			List<User> user = this.userRepository.getByName(name);
			UserDTO userDTO = new UserDTO();
			return userDTO.transformListUserToListUserDTO(user);
		} catch (UserException userException) {
			throw userException;
		}
	}
	
	public Optional<UserDTO> getById(Long id) {
		Optional<User> user = this.userRepository.findById(id);
		UserDTO userDTO = new UserDTO();
		return Optional.ofNullable(userDTO.transformToDTO(user.get()));
	}
	
	public void deleteById(Long id) {
		try {
			 this.userRepository.deleteById(id);
		} catch (UserException userException) {
			throw userException;
		}
	}
}

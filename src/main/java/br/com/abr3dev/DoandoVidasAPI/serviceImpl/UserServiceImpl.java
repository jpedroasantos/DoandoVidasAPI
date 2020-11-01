package br.com.abr3dev.DoandoVidasAPI.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.abr3dev.DoandoVidasAPI.models.User;
import br.com.abr3dev.DoandoVidasAPI.repository.UserRepository;
import br.com.abr3dev.DoandoVidasAPI.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public User register(User user) {
		return repository.save(user);
	}

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}
	
	@Override
	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}
	
	@Override
	public void deleteById(Long userId) {
		repository.deleteById(userId);
	}

	@Override
	public Optional<User> findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	
	
}

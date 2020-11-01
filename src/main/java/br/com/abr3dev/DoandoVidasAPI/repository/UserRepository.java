package br.com.abr3dev.DoandoVidasAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.abr3dev.DoandoVidasAPI.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmailAndPassword(String email, String password);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByCpf(String cpf);

}

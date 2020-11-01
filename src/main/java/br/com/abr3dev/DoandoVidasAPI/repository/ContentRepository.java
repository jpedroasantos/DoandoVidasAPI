package br.com.abr3dev.DoandoVidasAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.abr3dev.DoandoVidasAPI.models.Content;

public interface ContentRepository extends JpaRepository<Content, Long> {
	
}

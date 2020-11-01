package br.com.abr3dev.DoandoVidasAPI.service;

import java.util.Optional;

import br.com.abr3dev.DoandoVidasAPI.models.Content;

public interface ContentService {
	
	Content register(Content content);
	
	Optional<Content> findById(Long id);
	
	void deleteById(Long id);
	
}

package br.com.abr3dev.DoandoVidasAPI.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.abr3dev.DoandoVidasAPI.models.Content;
import br.com.abr3dev.DoandoVidasAPI.repository.ContentRepository;
import br.com.abr3dev.DoandoVidasAPI.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private ContentRepository repository;
	
	@Override
	public Content register(Content content) {
		return repository.save(content);
	}

	@Override
	public Optional<Content> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}

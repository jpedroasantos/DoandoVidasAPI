package br.com.abr3dev.DoandoVidasAPI.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.abr3dev.DoandoVidasAPI.dtos.ContentDto;
import br.com.abr3dev.DoandoVidasAPI.models.Content;
import br.com.abr3dev.DoandoVidasAPI.response.Response;
import br.com.abr3dev.DoandoVidasAPI.service.ContentService;

@RestController
@RequestMapping("api/doandovidas")
@CrossOrigin(origins = "*")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@GetMapping("/content/{id}")
	public ResponseEntity<Response<ContentDto>> search (@PathVariable (value = "id")Long id) {
		
		Response<ContentDto> response = new Response<ContentDto>(); 
		Optional<Content> content = contentService.findById(id);
		
		if(!content.isPresent()) {
			response.getErrors().add("Video e mensagem não encontrados");
			ResponseEntity.badRequest().body(response);
		}
		
		response.setData(converterContentDto(content.get()));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/content/update/{id}")
	public ResponseEntity<Response<ContentDto>> update(@PathVariable (value = "id")Long id,
			@Valid @RequestBody ContentDto contentDto, BindingResult result) {
		
		Response<ContentDto> response = new Response<ContentDto>(); 
		contentDto.setId(id);
		Content content = converterDtoParaContent(contentDto,result);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		contentService.register(content);
		response.setData(converterContentDto(content));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/content/delete/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id")Long id) {
		Response<String> response = new Response<String>();
		Optional<Content> content = contentService.findById(id); 
		
		if(!content.isPresent()) {
			response.getErrors().add("Erro ao remover video e mensagem."); 
			return ResponseEntity.badRequest().body(response);
		} 
		
		contentService.deleteById(id);
		response.setData("Video e mensagem removidos com sucesso");
		return ResponseEntity.ok(response);
	}
	
	private Content converterDtoParaContent(ContentDto contentDto, BindingResult result) {
		Content content = new Content(); 
		content.setId(contentDto.getId());
		content.setMessage(contentDto.getMessage());
		content.setVideo(contentDto.getVideo());
		
		return content;
	}
	
	private ContentDto converterContentDto(Content content) {
		ContentDto contentDto = new ContentDto();
		contentDto.setId(content.getId()); 
		contentDto.setMessage(content.getMessage());
		contentDto.setVideo(content.getVideo());
		
		return contentDto;
	}
}

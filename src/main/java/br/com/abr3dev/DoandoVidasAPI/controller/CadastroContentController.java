package br.com.abr3dev.DoandoVidasAPI.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
public class CadastroContentController {
	
	@Autowired
	private ContentService contentService;
	
	@PostMapping("/content")
	public ResponseEntity<Response<ContentDto>> update(@Valid @RequestBody ContentDto contentDto, BindingResult result) {
		
		Response<ContentDto> response = new Response<ContentDto>(); 
		validarDadosExistentes(contentDto, result);
		Content content = converterDtoParaContent(contentDto,result);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		contentService.register(content);
		response.setData(converterContentDto(content));
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
	
	private void validarDadosExistentes(ContentDto contentDto, BindingResult result) {
		contentService.findById(contentDto.getId()).ifPresent(cont -> result.addError(new ObjectError("cont", "Conteudo de video e mensagem já existentes")));
	}
}

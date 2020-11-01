package br.com.abr3dev.DoandoVidasAPI.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

import br.com.abr3dev.DoandoVidasAPI.dtos.UserDto;
import br.com.abr3dev.DoandoVidasAPI.models.Content;
import br.com.abr3dev.DoandoVidasAPI.models.User;
import br.com.abr3dev.DoandoVidasAPI.response.Response;
import br.com.abr3dev.DoandoVidasAPI.service.UserService;

@RestController
@RequestMapping("api/doandovidas")
@CrossOrigin(origins = "*")
public class CadastroUserController {
	
	private SimpleDateFormat df;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Response<UserDto>> register(@Valid @RequestBody UserDto userDto, 
			BindingResult result) throws ParseException {
		Response<UserDto> response = new Response<UserDto>();
		
		validarDadosExistentes(userDto, result);
		User user = this.converterDtoParaUser(userDto, result);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		userService.register(user);
		
		response.setData(this.converterRegisterDto(user)); 
		return ResponseEntity.ok(response);
		
	}
	
	private void validarDadosExistentes(UserDto userDto, BindingResult result) {
		
		this.userService.findByEmail(userDto.getEmail())
		.ifPresent(user -> result.addError(new ObjectError("user", "E-mail já cadastrado.")));
		
		this.userService.findByCpf(userDto.getCpf())
		.ifPresent(user -> result.addError(new ObjectError("user", "CPF já cadastrado.")));
		
	}
	
	private User converterDtoParaUser(UserDto userDto, BindingResult result) throws ParseException {
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setCpf(userDto.getCpf()); 
		user.setPassword(userDto.getPassword());
		String dataFormatada = "";
		dataFormatada = dataFormatada.concat(userDto.getBirthDate().substring(6)+"-"+userDto.getBirthDate().substring(3, 5)+"-"+userDto.getBirthDate().substring(0, 2));
		df = new SimpleDateFormat("yyyy-MM-dd");
		user.setBirthDate(this.df.parse(dataFormatada));
		user.setGender(userDto.getGender());
		user.setContent(new Content());
		
		return user;
	}
	
	private UserDto converterRegisterDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setCpf(user.getCpf());
		userDto.setPassword(user.getPassword()); 
		df = new SimpleDateFormat("dd/MM/yyyy");
		userDto.setBirthDate(this.df.format(user.getBirthDate())); 
		userDto.setGender(user.getGender());
		userDto.setContentId(user.getContent().getId());
		userDto.setMessage(user.getContent().getMessage()); 
		userDto.setVideo(user.getContent().getVideo());
		
		return userDto;
	}
	
}

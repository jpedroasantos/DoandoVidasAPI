package br.com.abr3dev.DoandoVidasAPI.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

public class UserDto {
	
	private Long id;
	
	private String avatar;
	
	@NotBlank(message="Nome deve ser informado.")
	private String name;
	
	@NotBlank(message="E-mail deve ser informado.")
	@Email(message="E-mail inválido.")
	private String email; 
	
	@NotBlank(message="CPF deve ser informado.")
	@CPF(message="CPF inválido.")
	private String cpf; 
	
	@NotBlank(message="Senha deve ser preenchida.")
	@Size(min=8, message="É necessario uma senha com no mínimo 8 caracteres.")
	private String password; 
	
	@NotBlank(message="Data de nascimento deve ser preenchida.")
	private String birthDate;
	
	@NotBlank(message="Gênero deve ser informado.")
	private String gender;
	
	private Long contentId;
	
	@Size(max=300, message="Tamanho da mensagem não pode ultrapassar 300 caracteres.")
	private String message; 
	
	private String video;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
}

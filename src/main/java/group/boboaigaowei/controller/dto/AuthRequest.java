package group.boboaigaowei.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

	@NotBlank(message = "Account can't be null.")
	private String account;
	
	@NotBlank(message = "Password can't be null.")
	private String password;
}

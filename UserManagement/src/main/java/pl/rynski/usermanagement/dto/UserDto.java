package pl.rynski.usermanagement.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto implements Serializable {
	private static final long serialVersionUID = -359257850736037937L;
	private String email;
	private String password;
	private String userId;
	private String encryptedPassword;

}

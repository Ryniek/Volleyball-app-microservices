package pl.rynski.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, unique = true, columnDefinition = "VARCHAR(130)")
	private String email;
	@Column(name = "user_id", nullable = false, unique = true, columnDefinition = "VARCHAR(250)")
	private String userId;
	@Column(name = "encrypted_password", nullable = false)
	private String encryptedPassword;
}

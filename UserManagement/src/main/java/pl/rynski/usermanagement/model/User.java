package pl.rynski.usermanagement.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@Column(name = "encrypted_password", nullable = false)
	private String encryptedPassword;
	@Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(50)")
	private String firstName;
	@Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(50)")
	private String lastName;
	@Column(name = "height")
	private Integer height;
	@Column(name = "attack_jump")
	private Integer attackJump;
	@Column(name = "block_jump")
	private Integer blockJump;
	@Enumerated(EnumType.ORDINAL)
	private Position position;
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( 
            name = "users_roles", 
            joinColumns = @JoinColumn(
              name = "user_id", referencedColumnName = "id"), 
            inverseJoinColumns = @JoinColumn(
              name = "role_id", referencedColumnName = "id")) 
	private List<UserRole> roles;
}

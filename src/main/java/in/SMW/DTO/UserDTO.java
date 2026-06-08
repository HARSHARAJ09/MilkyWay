package in.SMW.DTO;


import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

	private Integer id;

	private String firstName;

	private String lastName;

	private String email;

	private String phone;

	private Boolean enabled;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private Set<RoleDTO> roles;

}
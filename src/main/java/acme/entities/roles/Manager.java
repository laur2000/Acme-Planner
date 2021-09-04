
package acme.entities.roles;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.entities.task.Task;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Manager extends UserRole {

	protected static final long	serialVersionUID	= 1L;

	@NotBlank
	@Length(min = 1, max = 255)
	protected String			company;

	@NotBlank
	@Length(min = 1, max = 255)
	protected String			sector;

	@OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
	Set<Task>					tasks;
}

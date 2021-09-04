
package acme.entities.spam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpamConfiguration extends DomainEntity {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 23423423489L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(min = 0, max = 1024)
	@Column(length = 1024)
	protected String			words;

	@NotNull
	@DecimalMin(value = "0.0")
	@DecimalMax(value = "100.0")
	Double						threshold;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}

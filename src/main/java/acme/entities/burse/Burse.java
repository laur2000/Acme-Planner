
package acme.entities.burse;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.shout.Shout;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Burse extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	@NotNull
	@Column(unique = true)
	protected String	mark;

	@Temporal(TemporalType.TIMESTAMP)
	@Future
	@NotNull
	protected Date		deadline;

	@NotNull
	protected Money		budget;

	@NotNull
	protected Boolean	important;
	
	@OneToOne
	protected Shout shout;
}

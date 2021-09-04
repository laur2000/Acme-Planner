
package acme.entities.task;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.enums.Visibility;
import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {

	static final long	serialVersionUID	= 8331441137571663368L;

	@NotEmpty
	@Length(max = 80, min = 1)
	String				title;

	@NotEmpty
	@Length(max = 500, min = 1)
	@Column(length = 512)
	String				description;

	@NotNull
	@Min(0)
	Double				workload;

	@URL
	@Length(min = 0, max = 255)
	String				link;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date		startPeriod;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date		endPeriod;

	@Enumerated(EnumType.STRING)
	@NotNull
	Visibility			visibility;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	Manager				manager;
}

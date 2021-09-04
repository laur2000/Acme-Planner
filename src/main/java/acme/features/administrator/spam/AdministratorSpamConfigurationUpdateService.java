
package acme.features.administrator.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.SpamConfiguration;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSpamConfigurationUpdateService implements AbstractUpdateService<Administrator, SpamConfiguration> {

	@Autowired
	AdministratorSpamRepository repository;


	@Override
	public boolean authorise(final Request<SpamConfiguration> request) {
		return true;
	}

	@Override
	public void bind(final Request<SpamConfiguration> request, final SpamConfiguration entity, final Errors errors) {
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<SpamConfiguration> request, final SpamConfiguration entity, final Model model) {
		// Not used

	}

	@Override
	public SpamConfiguration findOne(final Request<SpamConfiguration> request) {
		return this.repository.getSpamConfiguration();
	}

	@Override
	public void validate(final Request<SpamConfiguration> request, final SpamConfiguration entity, final Errors errors) {
		// Not used
	}

	@Override
	public void update(final Request<SpamConfiguration> request, final SpamConfiguration entity) {
		this.repository.save(entity);

	}

}

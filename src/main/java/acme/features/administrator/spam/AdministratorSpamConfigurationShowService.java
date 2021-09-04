
package acme.features.administrator.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.SpamConfiguration;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSpamConfigurationShowService implements AbstractShowService<Administrator, SpamConfiguration> {

	@Autowired
	AdministratorSpamRepository repository;


	@Override
	public boolean authorise(final Request<SpamConfiguration> request) {
		return true;
	}

	@Override
	public void unbind(final Request<SpamConfiguration> request, final SpamConfiguration entity, final Model model) {
		request.unbind(entity, model, "words", "threshold");
	}

	@Override
	public SpamConfiguration findOne(final Request<SpamConfiguration> request) {
		return this.repository.getSpamConfiguration();
	}

}

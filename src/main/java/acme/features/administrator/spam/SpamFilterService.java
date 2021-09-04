
package acme.features.administrator.spam;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.SpamConfiguration;
import acme.entities.spam.SpamValidator;
import acme.framework.components.Errors;
import acme.framework.components.Request;

@Service
public class SpamFilterService {

	@Autowired
	AdministratorSpamRepository repository;


	public SpamValidator getValidator() {
		final SpamConfiguration configuration = this.repository.getSpamConfiguration();
		final Double threshold = configuration.getThreshold();
		final List<String> words = Arrays.asList(configuration.getWords().split(","));

		return new SpamValidator(threshold, words);
	}

	public void validate(final Request<?> request, final String attribute, final String text, final Errors errors) {
		this.getValidator().validate(request, attribute, text, errors);
	}
}

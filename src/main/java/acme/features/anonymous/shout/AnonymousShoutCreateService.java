
package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.burse.Burse;
import acme.entities.shout.Shout;
import acme.features.administrator.spam.SpamFilterService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousShoutRepository	repository;

	@Autowired
	protected SpamFilterService			spamService;

	// AbstractCreateService<Administrator, Shout> interface --------------


	@Override
	public boolean authorise(final Request<Shout> request) {
		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		request.unbind(entity, model, "author", "text", "info", "moment", "burse.mark", "burse.deadline", "burse.budget", "burse.important");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		Shout result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Shout();
		result.setAuthor("");
		result.setText("");
		result.setMoment(moment);
		result.setInfo("");

		final Burse burse = new Burse();

		result.setBurse(burse);

		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		final Burse burse = entity.getBurse();
		if (!errors.hasErrors()) {
			this.spamService.validate(request, "author", entity.getAuthor(), errors);
			this.spamService.validate(request, "text", entity.getText(), errors);
			this.spamService.validate(request, "info", entity.getInfo(), errors);

		}

		if (!errors.hasErrors("burse.mark")) {
			final int marksCount = this.repository.findMarks(burse.getMark()).size();
			errors.state(request, marksCount == 0, "burse.mark", "acme.validation.burse-unique");
		}

		if (!errors.hasErrors("burse.mark")) {
			final LocalDate now = LocalDate.now();

			final String year = now.format(DateTimeFormatter.ofPattern("YY"));
			final String month = now.format(DateTimeFormatter.ofPattern("MM"));
			final String date = now.format(DateTimeFormatter.ofPattern("dd"));

			final String pattern = "^\\w{2}\\d{2}-"+month+date+year+"$";

			errors.state(request, burse.getMark().matches(pattern), "burse.mark", "acme.validation.burse-pattern");
		}

		if (!errors.hasErrors("burse.budget")) {
			final Money budget = burse.getBudget();
			final String currency = budget.getCurrency();

			final Boolean validCurrency = currency.equals("EUR") || currency.equals("USD") || currency.equals("GBP");

			errors.state(request, validCurrency, "burse.budget", "acme.validation.burse-budget");

		}

		if (!errors.hasErrors("burse.deadline")) {
			final Date deadline = burse.getDeadline();

			final long DAY_IN_MS = 1000 * 60 * 60 * 24;
			final Date oneWeekAgo = new Date(System.currentTimeMillis() + (7 * DAY_IN_MS));

			errors.state(request, deadline.after(oneWeekAgo), "burse.deadline", "acme.validation.burse-deadline");

		}

	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity.getBurse());
		this.repository.save(entity);
	}

}

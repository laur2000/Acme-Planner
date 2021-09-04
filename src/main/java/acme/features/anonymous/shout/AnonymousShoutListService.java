
package acme.features.anonymous.shout;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shout.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousShoutListService implements AbstractListService<Anonymous, Shout> {

	// Internal state ----------------------------------------------------

	@Autowired
	AnonymousShoutRepository repository;

	// AbstractListService<Administrator, Shout> interface ---------------


	@Override
	public boolean authorise(final Request<Shout> request) {
		return true;
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		request.unbind(entity, model, "author", "text", "info", "moment");
	}

	@Override
	public Collection<Shout> findMany(final Request<Shout> request) {
		Collection<Shout> result;

		final Date referenceDate = new Date();
		final Calendar c = Calendar.getInstance();
		c.setTime(referenceDate);
		// Remove one month from the current date
		c.add(Calendar.MONTH, -1);

		final Date oneMonthOldDate = c.getTime();

		result = this.repository.findShoutsNotOlderThanDateAndSortedByDate(oneMonthOldDate);

		return result;
	}
}

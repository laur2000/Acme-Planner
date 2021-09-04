
package acme.entities.task;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Component;

import acme.forms.HoursAndMinutes;
import acme.framework.components.Errors;
import acme.framework.components.Request;

@Component
public class TaskValidator {

	static final long HOUR_TO_MILLIS = 60 * 60 * 1000L;


	public void validate(final Request<Task> request, final Task target, final Errors errors) {
		final Date startPeriod = target.getStartPeriod();
		final Date endPeriod = target.getEndPeriod();
		double workLoad = 0.0;

		if (target.getWorkload() != null) {
			try {
				workLoad = HoursAndMinutes.fromFormattedTime(target.getWorkload()).getDecimalTime();
			} catch (final Exception e) {
				errors.state(request, false, "workload", "acme.validation.invalid-time-format");
			}
		}

		errors.state(request, target.getWorkload() <= 99.59, "workload", "acme.validation.workload-exceeded");

		if (startPeriod != null && endPeriod != null) {
			final Date now = Date.from(Instant.now());
			errors.state(request, startPeriod.after(now), "startPeriod", "javax.validation.constraints.Future.message");
			errors.state(request, endPeriod.after(now), "endPeriod", "javax.validation.constraints.Future.message");

			if (!errors.hasErrors("startPeriod") && !errors.hasErrors("endPeriod")) {
				errors.state(request, startPeriod.before(endPeriod), "startPeriod", "acme.validation.start-after-end");
			}

			if (!errors.hasErrors("startPeriod") && !errors.hasErrors("endPeriod")) {
				final long periodDuration = endPeriod.getTime() - startPeriod.getTime();
				final long workLoadMilis = (long) (workLoad * TaskValidator.HOUR_TO_MILLIS);
				errors.state(request, workLoadMilis <= periodDuration, "workload", "acme.validation.too-big-workload");
			}
		}
	}
}

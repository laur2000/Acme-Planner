
package acme.entities.spam;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acme.framework.components.Errors;
import acme.framework.components.Request;

public class SpamValidator {

	Double			threshold;
	List<String>	words;


	public SpamValidator(final Double threshold, final List<String> words) {
		this.threshold = threshold;
		this.words = words;
	}

	public void validate(final Request<?> request, final String attribute, final String text, final Errors errors) {
		final String lowerText = text.toLowerCase();

		final Double spamCount = this.words.stream().map(censor -> {
			final Matcher m = Pattern.compile(censor.replace(" ", " *")).matcher(lowerText);
			double count = 0;
			while (m.find())
				count++;
			return count;
		}).reduce(0.0, (x, y) -> x + y);

		Double wordCount = 0.0;
		final Matcher m = Pattern.compile("[^ \n\t\r]+").matcher(lowerText);
		while (m.find())
			wordCount++;

		Double myThreshold = 0.0;
		if (!wordCount.equals(0.0)) {
			myThreshold = (spamCount / wordCount) * 100;
		}

		errors.state(request, this.threshold > myThreshold, attribute, "acme.validation.spam");
	}
}

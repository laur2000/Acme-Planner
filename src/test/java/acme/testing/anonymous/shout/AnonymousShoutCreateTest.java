
package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest {

	/*
	 * Principal: Anonymous
	 * Entity: Shout
	 * Action: create (positive)
	 * Cases: We test whether an anonymous principal is able to create and register a new
	 * shout in the system.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String author, final String text, final String info, final String mark, final String deadline, final String budget, final String important) {
		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);

		super.fillInputBoxIn("burse.mark", mark);
		super.fillInputBoxIn("burse.deadline", deadline);
		super.fillInputBoxIn("burse.budget", budget);
		super.fillInputBoxIn("burse.important", important);

		super.clickOnSubmitButton("Shout!");

		super.clickOnMenu("Anonymous", "Shout list");

		super.checkColumnHasValue(0, 1, author);
		super.checkColumnHasValue(0, 2, info);
		super.checkColumnHasValue(0, 3, text);
		super.checkColumnHasValue(0, 4, mark);
		super.checkColumnHasValue(0, 5, deadline);
		super.checkColumnHasValue(0, 6, budget);
		super.checkColumnHasValue(0, 7, important);
		
	}

	/*
	 * Principal: Anonymous
	 * Entity: Shout
	 * Action: create (negative)
	 * Cases: We test whether an anonymous principal is unable to create and register a new
	 * shout in the system when either:
	 * - The author is blank
	 * - The author has less than 5 characters
	 * - The author has more than 25 characters
	 * - The text is blank
	 * - The info is an invalid URL
	 * - The text has more than 100 characters
	 * - All the attributes are empty
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String author, final String text, final String info, final String mark, final String deadline, final String budget, final String important) {

		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		
		super.fillInputBoxIn("burse.mark", mark);
		super.fillInputBoxIn("burse.deadline", deadline);
		super.fillInputBoxIn("burse.budget", budget);
		super.fillInputBoxIn("burse.important", important);
		
		super.clickOnSubmitButton("Shout!");

		super.checkErrorsExist();
	}
}

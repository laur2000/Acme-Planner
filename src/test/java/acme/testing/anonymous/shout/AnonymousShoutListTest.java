
package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListTest extends AcmePlannerTest {

	/*
	 * Principal: Anonymous
	 * Entity: Shout
	 * Action: list (positive)
	 * Cases: We test whether an anonymous principal is able to list all the shouts
	 * registered in the system that aren't older than one month.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex, final String moment, final String author, final String info, final String text, final String mark, final String deadline, final String budget, final String important) {
		super.clickOnMenu("Anonymous", "Shout list");

		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, info);
		super.checkColumnHasValue(recordIndex, 3, text);
		super.checkColumnHasValue(recordIndex, 4, mark);
		super.checkColumnHasValue(recordIndex, 5, deadline);
		super.checkColumnHasValue(recordIndex, 6, budget);
		super.checkColumnHasValue(recordIndex, 7, important);
	}
}

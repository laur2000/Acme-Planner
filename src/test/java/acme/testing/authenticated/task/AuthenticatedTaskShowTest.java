package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskShowTest extends AcmePlannerTest {
	/*
	 * Principal: Authenticated
	 * Entity: Task
	 * Action: show (positive)
	 * Cases: We test whether an authenticated principal is able to see
	 * the details of the first seven public tasks that are already finished.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list-show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final int recordIndex, final String title, final String description,
			final String workload, final String link, final String start, final String end, final String visibility) {

		super.signIn("authenticated", "authenticated");
		super.clickOnMenu("Authenticated", "Task list");

		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("startPeriod", start);
		super.checkInputBoxHasValue("endPeriod", end);
		super.checkInputBoxHasValue("visibility", visibility);
	}
	
	/*
	 * Principal: Authenticated
	 * Entity: Task
	 * Action: show (negative)
	 * Cases: We test whether an authenticated principal is unable to see the
	 * details of the registered tasks in the system that are either private or
	 * they aren't finished yet.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showNegative(final int id) {
		super.navigate("/authenticated/task/show", String.format(".&id=%d", id));
		
		super.checkPanicExists();
	}
}

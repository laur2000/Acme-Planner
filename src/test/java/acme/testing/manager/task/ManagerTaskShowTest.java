
package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskShowTest extends AcmePlannerTest {
	/*
	 * Principal: Manager
	 * Entity: Task
	 * Action: show (positive)
	 * Cases: We test whether a manager principal is able to see the details
	 * of any of his tasks.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list-show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final int recordIndex, final String user, final String password, final String title, final String description, final String workload,
			final String link, final String startPeriod, final String endPeriod, final String visibility) {
		super.signIn(user, password);
		super.clickOnMenu("Manager", "Task list");
		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startPeriod", startPeriod);
		super.checkInputBoxHasValue("endPeriod", endPeriod);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("visibility", visibility);


	}

	/*
	 * Principal: Manager
	 * Entity: Task
	 * Action: show (negative)
	 * Cases: We test whether a manager principal is unable to see the tasks
	 * of others managers, or invalid tasks that doesn't exist in the system.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int id, final String user, final String password) {
		super.signIn(user, password);
		super.navigate("/managers/task/show", String.format(".&id=%d", id));

		super.checkPanicExists();
	}
}

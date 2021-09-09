
package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest {
	/*
	 * Principal: Manager
	 * Entity: Task
	 * Action: create (positive)
	 * Cases: We test whether a manager principal is able to create and register
	 * tasks in the system.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String description,
			final String workload, final String link, final String startPeriod, final String endPeriod,
			final String visibility) {
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "Create task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startPeriod", startPeriod);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("endPeriod", endPeriod);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("visibility", visibility);

		super.clickOnSubmitButton("Create task");

		super.clickOnMenu("Manager", "Task list");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, workload);
		super.checkColumnHasValue(recordIndex, 2, link);
		super.checkColumnHasValue(recordIndex, 3, startPeriod);
		super.checkColumnHasValue(recordIndex, 4, endPeriod);
		super.checkColumnHasValue(recordIndex, 5, visibility);

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
	 * Action: create (negative)
	 * Cases: We test whether a manager principal is unable to create and
	 * register tasks in the system whenever either:
	 *   - Any mandatory attribute is empty
	 *   - Start date is after end date
	 *   - Start date is exactly the same as the end date
	 *   - Workload doesn't fit in the execution period
	 *   - Workload is greater than 9999 hours
	 *   - Workload has more than 2 decimal digits
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int recordIndex, final String title, final String description,
			final String workload, final String link, final String startPeriod, final String endPeriod,
			final String visibility) {
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "Create task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startPeriod", startPeriod);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("endPeriod", endPeriod);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("link", link);
		if (visibility != null) {
			super.fillInputBoxIn("visibility", visibility);
		}

		super.clickOnSubmitButton("Create task");

		super.checkErrorsExist();
	}
}

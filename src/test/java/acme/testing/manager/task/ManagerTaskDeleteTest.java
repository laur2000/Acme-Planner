
package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTest extends AcmePlannerTest {
	/*
	 * Principal: Manager
	 * Entity: Task
	 * Action: create (positive)
	 * Cases: We test whether a manager principal is able to delete any registered
	 * its own tasks successfully.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int id) {
		super.signIn("manager2", "manager2");

		super.navigate("/managers/task/show", String.format(".&id=%d", id));
		
		super.clickOnSubmitButton("Delete task");
		
		super.navigate("/managers/task/show", String.format(".&id=%d", id));

		super.checkPanicExists();
		
		super.signOut();
	}

	/*
	 * Principal: Manager
	 * Entity: Task
	 * Action: create (negative)
	 * Cases: We test whether either:
	 *   - A manager principal is unable to delete any registered task of other owners
	 *   - Any other principal that is not a manager is unable to delete any registered task in the system.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteNegative(final int id, final String user, final String password) {
		super.signIn(user, password);

		super.navigate("/managers/task/show", String.format(".&id=%d", id));
		super.checkPanicExists();

		super.signOut();
	}
}

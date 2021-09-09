package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordShowTest extends AcmePlannerTest {
	/*
	 * Principal: Administrator
	 * Entity: SpamWord
	 * Action: show (positive)
	 * Cases: We test whether every spam word text initially registered in the system
	 * is shown in the form view.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final String threshold, final String words) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam Configuration");
		
		super.checkInputBoxHasValue("threshold", threshold);
		super.checkInputBoxHasValue("words", words);
		
		super.signOut();
	}

	/*
	 * Principal: Administrator
	 * Entity: SpamWord
	 * Action: show (negative)
	 * Cases: We test whether every principal in the system that is not an administrator
	 * (manager, authenticated) is unable to see the text of every spam word initially
	 * registered in the system.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showNegative(final int id, final String user, final String password) {
		super.signIn(user, password);
		super.navigate("/administrator/spam-word/show", String.format(".&id=%d", id));
		
		super.checkPanicExists();
		
		super.signOut();
	}
	
}

package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordCreateTest extends AcmePlannerTest {
	/*
	 * Principal: Administrator
	 * Entity: SpamWord
	 * Action: create (positive)
	 * Cases: We test whether an administrator principal is able to create new words
	 * and register them as spam words in the system.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final String threshold, final String words) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam Configuration");
		
		
		super.fillInputBoxIn("threshold", threshold);
		super.fillInputBoxIn("words", words);
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "Spam Configuration");
		
		super.checkInputBoxHasValue("threshold", threshold);
		super.checkInputBoxHasValue("words", words);
		
		super.signOut();
	}
	
	/*
	 * Principal: Administrator
	 * Entity: SpamWord
	 * Action: create (negative)
	 * Cases: We test whether an administrator principal is unable to register
	 * empty words or words that are already registered in the system.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final String threshold, final String words) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam Configuration");
		
		super.fillInputBoxIn("threshold", threshold);
		super.fillInputBoxIn("words", words);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
}

package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerCreateTest extends AcmePlannerTest{
	/*
	 * Principal: Authenticated
	 * Entity: Manager
	 * Action: create (positive)
	 * Cases: We test whether an authenticated principal is able to register himself
	 * as a manager successfully.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String company, final String sector){
		super.signIn("authenticated", "authenticated");
		super.clickOnMenu("Account", "Become a manager");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.clickOnSubmitButton("Register");
		
		super.clickOnMenu("Account", "Manager data");
		
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("sector", sector);
		
		super.signOut();
	}
	
	/*
	 * Principal: Authenticated
	 * Entity: Manager
	 * Action: create (negative)
	 * Cases: We test whether an authenticated principal is unable to register himself
	 * as a manager whenever either:
	 *   - The sector or company attributes are blank
	 *   - The sector or company attributes have more than 255 characters
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String company, final String sector){
		
		super.signIn("authenticated2", "authenticated2");
		super.clickOnMenu("Account", "Become a manager");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.clickOnSubmitButton("Register");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
}

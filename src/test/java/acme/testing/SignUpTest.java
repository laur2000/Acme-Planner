/*
 * SignUpTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class SignUpTest extends AcmePlannerTest {

	/*
	Positive sign up test
	
	Different valid sing up will be 
	created with different characteristics in order to have various positive cases.
	*/
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveSignUp(final String username, final String password, final String name, final String surname, final String email, final String phone) {
		super.signUp(username, password, name, surname, email);
		super.signIn(username, password);
		super.signOut();
	}

	/*
	Negative sign up test
	
	Different invalid sing up will be 
	created with different characteristics in order to have various positive cases.
	
	There will be seven test cases. These are as follows:
	0.Sign up with empty username
	1.Sign up with username attribute less than 5 characters
	2.Sign up with empty password
	3.Sign up with password attribute less than 6 characters
	4.Sign up with empty name
	5.Sign up with empty surname
	6.Sign up with email with invalid syntax
	7.Sign up with empty email
	8.Sign up with all attributes empty
	*/
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeSignUp(final String username, final String password, final String name, final String surname, final String email, final String phone) {
		super.signUpNegative(username, password, name, surname, email);
	}

}

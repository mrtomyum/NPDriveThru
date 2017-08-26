package connect;

import static org.junit.Assert.*;

import org.junit.Test;

public class bCryptPasswordTest {

	@Test
	public void test() {
		String test;
		String testbcrypt;
		boolean check;
		
		bCryptPassword cn = new bCryptPassword();
		
		String test_passwd = "happyveryrich";
		String test_hash = "$2a$12$hd6IiyBZrMbsRbK16NN8f.6a7KElcG45WgnBhShbHJ.LvsO4ejONe1";

		System.out.println("Testing BCrypt Password hashing and verification");
		System.out.println("Test password: " + test_passwd);
		System.out.println("Test stored hash: " + test_hash);
		System.out.println("Hashing test password...");
		System.out.println();

		String computed_hash = cn.hashPassword(test_passwd);
		System.out.println("Test computed hash: " + computed_hash);
		System.out.println();
		System.out.println("Verifying that hash and stored hash both match for the test password...");
		System.out.println();

		String compare_test = cn.checkPassword(test_passwd, test_hash)
			? "Passwords Match" : "Passwords do not match";
		//String compare_computed = cn.checkPassword(test_passwd, computed_hash)
			//? "Passwords Match" : "Passwords do not match";

		//System.out.println("Verify against stored hash:   " + compare_test);
		//System.out.println("Verify against computed hash: " + compare_computed);
		
		//$2a$12$hd6IiyBZrMbsRbK16NN8f.6a7KElcG45WgnBhShbHJ.LvsO4ejONe
		//$2a$12$aZKu7uHeTCHrCA4t65PneuqkyHlI1Z9yX4t8dAPn/AxNUPPiy9wxC
		//$2a$12$uwqfRRvsqIJhRnriQOIjheq9cGnBCiElebV1UfrCwv2Ct09GFJMN6
		
		//System.out.println(check);
	}

}

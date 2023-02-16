package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Testclass of Bankservice ")
class TestJunit01ApplicationTests {

	private static BankService service;

	
	@BeforeAll
	public static void setup() {
		System.out.println("TestJunit01ApplicationTests.setup()");
		service=new  BankService();
	}
	
	
	
	
	/*
	 * @BeforeEach
	 * 
	 * @Disabled public void setup() {
	 * System.out.println("TestJunit01ApplicationTests.setup()"); service = new
	 * BankService(); }
	 */

	@Test
	@DisplayName("test of big numbers")
	@Order(5)
	@Tag("dev")
	public void testBankServiceAmountwithBigNumbers() {
		// BankService service = new BankService();
		float actual = service.calcintrest(100000, 2, 12);
		float expected = 24000.0f;
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("test of small numbers")
	@Order(1)
	@Tag("uat")
	public void testBankServiceamountwithsmallnumbers() {
		// BankService service = new BankService();
		float actual = service.calcintrest(1000, 8, 10);
		float expected = 800.0f;
		assertEquals(expected, actual, "May result not match ");
	}

	@Test
	@Disabled
     @Order(-10)
	@Tag("dev")
	public void testbankservicewithinvalidinputs() {
		// BankService service = new BankService();
		assertThrows(ArithmeticException.class, () -> {
			service.calcintrest(0, 0, 0);
		}, " May be raised exception is not matched "

		);
	}

	@Test
	@DisplayName("test of timer ")
	@Order(2)
	@Tag("uat")
	public void testbankservicewithtimer() {
		// BankService service = new BankService();
		assertTimeout(Duration.ofMillis(2000), () -> {
			service.calcintrest(12000, 5, 10);
		});
	}

	@Test
	@DisplayName("test with no errors")
	@Order(7)
	@Tag("dev")
	public void testbankservicewithnoerror() {
		assertDoesNotThrow(() -> {
			service.calcintrest(45000, 5, 14);
		});
	}
	
	
	
	/*
	 * @AfterEach public void clear() {
	 * System.out.println("TestJunit01ApplicationTests.clear()"); service = null;
	 * 
	 * }
	 */
	
	
	
	@AfterAll
	public static void clear() {
		System.out.println("TestJunit01ApplicationTests.clear()");
		service=null ;
	}

}

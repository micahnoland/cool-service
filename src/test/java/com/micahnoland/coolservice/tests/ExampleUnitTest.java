package com.micahnoland.coolservice.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.micahnoland.coolservice.UnitTestApplication;


@RunWith(SpringJUnit4ClassRunner.class) 
@SpringApplicationConfiguration(classes = { UnitTestApplication.class })
@ActiveProfiles("test")
public class ExampleUnitTest {

	@Test
	public void defaultTest() { 
		assertThat(true, equalTo(true));
	}
}

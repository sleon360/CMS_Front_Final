package com.appcms.front;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FronendApplicationTests {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void deleteEmployeeAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders.delete("/employees/{id}", 1) )
	        .andExpect(status().isAccepted());
	}
	

}

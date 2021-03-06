package com.appcms.front;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import com.appcms.entity.ResourceEntity;
import com.appcms.router.ResourceRoutes;
import com.appcms.services.ResourceService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FronendApplication.class)
public class FronendApplicationUnitTests {
/*

	private MockMvc mvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	private ResourceRoutes controller;
    private ResourceService greetingService;
    
	//@Autowired
	//private FilterChainProxy filterChain;
	
	@Mock
    private RestTemplate restTemplate;
	
	  @Before
	  public void setUp()
	  { 
		  mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
		  request = new MockHttpServletRequest();
		  response = new MockHttpServletResponse();
		  
		  greetingService = mock(ResourceService.class);
	      controller = new ResourceRoutes(greetingService);
	  }
	  
	  
	  
	  @Test                                                                                         
	  public void Resources()
	  {   
	    	
	
	    	ResourceEntity emp = new ResourceEntity();
	    	emp.setNombre_resource("logo-scotiaclub.png");
	    	emp.setId("123");
	    	
	    	
	    	when(greetingService.getResouce("images", "logo-scotiaclub.png")).thenReturn(emp);
	    	Object httpResponsefalse = controller.resource("logo-scotiaclub.png", "images", request);
	    	assertNotNull((ModelAndView) httpResponsefalse);                                                
	  }
	  

	

	
	
	

	
	
	@Test
	public void login() throws Exception
	{
	    mvc.perform(formLogin("/auth").password("invalid")).andExpect(unauthenticated()).andReturn();

	    mvc.perform(post("/auth")
	                     .contentType(MediaType.APPLICATION_FORM_URLENCODED) 
	                     .param("username", "19")
	                     .param("password", "123").with(csrf())
	                     )
	                     .andExpect(status().is(302))
	                     .andReturn()
	                     .getRequest()
	                     .getSession();

	    
	}

*/
	

}

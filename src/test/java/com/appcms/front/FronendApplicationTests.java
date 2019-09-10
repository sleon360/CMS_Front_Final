package com.appcms.front;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import com.appcms.entity.ResourceEntity;
import com.appcms.entity.customer.Customer;
import com.appcms.router.ResourceRoutes;
import com.appcms.security.CustomerAuthentication;
import com.appcms.services.GetRestService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FronendApplication.class)
public class FronendApplicationTests {


	private MockMvc mvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	private ResourceRoutes controller;
    private GetRestService greetingService;
    
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
		  
		  greetingService = Mockito.mock(GetRestService.class);
	      controller = new ResourceRoutes(greetingService);
	  }
	 
	  
	  @Test
		public void contextLoads() {
		  
		}
		
	  
	  
	  
	  
	  
	  @Test                                                                                         
	  public void givenMokedObject()
	  {   
	    	
	
	    	ResourceEntity emp = new ResourceEntity();
	    	emp.setNombre_resource("logo-scotiaclub.png");
	    	
	    	Mockito.when(greetingService.getResouce("images", "logo-scotiaclub.png")).thenReturn(emp);
	    	Object httpResponsefalse = controller.resource("logo-scotiaclub.png", "images", request);
	    	Assert.assertNotNull((ModelAndView) httpResponsefalse);
	    	
	    	
	    	//Mockito.when(greetingService.getResouce("images", "logo-scotiaclub.png", "123")).thenReturn(emp);
	    	//Object httpResponsetrue = (ResourceEntity)controller.resource("logo-scotiaclub.png", "images", request, response);
	    	//Assert.assertNotNull(httpResponsetrue.);
	    	
	    	
	    	///Assert.assertEquals((ModelAndView) httpResponse, new ModelAndView("redirect:/404"));
	     //   Assert.assertEquals("Saludos", httpResponse.getBody());
	        
	    	
	    	
	    	/*ResourceEntity emp = new ResourceEntity();
	    	emp.setNombre_resource("logo-scotiaclub.png");
	    	
	        mockServer.expect(ExpectedCount.once(), 
	          requestTo(new URI("http://localhost:8080/resource/images/logo-scotiaclub.png")))
	          .andExpect(method(HttpMethod.GET))
	          .andRespond(withStatus(HttpStatus.OK)
	          .contentType(MediaType.APPLICATION_JSON)
	          .body(mapper.writeValueAsString(emp))
	        );                                   
	                        
	        ResourceEntity employee = empService.getResouce("images", "logo-scotiaclub.png", "123");
	        mockServer.verify();
	        Assert.assertEquals(emp, employee);      */                                                  
	  }
	  
	
	  
	  /*
	@Test
	public void logout() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders.get("/logout") ).andExpect(status().is(302));
	}
	
	
	@Test
<<<<<<< HEAD
	public void deleteEmployeeAPI() throws Exception {
	  mvc.perform( MockMvcRequestBuilders.delete("/employees/{id}", 1) )
=======
	public void raiz() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders.get("/") )
	        .andExpect(status().isOk());
	}
	
	
	@Test
	public void error404() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders.get("/404") )
	        .andExpect(status().isOk());
	}
	
	
	@Test
	public void errores() throws Exception
	{
		//mvc.perform( MockMvcRequestBuilders.get("/error/{id}", 1) )
		//  .andExpect(status().is(302)).andReturn().getResponse().getRedirectedUrl();
		
		mvc.perform( MockMvcRequestBuilders.get("/error/{id}", 1) )
		.andExpect(status().isOk());
		
		mvc.perform( MockMvcRequestBuilders.get("/error/{id}", 400) )
		.andExpect(status().isOk());
		
		mvc.perform( MockMvcRequestBuilders.get("/error/{id}", 401) )
		.andExpect(status().isOk());
		
		mvc.perform( MockMvcRequestBuilders.get("/error/{id}", 403) )
		.andExpect(status().isOk());
		
		mvc.perform( MockMvcRequestBuilders.get("/error/{id}", 404) )
		.andExpect(status().isOk());
		
		mvc.perform( MockMvcRequestBuilders.get("/error/{id}", 500) )
		.andExpect(status().isOk());
		
	}
	

	@Test
	public void getcupon() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders.get("/getcupon/{id_rew}",345234523))
		.andExpect(status().isOk());
		
		mvc.perform( MockMvcRequestBuilders.get("/getcupon/{id_rew}","55555"))
		.andExpect(status().is(302));
	}
	
	
	
	@Test
	public void categoria() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders.get("/categoria/{menu}/{submenu}","miscanjes","gfretail") )
		.andExpect(status().isOk());
		
		mvc.perform( MockMvcRequestBuilders.get("/categoria/{menu}/{submenu}","mundos","descuentos") )
		.andExpect(status().isOk());

		mvc.perform( MockMvcRequestBuilders.get("/categoria/{menu}/{submenu}","00000000","00000000000") )
		.andExpect(status().is(302));
		
		//mvc.perform( MockMvcRequestBuilders.get("/categoria/{menu}/{submenu}","00000000","00000000000") )
		//.andExpect(status().is(307));
		
	}
	
	@Test
	public void resources() throws Exception
	{
		ResourceEntity emp = new ResourceEntity();
		emp.setId("123");
		emp.setNombre_resource("logo-scotiaclub.png");
	
		Mockito
        .when(restTemplate.getForEntity("http://142.93.62.102:9080/cmsrest/resource/images/get?name=123.png", ResourceEntity.class))
        .thenReturn(new ResponseEntity<ResourceEntity>(emp, HttpStatus.OK));
		
		
		
		mvc.perform( MockMvcRequestBuilders.get("/resource/{folder}/{resourceid}","images", "logo-scotiaclub.png") )
		.andExpect(status().isOk());
		
		mvc.perform( MockMvcRequestBuilders.get("/resource/{folder}/{resourceid}","0000000", "00000000.png") )
		.andExpect(status().is(307));

	}
	
	
	
	@Test
	public void information() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders.get("/information/{nombreInformation}",54654))
		.andExpect(status().is(302));
		
		mvc.perform( MockMvcRequestBuilders.get("/information/{nombreInformation}","viajes"))
		.andExpect(status().isOk());
		
	}
	
	
	@Test
	public void resources404() throws Exception
	{
	//	mvc.perform( MockMvcRequestBuilders.get("/resource/{folder}/{resourceid}","0000000", "000000") )
	//	.andExpect(status().is(307));
	}
	
	
	
	
	@Test
	public void login() throws Exception
	{
		mvc.perform(post("/").with(csrf()));
		
	    mvc.perform(get("/user/account/micartola")
	    		.with(user("19").password("123").roles("ADMIN"))).andExpect(authenticated());
	    
	    mvc.perform(get("/user/account/micartola")
	    		.with(anonymous())).andExpect(unauthenticated());
	    
	    HttpSession session=mvc.perform(MockMvcRequestBuilders.get("/user/account/micartola").with(user("19").password("123").roles("ADMIN")))
	    .andExpect(authenticated()).andReturn().getRequest().getSession();
	    
	    
	    ServletContext servletcontext=mvc.perform(MockMvcRequestBuilders.get("/user/account/micartola").with(user("19").password("123").roles("ADMIN")))
	    	    .andExpect(authenticated()).andReturn().getRequest().getServletContext();
	    	    
	    MvcResult resultError = mvc.perform(formLogin("/auth").password("invalid")).andExpect(unauthenticated()).andReturn();
	    
	   // MvcResult resultOK = 
	    		mvc.perform(post("/auth").with(csrf()).with(user("19").password("123").roles("ADMIN")))
	    .andExpect(authenticated());//.andDo(print()).andReturn();
	    
	    		mvc.perform(post("/auth")
	                     .contentType(MediaType.APPLICATION_FORM_URLENCODED) 
	                     .param("username", "000")
	                     .param("password", "000").with(csrf())
	                     )
	                     .andExpect(status().is(302))
	                     //.andExpect(redirectedUrl("/user/home"))
	                     .andReturn()
	                     .getRequest()
	                     .getSession();
	    		 
	    		 HttpSession xsession = mvc.perform(post("/auth")
	                     .contentType(MediaType.APPLICATION_FORM_URLENCODED) 
	                     .param("username", "19")
	                     .param("password", "123").with(csrf())
	                     )
	                     .andExpect(status().is(302))
	                     //.andExpect(redirectedUrl("/user/home"))
	                     .andReturn()
	                     .getRequest()
	                     .getSession();
	    		 
	             request.setSession(xsession);
	             SecurityContext securityContext = (SecurityContext)   session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
	             SecurityContextHolder.setContext(securityContext);
	             Customer customer = CustomerAuthentication.CustomerAuth("19", "123", session.getValue("TOKENONE").toString());
	             
	             */
	           //  System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFF::::"+customer.getJwt());
	            // Authentication auth = securityContext.getAuthentication();
	             
	            // assertThat(customer.getJwt()).isNotNull();
	           //  assertThat(customer.getScotiauser()).isNotNull();
	             
	            // CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getCredentials();
	            // System.out.println("XXXXXXXXXXXX:"+credencialesEntity.getUserName());
	             
	             
	             /*
	              * 
	              *         request.setSession(xsession);
	  
	             SecurityContext securityContext = (SecurityContext)   session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
	             SecurityContextHolder.setContext(securityContext);
	             System.out.println(SecurityContextHolder.getContext());
	             
	             System.out.println("JJJJJJ:"+request.getSession().getValue("TOKENONE"));
	             Customer customer = CustomerAuthentication.CustomerAuth("19", "123", xsession.getValue("TOKENONE").toString());
	             System.out.println("GGGDDDDDDDDDDDDDDD:"+customer.getJwt());
	             */
	            // CustomerAuthentication authCustomer=new CustomerAuthentication("19", "123", String token);
	             
	          //   Authentication auth = securityContext.getAuthentication();
	     		//CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getCredentials();
	     	//	System.out.println("XXXXXXXXXXXX:"+credencialesEntity.getUserName());
	             
	    		
	   // for(Cookie cookie : resultOK.getResponse().getCookies()){
	  //      System.out.println(cookie.getName());
	  //      }
	        
	    
	   // Cookie c = resultOK.getResponse().getCookie("JSESSIONID");
	    //mvc.perform(get("/user/account/micartola").cookie(c)).andExpect(status().isOk());
	    
	   // mvc.perform(formLogin("/auth").user("username","19").password("password","123")).andExpect(status().is3xxRedirection())
       // .andExpect(authenticated());
	    
	   // System.out.println("AAAAAAABBBBB:"+session.getAttribute("TOKENONE"));
	    //mvc.perform(formLogin().user("19").password("123")).andExpect(authenticated());
	    
	//}
	
	
	
	
	
	


	
	
	/*@Test
	public void deleteEmployeeAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders.get("/employees/{id}", 1) )
>>>>>>> refs/heads/master
	        .andExpect(status().isAccepted());
	}*/
	
	
	
	

}

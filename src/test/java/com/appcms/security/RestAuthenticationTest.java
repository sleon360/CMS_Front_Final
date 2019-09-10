package com.appcms.security;

import static org.mockito.Mockito.mock;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.appcms.entity.UserGusto;
import com.appcms.front.FronendApplication;
import com.cms.views.ViewApp;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FronendApplication.class)
public class RestAuthenticationTest {

	
	private RestAuthentication restAuth;
	@MockBean
	private RestTemplate restTemplate;
	private HttpServletRequest rq;
	private HttpServletResponse response;
	
	@Before
	public void setUp() throws Exception {
		restAuth=new RestAuthentication();
		restAuth.setrestTemplate(restTemplate);
		rq=mock(HttpServletRequest.class);
	}

	@Test
	public final void testRestAuthentication() throws Exception {
		Mockito.when(restTemplate.postForEntity("http://142.93.62.102:9080/login", null, String.class)).thenReturn(new ResponseEntity<String>("true",HttpStatus.OK));
     	Assert.assertNotNull(restAuth.RestAutenticationLayerOne(rq,response,"http://142.93.62.102:9080"));
	}

}

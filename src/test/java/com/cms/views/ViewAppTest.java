package com.cms.views;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.appcms.entity.ViewEntity;
import com.appcms.front.FronendApplication;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FronendApplication.class)
public class ViewAppTest {

	/*
	
	private ViewApp vi;
	@MockBean
	private RestTemplate restTemplate;
	
	@Before
	public void setUp() throws Exception {
		vi=new ViewApp(null, null);
		vi.setrestTemplate(restTemplate);
		
	}

	@Test
	public final void testViewApp() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "TOKEN");
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		ViewEntity vista=new ViewEntity();
		vista.setContent("HTML");
		vista.setName("TEST");
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/cmsrest/view/getByName/test", HttpMethod.GET, httpEntity,ViewEntity.class)).thenReturn(new ResponseEntity<ViewEntity>(vista,HttpStatus.OK));
		Mockito.when(vi.loadView("TEST")).thenReturn(new ResponseEntity<ViewEntity>(vista,HttpStatus.OK));
		Assert.assertNotNull(vi.loadView("TEST"));
		vi.addView("TEST");		
		Assert.assertNotNull(vi.render());
	}

*/
}

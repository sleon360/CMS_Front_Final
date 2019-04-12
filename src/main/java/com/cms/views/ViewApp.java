package com.cms.views;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.appcms.entity.ViewEntity;
import com.appcms.security.RestAuthentication;

public class ViewApp {

	StringBuilder sb=new StringBuilder("");
	private HttpServletRequest rqx;
	
	public ViewApp( HttpServletRequest rq)
	{
		rqx=rq;
	}
	
	private String loadView(String view)
	{
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        RestAuthentication xrestAuthentication =new RestAuthentication();
        System.out.println(xrestAuthentication.getTOKENONE()+" 666666666666666666666666666666666666666");
        headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
      
        HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ViewEntity> response = restTemplate.exchange("http://localhost:9080/cmsrest/view/getByName/"+view,HttpMethod.GET, httpEntity, ViewEntity.class);
        if(response.getStatusCodeValue()==200)
	        {
        	ViewEntity vV = response.getBody();
        	System.out.println(vV.getContent()+"--------------------------------------------------");
        	return vV.getContent();
	        }
        else
	        {
	        	return "Not Load "+view;
	        }	
	}
	
	public void addView(String viewName)
	{
		String data=this.loadView(viewName);
		sb.append(data);
	}
	
	
	public String render()
	{
		return sb.toString();
	}
	
	
}

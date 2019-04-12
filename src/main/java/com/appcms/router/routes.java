package com.appcms.router;

import java.net.ConnectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import com.cms.views.ViewApp;



@Controller
public class routes {
	
	@RequestMapping("/home")
	public ModelAndView index(HttpServletRequest rq)
	{
		ViewApp vi=new ViewApp(rq);
		vi.addView("header");
		vi.addView("home");
		ModelAndView mav = new ModelAndView(vi.render());
		return mav;
	}
	
	@RequestMapping("/")
	public ModelAndView home()
	{
		return new ModelAndView("redirect:/home");
	}
	
	
	@RequestMapping("/test")
	public String groovy()
	{
		return "index";
	}
	
	@RequestMapping("/404")
	public ModelAndView notfound(HttpServletRequest rq)
	{
		
		ViewApp vi=new ViewApp(rq);
		vi.addView("header");
		vi.addView("404");
		ModelAndView mav = new ModelAndView(vi.render());
		return mav;
	}
	
	
	
	
	

	@ExceptionHandler(value = {Exception.class,MultipartException.class,NestedServletException.class,NestedServletException.class,ConnectException.class })
	@RequestMapping("/errores")
	public String error()
	{
		return "error";
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest rq)
	{
		ViewApp vi=new ViewApp(rq);
		vi.addView("header");
		vi.addView("login");
		ModelAndView mav = new ModelAndView(vi.render());
		return mav;
	}
	
	@RequestMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){   
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home";
    }

	@RequestMapping("/admin")
	public ModelAndView admin(HttpServletRequest rq)
	{
		ViewApp vi=new ViewApp(rq);
		vi.addView("header");
		vi.addView("admin");
		ModelAndView mav = new ModelAndView(vi.render());
		return mav;
	}

	
	
}

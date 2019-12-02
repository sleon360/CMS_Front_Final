package com.appcms.router;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.appcms.entity.Scotiauser;
import com.appcms.entity.UserGusto;
import com.appcms.entity.customer.Customer;
import com.appcms.model.DataServer;
import com.appcms.services.CustomerService;

@Component
public class CategoryHeaderSetter {

	@Autowired
	private DataServer dtserver;

	@Autowired
	private CustomerService customerModel;

	public void setHeaders(ModelAndView mav) {
		mav.addObject("menuesHeader", dtserver.loadAllScmenu());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			Customer customer = (Customer) auth.getPrincipal();
			mav.addObject("usuario", customer.getScotiauser());
			mav.addObject("points", customerModel.loadUserPoints());
			// Se agregan los gustos del usuario
			List<UserGusto> gustos = dtserver.loadGustos();
			List<UserGusto> gustosCliente = customerModel.loadCustomerGustos();
			for (int i = 0; i < gustos.size(); i++) {
				UserGusto gusto = gustos.get(i);
				for (int j = 0; j < gustosCliente.size(); j++) {
					UserGusto gustoCliente = gustosCliente.get(j);
					if (gusto.getId() == gustoCliente.getId()) {
						gusto.setGustado(true);
						break;
					}
				}
			}
			mav.addObject("gustos", gustos);
		} else {
			mav.addObject("usuario", new Scotiauser());
		}
	}

}

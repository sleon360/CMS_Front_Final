package com.appcms.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appcms.entity.user.transactions.TransactionsData;
import com.appcms.services.CustomerService;

@RestController
public class RouteCartola {
	
	@Autowired
	private CustomerService customerModel;
	
	@GetMapping("/user/cartola")
	public TransactionsData actualizarGustos(@RequestParam int year, @RequestParam(name = "next_cursor", required = false) String nextCursor) {
		return customerModel.loadUserTransactions(year, nextCursor);
	}
}

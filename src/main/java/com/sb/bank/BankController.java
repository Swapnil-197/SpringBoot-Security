package com.sb.bank;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController 
{
	@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
	@GetMapping("/home")
    public String getMsg()
    {
    	String msg = "Welcome to State Bank Of India";
    	return msg ;
    }
    
	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	@GetMapping("/balance")
    public String getBalance()
    {
    	String msg = "Your Account Balance is Rs 50,000";
    	return msg;
    }
    
	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	@GetMapping("/myloan")
    public String myloan()
    {
    	String msg = "Your loan Due Amount is : Rs 3,50,000";
    	return msg;
    }
    
	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	@GetMapping("/statement")
    public String getStatement()
    {
    	String msg =  "Your statemnt has been send to your email";
    	return msg;	
    }
    
	@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
	@GetMapping("/contact")
    public String contact()
    {
    	String msg = "Thank you for contacting with us";
    	return msg;
    }
}

package com.sp.sp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.sp.entity.Customer;
import com.sp.sp.entity.Owner;
import com.sp.sp.service.OwnerService;

@RestController
@RequestMapping("owner")
@CrossOrigin
public class OwnerController {
	@Autowired
	private OwnerService ownerService;
	@PostMapping
	public ResponseEntity<Owner> save (@RequestBody Owner owner) throws Exception
	{
		return ResponseEntity.ok(ownerService.save(owner));
	}
	//DTO: Data Transfer Object 
	@GetMapping("verification/email/{email}/activationCode/{activationCode}")
	public String emailVerification(@PathVariable String email, @PathVariable String activationCode) throws Exception
	{
		String message = "Not verified.";
	    Owner owner = ownerService.findByEmail(email);
		if(activationCode.equals(owner.getActivationCode()))
		{
			message = "<h1>Your email account verified successfully</h1>";
			owner.setStatus(1);
			ownerService.update(owner);
		}
	
	return message;

}
}

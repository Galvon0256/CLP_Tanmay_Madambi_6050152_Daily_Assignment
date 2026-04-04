package com.cg.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {
	@GetMapping("/publicEndPoint")
	public String forPublic() {
		return "This is a public endpoint accessible to everyone.";
	}
	
	@GetMapping("/SecuredUserEndPoint")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public String forUser() {
		return "This is a secured endpoint accessible to users with USER role only.";
	}
	@GetMapping("/SecuredAdminEndPoint")
	@PreAuthorize("hasRole('ADMIN')")
	public String forAdmin() {
		return "This is a secured endpoint accessible to users with ADMIN role only.";
	}
}

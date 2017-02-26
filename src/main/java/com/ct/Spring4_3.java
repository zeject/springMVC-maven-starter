package com.ct;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Spring4_3 {

	@PutMapping(value = "/putUrl")
	public String put() {
		return "put";
	}

}

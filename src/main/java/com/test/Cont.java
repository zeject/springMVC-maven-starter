package com.test;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.config.Response;

@Controller
public class Cont {

	@RequestMapping(value = "/ppp")
	@ResponseBody
	public Response ppp(@Valid PojoBean pb) {
		System.out.println(pb.toString());
		return new Response().success("abadwd");
	}

	@RequestMapping(value = "/aaa")
	public String abc() {
		// Map map = null;
		// System.out.println(map.get("a"));
		return "index.jsp";
	}

	@ResponseBody
	@RequestMapping(value = "/j")
	public Map abc(Map resMap) {
		resMap.put("data", 1);
		return resMap;
	}

}

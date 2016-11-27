package com.test;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Cont {

	@RequestMapping(value = "/aaa")
	public String abc() {
		Map map = null;
		System.out.println(map.get("a"));
		return "index.jsp";
	}

	@ResponseBody
	@RequestMapping(value = "/j")
	public Map abc(Map resMap) {
		resMap.put("data", 1);
		return resMap;
	}

}

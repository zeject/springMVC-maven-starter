package com.ct;

import com.config.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class ContController {

    private static Logger logger = LogManager.getLogger(ContController.class);

    @Scheduled(cron = "0/5 * *  * * ? ")   //每5秒执行一次
    public void myTest() {
        System.out.println("进入测试");
    }

    @RequestMapping(value = "/ppp")
    @ResponseBody
    public Response ppp(@Valid PojoBean pb) {
        System.out.println(pb.toString());
        return new Response().success("abadwd");
    }

    @RequestMapping(value = "/aaa")
    public ModelAndView abc(HttpServletRequest request) {
        // Map map = null;
        // System.out.println(map.get("a"));
        ModelAndView mv = new ModelAndView();
        mv.addObject("data", "this is data");
        mv.setViewName("index.jsp");
        return mv;
    }

    @RequestMapping(value = "/http")
    public String http(HttpServletRequest request) {
        // Map map = null;
        // System.out.println(map.get("a"));
        request.setAttribute("data", "this is res data");
        return "index.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/j")
    public Map abc(Map resMap) {
        resMap.put("data", 1);
        return resMap;
    }


    public static void main(String[] args) {
//        Jdbc.find("select * from spring_user");
    }

}

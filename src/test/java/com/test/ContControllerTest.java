package com.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Created by zeject on 2017/2/26.
 */
public class ContControllerTest extends BaseControllerTest {

    public ContControllerTest() {
        setPrint(false);
    }

    @Test
    public void testJson() throws Exception {
        ResultActions ra = getJson("/j.json");
        System.out.println(ra.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testJsp() throws Exception {
        ResultActions ra = getPage("/aaa", "index.jsp");
        ra.andExpect(model().attributeExists("data"));
        MvcResult result = ra.andReturn();
        Assert.assertNotNull("data数据为空", result.getModelAndView().getModel().get("data"));

    }

    @Test
    public void testJspForReq() throws Exception {
        ResultActions ra = getPage("/http", "index.jsp");
        MvcResult result = ra.andReturn();
        Assert.assertNotNull(result.getRequest().getAttribute("data"));

    }

}

package com.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by zeject on 2017/2/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:/**/dispatcherServlet-servlet1.xml")
public class BaseControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    private boolean print = true;

    public void setPrint(boolean print) {
        this.print = print;
    }

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    protected ResultActions getJson(String url, Object... obj) throws Exception {
        return json("get", url, obj);
    }

    protected ResultActions postJson(String url, Object... obj) throws Exception {
        return json("post", url, obj);
    }

    protected ResultActions putJson(String url, Object... obj) throws Exception {
        return json("put", url, obj);
    }

    protected ResultActions deleteJson(String url, Object... obj) throws Exception {
        return json("delete", url, obj);
    }

    protected ResultActions headJson(String url, Object... obj) throws Exception {
        return json("head", url, obj);
    }

    protected ResultActions json(String method, String url, Object... obj) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder;
        mockHttpServletRequestBuilder = getMockHttpServletRequestBuilder(method, url, obj);

        RequestBuilder requestBuilder = mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        ResultActions ra = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        if (print) {
            ra.andDo(print());
        }
        return ra;
    }


    protected ResultActions getPage(String url, String view, Object... obj) throws Exception {
        return page("get", url, view, obj);
    }

    protected ResultActions postPage(String url, String view, Object... obj) throws Exception {
        return page("post", url, view, obj);
    }

    protected ResultActions putPage(String url, String view, Object... obj) throws Exception {
        return page("put", url, view, obj);
    }

    protected ResultActions deletePage(String url, String view, Object... obj) throws Exception {
        return page("delete", url, view, obj);
    }

    protected ResultActions page(String method, String url, String view, Object... obj) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder;
        mockHttpServletRequestBuilder = getMockHttpServletRequestBuilder(method, url, obj);

        RequestBuilder requestBuilder = mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        ResultActions ra = mockMvc.perform(requestBuilder)
                .andExpect(view().name(view))
                .andExpect(status().isOk());

        if (print) {
            ra.andDo(print());
        }
        return ra;

    }

    private MockHttpServletRequestBuilder getMockHttpServletRequestBuilder(String method, String url, Object[] obj) {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder;
        if (method.equals("head")) {
            mockHttpServletRequestBuilder = head(url, obj);
        } else if (method.equals("post")) {
            mockHttpServletRequestBuilder = post(url, obj);
        } else if (method.equals("put")) {
            mockHttpServletRequestBuilder = put(url, obj);
        } else if (method.equals("delete")) {
            mockHttpServletRequestBuilder = delete(url, obj);
        } else {
            mockHttpServletRequestBuilder = get(url, obj);
        }
        return mockHttpServletRequestBuilder;
    }

}

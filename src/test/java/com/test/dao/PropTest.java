package com.test.dao;

import com.base.BaseTest;
import com.ct.Propties;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Created by zeject on 2017/3/1.
 */
public class PropTest extends BaseTest {

    @Autowired
    private Environment env;

    @Autowired
    private Propties p;

    @Test
    public void te() {
        System.out.println(1111);
        System.out.println(env.getProperty("text"));
        System.out.println("===" + p.text);

    }


}

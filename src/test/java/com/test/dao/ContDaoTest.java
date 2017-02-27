package com.test.dao;

import com.ct.ContDao;
import com.base.BaseDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zeject on 2017/2/27.
 */
public class ContDaoTest extends BaseDaoTest {

    @Autowired
    private ContDao contDao;

    @Test
    public void getData() {
        List list = contDao.getData();
    }

}

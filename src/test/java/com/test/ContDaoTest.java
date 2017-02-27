package com.test;

import com.ct.ContDao;
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

package com.ct;

import com.util.jdbc.Jdbc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zeject on 2017/2/27.
 */
@Component
public class ContDao {

    private static Logger logger = LogManager.getLogger(ContDao.class);


    public List getData() {
        return Jdbc.find("select * from spring_user");
    }

    public static void main(String[] args) {
        logger.error("1234234234");
    }

}

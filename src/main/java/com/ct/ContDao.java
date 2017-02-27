package com.ct;

import com.util.jdbc.Jdbc;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zeject on 2017/2/27.
 */
@Component
public class ContDao {

    public List getData() {
        return Jdbc.find("select * from verify_yzm");
    }

}

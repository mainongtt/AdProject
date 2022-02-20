package com.jd.dao;

import com.jd.entity.AdUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdUserDaoTest {

    @Autowired(required = false)
    private AdUserDao adUserDao;

    @Test
    public void test1(){
        List<AdUser> allUser = adUserDao.findAllUser(new AdUser(15l));
        System.out.println(allUser);
    }

    @Test
    @Transactional
    public void test2(){
        AdUser adUser = new AdUser("12", "234");
        adUserDao.save(adUser);
        System.out.println(adUser.getId());
    }
}


package com.atguigu.crowd.test;


import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;

    @Test
    public void test3(){
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andEmailLike("%7%");
        List<Admin> list = adminMapper.selectByExample(adminExample);
        for (Admin admin : list) {
            System.out.println(admin);
        }
    }

    @Test
    public void test2(){
        PageHelper.startPage(2,10);
        List<Admin> list = adminMapper.selectAdminByKeyword("");
        for (Admin admin : list) {
            System.out.println(admin);
        }
    }

    @Test
    public void testSelectAdminByKeyword(){
        PageInfo<Admin> pageInfo = adminService.getPageInfo("", 1, 5);
        List<Admin> list = pageInfo.getList();
        for (Admin admin : list) {
            System.out.println(admin);
        }
    }

    @Test
    public void test(){
        for (int i = 0; i < 238; i++) {
            adminMapper.insert(new Admin(null,"loginAcct"+i,"userPswd"+i,"userName"+i,"email"+i,null));
        }
    }


    @Test
    public void testLog(){
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        logger.debug("-------------bug");
        logger.debug("-------------bug");
        logger.debug("-------------bug");
        logger.info("--------------info");
        logger.info("--------------info");
        logger.info("--------------info");
        logger.warn("--------------warn");
        logger.warn("--------------warn");
        logger.warn("--------------warn");
        logger.error("-------------error");
        logger.error("-------------error");
        logger.error("-------------error");
    }

    @Test
    public void testInsertAdmin(){
        Admin admin = new Admin(null, "tom", "123123", "汤姆", "tom@qq.com", null);
        int count = adminMapper.insert(admin);
        System.out.println("受影响的行数="+count);
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }
}


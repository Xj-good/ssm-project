package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.entity.AdminExample.Criteria;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);

    }
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPwd) {
        //1.根据登录账号查询 Admin对
            //创建 AdminExample对象
        AdminExample adminExample = new AdminExample();
            //创建 Criteria对象
        Criteria criteria = adminExample.createCriteria();
        //在 Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
            //调用 AdminMapper的方法执行查询
        List<Admin> list = adminMapper.selectByExample(adminExample);
        System.out.println(list);
        // 2.判断 Admin对象是否为nu11
        if (list == null || list.size() ==0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (list.size()>1){
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = list.get(0);
        System.out.println(admin);
        System.out.println(admin==null);

        //3.如果 Admin对象为nu11则抛出异常
        if (admin == null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 4.如果 Admin对象不为nu11则将数据库密码从Admin对象中取出
        String userPswdDB = admin.getUserPswd();

        // 5.将表单提交的明文密码进行加密
        String userPswdForm = CrowdUtil.md5(userPwd);

        // 6.对密码进行比较
        if (!Objects.equals(userPswdDB,userPswdForm)){
            // 7.如果比较结果是不一致则抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 8.如果一致则返回 Admin对象
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        //1.调用 PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        //2.执行查询
        List<Admin> list = adminMapper.selectAdminByKeyword(keyword);
        // 3.封装到PageInfo对象中
        return new PageInfo<Admin>(list);
    }
}

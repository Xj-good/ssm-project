package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.api.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminHandler {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            @RequestParam(value = "keyword",defaultValue ="" ) String keyword,
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
            ModelMap modelMap
    ){
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-page";
    }

    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }

    @RequestMapping("admin/do/login.html")
    public String doLogin(@RequestParam String loginAcct, @RequestParam String userPwd, HttpSession session){
        Admin admin = adminService.getAdminByLoginAcct(loginAcct,userPwd);
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);
        return "redirect:/admin/to/main/page.html";
    }
}

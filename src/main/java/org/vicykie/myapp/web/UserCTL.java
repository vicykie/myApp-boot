package org.vicykie.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vicykie.myapp.dao.UserDAO;
import org.vicykie.myapp.entities.authority.UserInfo;
import org.vicykie.myapp.util.PasswordUtils;
import org.vicykie.myapp.vo.ResponseVO;

import java.util.Date;
import java.util.List;

/**
 * Created by d on 2016/5/5.
 */
@RequestMapping("/user")
@Controller("userCTL")
public class UserCTL extends AbstractUserController {
    @Autowired
    @Qualifier("mongoUserDAO")      //根据名称
    private UserDAO userDAO;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<UserInfo> addUser(@PathVariable("id") int id) {
        UserInfo user = new UserInfo();
//        user.setId(id);
        user.setName("vicykie");
        userDAO.addUser(user);
        UserInfo u = userDAO.getUserById(id);
        return ResponseVO.getDataSuccess(u, "获取成功");
    }

    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("user", userDAO.getUserById(22));
        return "user/info";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", userDAO.getUserById(1));
        return "user/info";
    }

    @RequestMapping(value = "/t", method = RequestMethod.PATCH)
    public String tes(Model model, @RequestBody Object statisticData) {
        System.out.println(statisticData != null);
        model.addAttribute("user", userDAO.getUserById(1));
        return "user/info";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAddUserPage(Model model) {
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(Model model, UserInfo user) {
        String salt = "123456";
        String encodePassword = PasswordUtils.encodeUserPassword(user.getPassword(), salt);
        user.setPassword(encodePassword);
        Date now = new Date();
        user.setExpireDate(new Date(now.getTime() + 1000 * 3600 * 100));
        user.setLocked(false);
        user.setCreateDate(now);
        user.setSalt(salt);
        int userId = userDAO.addUser(user);
        model.addAttribute("userId", userId);
        return "redirect:list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getAll(Model model) {
        return "user/list";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<UserInfo> getAllUsers() {
        List<UserInfo> users = userDAO.getAllUsers();
        return users;
    }


}

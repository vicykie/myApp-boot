package org.vicykie.myapp.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vicykie.myapp.dao.UserDAO;
import org.vicykie.myapp.entities.authority.RoleInfo;
import org.vicykie.myapp.entities.authority.UserInfo;
import org.vicykie.myapp.service.async.AsyncService;
import org.vicykie.myapp.util.PasswordUtils;
import org.vicykie.myapp.vo.ResponseVO;

import java.util.Date;
import java.util.List;

/**
 * Created by vicykie on 2016/5/5.
 */
@RequestMapping("/user")
@Controller("userCTL")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class UserCTL extends AbstractUserController {
    @Autowired
    @Qualifier("mongoUserDAO")      //根据名称
    private UserDAO userDAO;

    @Autowired
    private AsyncService asyncService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String addUser(@PathVariable("id") String id, Model model) {
        UserInfo u = userDAO.getUserById(id);
        model.addAttribute("user", u);
        return "user/info";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<?> updateUser(@PathVariable("id") String id, String roleId) {
        UserInfo userInfo = userDAO.getUserById(id);
        RoleInfo roleInfo = userDAO.getRoleById(roleId);
        userInfo.setRoleInfo(roleInfo);
        int status = userDAO.updateUser(userInfo);
        return ResponseVO.updateSuccess("更新成功");
    }
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<?> test(UserInfo userInfo) {

        return ResponseVO.updateSuccess("更新成功");
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "user/list";
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
//    @JsonView(UserInfo.NoPassWord.class)
    public List<UserInfo> getAllUsers() {
        List<UserInfo> users = userDAO.getAllUsers();
        return users;
    }

    @RequestMapping(value = "/async")
    @ResponseBody
    public void asyncTest(){
        asyncService.task1();
        asyncService.task2();
        asyncService.task3();
    }
}

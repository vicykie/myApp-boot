package org.vicykie.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vicykie.myapp.dao.UserDAO;
import org.vicykie.myapp.entities.authority.RoleInfo;

/**
 * Created by vicykie on 2016/6/28.
 */
@Controller
@RequestMapping("/role")
public class RoleCTL extends AbstractUserController {
    @Autowired
    @Qualifier("mongoUserDAO")      //根据名称
    private UserDAO userDAO;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAddUserPage(Model model) {
        return "role/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(Model model, RoleInfo role) {
        int roleId = userDAO.addRole(role);
        return "redirect:list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getAll(Model model) {

        return "role/list";
    }


}

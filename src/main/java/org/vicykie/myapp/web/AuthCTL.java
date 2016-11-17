package org.vicykie.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vicykie.myapp.dao.UserDAO;
import org.vicykie.myapp.entities.authority.OperationInfo;
import org.vicykie.myapp.entities.authority.RoleInfo;
import org.vicykie.myapp.vo.ResponseVO;
import org.vicykie.myapp.web.user.AbstractUserController;

/**
 * Created by vicykie on 2016/6/28.
 */
@Controller
@RequestMapping
@PreAuthorize("hasRole('ADMIN')")
public class AuthCTL extends AbstractUserController {
    @Autowired
    @Qualifier("mongoUserDAO")      //根据名称
    private UserDAO userDAO;

    @RequestMapping(value = "role/add", method = RequestMethod.GET)
    public String toAddRolePage(Model model) {
        return "role/add";
    }

    @RequestMapping(value = "role/add", method = RequestMethod.POST)
    public String addRole(Model model, RoleInfo role) {
        int roleId = userDAO.addRole(role);
        return "redirect:list";
    }

    @RequestMapping(value = "role/list", method = RequestMethod.GET)
    public String getAllRole(Model model) {
        return "role/list";
    }

    @RequestMapping(value = "auth/add", method = RequestMethod.GET)
    public String toAddAuthPage(Model model) {
        return "auth/add";
    }

    @RequestMapping(value = "auth/add", method = RequestMethod.POST)
    public String addAuth(Model model, OperationInfo authorityInfo) {
        int roleId = userDAO.addAuth(authorityInfo);
        return "redirect:list";
    }

    @RequestMapping(value = "auth/list", method = RequestMethod.GET)
    public String getAllAuth(Model model) {
        return "auth/list";
    }


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model) {
        model.addAttribute(ResponseVO.accessDenied());
        return "auth/403";
    }


}

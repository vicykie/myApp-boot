package org.vicykie.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vicykie.myapp.dao.UserDAO;
import org.vicykie.myapp.entities.authority.RoleInfo;
import org.vicykie.myapp.entities.authority.UserInfo;
import org.vicykie.myapp.util.PasswordUtils;
import org.vicykie.myapp.vo.ResponseVO;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.Enumeration;
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
    public ResponseVO<?> test() {

        return ResponseVO.updateSuccess("更新成功");
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        // model.addAttribute("user", userDAO.getUserById("dd"));
        Enumeration<String> headerNames = request.getHeaderNames();
        System.out.println("-----------request headers -----------------");
        while (headerNames.hasMoreElements()) {
            System.out.println(headerNames.nextElement());
        }

        System.out.println("-----------security context -----------------");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);


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
    public List<UserInfo> getAllUsers() {
        List<UserInfo> users = userDAO.getAllUsers();
        return users;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        String xmlStr ="";
        StringReader sr = new StringReader(xmlStr);
        InputSource is = new InputSource(sr);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        doc.getXmlEncoding();
    }
}

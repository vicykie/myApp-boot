package org.vicykie.myapp.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by vicykie on 2016/7/1.
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
//==> http://docs.spring.io/spring-security/site/docs/current/reference/html/el-access.html#el-common-built-in
public class AdminCTL extends AbstractUserController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)

    public String index(Model model) {
        return "admin/list";
    }

}
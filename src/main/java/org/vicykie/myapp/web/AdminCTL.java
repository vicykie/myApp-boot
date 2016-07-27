package org.vicykie.myapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by vicykie on 2016/7/1.
 */
@Controller
@RequestMapping("/admin")
public class AdminCTL extends AbstractUserController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/list";
    }

}
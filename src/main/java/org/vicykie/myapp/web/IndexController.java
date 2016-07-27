package org.vicykie.myapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by vicykie on 2016/6/28.
 */
@Controller
@RequestMapping
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "login";
    }
}

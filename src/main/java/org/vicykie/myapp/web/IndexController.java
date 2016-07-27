package org.vicykie.myapp.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vicykie on 2016/6/28.
 */
@RestController
@RequestMapping
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "login";
    }
}

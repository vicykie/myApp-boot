package org.vicykie.myapp.web.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 李朝衡 on 2016/8/16.
 * 错误页面
 */
@Controller
public class ErrorCTL {
    @RequestMapping("/error/401")
    public String error401(){
        return "401";
    }
    @RequestMapping("/error/403")
    public String error403(){
        return "401";
    }
    @RequestMapping("/error/500")
    public String error500(){
        return "401";
    }
}

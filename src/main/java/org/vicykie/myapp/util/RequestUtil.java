package org.vicykie.myapp.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vicykie on 2016/6/7.
 */
public class RequestUtil {
    public static boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return header != null;
    }
}

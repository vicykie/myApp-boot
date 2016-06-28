package org.vicykie.myapp.util;

import org.apache.http.entity.ContentType;

/**
 * Created by vicykie on 2016/6/23.
 */
public class HttpUtils {

    public static boolean isFormRequest(String contentType) {
        boolean formEncoded = contentType.contains(ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
        boolean multipartForm = contentType.contains(ContentType.MULTIPART_FORM_DATA.getMimeType());
        return formEncoded || multipartForm;
    }

    public static boolean isJsonRequest(String contentType) {
        boolean json = contentType.contains(ContentType.APPLICATION_JSON.getMimeType());
        return json;
    }

}

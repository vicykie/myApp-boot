package org.vicykie.myapp.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vicykie on 2016/6/23.
 */
public class HttpUtils {

    public static boolean isFormRequest(String contentType) {
        if (contentType != null) {
            boolean formEncoded = contentType.contains(ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
            boolean multipartForm = contentType.contains(ContentType.MULTIPART_FORM_DATA.getMimeType());
            return formEncoded || multipartForm;
        } else {
            return false;
        }
    }

    public static boolean isAjax(HttpServletRequest request) {
        String ajax = request.getHeader("X-Requested-With");
        if (ajax != null) {
            return ajax.equalsIgnoreCase("XMLHttpRequest");
        } else {
            return false;
        }
    }

    public static boolean isJsonRequest(String contentType) {
        boolean json = contentType.contains(ContentType.APPLICATION_JSON.getMimeType());
        return json;
    }

    public static String post(String url, Map<String, Object> parameterMap) {
        return post(url, null, parameterMap);
    }

    public static String post(String url, Header header, Map<String, Object> parameterMap) {
        String siteUrl = url;
        if (siteUrl.startsWith("https")) {
            return postSSL(siteUrl, parameterMap);
        }
        String result = null;
        HttpResponse httpResponse = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            setParameter(parameterMap, nameValuePairs);
            if (header != null) {
                httpPost.setHeader(header);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * https请求
     *
     * @param url
     * @param parameterMap
     * @return
     */
    private static String postSSL(String url, Map<String, Object> parameterMap) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
        HttpPost httpPost = null;
        try {
            SSLContext sslContext = SSLContexts.createDefault();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            setParameter(parameterMap, nameValuePairs);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.abort();
        }
        return result;
    }

    private static void setParameter(Map<String, Object> parameterMap, List<NameValuePair> nameValuePairs) {
        if (parameterMap != null) {
            for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                String name = entry.getKey();
                String value = entry.getValue().toString();
                if (!StringUtils.isEmpty(name)) {
                    nameValuePairs.add(new BasicNameValuePair(name, value));
                }
            }
        }
    }
//
//    public static void main(String[] args) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("tsData", "豹王机油滤清器TO-6741（NISSAN（尼桑））; s  ；】 &#xff08;");
//        String url = "http://localhost:8044/user/test";
//        String s = post(url, params);
//        System.out.println(s);
//    }
}

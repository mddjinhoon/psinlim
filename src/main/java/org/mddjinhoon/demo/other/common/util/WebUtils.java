package org.mddjinhoon.demo.other.common.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WebUtils {

    public static String getBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) {
            return "MSIE";
        } else if (userAgent.contains("Chrome")) {
            return "Chrome";
        } else if (userAgent.contains("Opera")) {
            return "Opera";
        } else if (userAgent.contains("Safari")) {
            return "Safari";
        } else if (userAgent.contains("Firefox")) {
            return "Firefox";
        } else {
            return "Chrome";
        }
    }

    public static String getFileName(String browser, String fileNm) throws UnsupportedEncodingException {
        String reFileNm;
        if (browser.equals("MSIE") || browser.equals("Trident") || browser.equals("Edge")) {
            reFileNm = URLEncoder.encode(fileNm, "UTF-8").replaceAll("\\+", "%20");
        } else {
            if (browser.equals("Chrome")) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < fileNm.length(); i++) {
                    char c = fileNm.charAt(i);
                    if (c > '~') {
                        sb.append(URLEncoder.encode(Character.toString(c), "UTF-8"));
                    } else {
                        sb.append(c);
                    }
                }
                reFileNm = sb.toString();
            } else {
                reFileNm = new String(fileNm.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
            if (browser.equals("Safari") || browser.equals("Firefox"))
                reFileNm = URLDecoder.decode(reFileNm, "UTF-8");
        }
        return reFileNm;
    }
}

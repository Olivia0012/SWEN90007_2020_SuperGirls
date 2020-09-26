package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.StringUtils;



public class CORSFilter implements Filter{
	private boolean isCross = false;
    private String excludedPaths = null;
    private String [] excludedPathArray;

    public void destroy() {
        isCross = false;
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 判断是否是直接放行的请求
        if (!isFilterExcludeRequest(httpServletRequest)) {
            if (isCross) {
                httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("origin"));
                httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                httpServletResponse.setHeader("Access-Control-Max-Age", "0");
                httpServletResponse.setHeader("Access-Control-Allow-Headers",
                        "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
                httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
                httpServletResponse.setHeader("XDomainRequestAllowed", "1");
            }
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        String isCrossStr = filterConfig.getInitParameter("IsCross");
        isCross = isCrossStr.equals("true") ? true : false;

        excludedPaths = filterConfig.getInitParameter("excludedPaths");
       /* if(!StringUtils.isEmpty(excludedPaths)){
            excludedPathArray = excludedPaths.split(",");
        }*/
    }
    private boolean isFilterExcludeRequest(HttpServletRequest request) {
        if(null != excludedPathArray && excludedPathArray.length > 0) {
            String url = request.getRequestURI();
            for (String ecludedUrl : excludedPathArray) {
                if (ecludedUrl.startsWith("*.")) {
                    // 如果配置的是后缀匹配, 则把前面的*号干掉，然后用endWith来判断
                    if(url.endsWith(ecludedUrl.substring(1))){
                        return true;
                    }
                } else if (ecludedUrl.endsWith("/*")) {
                    if(!ecludedUrl.startsWith("/")) {
                        // 前缀匹配，必须要是/开头
                        ecludedUrl = "/" + ecludedUrl;
                    }
                    // 如果配置是前缀匹配, 则把最后的*号干掉，然后startWith来判断
                    String prffixStr = request.getContextPath() + ecludedUrl.substring(0, ecludedUrl.length() - 1);
                    if(url.startsWith(prffixStr)) {
                        return true;
                    }
                } else {
                    // 如果不是前缀匹配也不是后缀匹配,那就是全路径匹配
                    if(!ecludedUrl.startsWith("/")) {
                        // 全路径匹配，也必须要是/开头
                        ecludedUrl = "/" + ecludedUrl;
                    }
                    String targetUrl = request.getContextPath() + ecludedUrl;
                    if(url.equals(targetUrl)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

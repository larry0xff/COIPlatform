package zhongd.coiplatform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**解决跨域问题的过滤器
 * @author zhongd
 *
 */
@Component  
public class CorsFilter implements Filter {  
  
    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);  
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {  
        HttpServletResponse response = (HttpServletResponse) res;  
        response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        chain.doFilter(req, res);  
    }  
    public void init(FilterConfig filterConfig) {}  
    public void destroy() {}  
}  

package zhongd.coiplatform.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		factoryBean.setSecurityManager(securityManager);
		
		Map<String, String> filterChainMap = new HashMap<String, String>();
		filterChainMap.put("/user/test", "authc");
		// 注销url
		filterChainMap.put("/logout", "logout");
		// 需要授权访问的链接
//		filterChainMap.put("/**", "authc");
		// anon表示可以匿名访问的url
		filterChainMap.put("/**", "anon");
		
		factoryBean.setSuccessUrl("http://baidu.com");
		factoryBean.setLoginUrl("/user/unlogin");
		factoryBean.setUnauthorizedUrl("/403");
		factoryBean.setFilterChainDefinitionMap(filterChainMap);
		return factoryBean;
	}
	@Bean
	public MyShiroRealm myShiroRealm() {
		return new MyShiroRealm();
	}
	@Bean
	public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    } 
}

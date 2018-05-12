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
		filterChainMap.put("/page/login/login.html", "anon");
		// 注销url
		filterChainMap.put("/logout", "logout");

		//角色操作的权限
		filterChainMap.put("/role/insert", "authc,perms[role_insert]");
		filterChainMap.put("/role/delete", "authc,perms[role_delete]");
		filterChainMap.put("/role/list", "authc,perms[role_list]");
		filterChainMap.put("/role/setPermission", "authc,perms[role_set_permission]");
		filterChainMap.put("/role/rmPermission", "authc,perms[role_remove_permission]");

		//人员管理权限
		filterChainMap.put("/user/insert", "authc,perms[user_insert]");
		filterChainMap.put("/user/delete", "authc,perms[user_delete]");
		filterChainMap.put("/user/update", "authc,perms[user_update]");
		filterChainMap.put("/user/list", "authc,perms[user_list]");
		filterChainMap.put("/user/setRole", "authc,perms[user_set_role]");
		filterChainMap.put("/user/rmRole", "authc,perms[user_remove_role]");

		//组织管理权限
		filterChainMap.put("/org/list", "authc,perms[org_list]");
		filterChainMap.put("/org/update", "authc,perms[org_update]");
		filterChainMap.put("/org/delete", "authc,perms[org_delete]");
		filterChainMap.put("/org/save", "authc,perms[org_save]");

		//成员管理
		filterChainMap.put("/member/list", "authc,perms[member_list]");
		filterChainMap.put("/member/delete", "authc,perms[member_delete]");
		filterChainMap.put("/member/insert", "authc,perms[member_insert]");
		filterChainMap.put("/member/resetPassword", "authc,perms[member_reset_password]");
		filterChainMap.put("/member/bulkinsert", "authc,perms[member_bulkinsert]");
		filterChainMap.put("/member/bulkRecords", "authc,perms[member_bulkRecords]");

		//信箱权限
		filterChainMap.put("/mailbox/list", "authc,perms[mailbox_list]");
		filterChainMap.put("/mailbox/reply", "authc,perms[mailbox_reply]");

		//意见征集权限
		filterChainMap.put("/adviceCollection/save", "authc,perms[advice_collection_save]");
		filterChainMap.put("/adviceCollection/delete", "authc,perms[advice_collection_delete]");
		filterChainMap.put("/adviceCollection/list", "authc,perms[advice_collection_list]");
		filterChainMap.put("/adviceCollection/handle", "authc,perms[advice_collection_handle]");


		// 需要授权访问的链接
		filterChainMap.put("/**", "anon");
		// anon表示可以匿名访问的url
//		filterChainMap.put("/page/user/**", "authc");
//		filterChainMap.put("/page/role/**", "authc");
//		filterChainMap.put("/page/index/**", "authc");
		factoryBean.setLoginUrl("/page/login/login.html");
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

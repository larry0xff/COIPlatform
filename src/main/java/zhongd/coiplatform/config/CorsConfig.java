package zhongd.coiplatform.config;

import org.springframework.core.env.PropertySource;

import zhongd.coiplatform.utils.YamlLoadUtil;
public class CorsConfig {
	private CorsConfig() {
        PropertySource propertySource = YamlLoadUtil.getYamlLoad("classpath:application.yml");
        this.tokenheaderName = propertySource.getProperty("shiro.tokenheaderName").toString();
        this.accessControlAllowMethods = propertySource.getProperty("shiro.accessControlAllowMethods").toString();
        this.accessControlAllowHeaders = propertySource.getProperty("shiro.accessControlAllowHeaders").toString();
        this.accessControlMaxAge = propertySource.getProperty("shiro.accessControlMaxAge").toString();
        this.accessControlAllowOrigin = propertySource.getProperty("shiro.accessControlAllowOrigin").toString();
    }
    private static CorsConfig corsConfig= new CorsConfig();
    //静态工厂方法
    public static synchronized  CorsConfig getInstance() {
        return corsConfig;
    }

    //存放token的表头名称
    private String tokenheaderName;

    private String accessControlAllowMethods;

    private String accessControlAllowHeaders;

    private String accessControlMaxAge;

    private String accessControlAllowOrigin;

    public String getTokenheaderName() {
        return tokenheaderName;
    }

    public void setTokenheaderName(String tokenheaderName) {
        this.tokenheaderName = tokenheaderName;
    }

    public String getAccessControlAllowMethods() {
        return accessControlAllowMethods;
    }

    public void setAccessControlAllowMethods(String accessControlAllowMethods) {
        this.accessControlAllowMethods = accessControlAllowMethods;
    }

    public String getAccessControlAllowHeaders() {
        return accessControlAllowHeaders;
    }

    public void setAccessControlAllowHeaders(String accessControlAllowHeaders) {
        this.accessControlAllowHeaders = accessControlAllowHeaders;
    }

    public String getAccessControlMaxAge() {
        return accessControlMaxAge;
    }

    public void setAccessControlMaxAge(String accessControlMaxAge) {
        this.accessControlMaxAge = accessControlMaxAge;
    }

    public String getAccessControlAllowOrigin() {
        return accessControlAllowOrigin;
    }

    public void setAccessControlAllowOrigin(String accessControlAllowOrigin) {
        this.accessControlAllowOrigin = accessControlAllowOrigin;
    }
}

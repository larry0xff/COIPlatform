package zhongd.coiplatform.config.mybatis;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;
@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "mybatis.config")
public class MyBatisConfig implements TransactionManagementConfigurer {

    //private Logger LOGGER = LogManager.getLogger(MyBatisConfig.class);

    private String typeAliasesPackage;

    private String reasonable;

    private String supportMethodsArguments;

    private String returnPageInfo;

    private String params;

    private String mapperLocations;

    @Autowired
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(VendorDatabaseIdProvider vendorDatabaseIdProvider) {

        //LOGGER.debug("生成 SqlSessionFactory");

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置Model 路径
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);

        //设置分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();

        properties.setProperty("reasonable", reasonable);
        properties.setProperty("supportMethodsArguments", supportMethodsArguments);
        properties.setProperty("returnPageInfo", returnPageInfo);
        properties.setProperty("params", params);
        pageHelper.setProperties(properties);

        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        //设置多数据库厂商支持
        sqlSessionFactoryBean.setDatabaseIdProvider(vendorDatabaseIdProvider);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        //LOGGER.debug("生成 SqlSessionTemplate");
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 多数据库支持
     * @return
     */
    @Bean
    public VendorDatabaseIdProvider getVendorDatabaseIdProvider() {

        //LOGGER.debug("生成多数据厂商支持 VendorDatabaseIdProvider");

        VendorDatabaseIdProvider vendorDatabaseIdProvider = new VendorDatabaseIdProvider();
        Properties properties = new Properties();

        properties.setProperty("SQL Server", "sqlserver");
        properties.setProperty("DB2", "db2");
        properties.setProperty("Oracle", "oracle");
        properties.setProperty("MySQL", "mysql");

        vendorDatabaseIdProvider.setProperties(properties);

        return vendorDatabaseIdProvider;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        //LOGGER.debug("生成事务管理器");
        return new DataSourceTransactionManager(dataSource);
    }
    public String getTypeAliasesPackage() {
        return typeAliasesPackage;
    }

    public void setTypeAliasesPackage(String typeAliasesPackage) {
        this.typeAliasesPackage = typeAliasesPackage;
    }

    public String getReasonable() {
        return reasonable;
    }

    public void setReasonable(String reasonable) {
        this.reasonable = reasonable;
    }

    public String getSupportMethodsArguments() {
        return supportMethodsArguments;
    }

    public void setSupportMethodsArguments(String supportMethodsArguments) {
        this.supportMethodsArguments = supportMethodsArguments;
    }

    public String getReturnPageInfo() {
        return returnPageInfo;
    }

    public void setReturnPageInfo(String returnPageInfo) {
        this.returnPageInfo = returnPageInfo;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }


}

package zhongd.coiplatform.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
import zhongd.coiplatform.utils.YamlLoadUtil;

import javax.xml.bind.PropertyException;
import java.util.Properties;
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
class MyBatisMapperScannerConfig {

    //private Logger LOGGER = LogManager.getLogger(MyBatisMapperScannerConfig.class);

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {

        //LOGGER.debug("扫描 Mybatis 配置");

        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");

        try {

            PropertySource propertySource = YamlLoadUtil.getYamlLoad("classpath:application.yml");

            if (propertySource.getProperty("mybatis.config.basePackage").toString() == null) {
                throw new PropertyException("缺少 application.yml: mybatis.config.basePackage 属性");
            }

            if (propertySource.getProperty("mybatis.config.mappers").toString() == null) {
                throw new PropertyException("缺少 application.yml: mybatis.config.mappers 属性");
            }

            if (propertySource.getProperty("mybatis.config.identity") == null) {
                throw new PropertyException("缺少 application.yml: mybatis.config.identity 属性");
            }

            mapperScannerConfigurer.setBasePackage(propertySource.getProperty("mybatis.config.basePackage").toString());
            Properties properties = new Properties();
            properties.setProperty("mappers", propertySource.getProperty("mybatis.config.mappers").toString());
            properties.setProperty("IDENTITY", propertySource.getProperty("mybatis.config.identity").toString());
            mapperScannerConfigurer.setProperties(properties);
        } catch (PropertyException e) {
            e.printStackTrace();
        }

        return mapperScannerConfigurer;
    }

}

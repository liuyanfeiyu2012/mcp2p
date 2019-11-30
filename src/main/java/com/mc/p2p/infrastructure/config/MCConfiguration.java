package com.mc.p2p.infrastructure.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.MultipartConfigElement;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Configuration
@MapperScan(basePackages = "com.mc.p2p.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class MCConfiguration {

    @Configuration
    protected static class DBConfiguration {

        @Bean(destroyMethod = "close")
        public DruidDataSource druidDataSource() throws Exception {
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            druidDataSource.setFilters("config");
            druidDataSource.setConnectionProperties("config.decrypt=false");
            druidDataSource.setInitialSize(50);
            druidDataSource.setMinIdle(30);
            druidDataSource.setMaxActive(50);
            druidDataSource.setMaxWait(60000);
            druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
            druidDataSource.setMinEvictableIdleTimeMillis(300000);
            druidDataSource.setValidationQuery("SELECT 1");
            druidDataSource.setTestWhileIdle(true);
            druidDataSource.setTestOnBorrow(false);
            druidDataSource.setTestOnReturn(false);
            druidDataSource.setPoolPreparedStatements(false);
            druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(0);
            druidDataSource.setRemoveAbandoned(false);
            druidDataSource.setRemoveAbandonedTimeout(1800);
            druidDataSource.setLogAbandoned(true);
            druidDataSource.setUrl("jdbc:mysql://"
                    + "58." + "87." + "119." + "113:" + "4406"
                    + "/mc_p2p?useUnicode=true&characterEncoding=utf-8&useSSL=false");
            druidDataSource.setUsername("root");
            druidDataSource.setPassword("root");
            druidDataSource.init();
            return druidDataSource;
        }

        @Bean
        public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
            SqlSessionFactoryBean sqlSessionFactoryBean =
                    new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(druidDataSource());
            return sqlSessionFactoryBean;
        }

        @Bean
        public DataSourceTransactionManager dataSourceTransactionManager() throws Exception {
            DataSourceTransactionManager dataSourceTransactionManager =
                    new DataSourceTransactionManager();
            dataSourceTransactionManager.setDataSource(druidDataSource());
            return dataSourceTransactionManager;
        }
    }

    @Configuration
    protected static class RedisConfiguration {

        @Bean
        public RedissonConnectionFactory redissonConnectionFactory() {
            Config config = new Config();
            String redisAddress = "redis://58.87"
                    + ".119.113"
                    + ":6380";
            config.useSingleServer()
                    .setAddress(redisAddress)
                    .setConnectTimeout(5000);
            RedissonClient redissonClient = Redisson.create(config);
            return new RedissonConnectionFactory(redissonClient);
        }

        @Bean
        public RedisTemplate<String, Object> redisTemplate(
                RedissonConnectionFactory redisConnectionFactory) {
            RedisTemplate<String, Object> template = new RedisTemplate<>();
            template.setKeySerializer(new StringRedisSerializer());
            template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }

        @Bean
        public StringRedisTemplate stringRedisTemplate(
                RedissonConnectionFactory redisConnectionFactory) {
            StringRedisTemplate template = new StringRedisTemplate();
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }
    }

    @Configuration
    @EnableSwagger2
    public class Swagger2Config {

        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.mc.p2p.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("MCP2P 接口文档")
                    .version("1.0")
                    .build();
        }
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("10240KB");
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
}

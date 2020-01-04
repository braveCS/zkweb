package com.yasenagat.zkweb;
/**
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Shandy
 * SpringBootServletInitializer 是 WebApplicationInitializer 的实现，它从部署在 Web 容器上的传统 WAR 包运行 Spring Boot 应用。
 * 将 Servlet，Filter 和 ServletContextInitializer Bean 从应用程序上下文绑定到服务器，
 */
@SpringBootApplication
@ComponentScan
@ServletComponentScan
public class ZkWebSpringBootApplication extends SpringBootServletInitializer {
    private final static Logger logger = LoggerFactory.getLogger(ZkWebSpringBootApplication.class);
    public final static String applicationYamlFileName = "application-zkweb.yaml";


    //for war打包方式，配置由 Servlet 容器运行的应用程序
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        String file = ZkWebSpringBootApplication.class.getClassLoader().getResource(applicationYamlFileName).getFile();
        logger.info("applicationYamlFileName({})={}", applicationYamlFileName, file);
        application.properties("spring.config.location=classpath:/" + applicationYamlFileName);
        return application.sources(ZkWebSpringBootApplication.class);
    }


    public static void main(String[] args) {
        String file = ZkWebSpringBootApplication.class.getClassLoader().getResource(applicationYamlFileName).getFile();
        logger.info("applicationYamlFileName({})={}", applicationYamlFileName, file);
        new SpringApplicationBuilder(ZkWebSpringBootApplication.class).
                properties("spring.config.location=classpath:/" + applicationYamlFileName).
                run(args);
    }
}
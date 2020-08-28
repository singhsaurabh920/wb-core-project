package org.worldbuild.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Log4j2
@Configuration
@EnableScheduling
public class CoreConfiguration {

    @Bean(name = "objectMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean("scheduledExecutorService")
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(1);
    }

    @Bean("restTemplate")
    public RestTemplate restTemplate() {
        log.info("RestTemplate is initializing..........");
        RestTemplate restTemplate = new RestTemplate();
        int timeoutMilliseconds = 60 * 1000;
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(timeoutMilliseconds);
        factory.setReadTimeout(timeoutMilliseconds);
        factory.setConnectTimeout(timeoutMilliseconds);
        List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<>();
        InterceptingClientHttpRequestFactory interceptorFactory = new InterceptingClientHttpRequestFactory(new BufferingClientHttpRequestFactory(factory), interceptorList);
        restTemplate.setRequestFactory(interceptorFactory);
        //restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        //restTemplate.setInterceptors(Arrays.asList(new RestTemplateInterceptor()));
        return restTemplate;
    }

    @Bean
    public VelocityEngine getVelocityEngine() throws VelocityException, IOException {
        VelocityEngine velocityEngine = new VelocityEngine();
        Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class",  "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init(props);
        return velocityEngine;
    }
}

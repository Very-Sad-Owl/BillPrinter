package com.project.bill.config;

import by.epam.training.jwd.task03.service.NodeTreeBuilder;
import by.epam.training.jwd.task03.service.NodeTreeBuilderFactory;
import com.project.bill.util.node_converter.WorkerFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static com.project.bill.util.Constant.BASE_PACKAGES_TO_SCAN;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages={BASE_PACKAGES_TO_SCAN},
        excludeFilters={
                @ComponentScan.Filter(type= FilterType.ANNOTATION, value= EnableWebMvc.class)
        })
public class AppConfig {

        @Bean
        public NodeTreeBuilder nodeTreeBuilder(){
                NodeTreeBuilderFactory factory = NodeTreeBuilderFactory.getInstance();
                return factory.getBuilder();
        }

        @Bean
        @Scope("prototype")
        public Logger logger(InjectionPoint injectionPoint) {
                return Logger.getLogger(injectionPoint.getMember().getDeclaringClass());
        }

        @Bean
        public WorkerFactory nodeWorkerFactory(){
                return WorkerFactory.getInstance();
        }

}

package com.musala.dronetask.utils;

import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Field;

@Component
public class JPAUtils {
    public static <T> void forceLoadLazyCollections(Class<T> tClass, T entity) {
        if (entity == null) {
            return;
        }
        for (Field field : tClass.getDeclaredFields()) {
            LazyCollection annotation = field.getAnnotation(LazyCollection.class);
            if (annotation != null) {
                try {
                    field.setAccessible(true);
                    Hibernate.initialize(field.get(entity));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//        resourceDatabasePopulator.addScripts(new ClassPathResource("/Procedures.sql"),new ClassPathResource("/Views.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }




}

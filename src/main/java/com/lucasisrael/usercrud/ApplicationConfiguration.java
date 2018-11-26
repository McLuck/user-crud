
package com.lucasisrael.usercrud;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lucasisrael.usercrud.domain.UserActivity;
import com.lucasisrael.usercrud.filter.UserActivityHandlerInterceptor;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * TODO Definir documentação da classe. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@ComponentScan ( "com.lucasisrael" )
@EntityScan ( "com.lucasisrael.usercrud.domain" )
@EnableJpaRepositories ( "com.lucasisrael.usercrud.repository" )
@Configuration
@EnableConfigurationProperties
@EnableWebMvc
@EnableWebSecurity
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    Logger log = LoggerFactory.getLogger( getClass() );
    
    @Autowired
    UserActivityHandlerInterceptor interceptor;

    @Bean
    public CommandLineRunner bootstrap () {
        return ( args ) -> {
            log.info( "Bootstraping application {}" , Arrays.toString( args ) );
        };
    }

    @Bean
    public ThreadLocal < UserActivity > userActivity () {
        return new ThreadLocal <>();
    }

    @Override
    public void addCorsMappings ( final CorsRegistry registry ) {
        registry.addMapping( "/**" )
        .allowedOrigins( "http://localhost:9000" , "http://localhost:8081" , "http://localhost:8080" )
        .allowedMethods( "GET" , "POST" , "PUT" , "DELETE" , "OPTIONS" )
        .allowCredentials(false)
        .maxAge(4800);
    }
    
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
    }
    
    /** 
         * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
         */
        @Override
        public void addInterceptors ( final InterceptorRegistry registry ) {
            WebMvcConfigurer.super.addInterceptors( registry );
            registry.addInterceptor( interceptor );
        }
}


package com.lucasisrael.usercrud;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lucasisrael.usercrud.domain.UserActivity;

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
public class ApplicationConfiguration {
    Logger log = LoggerFactory.getLogger( getClass() );
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
}

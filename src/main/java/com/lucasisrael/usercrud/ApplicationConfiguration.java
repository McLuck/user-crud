
package com.lucasisrael.usercrud;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
@Configuration
@EnableConfigurationProperties
public class ApplicationConfiguration {

    @Bean
    public ThreadLocal < UserActivity > userActivity () {
        return new ThreadLocal <>();
    }
}

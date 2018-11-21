
package com.lucasisrael.usercrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Classe principal/inicial da aplicação. Responsável por inicializar o spring
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

@SpringBootApplication
public class Application {
    public static void main ( final String[] args ) {
        SpringApplication.run( ApplicationConfiguration.class , args );
    }
}

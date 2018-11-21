
package com.lucasisrael.usercrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasisrael.usercrud.domain.AuthenticationMethod;
import com.lucasisrael.usercrud.domain.User;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Repositório de dados da entidade {@link AuthenticationMethod} <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public interface AuthenticationMethodRepository extends JpaRepository < AuthenticationMethod , Long > {

    /**
     * Busca a lista de métodos de autenticações filtrando pelo usuário
     * 
     * @param user
     * @return
     */
    List < AuthenticationMethod > findByUser ( User user );

    /**
     * Busca o método de autenticação cadastrado para o
     * authenticationId informado e com o mesmo tipo de mecanismo de autenticação
     * 
     * @param authenticationId
     * @param authenticationType
     * @return
     */
    AuthenticationMethod findByAuthenticationIdAndTypeCode ( String authenticationId , String authenticationType );

}

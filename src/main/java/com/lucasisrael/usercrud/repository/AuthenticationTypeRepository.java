
package com.lucasisrael.usercrud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasisrael.usercrud.domain.AuthenticationType;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Repositório da entidade {@link AuthenticationType} <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public interface AuthenticationTypeRepository extends JpaRepository < AuthenticationType , Long > {
    Optional < AuthenticationType > findByCode ( String aCode );
}

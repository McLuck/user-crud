package com.lucasisrael.usercrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasisrael.usercrud.domain.User;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Repositório de dados do usuário
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public interface UserRepository extends JpaRepository < User , Long > {

}

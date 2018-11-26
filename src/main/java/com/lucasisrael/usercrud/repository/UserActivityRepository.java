
package com.lucasisrael.usercrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasisrael.usercrud.domain.User;
import com.lucasisrael.usercrud.domain.UserActivity;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * TODO Definir documentação da classe. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 26, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public interface UserActivityRepository extends JpaRepository < UserActivity , Long > {

    /** 
     * @param user
     */
    void deleteByUser ( User user );

}


package com.lucasisrael.usercrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasisrael.usercrud.domain.Telephone;
import com.lucasisrael.usercrud.domain.User;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Repositório de dados do {@link Telephone}
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public interface TelephoneRepository extends JpaRepository < Telephone , Long > {

    /** 
     * @return
     */
    List < Telephone > findByUser ( User user );

}

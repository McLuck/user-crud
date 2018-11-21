
package com.lucasisrael.usercrud.service;

import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lucasisrael.usercrud.domain.User;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Abstração de serviços com suporte a usuário logado <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
public class LoggedUserService {

    /**
     * Recupera o usuário logado
     * @return
     */
    @Nullable
    public User loggedUser () {
        if ( SecurityContextHolder.getContext().getAuthentication() != null ) {
            return ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return null;
    }
}

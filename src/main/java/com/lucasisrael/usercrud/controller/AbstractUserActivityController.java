
package com.lucasisrael.usercrud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucasisrael.usercrud.domain.User;
import com.lucasisrael.usercrud.domain.UserActivity;
import com.lucasisrael.usercrud.repository.UserActivityRepository;
import com.lucasisrael.usercrud.repository.UserRepository;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Classe abstrata para tratamento das atividades de usuários <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 26, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class AbstractUserActivityController {
    @Autowired
    ThreadLocal < UserActivity > userActivity;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserActivityRepository userActivityRepository;

    protected UserActivity createUserActivity ( User user , final HttpServletRequest request ) {
        UserActivity activity = new UserActivity();
        if( activity.getId() == null) {
            user = userRepository.save( user );
        }
        activity.setUser( user );
        activity.setOriginDetails( new StringBuffer( "Requisicao do endereço " )
                .append( request.getRemoteAddr() ).append( ". Detalhes do cliente que realizou a conexção: " )
                .append( request.getHeader( "User-Agent" ) ).toString() );
        activity = userActivityRepository.save( activity );
        userActivity.set( activity );
        return activity;
    }
}

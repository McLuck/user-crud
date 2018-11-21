package com.lucasisrael.usercrud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.lucasisrael.usercrud.domain.LoginDTO;
import com.lucasisrael.usercrud.login.UserAuthenticator;

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
@Service
@Transactional
public class LoginService {
    Logger logger = LoggerFactory.getLogger( getClass() );
    @Autowired
    ApplicationContext appContext;

    public void authenticate ( final LoginDTO login ) {
        login.resetLogin();
        if ( Strings.isNullOrEmpty( login.getAuthenticationTypeCode() ) ) {
            login.fail( "Login solicitado sem informar o tipo de autenticação!" );
            logger.error( login.getMessage() );
        } else if ( Strings.isNullOrEmpty( login.getUserId() ) ) {
            login.fail( "Login solicitado sem informar o id para a autenticação. O id depende do tipo de autenticação." );
            logger.error( login.getMessage() );
        } else {
            appContext.getBeansOfType( UserAuthenticator.class ).values().stream().filter( a -> a.authenticationType().equals( login.getAuthenticationTypeCode() ) ).findAny()
                    .ifPresent( a -> a.authenticate( login ) );
            if ( login.getAuthenticated() == null ) {
                login.fail( "O tipo de autenticação solicitado não está disponível na aplicação" );
            }
        }
    }
}

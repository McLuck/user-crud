
package com.lucasisrael.usercrud.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.lucasisrael.usercrud.domain.AuthenticationMethod;
import com.lucasisrael.usercrud.domain.User;
import com.lucasisrael.usercrud.login.UserAuthenticator;
import com.lucasisrael.usercrud.repository.AuthenticationMethodRepository;
import com.lucasisrael.usercrud.repository.AuthenticationTypeRepository;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Serviços disponibilizado pela aplicação para tratar
 * {@link AuthenticationMethod} <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@Service
public class AuthenticationMethodService extends LoggedUserService {
    Logger logger = LoggerFactory.getLogger( getClass() );
    @Autowired
    AuthenticationMethodRepository authenticationMethodRepository;
    @Autowired
    AuthenticationTypeRepository authenticationTypeRepository;
    @Autowired
    ApplicationContext appContext;

    public List < AuthenticationMethod > getRegisteredAuthenticationMethodFor ( final User user ) {
        return authenticationMethodRepository.findByUser( user );
    }

    public List < AuthenticationMethod > getRegisteredAuthenticationMethodForLoggedUser () {
        final User loggedUser = loggedUser();
        if ( loggedUser == null ) {
            return null;
        }
        return getRegisteredAuthenticationMethodFor( loggedUser );
    }

    /**
     * Adiciona o novo {@link AuthenticationMethod} no usuário logado
     * 
     * @param authenticationMethod
     */
    public AuthenticationMethod addAuthenticationMethod ( final AuthenticationMethod authenticationMethod ) {
        AuthenticationMethod validateMethod = validateMethod( authenticationMethod );
        if ( validateMethod == null ) {
            return null;
        }
        final boolean newEntity = authenticationMethod.getId() == null;
        validateMethod = authenticationMethodRepository.save( validateMethod );
        if ( ! newEntity ) {
            loggedUser().getAuthentications()
                    .removeIf( a -> a.getAuthenticationId().equals( authenticationMethod.getAuthenticationId() ) 
                            && a.getType().getCode().equals( authenticationMethod.getType().getCode() ) );
        }
        loggedUser().getAuthentications().add( validateMethod );
        return validateMethod;
    }

    /**
     * @param authenticationMethod
     */
    private AuthenticationMethod validateMethod ( final AuthenticationMethod authenticationMethod ) {
        final User loggedUser = loggedUser();
        if ( loggedUser == null ) {
            logger.error( "Tentativa de criar novo método de autenticação, mas não existe um usuário na sessao" );
            return null;
        }
        authenticationMethod.setUser( loggedUser );
        if ( authenticationMethod.getType() != null && Strings.isNullOrEmpty( authenticationMethod.getType().getCode() ) ) {
            logger.error( "Tentativa de criar novo método de autenticação sem definir o tipo da autenticação" );
            return null;
        }
        if ( Strings.isNullOrEmpty( authenticationMethod.getAuthenticationId() ) ) {
            logger.error( "Tentativa de criar novo método de autenticação sem definir authenticationId" );
            return null;
        }
        final Optional < UserAuthenticator > userAuthenticator = appContext.getBeansOfType( UserAuthenticator.class ).values().stream()
                .filter( a -> a.authenticationType().equals( authenticationMethod.getType().getCode() ) ).findAny();
        if ( ! userAuthenticator.isPresent() ) {
            logger.error( "Tentativa de criar novo método de autenticação falhou. Não existe uma implementação para o tipo de autenticação solicitada!" );
            return null;
        }
        if ( authenticationMethod.getType().getId() == null ) {
            authenticationTypeRepository.findByCode( authenticationMethod.getType().getCode() ).ifPresent( authenticationMethod::setType );
        }
        authenticationMethod.setAuthenticationKey( userAuthenticator.get().encodeKey( authenticationMethod.getAuthenticationKey() ) );
        return authenticationMethod;
    }
}

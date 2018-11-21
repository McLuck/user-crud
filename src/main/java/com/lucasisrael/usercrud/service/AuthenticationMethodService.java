
package com.lucasisrael.usercrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasisrael.usercrud.domain.AuthenticationMethod;
import com.lucasisrael.usercrud.domain.User;
import com.lucasisrael.usercrud.repository.AuthenticationMethodRepository;

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
    @Autowired
    AuthenticationMethodRepository authenticationMethodRepository;

    public List < AuthenticationMethod > getRegisteredAuthenticationMethodFor ( final User user ) {
        return authenticationMethodRepository.findByUser( user );
    }

    public List < AuthenticationMethod > getRegisteredAuthenticationMethodForLoggedUser () {
        return getRegisteredAuthenticationMethodFor( loggedUser() );
    }

}

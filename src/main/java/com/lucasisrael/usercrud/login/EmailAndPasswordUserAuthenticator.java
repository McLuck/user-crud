package com.lucasisrael.usercrud.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.lucasisrael.usercrud.domain.AuthenticationMethod;
import com.lucasisrael.usercrud.domain.AuthenticationType;
import com.lucasisrael.usercrud.domain.LoginDTO;
import com.lucasisrael.usercrud.repository.AuthenticationMethodRepository;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Implementa autenticador para validar usuario e senha <br>
 * Usuário: um email <br>
 * Senha: alfanumérica <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@Component
public class EmailAndPasswordUserAuthenticator implements UserAuthenticator {
    @Autowired
    AuthenticationMethodRepository authenticationMethodRepository;

    /**
     * @see com.lucasisrael.usercrud.login.UserAuthenticator#authenticationType()
     */
    @Override
    public String authenticationType () {
        return AuthenticationType.EMAIL_AND_PASSWORD;
    }

    /**
     * @see com.lucasisrael.usercrud.login.UserAuthenticator#authenticate(com.lucasisrael.usercrud.domain.LoginDTO)
     */
    @Override
    public void authenticate ( final LoginDTO login ) {
        if ( Strings.isNullOrEmpty( login.getUserKey() ) ) {
            login.fail( "Senha não informada" );
        }
        final AuthenticationMethod registeredMethod = authenticationMethodRepository.findByAuthenticationIdAndTypeCode( login.getUserId() , authenticationType() );
        if ( registeredMethod != null ) {
            final String password = encodeKey( login.getUserKey() );
            if ( registeredMethod.getAuthenticationKey().equals( password ) ) {
                login.success( registeredMethod.getUser() );
            } else {
                login.fail( "Senha incorreta" );
            }
        } else {
            login.fail( "Email não encontrado" );
        }
    }

    /**
     * @see com.lucasisrael.usercrud.login.UserAuthenticator#encodeKey(java.lang.String)
     */
    @Override
    public String encodeKey ( final String decodedKey ) {
        return new String( Sha512DigestUtils.sha( decodedKey ) );
    }
}

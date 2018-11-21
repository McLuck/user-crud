
package com.lucasisrael.usercrud.login;

import com.lucasisrael.usercrud.domain.LoginDTO;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Contrato para autenticar um usuário <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public interface UserAuthenticator {
    /**
     * Informa o tipo de autenticação que a implementação do autenticador irá
     * tratar
     * 
     * @return o código do tipo do autenticador
     */
    String authenticationType ();

    /**
     * Autentica o usuário
     * @param login
     */
    void authenticate ( LoginDTO login );
    
    /**
     * Trata a chave para ser transmitida ou armazenada
     * @param decodedKey
     * @return
     */
    String encodeKey(String decodedKey);
}

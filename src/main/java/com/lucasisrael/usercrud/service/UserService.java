
package com.lucasisrael.usercrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasisrael.usercrud.domain.Email;
import com.lucasisrael.usercrud.domain.Telephone;
import com.lucasisrael.usercrud.domain.User;
import com.lucasisrael.usercrud.repository.EmailRepository;
import com.lucasisrael.usercrud.repository.TelephoneRepository;
import com.lucasisrael.usercrud.repository.UserRepository;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Serviços do usuário cedidos pela aplicação <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@Service
public class UserService extends LoggedUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    TelephoneRepository telephoneRepository;

    public Optional < User > loadUserById ( final Long id ) {
        return userRepository.findById( id );
    }

    public List < User > listRegisteredUsers () {
        return userRepository.findAll();
    }

    public List < Email > getRegisteredEmailsFor ( final User user ) {
        return emailRepository.findByUser( user );
    }

    public List < Telephone > getRegisteredTelephonesFor ( final User user ) {
        return telephoneRepository.findByUser( user );
    }
}

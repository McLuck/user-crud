
package com.lucasisrael.usercrud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasisrael.usercrud.domain.Email;
import com.lucasisrael.usercrud.domain.Telephone;
import com.lucasisrael.usercrud.domain.User;
import com.lucasisrael.usercrud.repository.EmailRepository;
import com.lucasisrael.usercrud.repository.TelephoneRepository;
import com.lucasisrael.usercrud.repository.UserActivityRepository;
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
    @Autowired
    UserActivityRepository userActivityRepository;

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

    /**
     * @return
     */
    public User save ( final User user ) {
        return userRepository.save( user );
    }

    public void removeUser ( final Long idUser ) {
        userRepository.findById( idUser ).ifPresent( this::removeDependencies );
        userRepository.deleteById( idUser );
    }

    public void removeDependencies ( final User user ) {
        user.getEmails().forEach( emailRepository::delete );
        user.getTelephones().forEach( telephoneRepository::delete );
        userActivityRepository.deleteByUser( user );
    }

    public Telephone addTelephone ( final User user , final Telephone telephone ) {
        if ( user.getTelephones() == null ) {
            user.setTelephones( new ArrayList <>() );
        }
        user.getTelephones().add( telephone );
        telephone.setUser( user );
        return telephoneRepository.save( telephone );
    }
    
    public Optional<User> loadUser(final Long idUser) {
        return userRepository.findById( idUser );
    }

    /** 
     * @param idTelephone
     */
    public void removeTelephone ( final Telephone telephone ) {
        telephoneRepository.delete( telephone );
    }

    /** 
     * @return
     */
    public Optional < Telephone > loadTelephone ( final Long idTelephone ) {
        return telephoneRepository.findById( idTelephone );
    }
}

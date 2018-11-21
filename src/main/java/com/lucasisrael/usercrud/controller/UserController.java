
package com.lucasisrael.usercrud.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucasisrael.usercrud.domain.User;
import com.lucasisrael.usercrud.service.UserService;

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
@RestController ( "/api/v1/user" )
@Transactional
public class UserController {
    Logger logger = LoggerFactory.getLogger( getClass() );

    @Autowired
    UserService userService;

    @GetMapping
    public @ResponseBody ResponseEntity < List < User > > listUsers () {
        return new ResponseEntity <>( userService.listRegisteredUsers() , HttpStatus.OK );
    }

    @GetMapping ( "/{id}" )
    public @ResponseBody ResponseEntity < User > loaduser ( @RequestParam ( value = "id" , required = true ) final Long idUser ) {
        final Optional < User > loadedUser = userService.loadUserById( idUser );
        if ( loadedUser.isPresent() ) {
            return new ResponseEntity <>( loadedUser.get() , HttpStatus.OK );
        }
        return new ResponseEntity <>( HttpStatus.NO_CONTENT );
    }

    @PostMapping
    public @ResponseBody ResponseEntity < User > createUser ( final User user ) {
        if ( user.getId() != null ) {
            logger.error( "Tentativa de salvar um usuário com o ID já preenchido! HTTP 226 - IM_USED" );
            return new ResponseEntity <>( HttpStatus.IM_USED );
        }
        return new ResponseEntity <>( HttpStatus.NO_CONTENT );
    }

    @PutMapping
    public @ResponseBody ResponseEntity < User > changeUser ( final User user ) {
        if ( user.getId() == null ) {
            logger.error( "Tentativa de atualizar um usuário sem o ID preenchido! HTTP 417 - EXPECTATION_FAILED" );
            return new ResponseEntity <>( HttpStatus.EXPECTATION_FAILED );
        }
        return new ResponseEntity <>( HttpStatus.NO_CONTENT );
    }
}

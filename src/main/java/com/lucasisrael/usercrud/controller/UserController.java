
package com.lucasisrael.usercrud.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucasisrael.usercrud.domain.User;
import com.lucasisrael.usercrud.domain.UserActivity;
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
@RestController
@Transactional
public class UserController extends AbstractUserActivityController {
    Logger logger = LoggerFactory.getLogger( getClass() );

    @Autowired
    UserService userService;

    @GetMapping ( "/user" )
    public @ResponseBody ResponseEntity < List < User > > listUsers () {
        return new ResponseEntity <>( userService.listRegisteredUsers() , HttpStatus.OK );
    }

    @DeleteMapping ( "/user/{id}" )
    public @ResponseBody ResponseEntity < User > deleteUser ( @PathVariable ( value = "id" , required = true ) final Long idUser ) {
        userService.removeUser( idUser );
        return new ResponseEntity <>( HttpStatus.OK );
    }

    @GetMapping ( "/user/{id}" )
    public @ResponseBody ResponseEntity < User > loaduser ( @RequestParam ( value = "id" , required = true ) final Long idUser , final HttpServletRequest request ) {
        final Optional < User > loadedUser = userService.loadUserById( idUser );
        if ( loadedUser.isPresent() ) {
            createUserActivity( loadedUser.get() , request ).setOperationDescription( "Visualizou cadastro" );
            return new ResponseEntity <>( loadedUser.get() , HttpStatus.OK );
        }
        return new ResponseEntity <>( HttpStatus.NO_CONTENT );
    }

    @PostMapping ( "/user" )
    public @ResponseBody ResponseEntity < User > createUser ( @RequestBody User user , final HttpServletRequest request ) {
        if ( user.getId() != null ) {
            logger.error( "Tentativa de salvar um usuário com o ID já preenchido! HTTP 226 - IM_USED" );
            return new ResponseEntity <>( HttpStatus.IM_USED );
        }
        user = userService.save( user );
        createUserActivity( user , request ).setOperationDescription( "Criou cadastro" );
        return new ResponseEntity <>( user , HttpStatus.OK );
    }

    @PutMapping ( "/user" )
    public @ResponseBody ResponseEntity < User > changeUser ( @RequestBody User user , final HttpServletRequest request ) {
        if ( user.getId() == null ) {
            logger.error( "Tentativa de atualizar um usuário sem o ID preenchido! HTTP 417 - EXPECTATION_FAILED" );
            return new ResponseEntity <>( HttpStatus.EXPECTATION_FAILED );
        }
        user = userService.save( user );
        createUserActivity( user , request ).setOperationDescription( "Alterou cadastro" );
        return new ResponseEntity <>( user , HttpStatus.OK );
    }

    @GetMapping ( "/user/{id}/activitys" )
    public @ResponseBody ResponseEntity < List < UserActivity > > loadActivitys ( @RequestParam ( value = "id" , required = true ) final Long idUser , final HttpServletRequest request ) {
        final Optional < User > loadedUser = userService.loadUserById( idUser );
        if ( loadedUser.isPresent() ) {
            createUserActivity( loadedUser.get() , request ).setOperationDescription( "Visualizou cadastro" );
            return new ResponseEntity <>( userService.loadActivitys( loadedUser.get() ) , HttpStatus.OK );
        }
        return new ResponseEntity <>( HttpStatus.NO_CONTENT );
    }

}

package com.lucasisrael.usercrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucasisrael.usercrud.domain.AuthenticationMethod;
import com.lucasisrael.usercrud.service.AuthenticationMethodService;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Controlador para {@link AuthenticationMethod} <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@RestController
@Transactional
public class AuthenticationMethodController {
    @Autowired
    AuthenticationMethodService authenticationMethodService;

    @GetMapping ( "/authentication-method" )
    public @ResponseBody ResponseEntity < List < AuthenticationMethod > > listMyAuthentitionMethods () {
        final List < AuthenticationMethod > methodForLoggedUser = authenticationMethodService.getRegisteredAuthenticationMethodForLoggedUser();
        if ( methodForLoggedUser == null ) {
            return new ResponseEntity <>( HttpStatus.NO_CONTENT );
        }
        return new ResponseEntity <>( methodForLoggedUser , HttpStatus.OK );
    }

    @PostMapping ( "/authentication-method" )
    public @ResponseBody ResponseEntity < AuthenticationMethod > createAuthenticationMethod ( final AuthenticationMethod authenticationMethod ) {
        final AuthenticationMethod savedMethod = authenticationMethodService.addAuthenticationMethod( authenticationMethod );
        final HttpStatus httpStatus = savedMethod != null ? HttpStatus.OK : HttpStatus.UNPROCESSABLE_ENTITY;
        return new ResponseEntity <>( savedMethod , httpStatus );
    }

    @PutMapping ( "/authentication-method" )
    public @ResponseBody ResponseEntity < AuthenticationMethod > changeAuthenticationMethod ( final AuthenticationMethod authenticationMethod ) {
        final AuthenticationMethod savedMethod = authenticationMethodService.addAuthenticationMethod( authenticationMethod );
        final HttpStatus httpStatus = savedMethod != null ? HttpStatus.OK : HttpStatus.UNPROCESSABLE_ENTITY;
        return new ResponseEntity <>( savedMethod , httpStatus );
    }
}

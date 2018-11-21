
package com.lucasisrael.usercrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucasisrael.usercrud.domain.LoginDTO;
import com.lucasisrael.usercrud.service.LoginService;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Controlador para realizar authenticação do usuario <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@RestController ( "/api/v1/login" )
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping
    public @ResponseBody ResponseEntity < LoginDTO > createUser ( final LoginDTO login ) {
        loginService.authenticate( login );
        final HttpStatus httpStatus = login.getAuthenticated() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return new ResponseEntity <>( login , httpStatus );
    }
}

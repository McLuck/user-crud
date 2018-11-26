
package com.lucasisrael.usercrud.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucasisrael.usercrud.domain.Telephone;
import com.lucasisrael.usercrud.domain.User;
import com.lucasisrael.usercrud.service.UserService;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * TODO Definir documentação da classe. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 26, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@RestController
@Transactional
public class TelephoneController extends AbstractUserActivityController {
    Logger logger = LoggerFactory.getLogger( getClass() );

    @Autowired
    UserService userService;

    @DeleteMapping ( "/telephone/{id}" )
    public @ResponseBody ResponseEntity < ? > deleteTelephone ( @PathVariable ( value = "id" , required = true ) final Long idTelephone , final HttpServletRequest request ) {
        final Optional < Telephone > loadTelephone = userService.loadTelephone( idTelephone );
        if ( loadTelephone.isPresent() ) {
            createUserActivity( loadTelephone.get().getUser() , request ).setOperationDescription( "Removeu telefone" );
            userService.removeTelephone( loadTelephone.get() );
        }
        return new ResponseEntity <>( HttpStatus.OK );
    }

    @PostMapping ( "/telephone" )
    public @ResponseBody ResponseEntity < Telephone > createTelephone ( @RequestBody final Telephone telephone , final HttpServletRequest request ) {
        if ( telephone.getId() != null ) {
            logger.error( "Tentativa de salvar um telephone com o ID já preenchido! HTTP 226 - IM_USED" );
            return new ResponseEntity <>( HttpStatus.IM_USED );
        }
        if ( telephone.getUser() == null ) {
            logger.error( "Tentativa de salvar um telephone sem informar um usuário!" );
            return new ResponseEntity <>( HttpStatus.PRECONDITION_FAILED );
        }
        final Optional < User > userOpt = userRepository.findById( telephone.getUser().getId() );
        if ( userOpt.isPresent() ) {
            createUserActivity( userOpt.get() , request ).setOperationDescription( "Criou novo telefone" );
            return new ResponseEntity <>( userService.addTelephone( userOpt.get() , telephone ) , HttpStatus.CREATED );
        }
        return new ResponseEntity <>( HttpStatus.PRECONDITION_FAILED );
    }
}

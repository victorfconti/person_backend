package br.com.victor.teste_supply.person_register.controller.exception_handler;


import br.com.victor.teste_supply.person_register.model.dto.ErrorResponseDTO;
import br.com.victor.teste_supply.person_register.model.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(400, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex){
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(400, ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(NotFoundException ex){
        return new ResponseEntity<>(new ErrorResponseDTO(404, ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}

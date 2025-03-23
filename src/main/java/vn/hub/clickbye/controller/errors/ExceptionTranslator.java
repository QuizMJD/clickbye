package vn.hub.clickbye.controller.errors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.hub.clickbye.service.dto.response.ErrorResponse;

import java.util.HashMap;
import java.util.Map;

//ControllerAdvice xu dung cho mvc va restful
//RestControllerAdvice chi su dung cho restful
//RestControllerAdvice=@ControllerAdvice+@ResponseBody
@RestControllerAdvice
public class ExceptionTranslator {
    @ExceptionHandler(EmailExistException.class)
    public ErrorResponse<String> handleEmailExistException(EmailExistException e){
        return ErrorResponse.badRequest(e.getMessage());
}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest()
                .body(ErrorResponse.badRequest(errors));
    }
    @ExceptionHandler(Exception.class)
    public ErrorResponse<String>handleException(Exception e){
        return ErrorResponse.internalServer(e.getMessage());
    }
    @ExceptionHandler(FieldValidationException.class)
    public ErrorResponse<Map<String, Object>> handleFieldValidationException(FieldValidationException e) {
        return ErrorResponse.badRequest(e.getErrors());
    }

}

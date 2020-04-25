package kr.purred.fc.mfriend.handler;

import kr.purred.fc.mfriend.dto.ErrorResponse;
import kr.purred.fc.mfriend.exception.PersonNotFoundException;
import kr.purred.fc.mfriend.exception.RenameNotPermittedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(RenameNotPermittedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //public ResponseEntity<ErrorResponse> handleRenameNotPermittedException (RenameNotPermittedException ex)
    public ErrorResponse handleRenameNotPermittedException (RenameNotPermittedException ex)
    {
        // return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public ResponseEntity<ErrorResponse> handlePersonNotFoundException (PersonNotFoundException ex)
    public ErrorResponse handlePersonNotFoundException (PersonNotFoundException ex)
    {
        // return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public ResponseEntity<ErrorResponse> handleRuntimeException (RuntimeException ex)
    public ErrorResponse handleRuntimeException (RuntimeException ex)
    {
        log.error("서버 오류 : {}", ex.getMessage(), ex);
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "알수 없는 서버 오류가 발생 하였습니다");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException (MethodArgumentNotValidException ex)
    {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getBindingResult().getFieldError());
    }
}


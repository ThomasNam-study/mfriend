package kr.purred.fc.mfriend.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private int code;

    private String message;

    public static ErrorResponse of (HttpStatus status, String message)
    {
        return new ErrorResponse(status.value(), message);
    }
}


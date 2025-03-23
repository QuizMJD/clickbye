package vn.hub.clickbye.service.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class StatusResponse {
    int code;
    String message;
    final LocalDateTime timestamp=LocalDateTime.now();


    private StatusResponse setCode(int code) {
        this.code = code;
        return this;
    }

    private StatusResponse setMessage(String message) {
        this.message = message;
        return this;
    }


    public static StatusResponse build(int code , String message){
        return new StatusResponse()
                .setCode(code)
                .setMessage(message);
    }
}


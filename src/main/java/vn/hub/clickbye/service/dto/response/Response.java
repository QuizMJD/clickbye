package vn.hub.clickbye.service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Response<T> {
    StatusResponse status;
    T data;

    private Response<T> setStatus(StatusResponse status) {
        this.status = status;
        return this;
    }

    private Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    private Response(StatusResponse status) {
        this.status = status;
    }

    public static Response<Void> noContent() {
        return new Response<>(StatusResponse.build(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase()));
    }

    public static <T> Response<T> ok(T data) {
        return new Response<T>()
                .setStatus(StatusResponse.build(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()))
                .setData(data);
    }

    public static <T> Response<T> created(T data) {
        return new Response<T>()
                .setStatus(StatusResponse.build(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()))
                .setData(data);
    }
}

package com.midwesttape.challenge.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private int errorCode;
    private String message;
    private String messageId;

    @JsonCreator
    public ErrorResponse(@JsonProperty("ErrorCode") int errorCode, @JsonProperty("ErrorMessage") String errorMessage) {
        this.errorCode = errorCode;
        this.message = errorMessage;
    }

    public ErrorResponse(int errorCode, String messageId, String errorMessage) {
        this.errorCode = errorCode;
        this.messageId = messageId;
        this.message = errorMessage;
    }

}

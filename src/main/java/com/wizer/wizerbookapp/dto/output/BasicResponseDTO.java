package com.wizer.wizerbookapp.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wizer.wizerbookapp.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BasicResponseDTO extends StandardResponseDTO {

    @JsonProperty
    private Object responseData;

    public BasicResponseDTO(Status status) {
        super(status);
    }

    public BasicResponseDTO(Status status, Object responseData) {
        super(status);
        this.responseData = responseData;
    }

}

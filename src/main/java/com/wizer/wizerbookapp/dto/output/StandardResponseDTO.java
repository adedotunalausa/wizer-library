package com.wizer.wizerbookapp.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wizer.wizerbookapp.enums.Status;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponseDTO implements Serializable {

    @JsonProperty
    protected Status status;

}

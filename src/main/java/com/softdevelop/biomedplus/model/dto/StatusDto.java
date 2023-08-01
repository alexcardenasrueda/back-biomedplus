package com.softdevelop.biomedplus.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.softdevelop.biomedplus.enums.Status;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatusDto implements Serializable {

    private static final long serialVersionUID = 2909228228891829275L;

    private Long id;
    private Status name;
}

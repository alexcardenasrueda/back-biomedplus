package com.softdevelop.biomedplus.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RolDto implements Serializable {
    private static final long serialVersionUID = 7340724116569669044L;

    private Long id;
    private String name;
}

package com.softdevelop.biomedplus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto implements Serializable {

    private static final long serialVersionUID = 2909228228891829275L;

    private BigInteger id;
    private String name;
    private String psw;
    private String email;
    private RolDto rol;
}

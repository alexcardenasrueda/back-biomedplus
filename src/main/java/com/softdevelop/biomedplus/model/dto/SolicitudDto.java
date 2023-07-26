package com.softdevelop.biomedplus.model.dto;

import com.softdevelop.biomedplus.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Builder
@ToString
public class SolicitudDto implements Serializable {
    private static final long serialVersionUID = -2726183053018213672L;

    private String descripcion;
    private String fechaCreacion;
    private String fechaCierre;
    private String imagen;
    private Status estado;
    private BigInteger equipo;
    private BigInteger usuario;
}

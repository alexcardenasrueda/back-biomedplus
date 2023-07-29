package com.softdevelop.biomedplus.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class ErrorDto implements Serializable {


    private static final long serialVersionUID = 8267511263831903691L;

    private String errorCode;
    private String errorMessage;
}

package com.svc.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GeneralDto<T> implements Serializable {

    private T data;

    public GeneralDto(T data) {
        this.data = data;
    }
}

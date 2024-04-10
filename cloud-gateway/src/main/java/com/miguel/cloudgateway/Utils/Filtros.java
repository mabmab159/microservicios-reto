package com.miguel.cloudgateway.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Filtros {
    private String tipo;
    private String id;
    private String validez;
}
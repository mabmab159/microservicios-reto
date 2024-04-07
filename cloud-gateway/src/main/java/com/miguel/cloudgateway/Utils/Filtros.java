package com.miguel.cloudgateway.Utils;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Filtros {
    private String tipo;
    private String id;
    private String validez;
}
package com.miguel.cloudgateway.Model;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class License {
    private String id;
    private String license;
    private String usuario;
    private String fecha_caducidad;
}

package com.miguel.cloudgateway.Model;

import com.miguel.cloudgateway.Utils.Categoria;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class License {
    private String id;
    private String DNI;
    private String nombres;
    private String apellidos;
    private Categoria categoria;
    private Date fecha_emision;
    private Date fecha_caducidad;
    private Boolean activo;
}

package com.miguel.cloudgateway.Model;

import com.miguel.cloudgateway.Utils.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class License {
    private String id;
    private String dni;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private Categoria categoria;
    private Date fecha_emision;
    private Date fecha_caducidad;
    private Boolean activo;
}

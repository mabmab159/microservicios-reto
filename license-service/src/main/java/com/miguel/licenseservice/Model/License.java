package com.miguel.licenseservice.Model;

import com.miguel.licenseservice.Utils.Categoria;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Builder
@Getter
@Setter
public class License {
    @Id
    private String id;
    private String DNI;
    private String nombres;
    private String apellidos;
    private Categoria categoria;
    private Date fecha_emision;
    private Date fecha_caducidad;
    private Boolean activo;
}

package com.miguel.licenseservice.Model;

import com.miguel.licenseservice.Utils.Categoria;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "license")
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

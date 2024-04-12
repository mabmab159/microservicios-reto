package com.miguel.licenseservicequery.Model;

import com.miguel.licenseservicequery.Utils.Categoria;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(collection = "licenses")
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

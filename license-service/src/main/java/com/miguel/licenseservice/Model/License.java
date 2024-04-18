package com.miguel.licenseservice.Model;

import com.miguel.licenseservice.Utils.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Client client;
    private Categoria categoria;
    private Date fecha_emision;
    private Date fecha_caducidad;
    private Boolean activo;
}

package com.miguel.licenseservicequery.Model;

import com.miguel.licenseservicequery.Utils.Categoria;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "license")
@ToString
public class License {
    @Id
    private String id;
    private Client client;
    private Categoria categoria;
    private Date fecha_emision;
    private Date fecha_caducidad;
    private Boolean activo;
}
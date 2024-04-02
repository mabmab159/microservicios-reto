package com.miguel.licenseservice.Model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Builder
@Document(collection = "licenses")
public class License {
    @Id
    private String id;
    private String license;
    private String usuario;
    private String fecha_caducidad;
}

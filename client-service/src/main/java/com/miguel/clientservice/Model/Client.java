package com.miguel.clientservice.Model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(collection = "client")
public class Client {
    @Id
    private String id;
    private String dni;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
}

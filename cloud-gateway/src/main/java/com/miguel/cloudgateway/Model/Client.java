package com.miguel.cloudgateway.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private String id;
    private String dni;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
}

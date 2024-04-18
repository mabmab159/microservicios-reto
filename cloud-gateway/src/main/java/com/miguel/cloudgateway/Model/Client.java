package com.miguel.cloudgateway.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private String DNI;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
}

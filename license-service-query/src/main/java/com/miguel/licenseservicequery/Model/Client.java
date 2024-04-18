package com.miguel.licenseservicequery.Model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Client {
    private String DNI;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
}

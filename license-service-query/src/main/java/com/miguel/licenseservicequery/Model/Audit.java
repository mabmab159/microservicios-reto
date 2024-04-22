package com.miguel.licenseservicequery.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Audit {
    private String id;
    private String accion;
    private Date fecha;
}

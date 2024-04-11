package com.miguel.cloudgateway.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateResponse {
    private Boolean success;
    private List<String> roles;
}

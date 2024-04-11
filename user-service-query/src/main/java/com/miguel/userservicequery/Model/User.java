package com.miguel.userservicequery.Model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private List<String> roles;
}

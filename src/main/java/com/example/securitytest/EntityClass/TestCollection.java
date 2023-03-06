package com.example.securitytest.EntityClass;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "user")
public class TestCollection {
    @Id
    private String id;
    private String name;

    private int age;
    private String Email;

}

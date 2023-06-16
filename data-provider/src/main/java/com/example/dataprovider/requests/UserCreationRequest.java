package com.example.dataprovider.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {
    private String rollNum;
    private String batchId;
    private String email;
    private String name;
    private String password;
}

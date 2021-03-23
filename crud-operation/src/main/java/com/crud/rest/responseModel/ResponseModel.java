package com.crud.rest.responseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseModel <T>{

    private Boolean status;
    private int code;
    private String message;
    private T responseData;


}

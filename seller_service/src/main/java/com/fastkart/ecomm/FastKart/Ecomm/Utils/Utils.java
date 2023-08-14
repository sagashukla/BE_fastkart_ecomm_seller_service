package com.fastkart.ecomm.FastKart.Ecomm.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastkart.ecomm.FastKart.Ecomm.dto.TokenValidationResponse;
import org.springframework.http.ResponseEntity;

public class Utils {
    public static TokenValidationResponse convertToDto(ResponseEntity<String> response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TokenValidationResponse dto = mapper.readValue(response.getBody(), TokenValidationResponse.class);
        return dto;
    }
}

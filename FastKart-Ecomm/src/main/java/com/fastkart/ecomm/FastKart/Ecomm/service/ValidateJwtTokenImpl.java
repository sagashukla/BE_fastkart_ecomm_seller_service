package com.fastkart.ecomm.FastKart.Ecomm.service;

import com.fastkart.ecomm.FastKart.Ecomm.Utils.Utils;
import com.fastkart.ecomm.FastKart.Ecomm.dto.TokenValidationResponse;
import com.fastkart.ecomm.FastKart.Ecomm.exception.AuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidateJwtTokenImpl implements ValidateJwtToken{
    @Override
    public TokenValidationResponse validateToken(HttpServletRequest requestServlet) throws Exception {
        String bearerToken = requestServlet.getHeader("Authorization");
        if(bearerToken == null){
            throw new AuthorizationException("Bearer token not provided");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8085/AUTH-SERVICE/api/v1/auth/validatetoken";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearerToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        TokenValidationResponse tokenValidationResponse = Utils.convertToDto(response);

        return tokenValidationResponse;
    }
}

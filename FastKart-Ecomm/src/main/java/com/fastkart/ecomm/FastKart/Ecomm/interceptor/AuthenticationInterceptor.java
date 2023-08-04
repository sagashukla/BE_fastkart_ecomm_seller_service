package com.fastkart.ecomm.FastKart.Ecomm.interceptor;

import com.fastkart.ecomm.FastKart.Ecomm.Utils.Utils;
import com.fastkart.ecomm.FastKart.Ecomm.dto.TokenValidationResponse;
import com.fastkart.ecomm.FastKart.Ecomm.exception.AuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Value("${api.gateway.url}")
    private String apiGatewayUrl;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception
    {
        TokenValidationResponse tokenValidationResponse = validateJwtToken(requestServlet);

        if(!tokenValidationResponse.getStatus().equals("OK")){
            throw new AuthorizationException("Please provide a valid token");
        }
        else if(!tokenValidationResponse.getRoleType().equals("SELLER")){
            throw new AuthorizationException("Not authorized to access this resource. User type: Buyer");
        }
        else {
            return true;
        }
    }

    private TokenValidationResponse validateJwtToken(HttpServletRequest requestServlet) throws Exception {
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

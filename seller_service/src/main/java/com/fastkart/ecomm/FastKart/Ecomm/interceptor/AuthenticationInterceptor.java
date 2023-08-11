package com.fastkart.ecomm.FastKart.Ecomm.interceptor;

import com.fastkart.ecomm.FastKart.Ecomm.Utils.Utils;
import com.fastkart.ecomm.FastKart.Ecomm.dto.TokenValidationResponse;
import com.fastkart.ecomm.FastKart.Ecomm.exception.AuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Value("${api.gateway.url}")
    private String apiGatewayUrl;

    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception
    {
        log.info("Inside AuthenticationInterceptor");
        log.info("Inside preHandle()");
        TokenValidationResponse tokenValidationResponse = validateJwtToken(requestServlet);

        if(!tokenValidationResponse.getStatus().equals("OK")){
            log.info("Please provide a valid token");
            throw new AuthorizationException("Please provide a valid token");
        }
        else if(!tokenValidationResponse.getRoleType().equals("SELLER")){
            log.info("Not authorized to access this resource. User type: Buyer");
            throw new AuthorizationException("Not authorized to access this resource. User type: Buyer");
        }
        else {
            return true;
        }
    }

    private TokenValidationResponse validateJwtToken(HttpServletRequest requestServlet) throws Exception {
        log.info("Inside AuthenticationInterceptor");
        log.info("Inside validateJwtToken()");
        String bearerToken = requestServlet.getHeader("Authorization");
        if(bearerToken == null){
            throw new AuthorizationException("Bearer token not provided");
        }

        log.info("Calling /api/v1/auth/validatetoken endpoint");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/v1/auth/validatetoken";

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

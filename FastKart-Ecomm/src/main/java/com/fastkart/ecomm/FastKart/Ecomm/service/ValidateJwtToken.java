package com.fastkart.ecomm.FastKart.Ecomm.service;

import com.fastkart.ecomm.FastKart.Ecomm.dto.TokenValidationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface ValidateJwtToken {
    public TokenValidationResponse validateToken(HttpServletRequest requestServlet) throws Exception;
}

package com.spectrum.spectrum_vms.config;

import com.spectrum.spectrum_vms.config.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Get the authorization header from the request
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            // Extract the JWT token from the authorization header
            String token = authHeader.substring(7);

            try {
                // Parse the JWT token and get the expiration date
                Claims claims = Jwts.parser()
                        .setSigningKey(jwtService.getSignInKey())
                        .parseClaimsJws(token)
                        .getBody();
                Date expirationDate = claims.getExpiration();

                // Check if the token has expired
                if (expirationDate.before(new Date())) {
                    // If the token has expired, send a 401 Unauthorized response
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }

            } catch (JwtException e) {
                // If there's an error parsing the token, send a 401 Unauthorized response
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }

        // If the token is valid or there's no token, continue processing the request
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Perform post-processing of the response
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Perform any cleanup tasks
    }
}

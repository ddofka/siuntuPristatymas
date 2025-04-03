package org.codeacademy.siuntupristatymas.Security;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         org.springframework.security.core.AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();
        writer.println("{ \"error\": \"Unauthorized\", \"message\": \"" + authException.getMessage() + "\" }");
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("SiuntupristatymasAPI");
        super.afterPropertiesSet();
    }

}

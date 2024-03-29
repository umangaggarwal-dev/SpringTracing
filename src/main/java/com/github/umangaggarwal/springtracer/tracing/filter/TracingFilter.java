package com.github.umangaggarwal.springtracer.tracing.filter;

import org.springframework.util.StringUtils;
import com.github.umangaggarwal.springtracer.tracing.constant.TracingConstants;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class TracingFilter implements Filter {
    @Override
    public void doFilter(
        final ServletRequest servletRequest,
        final ServletResponse servletResponse,
        final FilterChain filterChain
    ) throws IOException, ServletException {
        Optional<Object> requestIdOptional = Optional.ofNullable(
                servletRequest.getAttribute(TracingConstants.REQUEST_ID_HEADER));
        if (!requestIdOptional.isPresent()
            || !StringUtils.hasText(requestIdOptional.get().toString())) {
            String requestId = UUID.randomUUID().toString();
            servletRequest.setAttribute(TracingConstants.REQUEST_ID_HEADER, requestId);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

package com.github.umangaggarwal.springtracer.tracing.interceptor;

import com.github.umangaggarwal.springtracer.tracing.constant.TracingConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.util.Optional;

public class TracingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(
        final HttpRequest request,
        final byte[] body,
        final ClientHttpRequestExecution execution
    ) throws IOException {
        Optional<Object> requestIdOptional = Optional.ofNullable(RequestContextHolder.currentRequestAttributes()
            .getAttribute(TracingConstants.REQUEST_ID_HEADER, RequestAttributes.SCOPE_REQUEST));
        HttpHeaders httpHeaders = request.getHeaders();
        if (requestIdOptional.isPresent()
            && StringUtils.hasText(requestIdOptional.get().toString())) {
            httpHeaders.add(TracingConstants.REQUEST_ID_HEADER, requestIdOptional.get().toString());
        }
        return execution.execute(request, body);
    }
}

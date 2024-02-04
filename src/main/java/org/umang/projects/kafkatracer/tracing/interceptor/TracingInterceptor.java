package org.umang.projects.kafkatracer.tracing.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.umang.projects.kafkatracer.tracing.constant.TracingConstants;

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
        if (requestIdOptional.isPresent() && !StringUtils.isEmpty(requestIdOptional.get().toString())) {
            httpHeaders.add(TracingConstants.REQUEST_ID_HEADER, requestIdOptional.get().toString());
        }
        return execution.execute(request, body);
    }
}

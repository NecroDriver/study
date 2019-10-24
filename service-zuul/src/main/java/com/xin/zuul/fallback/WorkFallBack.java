package com.xin.zuul.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Zuul 目前只支持服务级别的熔断，不支持具体到某个URL进行熔断。
 * @author creator mafh 2019/10/24 16:36
 * @author updater
 * @version 1.0.0
 */
@Component
public class WorkFallBack implements FallbackProvider {

    private final Logger logger = LoggerFactory.getLogger(WorkFallBack.class);

    /**
     * 指定要处理的服务
     * The route this fallback will be used for.
     *
     * @return The route the fallback will be used for.
     */
    @Override
    public String getRoute() {
        return "study-service-work";
    }

    /**
     * Provides a fallback response based on the cause of the failed execution.
     *
     * @param route The route the fallback is for
     * @param cause cause of the main method failure, may be <code>null</code>
     * @return the fallback response
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        System.out.println("-----------route" + route);
        if (cause != null && cause.getCause() != null) {
            String reason = cause.getCause().getMessage();
            logger.error("reason:{}", reason);
        }
        return fallbackResponse();
    }

    /**
     * 自动以返回值
     *
     * @return 返回对象
     */
    private ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            /**
             * Return the headers of this message.
             *
             * @return a corresponding HttpHeaders object (never {@code null})
             */
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

            /**
             * Return the body of the message as an input stream.
             *
             * @return the input stream body (never {@code null})
             * @throws IOException in case of I/O errors
             */
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("当前服务访问异常，请稍后重试！".getBytes());
            }

            /**
             * Return the HTTP status code as an {@link HttpStatus} enum value.
             *
             * @return the HTTP status as an HttpStatus enum value (never {@code null})
             * @throws IOException              in case of I/O errors
             * @throws IllegalArgumentException in case of an unknown HTTP status code
             * @see HttpStatus#valueOf(int)
             * @since #getRawStatusCode()
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            /**
             * Return the HTTP status code (potentially non-standard and not
             * resolvable through the {@link HttpStatus} enum) as an integer.
             *
             * @return the HTTP status as an integer value
             * @throws IOException in case of I/O errors
             * @see #getStatusCode()
             * @see HttpStatus#resolve(int)
             * @since 3.1.1
             */
            @Override
            public int getRawStatusCode() throws IOException {
                return 10;
            }

            /**
             * Return the HTTP status text of the response.
             *
             * @return the HTTP status text
             * @throws IOException in case of I/O errors
             */
            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            /**
             * Close this response, freeing any resources created.
             */
            @Override
            public void close() {

            }
        };
    }
}

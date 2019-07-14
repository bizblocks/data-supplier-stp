package com.groupstp.datasupplier.core.bean.dadata;

import com.groupstp.datasupplier.core.config.DataSupplierConfig;
import com.groupstp.datasupplier.core.util.HeaderRequestInterceptor;
import com.groupstp.datasupplier.core.util.RestInterceptor;
import com.haulmont.cuba.core.global.TimeSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * This component communicate with DaData service
 *
 * @author adiatullin
 */
@Component(DaDataTools.NAME)
public class DaDataTools {
    public static final String NAME = "dsstp_DaDataTools";

    @Inject
    protected TimeSource timeSource;

    @Inject
    protected DataSupplierConfig config;

    protected final Object requestLock = new Object();
    protected volatile long lastRequestMillis = 0;

    /**
     * Make a request to DaData
     *
     * @param endpoint      calling endpoint
     * @param method        calling method
     * @param body          sending object
     * @param responseClass expecting result class
     * @param suggestion    is suggestion
     * @param <T>           expected result
     * @return result of call
     */
    public <T> T doRequest(String endpoint, HttpMethod method, Object body, Class<T> responseClass, boolean suggestion) {
        waitIfNeed();

        ResponseEntity response = getRestTemplate().exchange((suggestion ? config.getDaDataRestSuggestionEndpoint() : config.getDaDataRestEndpoint()) + endpoint,
                method, new HttpEntity<>(body), responseClass);
        //noinspection unchecked
        return (T) response.getBody();
    }

    protected RestTemplate getRestTemplate() {
        RestTemplate rt = new RestTemplate();
        rt.setInterceptors(Arrays.asList(
                new RestInterceptor(),
                new HeaderRequestInterceptor(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8"),
                new HeaderRequestInterceptor(HttpHeaders.ACCEPT, "application/json"),
                new HeaderRequestInterceptor(HttpHeaders.AUTHORIZATION, "Token " + config.getDaDataApiKey()),
                new HeaderRequestInterceptor("X-Secret", config.getDaDataSecretKey())
        ));
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(config.getTimeoutMs());
        factory.setReadTimeout(config.getTimeoutMs());
        rt.setRequestFactory(new BufferingClientHttpRequestFactory(factory));

        return rt;
    }

    protected void waitIfNeed() {
        Integer requestPerSecond = config.getRequestsPerSecond();
        if (requestPerSecond != null && requestPerSecond > 0) {
            long waitMillis = 1000 / requestPerSecond;

            synchronized (requestLock) {
                long millisFromLastCall = timeSource.currentTimeMillis() - lastRequestMillis;

                if (millisFromLastCall < waitMillis) {
                    try {
                        Thread.sleep(waitMillis - millisFromLastCall);
                    } catch (Exception ignore) {
                        //do nothing
                    }
                }

                lastRequestMillis = timeSource.currentTimeMillis();
            }
        }
    }
}

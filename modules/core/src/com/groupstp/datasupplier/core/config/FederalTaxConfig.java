package com.groupstp.datasupplier.core.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;
import com.haulmont.cuba.core.config.defaults.DefaultInteger;

/**
 * Federal Tax Service configuration
 *
 * @author adiatullin
 */
@Source(type = SourceType.DATABASE)
public interface FederalTaxConfig extends Config {

    /**
     * @return FNS system connection timeout in ms
     */
    @Property("tax.timeOutMs")
    @DefaultInteger(15000)
    Integer getTimeoutMs();

    /**
     * @return FNS system api key
     */
    @Property("tax.fns.apiKey")
    String getFnsApiKey();

    /**
     * @return FNS system call base url
     */
    @Property("tax.fns.restEndpoint")
    @Default("https://api-fns.ru/api")
    String getFnsRestEndpoint();

    /**
     * @return FNS system egrul communication path
     */
    @Property("tax.fns.restEgrulPath")
    @Default("/egr")
    String getFnsRestEgrulPath();
}

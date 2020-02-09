package com.groupstp.datasupplier.core.mbean;

import com.groupstp.datasupplier.entity.ImContragent;

import com.groupstp.datasupplier.exception.FederalTaxException;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * Federal Tax Service manageable bean which are available in JMX console
 *
 * @author adiatullin
 */
@ManagedResource(description = "Federal Tax Service management bean")
public interface FederalTaxMBean {

    /**
     * Retrieve a information about the contragent from external system by it tax or reg number
     *
     * @param regNumber tax or register number of contragent ("ОГРН" or "ИНН")
     * @return new contragent with information or null if nothing found
     */
    @Nullable
    @ManagedOperation(description = "Get contragent details from external system by it tax or reg number")
    ImContragent getContragent(String regNumber) throws FederalTaxException;

}

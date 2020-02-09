package com.groupstp.datasupplier.core.bean;



import com.groupstp.datasupplier.entity.ImContragent;
import com.groupstp.datasupplier.entity.ImContragentApiCall;
import com.groupstp.datasupplier.exception.FederalTaxException;

import javax.annotation.Nullable;

/**
 * Federal Tax integration server bean which communicate with external Tax system.
 *
 * @author adiatullin
 */
public interface FederalTaxWorker {
    String NAME = "rtneo_FederalTaxWorker";

    /**
     * Retrieve a information about the contragent by it tax or reg number from internal cache and if information are not exist,
     * retrieve it from external system
     *
     * @param regNumber tax or register number of contragent ("ОГРН" or "ИНН")
     * @return new contragent with information or null if nothing found
     * @throws FederalTaxException in case of any problem
     */
    @Nullable
    ImContragent getContragent(String regNumber) throws FederalTaxException;


    /**
     * Retrieve a information about the contragent by it tax or reg number from internal cache only without calling external server
     * retrieve it from external system
     *
     * @param regNumber tax or register number of contragent ("ОГРН" or "ИНН")
     * @return new contragent with information or null if nothing found
     * @throws FederalTaxException in case of any problem
     */
    @Nullable
    ImContragent getContragentFromCache(String regNumber) throws FederalTaxException;

    /**
     * Retrieve a information about the contragent from external system by it tax or reg number
     *
     * @param regNumber tax or register number of contragent ("ОГРН" or "ИНН")
     * @return new contragent with information or null if nothing found
     * @throws FederalTaxException in case of any problem
     */
    @Nullable
    ImContragent getContragentFromExternal(String regNumber) throws FederalTaxException;

    @Nullable
    ImContragentApiCall getApiCall(String regNumber);
}

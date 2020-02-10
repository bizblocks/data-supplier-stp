package com.groupstp.datasupplier.service;

import com.groupstp.datasupplier.core.bean.federal_tax.FederalTaxWorkerBean;
import com.groupstp.datasupplier.entity.ImContragent;
import com.groupstp.datasupplier.exception.FederalTaxException;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.inject.Inject;

@Service(FederalTaxService.NAME)
public class FederalTaxServiceBean implements FederalTaxService {

    @Inject
    FederalTaxWorkerBean federalTaxWorkerBean;

    @Nullable
    @Override
    public ImContragent getContragent(String regNumber) throws FederalTaxException {
        return federalTaxWorkerBean.getContragent(regNumber);
    }

    @Nullable
    @Override
    public ImContragent getContragentFromCache(String regNumber) throws FederalTaxException {
        return federalTaxWorkerBean.getContragentFromCache(regNumber);
    }

    @Nullable
    @Override
    public ImContragent getContragentFromExternal(String regNumber) throws FederalTaxException {
        return federalTaxWorkerBean.getContragentFromExternal(regNumber);
    }
}

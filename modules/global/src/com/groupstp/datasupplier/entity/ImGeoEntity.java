package com.groupstp.datasupplier.entity;

import java.math.BigDecimal;

/**
 * Geo coordinated point entity
 *
 * @author adiatullin
 */
public interface ImGeoEntity {

    /**
     * @return geo point address name
     */
    String getAddress();

    void setAddress(String address);
    /**
     * @return latitude of point
     */
    BigDecimal getLatitude();

    void setLatitude(BigDecimal latitude);

    /**
     * @return longitude of point
     */
    BigDecimal getLongitude();

    void setLongitude(BigDecimal longitude);
}

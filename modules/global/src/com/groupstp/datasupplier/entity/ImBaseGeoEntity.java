package com.groupstp.datasupplier.entity;

import javax.persistence.MappedSuperclass;
import com.haulmont.chile.core.annotations.NumberFormat;
import java.math.BigDecimal;
import javax.persistence.Column;
import com.haulmont.chile.core.annotations.MetaProperty;
import javax.persistence.Transient;
import com.haulmont.cuba.core.entity.StandardEntity;

@MappedSuperclass
public abstract class ImBaseGeoEntity extends StandardEntity implements ImGeoEntity {
    private static final long serialVersionUID = 3021274841861086918L;

    @Transient
    private transient ImGeoEntity target;

    @NumberFormat(pattern = "###.######")
    @Column(name = "LATITUDE", precision = 19, scale = 6)
    protected BigDecimal latitude;

    @NumberFormat(pattern = "###.######")
    @Column(name = "LONGITUDE", precision = 19, scale = 6)
    protected BigDecimal longitude;

    @Transient
    private transient BigDecimal distanceInMeters;

    @Transient
    private transient BigDecimal distanceInKilometers;

    public ImGeoEntity getTarget() {
        return target;
    }

    public void setTarget(ImGeoEntity target) {
        this.target = target;
        calculateDistance();
    }

    @Override
    public BigDecimal getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        calculateDistance();
    }

    @Override
    public BigDecimal getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        calculateDistance();
    }

    @MetaProperty
    public BigDecimal getDistanceInMeters() {
        return distanceInMeters;
    }

    @MetaProperty
    public BigDecimal getDistanceInKilometers() {
        return distanceInKilometers;
    }

    private void calculateDistance() {
        if (target == null || target.getLatitude() == null || target.getLongitude() == null || latitude == null || longitude == null) {
            distanceInKilometers = null;
            distanceInMeters = null;
        } else {
            distanceInKilometers = getCircleInKilometers(target.getLatitude().doubleValue(), target.getLongitude().doubleValue(),
                    latitude.doubleValue(), longitude.doubleValue());
            distanceInMeters = distanceInKilometers.multiply(new BigDecimal(1000)).setScale(2, BigDecimal.ROUND_UP);
        }
    }

    /**
     * Use Great Circle distance formula to calculate distance between 2 coordinates in kilometers.
     * https://software.intel.com/en-us/blogs/2012/11/25/calculating-geographic-distances-in-location-aware-apps
     */
    private BigDecimal getCircleInKilometers(double lat1, double lon1, double lat2, double lon2) {
        double piRad = Math.PI / 180.0;
        double earthRad = 6371.01;

        double phi1 = lat1 * piRad;
        double phi2 = lat2 * piRad;
        double lam1 = lon1 * piRad;
        double lam2 = lon2 * piRad;

        return new BigDecimal(earthRad * Math.acos(Math.sin(phi1) * Math.sin(phi2) + Math.cos(phi1) * Math.cos(phi2) * Math.cos(lam2 - lam1)))
                .setScale(2, BigDecimal.ROUND_UP);
    }
}
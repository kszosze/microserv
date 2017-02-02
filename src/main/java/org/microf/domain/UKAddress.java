package org.microf.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A UKAddress.
 */

@Table(name = "uKAddress")
public class UKAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String summaryline;

    private String organisation;

    private String buildingname;

    private String premise;

    private String street;

    private String dependentlocality;

    private String posttown;

    private String county;

    private String postcode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSummaryline() {
        return summaryline;
    }

    public UKAddress summaryline(String summaryline) {
        this.summaryline = summaryline;
        return this;
    }

    public void setSummaryline(String summaryline) {
        this.summaryline = summaryline;
    }

    public String getOrganisation() {
        return organisation;
    }

    public UKAddress organisation(String organisation) {
        this.organisation = organisation;
        return this;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public UKAddress buildingname(String buildingname) {
        this.buildingname = buildingname;
        return this;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public String getPremise() {
        return premise;
    }

    public UKAddress premise(String premise) {
        this.premise = premise;
        return this;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public String getStreet() {
        return street;
    }

    public UKAddress street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDependentlocality() {
        return dependentlocality;
    }

    public UKAddress dependentlocality(String dependentlocality) {
        this.dependentlocality = dependentlocality;
        return this;
    }

    public void setDependentlocality(String dependentlocality) {
        this.dependentlocality = dependentlocality;
    }

    public String getPosttown() {
        return posttown;
    }

    public UKAddress posttown(String posttown) {
        this.posttown = posttown;
        return this;
    }

    public void setPosttown(String posttown) {
        this.posttown = posttown;
    }

    public String getCounty() {
        return county;
    }

    public UKAddress county(String county) {
        this.county = county;
        return this;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public UKAddress postcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UKAddress uKAddress = (UKAddress) o;
        if (uKAddress.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, uKAddress.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UKAddress{" +
            "id=" + id +
            ", summaryline='" + summaryline + "'" +
            ", organisation='" + organisation + "'" +
            ", buildingname='" + buildingname + "'" +
            ", premise='" + premise + "'" +
            ", street='" + street + "'" +
            ", dependentlocality='" + dependentlocality + "'" +
            ", posttown='" + posttown + "'" +
            ", county='" + county + "'" +
            ", postcode='" + postcode + "'" +
            '}';
    }
}

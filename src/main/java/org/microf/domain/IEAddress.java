package org.microf.domain;

import com.datastax.driver.mapping.annotations.*;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * not an ignored comment
 */
@ApiModel(description = "not an ignored comment")

@Table(name = "iEAddress")
public class IEAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String addressline1;

    private String addressline2;

    private String summaryline;

    private String organisation;

    private String street;

    private String posttown;

    private String county;

    private String postcode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public IEAddress addressline1(String addressline1) {
        this.addressline1 = addressline1;
        return this;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public IEAddress addressline2(String addressline2) {
        this.addressline2 = addressline2;
        return this;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getSummaryline() {
        return summaryline;
    }

    public IEAddress summaryline(String summaryline) {
        this.summaryline = summaryline;
        return this;
    }

    public void setSummaryline(String summaryline) {
        this.summaryline = summaryline;
    }

    public String getOrganisation() {
        return organisation;
    }

    public IEAddress organisation(String organisation) {
        this.organisation = organisation;
        return this;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getStreet() {
        return street;
    }

    public IEAddress street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPosttown() {
        return posttown;
    }

    public IEAddress posttown(String posttown) {
        this.posttown = posttown;
        return this;
    }

    public void setPosttown(String posttown) {
        this.posttown = posttown;
    }

    public String getCounty() {
        return county;
    }

    public IEAddress county(String county) {
        this.county = county;
        return this;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public IEAddress postcode(String postcode) {
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
        IEAddress iEAddress = (IEAddress) o;
        if (iEAddress.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, iEAddress.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "IEAddress{" +
            "id=" + id +
            ", addressline1='" + addressline1 + "'" +
            ", addressline2='" + addressline2 + "'" +
            ", summaryline='" + summaryline + "'" +
            ", organisation='" + organisation + "'" +
            ", street='" + street + "'" +
            ", posttown='" + posttown + "'" +
            ", county='" + county + "'" +
            ", postcode='" + postcode + "'" +
            '}';
    }
}

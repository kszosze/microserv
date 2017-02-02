package org.microf.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the IEAddress entity.
 */
public class IEAddressDTO implements Serializable {

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

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }
    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }
    public String getSummaryline() {
        return summaryline;
    }

    public void setSummaryline(String summaryline) {
        this.summaryline = summaryline;
    }
    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    public String getPosttown() {
        return posttown;
    }

    public void setPosttown(String posttown) {
        this.posttown = posttown;
    }
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
    public String getPostcode() {
        return postcode;
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

        IEAddressDTO iEAddressDTO = (IEAddressDTO) o;

        if ( ! Objects.equals(id, iEAddressDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "IEAddressDTO{" +
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

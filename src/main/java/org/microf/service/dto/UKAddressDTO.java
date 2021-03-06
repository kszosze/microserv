package org.microf.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the UKAddress entity.
 */
public class UKAddressDTO implements Serializable {

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

    public void setSummaryline(String summaryline) {
        this.summaryline = summaryline;
    }
    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }
    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }
    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    public String getDependentlocality() {
        return dependentlocality;
    }

    public void setDependentlocality(String dependentlocality) {
        this.dependentlocality = dependentlocality;
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

        UKAddressDTO uKAddressDTO = (UKAddressDTO) o;

        if ( ! Objects.equals(id, uKAddressDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UKAddressDTO{" +
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

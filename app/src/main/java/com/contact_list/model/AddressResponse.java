package com.contact_list.model;

import com.google.gson.annotations.SerializedName;

public class AddressResponse {

    @SerializedName("cep")
    private String zipcode;
    @SerializedName("logradouro")
    private String street;
    @SerializedName("complemento")
    private String complement;
    @SerializedName("bairro")
    private String neighborhood;
    @SerializedName("localidade")
    private String city;
    @SerializedName("uf")
    private String state;

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

package br.com.victor.teste_supply.person_register.model.dto;

import lombok.Getter;

@Getter
public class AddressDTO {
    private String street;
    private String number;
    private String district;
    private CityDTO city;
    private  String zipCode;

    public AddressDTO(String street, String number, String district, CityDTO city, String zipCode) {
        setStreet(street);
        setNumber(number);
        setDistrict(district);
        setCity(city);
        setZipCode(zipCode);
    }

    public void setStreet(String street) {
        if(street == null || street.isBlank() || street.isEmpty()){
            throw new IllegalArgumentException("Street is empty");
        }
        this.street = street;
    }

    public void setNumber(String number) {
        if(number == null || number.isEmpty() || number.isBlank()){
            throw new IllegalArgumentException("Number is empty");
        }
        this.number = number;
    }

    public void setDistrict(String district) {
        if(district == null || district.isBlank() || district.isEmpty()){
            throw new IllegalArgumentException("District is empty");
        }
        this.district = district;
    }

    public void setCity(CityDTO city) {
        if(city == null){
            throw new IllegalArgumentException("City is empty");
        }
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        if(zipCode == null || zipCode.isEmpty() || zipCode.isBlank()){
            throw new IllegalArgumentException("ZipCode is empty");
        }
        this.zipCode = zipCode;
    }
}

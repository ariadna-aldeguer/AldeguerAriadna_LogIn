package com.example.levi.Model;

/**
 * Class that represents a travel
 */

public class Travel {

    private String country;
    private String city;
    private String airport;
    private int id;

    /**
     * Constructor of class Travel
     */
    public Travel(String country, String city, String airport){
        this.country = country;
        this.city = city;
        this.airport = airport;
    }

    /**
     * Constructor of class Travel
     */
    public Travel(int id, String country, String city, String airport){
        this.id = id;
        this.country = country;
        this.city = city;
        this.airport = airport;
    }

    /**
     * Getters and setter of class travel
     */
    public String getCountry(){
        return country;
    }

    public void setCountry(String country){ this.country = country; }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.vitechinc.step2;

class User{
    private String firstName;
    private String lastName;
    private String sport;
    private Integer numOfYears;
    private Boolean vegetarian;
    
    public User(String firstName, String lastName, String sport,
            Integer numOfYears, Boolean vegetarian) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.sport = sport;
        this.numOfYears = numOfYears;
        this.vegetarian = vegetarian;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public Integer getNumOfYears() {
        return numOfYears;
    }

    public void setNumOfYears(Integer numOfYears) {
        this.numOfYears = numOfYears;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }
}
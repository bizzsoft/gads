package com.example.gadso;

class skill {
    String name;
    String hours;
    String country;
    String badgeUrl;
    public skill(){

    }
    public skill(String name, String hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeurl() {
        return badgeUrl;
    }

    public void setBadgeurl(String badgeurl) {
        this.badgeUrl = badgeurl;
    }
}

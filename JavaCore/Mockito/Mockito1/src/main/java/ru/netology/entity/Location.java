package ru.netology.entity;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final int builing;

    public Location(String city, Country country, String street, int builing) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.builing = builing;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public int getBuiling() {
        return builing;
    }

    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Location location = (Location) obj;
        if (this.country == null &&
                this.city == null &&
                this.street == null &&
                location.country == null &&
                location.city == null &&
                location.street == null) {
            return true;
        } else if (this.street == null && location.street == null) {
            return this.country.equals(location.country) && this.city.equals(location.city);
        } else {
            return this.street.equals(location.street)
                    && this.country.equals(location.country)
                    && (this.city.equals(location.city))
                    && this.builing == location.builing;
        }
    }
}

package com.example.grandetravel;

public class Package {

    //fields
    private String name;
    private String location;
    private String description;
    private double price;

    //constructor
    public Package(String name, String location, String description, double price){
        this.name = name;
        this.location = location;
        this.description = description;
        this.price = price;
    }

    //setter and getter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    //ToString

    @Override
    public String toString() {
        return name + ":\n" +
                "Location: " + location + "\n" +
                "Details: " + description + "\n" +
                "Price: " + price ;
    }
}

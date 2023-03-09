package sample;

public class City {
    int year;
    String name;
    String country;
    int value;
    String category;

    public City(int year, String name, String country, int value, String category) {
        this.year = year;
        this.name = name;
        this.country = country;
        this.value = value;
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "City{" +
                "year='" + year + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", value=" + value +
                ", category='" + category + '\'' +
                '}';
    }

}
package sample;

public class Brand {
    String year;
    String name;
    String country;
    int value;
    String category;

    public Brand(String year, String name, String country, int value, String category) {
        this.year = year;
        this.name = name;
        this.country = country;
        this.value = value;
        this.category = category;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
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
        return "Brand{" +
                "year='" + year + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", value=" + value +
                ", category='" + category + '\'' +
                '}';
    }
}

package sample;

public class Record implements Comparable<Record>{
    String name;
    String category;
    int value;

    public Record(String name, String category, int value) {
        this.name = name;
        this.category = category;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Record o) {
        if (this.value > o.value) {

            // if current object is greater,then return 1
            return 1;
        }
        else if (this.value < o.value) {

            // if current object is greater,then return -1
            return -1;
        }
        else {

            // if current object is equal to o,then return 0
            return 0;
        }
    }
}

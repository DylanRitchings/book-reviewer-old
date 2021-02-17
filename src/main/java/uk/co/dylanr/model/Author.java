package uk.co.dylanr.model;
public class Author {
    int id;
    String first_name;
    String last_name;
    String middle_names;

    public Author(int id, String first_name, String last_name, String middle_names) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_names = middle_names;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_names() {
        return middle_names;
    }

    public void setMiddle_names(String middle_names) {
        this.middle_names = middle_names;
    }
}

package info.androidhive.recyclerviewsearch;

/**
 * Created by ravi on 16/11/17.
 */

public class Contact {
    private String name;
    private String image;
    private String phone;
    private Boolean isChecked;

    public Contact(String name, String image, String phone, Boolean isChecked) {
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.isChecked = isChecked;
    }


    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
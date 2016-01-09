package utils;

/**
 * Created by Jenya on 21.11.2015.
 */

public class categoryItem
{

    private int id;
    private int value;
    private String categoryName;
    private String description;
    private String favColor;

    public categoryItem() {

    }

    public categoryItem(int id, int value, String productName, String description, String favColor)
    {
        this.id=id;
        this.value = value;
        this.categoryName = productName;
        this.description = description;
        this.favColor = favColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.value = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFavColor() {
        return favColor;
    }

    public void setFavColor(String favColor) {
        this.favColor = favColor;
    }
}

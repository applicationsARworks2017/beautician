package androidapp.com.sapplication.Pojo;

/**
 * Created by Amaresh on 11/11/17.
 */

public class SubCategoryList {
    String id,sub_category_id,price,category,subcategory,category_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(String sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public SubCategoryList(String id, String subcategory, String category_id, String sub_category_id, String price, String category) {
        this.id=id;
        this.subcategory=subcategory;
        this.category_id=category_id;
        this.sub_category_id=sub_category_id;
        this.price=price;
        this.category=category;


    }
}

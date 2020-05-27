package com.cpetsolut.com.voliveassignment;

public class DetailsModel {
    String order_id;
    String order_status;
    String added_on;
    String paid_amount;
    String rating;
    String product_id;
    String order_title;
    String image;

    public DetailsModel() {
    }

    public DetailsModel(String order_id, String order_status, String added_on, String paid_amount, String rating, String product_id, String order_title, String image) {
        this.order_id = order_id;
        this.order_status = order_status;
        this.added_on = added_on;
        this.paid_amount = paid_amount;
        this.rating = rating;
        this.product_id = product_id;
        this.order_title = order_title;
        this.image = image;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getAdded_on() {
        return added_on;
    }

    public void setAdded_on(String added_on) {
        this.added_on = added_on;
    }

    public String getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(String paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


package com.example.restaurant_app.model.demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Demo {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ingredient")
    @Expose
    private Ingredient ingredient;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

}

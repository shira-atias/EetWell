package com.example.eatwell.models;

public class MyRecipe {

    private String title;
    private String instructions;
    private String ingredient;
    private String id;
    private String image;

    public MyRecipe() {
    }

    public MyRecipe(String recipeName, String instructions, String ingredient, String id, String image) {
        title = recipeName;
        this.instructions = instructions;
        this.ingredient = ingredient;
        this.id = id;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "MyRecipe{" +
                "RecipeName='" + title + '\'' +
                ", instructions='" + instructions + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", id='" + id + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

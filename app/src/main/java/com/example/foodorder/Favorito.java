package com.example.foodorder;

public class Favorito {

    private String menuId;
    private String name;
    private String photo;
    private String categories;
    private String rating;


    public Favorito(String id, String nombre, String categoria, String foto,String puntuacion) {
        this.menuId = id;
        this.name = nombre;
        this.photo = foto;
        this.categories = categoria;
        this.rating = puntuacion;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "{" + "menuId=" + this.getMenuId() + ", name=" + this.getName() + ", photo=" + this.getPhoto() + "', categories=" + this.getCategories() + ", rating=" + this.getRating() + "}";
    }
}



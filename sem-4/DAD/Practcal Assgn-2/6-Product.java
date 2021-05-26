 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      6-Product.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 public class Product {
    final int id;
    final String name;
    final String description;
    final double price;

    public Product(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }
}
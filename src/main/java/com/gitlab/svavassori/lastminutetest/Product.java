
package com.gitlab.svavassori.lastminutetest;

import java.util.Objects;

/**
 * This class represents a specific product, for the sake of the exercise,
 * I assume the name identify uniquely the product.
 * 
 * @author sergio
 */
public class Product {
    
    private String name;
    private int price;
    private Origin origin;
    
    /**
     * Construct an immutable instance of {@link Product} using the given
     * parameters.
     * 
     * @param name the name of the product, it identifies uniquely
     * @param price the price of the product, in cents
     * @param origin the origin of the product
     * @throws NullPointerException if {@code name} or {@code origin} are null
     * @throws IllegalArgumentException if {@code price} is negative
     */
    public Product(String name, int price, Origin origin) {
        
        this.name = Objects.requireNonNull(name);
        this.price = requireNonNegative(price);
        this.origin = Objects.requireNonNull(origin);
    }
    
    private int requireNonNegative(int price) {
        
        if (price < 0) {
            
            throw new IllegalArgumentException("Price cannot be negative: " + price);
        }
        
        return price;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        
        return name;
    }
    
    /**
     * @return the price, in cents
     */
    public int getPrice() {
        
        return price;
    }
    
    /**
     * @return the origin
     */
    public Origin getOrigin() {
        
        return origin;
    }
}


package com.gitlab.svavassori.lastminutetest;


/**
 * This enumeration represents all the known categories of a given item.
 * 
 * @author sergio
 */
public enum Category {
    
    BOOKS,
    FOOD,
    MEDICAL,
    OTHER;
    
    public static Category findCategory(String name) {
        
        // this is an heuristic
        Category category = Category.OTHER;
        
        if (name.contains("book")) {
            
            category = Category.BOOKS;
            
        } else if (name.contains("chocolate")) {
            
            category = Category.FOOD;
            
        } else if (name.contains("headache")) {
            
            category = Category.MEDICAL;
        }
        
        return category;
    }
}

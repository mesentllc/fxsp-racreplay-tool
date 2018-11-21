package com.fedex.smartpost.rodes.racreplay;

public class SystemParameter implements Comparable {
    private String category;
    private String subCategory;
    private String name;
    private String cache;
    private String value;
    private String comment;
    private String type;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public boolean equals(SystemParameter target) {
        return (name.equals(target.name) && category.equals(target.category) && 
                subCategory.equals(target.subCategory));
    }
    
    public int compareTo(Object o) {
        SystemParameter target = (SystemParameter)o;
        
        int returnVal = category.compareTo(target.category);
        if (returnVal == 0) {
            returnVal = subCategory.compareTo(target.subCategory);
        }
        if (returnVal == 0) {
            returnVal = name.compareTo(target.name);
        }
        return returnVal;
    }
}

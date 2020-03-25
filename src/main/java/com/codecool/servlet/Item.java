package com.codecool.servlet;

public class Item {
    String id;
    String name;
    float price;

    Item(String id, String name,float price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o){
        if(o == null)   return false;
        if(o instanceof Item){
            Item item = (Item) o;
            return this.name.equals(item.name);
        }
        return false;
    }
}

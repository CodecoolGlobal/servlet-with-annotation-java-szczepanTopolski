package com.codecool.servlet;

import java.util.ArrayList;
import java.util.List;

public class Card {
    static List<Item> items = new ArrayList<>();

    static void addItem(Item item){
        items.add(item);
    }

    static void removeItem(Item item){
        items.remove(item);
    }
}

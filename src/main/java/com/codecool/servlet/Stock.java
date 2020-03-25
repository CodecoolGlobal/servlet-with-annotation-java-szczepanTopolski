package com.codecool.servlet;

import java.util.Arrays;
import java.util.List;

public class Stock {
    public static List<Item> stock = Arrays.asList(
            new Item("1","buty",30),
            new Item("2","spodnie",100),
            new Item("3","skarpetki",10),
            new Item("4","koszulka",15),
            new Item("5","czapka",50),
            new Item("6","okulary",150),
            new Item("7","koszula",120)
    );
}

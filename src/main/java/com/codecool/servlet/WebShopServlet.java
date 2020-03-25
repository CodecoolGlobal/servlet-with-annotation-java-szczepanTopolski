package com.codecool.servlet;
import com.google.gson.Gson;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "userStoreHandler", urlPatterns = {"/webshop","/webshop/buy","/webshop/remove"}, loadOnStartup = 1)
public class WebShopServlet extends HttpServlet {
    static List<Item> availableItems = initializeItems();

    static List<Item> initializeItems() {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            double x = (Math.random()*((6-0)+1))+0;
            items.add(Stock.stock.get((int) x));
        }
        return items;
    }

        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if(request.getMethod().equals("GET")) {
            response.setStatus(200);
            response.getWriter().println(prepareWeb());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if(request.getMethod().equals("POST")) {
            Gson gson = new Gson();
            Item reqItem = getRequestedObject(request);
            if (request.getRequestURI().contains("buy")) {
                handleBuy(reqItem);
            }
            if (request.getRequestURI().contains("remove")) {
                handleRemove(reqItem);
            }
            response.setStatus(200);
            response.getWriter().println(gson.toJson(reqItem));
        }
    }

    private void handleRemove(Item reqItem) {
        Card.removeItem(reqItem);
    }

    private void handleBuy(Item reqItem) {
        Card.addItem(reqItem);
    }

    private Item getRequestedObject(HttpServletRequest request) {
        StringBuffer jb = new StringBuffer();
        Gson gson = new Gson();
        String line = null;
        Item item = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }
        item =  gson.fromJson(jb.toString(),Item.class);
        return item;
    }

    private String prepareWeb() {
        List<List<String>> items = new ArrayList<>();
        for(Item item: availableItems){
            List<String> row = Arrays.asList(item.id,item.name,String.valueOf(item.price));
            items.add(row);
        }
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/userStore.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("items",items);
        return template.render(model);
    }

}

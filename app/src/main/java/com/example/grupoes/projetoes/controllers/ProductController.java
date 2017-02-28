package com.example.grupoes.projetoes.controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.grupoes.projetoes.beans.DeletePointOfSaleBean;
import com.example.grupoes.projetoes.beans.DeleteProductBean;
import com.example.grupoes.projetoes.beans.EditPointOfSaleBean;
import com.example.grupoes.projetoes.beans.EditProductBean;
import com.example.grupoes.projetoes.custom_callbacks.PointsOperationCallback;
import com.example.grupoes.projetoes.localstorage.SessionStorage;
import com.example.grupoes.projetoes.models.PointOfSale;
import com.example.grupoes.projetoes.models.Product;
import com.example.grupoes.projetoes.request_handlers.PointOfSaleHandler;
import com.example.grupoes.projetoes.request_handlers.ProductHandler;
import com.example.grupoes.projetoes.util.UtilOperations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hadrizia on 28/02/2017.
 */

public class ProductController {
    private static ProductController instance;

    private List<Product> products;

    public static ProductController getInstance() {
        if (instance == null) {
            instance = new ProductController();
        }

        return instance;
    }

    private ProductController() {

    }
    public void deleteProduct(String productName, Context context) {
        SessionStorage storage = new SessionStorage(context);
        String username = storage.getLoggedUser().getUsername();
        String token = storage.getToken();

        DeleteProductBean bodyData = new DeleteProductBean(username, productName);

        ProductHandler.getInstance().requestDelete(bodyData, token,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            products.remove(findProductByName(response.getString("name")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }

                });
    }

    public void editProduct(final String productName, String newName, String productDescription, double productPrice, Bitmap productImage, Context context) {
        SessionStorage storage = new SessionStorage(context);
        String requester = storage.getLoggedUser().getUsername();
        String token = storage.getToken();

        String productImageStr = UtilOperations.bitMapToString(productImage);

        EditProductBean bodyData = new EditProductBean(requester, newName, productDescription, productPrice, productImageStr, productName);

        ProductHandler.getInstance().requestEditProduct(bodyData, token,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Product product = findProductByName(productName);
                            product.setProductName(response.getString("name"));
                            product.setProductComment(response.getString("comment"));
                            product.setProductImage((response.getString("image")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }

                });
    }
    public void getProducts(Context context, String pointOfSale) {
        products = new ArrayList<>();
        ProductHandler.getInstance().requestGetProducts(pointOfSale, new SessionStorage(context).getToken(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);
                                products.add(createProduct(object));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("PRODUCTS", ""+products.size());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
    }

    private Product createProduct(JSONObject object) {
        try {
            String creator = object.getString("creator");
            String pointOfSale = object.getString("pointOfSale");
            String productName = object.getString("productName");
            String productComment = object.getString("productComment");
            double productPrice = object.getDouble("productPrice");
            String productImage = object.getString("productImage");
            return new Product(creator, pointOfSale, productName, productComment, productPrice, productImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Product findProductByName(String productName) {
        if (products.size() > 0) {
            for (Product product : products) {
                if (product.getProductName().equals(productName)) {
                    return product;
                }
            }
        }
        return null;
    }

    public List<Product> getProductsByPointOfSale(String pointOfSale){
        List<Product> productsList = new ArrayList<>();
        for (Product product : products){
            if(product.getPointOfSale().equals(pointOfSale)){
                productsList.add(product);
            }
        }
        return productsList;
    }
}

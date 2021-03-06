package com.example.grupoes.projetoes.util;

import com.android.volley.Request;
import com.example.grupoes.projetoes.util.FrontendConstants;

/**
 * Created by Wesley on 21/02/2017.
 */

public enum RequestActions {
    LOGIN("login_request",
           FrontendConstants.LOGIN_REQUEST_URL,
           Request.Method.POST),
    SIGNUP("signup_request",
            FrontendConstants.SIGNUP_REQUEST_URL,
            Request.Method.POST),
    EDIT_USER("edit_user_request",
              FrontendConstants.EDIT_USER_REQUEST_URL,
              Request.Method.PUT),
    ADD_POINT_OF_SALE("add_point_of_sale",
                      FrontendConstants.ADD_POINT_OF_SALE_REQUEST_URL,
                      Request.Method.POST),
    EDIT_POINT_OF_SALE("edit_point_of_sale",
                       FrontendConstants.EDIT_POINT_OF_SALE_REQUEST_URL,
                       Request.Method.PUT),
    DELETE_POINT_OF_SALE("delete_point_of_sale",
                         FrontendConstants.DELETE_POINT_OF_SALE_REQUEST_URL,
                         Request.Method.POST),
    GET_ALL_POINTS_OF_SALE("get_all_points_of_sale",
            FrontendConstants.GET_ALL_POINTS_OF_SALE_REQUEST_URL,
            Request.Method.GET),
    ADD_PRODUCT("add_product",
            FrontendConstants.ADD_PRODUCT_REQUEST_URL,
            Request.Method.POST),
    EDIT_PRODUCT("edit_product",
            FrontendConstants.EDIT_PRODUCT_REQUEST_URL,
            Request.Method.PUT),
    DELETE_PRODUCT("delete_product",
            FrontendConstants.DELETE_PRODUCT_REQUEST_URL,
            Request.Method.POST),
    GET_PRODUCTS("get_products",
            FrontendConstants.GET_PRODUCTS_REQUEST_URL,
            Request.Method.POST),
    SEARCH_PRODUCTS("search_products",
            FrontendConstants.SEARCH_PRODUCT_URL,
            Request.Method.POST),
    SEARCH_POINTS_OF_SALE("search_pointsofsale",
            FrontendConstants.SEARCH_POINTOFSALE_URL,
            Request.Method.POST);

    private String name;
    private String url;
    private int method;

    RequestActions(String name, String url, int method) {
        this.name = name;
        this.url = url;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getRequestMethod() {
        return method;
    }


}

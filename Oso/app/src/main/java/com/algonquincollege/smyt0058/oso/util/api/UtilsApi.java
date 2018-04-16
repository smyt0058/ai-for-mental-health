package com.algonquincollege.smyt0058.oso.util.api;

public class UtilsApi {

    // Base URL for Login Authentication
    public static final String BASE_URL_API = "http://dosh0005.edumedia.ca/";
    //public static final String BASE_QUESTION_URL_API = "http://kerr0215.edumedia.ca/api";

    // Declaring Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}

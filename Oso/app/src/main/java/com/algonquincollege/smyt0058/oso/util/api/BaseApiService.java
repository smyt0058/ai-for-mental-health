package com.algonquincollege.smyt0058.oso.util.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApiService {

    // This function is to call API http://http://dosh0005.edumedia.ca/api/auth/
    @FormUrlEncoded
    @POST("api/auth/")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);
    @FormUrlEncoded
    @POST("api/chat/")
    Call<ResponseBody> msgPost(@Field("authkey") String authkey,
                               @Field("query") String query);

    @FormUrlEncoded
    @POST("api/chat/")
    Call<ResponseBody> msgEventPost(@Field("authkey") String authkey,
                                    @Field("query") String query,
                                    @Field("e") String event);

    @FormUrlEncoded
    @POST("api/name/change/")
    Call<ResponseBody> changeNamePost(@Field("authkey") String authkey,
                                      @Field("name") String name);

    @FormUrlEncoded
    @POST("api/password/change/")
    Call<ResponseBody> changePasswordPost(@Field("authkey") String authkey,
                                          @Field("oldpassword") String oldpassword,
                                          @Field("newpassword") String newpassword);

}

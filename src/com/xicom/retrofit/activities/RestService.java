/**
 * Project : Retrofit
 * Author : xicom
 * Creation Date : 12-Feb-2014
 * Description : @TODO
 */
package com.xicom.retrofit.activities;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

import com.google.gson.JsonObject;
import com.xicom.retrofit.models.Model;
import com.xicom.retrofit.models.ModelNew;
import com.xicom.retrofit.utils.GlobalVariables;

public interface RestService
{
	//Example Services

	// GET Request
	@GET("/key/{key}/value/{value}")
	void GetModel(@Path("key") String key, @Path("value") String value, Callback<Model> callback);

	@GET("/key/{key}/value/{value}")
	void GetModelNew(@Path("key") String key, @Path("value") String value, Callback<ModelNew> callback);

	@GET(GlobalVariables.URL_CHECK_EMAIL_CONSTANT)
	void GetLogin(@Query("email") String email, Callback<Response> callback);
	
	//POST Request
	@POST(GlobalVariables.URL_REGISTER_CONSTANT)
	void Register(@Body JsonObject object, Callback<Response> callback);
	
	// NameValuePair
	@FormUrlEncoded
	@POST("/user/edit")
	void updateUser(@Field("first_name") String first, @Field("last_name") String last, Callback<ModelNew> callback);

	// MultiPart
	@Multipart
	@PUT("/user/photo")
	void updateUser(@Part("photo") TypedFile photo, @Part("description") TypedString description, Callback<ModelNew> callback);

	// Headers InBuilt
	@Headers({ "Accept: application/vnd.github.v3.full+json", "User-Agent: Retrofit-Sample-App" })
	@GET("/users/{username}")
	void getUser(@Path("username") String username, Callback<ModelNew> callback);

	// Dynamic Header
	@GET("/user")
	void getUser2(@Header("Authorization") String authorization, Callback<ModelNew> callback);

}

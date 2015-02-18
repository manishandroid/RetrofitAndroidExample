/**
 * Project : Retrofit
 * Author : xicom
 * Creation Date : 13-Feb-2014
 * Description : @TODO
 */
package com.xicom.retrofit.utils;

public interface GlobalVariables
{
	public enum SERVICE_MODE
	{
		MODEL, MODEL_NEW, UNKNOWN, EMAIL, REGISTER
	}
	
	public final static String BASE_URL_CONSTANT = "http://python.xicom.us/api/v1";//"http://echo.jsontest.com";
	public static final String URL_CHECK_EMAIL_CONSTANT = "/check_user_exists/";
	public static final String URL_REGISTER_CONSTANT = "/register_user/";
	
}

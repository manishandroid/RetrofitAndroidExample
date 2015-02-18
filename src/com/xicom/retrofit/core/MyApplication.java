/**
 * Project : Retrofit
 * Author : xicom
 * Creation Date : 12-Feb-2014
 * Description : @TODO
 */
package com.xicom.retrofit.core;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.OkClient;
import android.app.Application;

import com.squareup.okhttp.OkHttpClient;
import com.xicom.retrofit.activities.RestService;
import com.xicom.retrofit.utils.GlobalVariables;

public class MyApplication extends Application
{
	private RestService service;

	public RestService getService()
	{
		return (service == null)?setService():service;
	}
	
	/*Headers:- {
	    ClientProperty = "PLATFORM##IPHONE::CLIENT_VERSION##VOIZD_IOS_1_2::IMEI##207d3c8e4c05f28ad64985e56a3f7f95::MODEL##iPhone Simulator::os_ver##8.1::audio##caf,mp3,wav::image##tiff,jpeg,gif,png::video##mov,mp4,m4v,avi::scr_size##320X460::MANUFACTURER##apple::imagesize##240x180::thumbsize##80x60";
	    "Content-Type" = "multipart/form-data; charset=utf-8; boundary=Boundary-1FFFB3B6-5B8D-47A4-AF31-3BE31FA9137F";
	    authparams = "imei##207d3c8e4c05f28ad64985e56a3f7f95::authkey##8be7a063a19fd760850351fcbc43eec9";
	    requesttype = 1006;
	}*/

	public RestService setService()
	{
		//Default Headers
		RequestInterceptor requestInterceptor = new RequestInterceptor()
		{
			@Override
			public void intercept(RequestFacade request)
			{
				request.addHeader("ClientProperty", "PLATFORM##IPHONE::CLIENT_VERSION##VOIZD_IOS_1_2::IMEI##207d3c8e4c05f28ad64985e56a3f7f95::MODEL##iPhone Simulator::os_ver##8.1::audio##caf,mp3,wav::image##tiff,jpeg,gif,png::video##mov,mp4,m4v,avi::scr_size##320X460::MANUFACTURER##apple::imagesize##240x180::thumbsize##80x60");
				request.addHeader("Content-Type", "multipart/form-data; charset=utf-8; boundary=Boundary-1FFFB3B6-5B8D-47A4-AF31-3BE31FA9137F");
				request.addHeader("authparams", "imei##207d3c8e4c05f28ad64985e56a3f7f95::authkey##8be7a063a19fd760850351fcbc43eec9");
				request.addHeader("requesttype", "1006");
			}
		};

		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(GlobalVariables.BASE_URL_CONSTANT).setClient(new OkClient(new OkHttpClient()))
				.setRequestInterceptor(requestInterceptor).setLogLevel(LogLevel.FULL).build();
		service = restAdapter.create(RestService.class);
		return service;
	}

}

package com.xicom.retrofit.core;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View.OnClickListener;

import com.xicom.retrofit.activities.RestService;
import com.xicom.retrofit.utils.GlobalVariables.SERVICE_MODE;

public abstract class BaseActivity<T> extends Activity implements OnClickListener
{
	public Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		this.context = this;
		super.onCreate(savedInstanceState);
	}

	public abstract void success(T model, Response arg1, SERVICE_MODE mode);
	public abstract void failure(RetrofitError e, SERVICE_MODE mode);

	public RestService getService()
	{
		return ((MyApplication) getApplicationContext()).getService();
	}

}

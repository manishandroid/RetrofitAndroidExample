package com.xicom.retrofit.activities;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.xicom.retrofit.R;
import com.xicom.retrofit.core.BaseActivity;
import com.xicom.retrofit.utils.GlobalVariables.SERVICE_MODE;
import com.xicom.retrofit.utils.UtilitySingleton;

public class MainActivity<T> extends BaseActivity<T>
{
	private int[] clickIDs = { R.id.btn_call };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		UtilitySingleton.getInstance(context).SetClickListener(clickIDs);
	}

	@Override
	public void onClick(final View v)
	{
		switch (v.getId())
		{
		case R.id.btn_call:
			new Handler().post(new Runnable()
			{
				@Override
				public void run()
				{
//					getService().GetModel("1", "navjot", new MyCallback<Model>(context, true, v, SERVICE_MODE.MODEL));
					getService().GetLogin("abc@gmail.com", new MyCallback<Response>(context, true, v, SERVICE_MODE.EMAIL));
				}
			});

			break;
		default:
			break;
		}
	}

	@Override
	public void success(T model, Response arg1, SERVICE_MODE mode)
	{
		switch (mode)
		{
		case MODEL:
			break;
		case EMAIL:
			break;
		default:
			break;
		}
	}

	@Override
	public void failure(RetrofitError e, SERVICE_MODE mode)
	{
		switch (mode)
		{
		case MODEL:
			break;
		case EMAIL:
			break;
		default:
			break;
		}
	}

}

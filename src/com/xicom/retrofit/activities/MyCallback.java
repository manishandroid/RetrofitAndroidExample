/**
 * Project : Retrofit
 * Author : xicom
 * Creation Date : 12-Feb-2014
 * Description : @TODO
 */
package com.xicom.retrofit.activities;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.google.gson.GsonBuilder;
import com.xicom.retrofit.core.BaseActivity;
import com.xicom.retrofit.utils.GlobalVariables.SERVICE_MODE;
import com.xicom.retrofit.utils.Log4Android;
import com.xicom.retrofit.utils.UtilitySingleton;

public class MyCallback<T> implements Callback<T>
{
	private final static String defaultMessage = "Loading";
	public static final float DIALOG_OFFSET_MILISEC = 300;
	private static ProgressDialog dialog;
	private static int callCount;

	private View v;
	private BaseActivity<T> baseActivity;
	private SERVICE_MODE mode;

	/**
	 * Description : Callback with custom message
	 */
	@SuppressWarnings("unchecked")
	public MyCallback(Context context, boolean showProgress, View v, String message, SERVICE_MODE mode)
	{
		this.baseActivity = (BaseActivity<T>) context;
		this.mode = mode;
		this.v = v;
		v.setClickable(false);
		if (showProgress)
		{
			StartProgress(message);
		}
	}
	
	/**
	 * Description : Callback with default message
	 */
	public MyCallback(Context context, boolean showProgress, View v, SERVICE_MODE mode)
	{
		this(context, showProgress, v, defaultMessage, mode);
	}

	@Override
	public void failure(RetrofitError error)
	{
		StopProgress();
		if (error.isNetworkError())
		{
			//TODO What to show when network fails
			error.printStackTrace();
			Log4Android.e(this, "Network Failure==> " + error.getMessage());
		}
		else
		{
			Log4Android.e(this, "Failure==> " + UtilitySingleton.getInstance(baseActivity).getResponse(error.getResponse()));
		}
		baseActivity.failure(error, mode);
	}

	@Override
	public void success(T model, Response arg1)
	{
		StopProgress();
		ShowLog(model, arg1);
		baseActivity.success(model, arg1, mode);
	}

	private void ShowLog(T model, Response arg1)
	{
		String body = UtilitySingleton.getInstance(baseActivity).getResponse(arg1);
		System.out.println("headers :: "+arg1.getHeaders().get(0).toString());
		Log4Android.l(this, "URL==> " + arg1.getUrl());
		Log4Android.l(this, (body.equalsIgnoreCase("null")) ? "Model==> " + new GsonBuilder().setPrettyPrinting().create().toJson(model)
				: "Body==> " + body);
	}

	public void StartProgress(String message)
	{
		if (dialog == null)
		{
			SetDialog(message);
			callCount = 0;
		}
		callCount++;
		if (dialog.isShowing())
		{
			dialog.setMessage(message);
		}
		else
		{
			new Handler().postDelayed(new Runnable()
			{
				@Override
				public void run()
				{
					if (callCount > 0)
					{
						dialog.show();
					}
				}
			}, (long) DIALOG_OFFSET_MILISEC);
		}
	}

	public void StopProgress()
	{
		callCount--;
		if (callCount <= 0)
		{
			v.setClickable(true);
			if (dialog != null && dialog.isShowing())
			{
				dialog.dismiss();
			}
		}
	}

	private void SetDialog(String message)
	{
		dialog = new ProgressDialog(baseActivity);
		dialog.setMessage(message);
		dialog.setCancelable(false);
	}

}

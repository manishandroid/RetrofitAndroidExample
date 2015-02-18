/**
 * Project : Retrofit
 * Author : xicom
 * Creation Date : 14-Feb-2014
 * Description : @TODO
 */
package com.xicom.retrofit.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit.client.Response;
import android.app.Activity;
import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public final class UtilitySingleton
{

	private static volatile UtilitySingleton instance = null;

	private Context context;
	private UtilitySingleton(Context context)
	{
		this.context=context;
	}

	public static UtilitySingleton getInstance(Context context)
	{
		if (instance == null)
		{
			synchronized (UtilitySingleton.class)
			{
				instance = new UtilitySingleton(context);
			}
		}
		return instance;
	}

	public void SetClickListener(int[] clickIDs)
	{
		if (context instanceof OnClickListener)
		{
			for (int id : clickIDs)
			{
				((Activity) context).findViewById(id).setOnClickListener((OnClickListener) context);
			}
		}
	}

	public void ShowToast(String msg)
	{
		if(msg!=null && !msg.trim().equalsIgnoreCase(""))
		{
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		}
	}

	public String getResponse(Response response)
	{
		System.out.println(response.getStatus()+" :: "+response.getBody());
		try
		{
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(convertStreamToString(response.getBody().in()));
			return gson.toJson(je);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}
		catch (JsonSyntaxException e)
		{
			e.printStackTrace();
			try
			{
				return convertStreamToString(response.getBody().in());
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
				return "";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public String convertStreamToString(InputStream is)
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try
		{
			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				is.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}

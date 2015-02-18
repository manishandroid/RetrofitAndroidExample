/**
 * Project : Retrofit
 * Author : xicom
 * Creation Date : 12-Feb-2014
 * Description : @TODO
 */
package com.xicom.retrofit.models;


public class ModelNew
{
	private String key;
	private String value;

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
	
	
	@Override
	public String toString()
	{
		String string="Key=>"+this.key+"\nValue=>"+this.value;
		return string;
	}
}

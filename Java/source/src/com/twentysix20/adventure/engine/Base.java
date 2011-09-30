package com.twentysix20.adventure.engine;

import com.twentysix20.adventure.engine.*;

public abstract class Base
{

	private String Name;
	private String Description;
	private String Uid;

	protected Base(String uid)
	{
		this(uid, "", "");
	}

	protected Base(String uid, String name, String desc)
	{
		Uid = uid;
		setName(name);
		setDescription(desc);
	}

	public String getName()
	{
		return Name;
	}

	public void setName(String s)
	{
		Name = s;
	}

	public String getDescription()
	{
		return Description;
	}

	public void setDescription(String s)
	{
		Description = s;
	}

	public String getUid()
	{
		return Uid;
	}

	public boolean isPlaceholder()
	{
		return (Name.equals(""));
	}

	public boolean equals(Base base)
	{
		return this.Uid.equals(base.Uid);
	}

	public boolean equals(String uid)
	{
		return this.Uid.equals(uid);
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.engine.Base main");
	}
}
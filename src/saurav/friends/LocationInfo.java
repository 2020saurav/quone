package saurav.friends;

public class LocationInfo {
	
	private String name;
	private String address;
	private String time;
	
	public LocationInfo(String name, String address, String time)
	{
		this.name = name;
		this.address = address;
		this.time = time;
		
	}
	public String getName()
	{
		return name;
	}
	public String getAddress()
	{
		return address;
	}
	public String getTime()
	{
		return time;
	}

}

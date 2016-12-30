package net.ctrlor.test.mapdemo;

import java.io.Serializable;

public class CustomMapData implements Serializable{

	private String strMapVendor = "";
	private String strCurrentGeoLocation = "";
	private String strTargetGeoLocation = "";
	private Double dLatitude = 0.0d;
	private Double dLongitude = 0.0d;

	public CustomMapData() {

	}

	public void setMapVendor(String mapVendor) {

		this.strMapVendor = mapVendor;
	}

	public void setCurrentGeoLocation(String location) {

		this.strCurrentGeoLocation = location;
	}

	public void setTargetGeoLocation(String location) {

		this.strTargetGeoLocation = location;
	}
	public void setLatitude(Double latitude) {

		this.dLatitude = latitude;
	}

	public void setLongitude(Double longitude) {

		this.dLongitude = longitude;
	}
	public String getMapVendor() {

		return this.strMapVendor;
	}

	public String getCurrentGeoLocation() {

		return this.strCurrentGeoLocation;
	}

	public String getTargetGeoLocation() {

		return this.strTargetGeoLocation;
	}
	public Double getLatitude() {

		return this.dLatitude;
	}

	public Double getLongitude() {

		return this.dLongitude;
	}
}

/**
 * Copyright (c) 2010, Todd Ginsberg
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *    * Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *    * Neither the name of Todd Ginsberg, or Gowalla nor the
 *      names of any contributors may be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 *  Also, please use this for Good and not Evil.  
 */
package com.ginsberg.gowalla.dto;


/**
 * Data Transfer Object for a Spot that returns Basic information as well
 * as location.
 * 
 * @author Todd Ginsberg
 * 
 */
public class LocatedSpot extends Spot implements Checkinable, Locatable {

	private static final long serialVersionUID = 1800372485821163566L;
	protected String lng;
	protected String lat;
	protected boolean strict_radius;
	protected int radius_meters;
	protected GeoPoint geoLocation;

	/**
	 * Constructor!
	 */
	public LocatedSpot() {
		super();
	}

	/**
	 * Can somebody on the spot given check in at this spot? This currently only
	 * takes spot radius into account. Once more information about elastic GPS
	 * is available, or the part of the API that describes when this spot is
	 * visitable becomes official, this will change to encompass that.
	 * 
	 * @param fromLocation
	 *            The spot from which the checkin is being attempted.
	 * @return true if allowed, false otherwise.
	 */
	public boolean canCheckIn(final GeoPoint fromLocation) {
		return radius_meters >= fromLocation.getDistanceMeters(getGeoLocation());
	}

	@Override
	public GeoPoint getGeoLocation() {
		if(geoLocation == null) {
			geoLocation = new GeoPoint(lat, lng);
		}
		return geoLocation;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public boolean isStrictRadius() {
		return strict_radius;
	}

	public void setStrictRadius(boolean strictRadius) {
		this.strict_radius = strictRadius;
	}

	public int getRadiusMeters() {
		return radius_meters;
	}

	public void setRadiusMeters(int radiusMeters) {
		this.radius_meters = radiusMeters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocatedSpot other = (LocatedSpot) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("LocatedSpot[name=%s, location=%s]", name, getGeoLocation());
	}

}

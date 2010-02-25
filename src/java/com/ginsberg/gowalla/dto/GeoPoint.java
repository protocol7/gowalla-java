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

import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Immutable class representing a Geographical Point.  This object cannot be created in an invalid 
 * state, so if you have one, you can be assured that it is both correct and threadsafe.
 * 
 * @author Todd Ginsberg
 *
 */
public class GeoPoint implements Serializable, Locatable {

	private static final long serialVersionUID = -8291913597851561467L;
	public static final GeoPoint DEFAULT_POINT = new GeoPoint(BigDecimal.ZERO, BigDecimal.ZERO);
	

	private BigDecimal longitude;
	private BigDecimal latitude;
		
	/**
	 * Create a new GeoPoint object from BigDecimals.
	 * 
	 * @param latitude The latitude of the point.
	 * @param longitude The longitude of the point.
	 * @throws IllegalArgumentException when longitude or latitude are null, or when point is not valid.
	 */
	public GeoPoint(final BigDecimal latitude, final BigDecimal longitude) {
		this.longitude = longitude;
		this.latitude = latitude;
		if(!isValid()) {
			throw new IllegalArgumentException("Coordinates provided are not a valid Geographical Point.");
		}
	}
	
	/**
	 * Create a new GeoPoint object from Strings.
	 * 
	 * @param latitude The latitude of the point.
	 * @param longitude The longitude of the point.
	 * @throws IllegalArgumentException when longitude or latitude are null, or when point is not valid.
	 */
	public GeoPoint(final String latitude, final String longitude) {
		this(new BigDecimal(latitude), new BigDecimal(longitude));
	}

	/**
	 * Create a new GeoPoint object from doubles.
	 * 
	 * @param latitude The latitude of the point.
	 * @param longitude The longitude of the point.
	 * @throws IllegalArgumentException when point is not valid.
	 */
	public GeoPoint(final double latitude, final double longitude) {
		this(new BigDecimal(latitude), new BigDecimal(longitude));
	}

	
	/**
	 * Checks that the internal representation is a valid Geographical Point.
	 * 
	 * @return boolean true if valid for Earth, false otherwise.
	 */
	private boolean isValid() {
		return Math.abs(longitude.doubleValue()) <= 180 &&
		       Math.abs(latitude.doubleValue()) <= 90;
	}

	/**
	 * @return The longitude of the point.
	 */
	public BigDecimal getLongitude() {
		return longitude;
	}

	/**
	 * @return The latitude of the point.
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}
	
	/**
	 * Calculate the distance in meters between this point and the one given.
	 * 
	 * TODO: Evaluate if this works over short distances well.  I read
	 * that it might not, but it's what a lot of geo packages seem to use.
	 * 
	 * @param other The other point to calculate the distance between.
	 * @return An int, representing the distance between the points in meters.
	 */
	public long getDistanceMeters(final GeoPoint other) {
		double l1 = toRadians(this.latitude.doubleValue());
		double l2 = toRadians(other.latitude.doubleValue());
		double g1 = toRadians(this.longitude.doubleValue());
		double g2 = toRadians(other.longitude.doubleValue());
		
		double dist = acos(sin(l1) * sin(l2) + cos(l1) * cos(l2) * cos(g1 - g2));
		if(dist < 0) {
			dist = dist + Math.PI;
		}
		return Math.round(dist * 6378100);
	}
	
	@Override
	public GeoPoint getGeoLocation() {
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
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
		GeoPoint other = (GeoPoint) obj;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("GeoPoint [latitude=%1$3.10f, longitude=%2$3.10f]", latitude, longitude);
	}

}

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

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ginsberg.gowalla.dto.compare.DistanceComparator;

/**
 * Full information about a Trip, with as much Spot and User information
 * returned by Gowalla.
 * 
 * @author Todd Ginsberg
 */
public class Trip implements Serializable, Id<Trip> {

	private static final long serialVersionUID = -335467868378920628L;
	private String url;
	private int id;
	private int completed_users_count;
	private boolean  _completed;
	private User creator;
	private String name;
	private Date created_at;
	private String description;
	private String image_url;
	private List<LocatedSpot> spots;
	
	public Trip() {
		super();
	}
	
	/**
	 * Get the closest spot on this trip to the location given, in meters.
	 * If this trip has no spots, it returns null.
	 * 
	 * @param location A Locatable
	 * @return The spot closst to the location given.
	 */
	public LocatedSpot getClosestSpot(final Locatable location) {
		if(spots == null || spots.size() == 0) {
			return null;
		}
		// Copy this so we can reorder them without altering the order
		// in the copy the trip object keeps.
		final List<LocatedSpot> spotClone = new LinkedList<LocatedSpot>(spots);
		Collections.sort(spotClone, new DistanceComparator(location.getGeoLocation()));
		return spotClone.get(0);
	}
	
	/**
	 * Get how far away the closest spot on this trip is to the given 
	 * point.  A distance of zero is returned when the trip has no spots;
	 * 
	 * @param location A Locatable.
	 * @return Distance, in meters.
	 */
	public long getDistanceMetersToClosestSpot(final Locatable location) {
		final LocatedSpot closest = getClosestSpot(location);
		return closest == null ? 0 : location.getGeoLocation().getDistanceMeters(closest.getGeoLocation());
	}
	
	/**
	 * Returns true when this trip is used in the rewarding of a pin only.
	 */
	public boolean isPinOnly() {
		return spots == null || spots.size() == 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCompletedUsersCount() {
		return completed_users_count;
	}

	public void setCompletedUsersCount(int completedUsersCount) {
		this.completed_users_count = completedUsersCount;
	}

	/**
	 * Returns true if and only if the user who authenticated the
	 * request to Gowalla has completed this trip.
	 */
	public boolean isCompleted() {
		return _completed;
	}

	public void setCompleted(boolean completed) {
		this._completed = completed;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public void setCreated_at(Date createdAt) {
		this.created_at = createdAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return image_url;
	}

	public void setImageUrl(String imageUrl) {
		this.image_url = imageUrl;
	}

	public List<LocatedSpot> getSpots() {
		return spots;
	}

	public void setSpots(List<LocatedSpot> spots) {
		this.spots = spots;
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
		Trip other = (Trip) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Trip[id=%d, name=%s, creator=%s, spots=%s", id, name, creator, spots);
	}
	
}

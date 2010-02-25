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

import java.util.Date;
import java.util.List;

/**
 * DTO for full Spot data, as returned from Gowalla.
 *  
 * @author Todd Ginsberg
 */
public class FullSpot extends SimpleSpot implements Locatable, Checkinable {

	private static final long serialVersionUID = 8728173403168995009L;
	private List<Category> spot_categories;
	private String description;
	private String twitter_username;
	private String[] websites;
	private Date created_at;
	private User creator;
	private List<SpotVisitor> top_10;
	private List<User> founders;
	private boolean merged;
	private int max_items_count;
	private GeoPoint geoLocation;
	private Address address;

	public FullSpot() {
		super();
	}

	@Override
	public GeoPoint getGeoLocation() {
		if (geoLocation == null) {
			geoLocation = new GeoPoint(lat, lng);
		}
		return geoLocation;
	}

	/**
	 * Was this spot merged into another? Meaning: even though you asked for one
	 * spot, are you really getting another?
	 * 
	 * @return boolean true if the spot was merged, false if not.
	 */
	public boolean isMerged() {
		return merged;
	}

	public void setMerged(boolean merged) {
		this.merged = merged;
	}

	public List<Category> getCategories() {
		return spot_categories;
	}

	public void setCategories(List<Category> categories) {
		this.spot_categories = categories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTwitterUsername() {
		return twitter_username;
	}

	public void setTwitterUsername(String twitter_username) {
		this.twitter_username = twitter_username;
	}

	public String[] getWebsites() {
		return websites;
	}

	public void setWebsites(String[] websites) {
		this.websites = websites;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<SpotVisitor> getTop10() {
		return top_10;
	}

	public void setTop10(List<SpotVisitor> top10) {
		this.top_10 = top10;
	}

	public List<User> getFounders() {
		return founders;
	}

	public void setFounders(List<User> founders) {
		this.founders = founders;
	}

	public int getMaxItemsCount() {
		return max_items_count;
	}

	public void setMaxItemsCount(int maxItemsCount) {
		this.max_items_count = maxItemsCount;
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
		FullSpot other = (FullSpot) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("FullSpot[id=%d, name=%s, location=%s, categories=%s, createdAt=%s, by=%s, address=%s]",
						id, name, getGeoLocation(), spot_categories,
						created_at, creator, address);
	}

}

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
 * DTO for a Spot in which basic info, location info, and checkin counts are returned.
 * 
 * @author Todd Ginsberg
 */
public class SimpleSpot extends LocatedSpot {

	private static final long serialVersionUID = 5821917739644550780L;
	protected int items_count;
	protected int users_count;
	protected int checkins_count;
	protected int trending_level;
	protected Address address;
	
	/**
	 * 
	 */
	public SimpleSpot() {
		super();
	}

	public int getItemsCount() {
		return items_count;
	}

	public void setItemsCount(int itemsCount) {
		this.items_count = itemsCount;
	}

	public int getUsersCount() {
		return users_count;
	}

	public void setUsersCount(int usersCount) {
		this.users_count = usersCount;
	}

	public int getCheckinsCount() {
		return checkins_count;
	}

	public void setCheckinsCount(int checkinsCount) {
		this.checkins_count = checkinsCount;
	}

	public int getTrendingLevel() {
		return trending_level;
	}

	public void setTrendingLevel(int trendingLevel) {
		this.trending_level = trendingLevel;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
		SimpleSpot other = (SimpleSpot) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

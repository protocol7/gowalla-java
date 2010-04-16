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

/**
 * Representation of an Pin returned by the Gowalla system. 
 * 
 * @author Ryan Crutchfield - rc6750@gmail.com
 */
public class Pin implements Serializable, Id<Pin> {

	private static final long serialVersionUID = -3009911872959637613L;
	private int issue_number;
	private String name;
	private String url;
	private String image_url;
	private int id;
	private Trip trip;

	public Pin() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIssueNumber() {
		return issue_number;
	}

	public void setIssueNumber(int issueNumber) {
		this.issue_number = issueNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return image_url;
	}

	public void setImage_url(String imageUrl) {
		this.image_url = imageUrl;
	}
	
	public Trip getTrip() {
		return trip;
	}

	public void getTrip(Trip trip) {
		this.trip = trip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((image_url == null) ? 0 : image_url.hashCode());
		result = prime * result + issue_number;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((trip == null) ? 0 : trip.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Pin other = (Pin) obj;
		if (id != other.id)
			return false;
		if (image_url == null) {
			if (other.image_url != null)
				return false;
		} else if (!image_url.equals(other.image_url))
			return false;
		if (issue_number != other.issue_number)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (trip == null) {
			if (other.trip != null)
				return false;
		} else if (!trip.equals(other.trip))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Pin[%s, issue=%d, id=%d, trip=%s, image_url=%s, url=%s]", name, issue_number, id, getTrip(), image_url, url);
	}

}

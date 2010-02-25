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


/**
 * DTO representing a spot visited by a user, as it would appear in their Passport.
 * 
 * @author Todd Ginsberg
 */
public class Stamp extends Spot {

	private static final long serialVersionUID = 5802714786311705430L;
	protected Address address;
	protected int checkins_count;
    protected Date first_checkin_at;
    protected Date last_checkin_at;
    
	public Stamp() {
		super();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getCheckinsCount() {
		return checkins_count;
	}

	public void setCheckinsCount(int checkinsCount) {
		this.checkins_count = checkinsCount;
	}

	public Date getFirstCheckinAt() {
		return first_checkin_at;
	}

	public void setFirstCheckinAt(Date firstCheckinAt) {
		this.first_checkin_at = firstCheckinAt;
	}

	public Date getLastCheckinAt() {
		return last_checkin_at;
	}

	public void setLastCheckinAt(Date lastCheckinAt) {
		this.last_checkin_at = lastCheckinAt;
	}

	@Override
	public String toString() {
		return String.format("Stamp[id=%d, name=%s, address=%s, firstCheckin=%s, lastCheckin=%s, checkins=%d]", 
				id, name, address, first_checkin_at, last_checkin_at, checkins_count);
	}
    
	
}

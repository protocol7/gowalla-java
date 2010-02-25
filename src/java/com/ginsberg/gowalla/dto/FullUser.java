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

import java.util.List;

/**
 * Full information about a Gowalla User.
 * 
 * @author Todd Ginsberg
 */
public class FullUser extends User {

	private static final long serialVersionUID = 8113746552452084692L;
	private String bio;
	private String hometown;
	private int friends_count;
	private boolean _is_friend;
	private int items_count;
	private int pins_count;
	private int stamps_count;
	private String twitter_username;
	private String facebook_id;
	private String website;
	private List<UserEvent> last_checkins;
	
	/**
	 * 
	 */
	public FullUser() {
		super();
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public int getFriends_count() {
		return friends_count;
	}

	public void setFriendsCount(int friendsCount) {
		this.friends_count = friendsCount;
	}

	public boolean isFriend() {
		return _is_friend;
	}

	public void setFriend(boolean isFiend) {
		this._is_friend = isFiend;
	}

	public int getItemsCount() {
		return items_count;
	}

	public void setItemsCount(int itemsCount) {
		this.items_count = itemsCount;
	}

	public int getPinsCount() {
		return pins_count;
	}

	public void setPinsCount(int pinsCount) {
		this.pins_count = pinsCount;
	}

	public int getStampsCount() {
		return stamps_count;
	}

	public void setStampsCount(int stampsCount) {
		this.stamps_count = stampsCount;
	}

	public String getImageUrl() {
		return image_url;
	}

	public void setImageUrl(String imageUrl) {
		this.image_url = imageUrl;
	}

	public String getTwitterUsername() {
		return twitter_username;
	}

	public void setTwitterUsername(String twitterUsername) {
		this.twitter_username = twitterUsername;
	}

	public String getFacebookId() {
		return facebook_id;
	}

	public void setFacebookId(String facebookId) {
		this.facebook_id = facebookId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<UserEvent> getLastCheckins() {
		return last_checkins;
	}

	public void setLastCheckins(List<UserEvent> lastCheckins) {
		this.last_checkins = lastCheckins;
	}
	
	/**
	 * Get the most recent checkin from a potential array of checkins
	 * Gowalla returns.  
	 * 
	 * Note: This might have to change, I've not seen the API return more
	 * than one of these, so I'm guessing they'll be in order.
	 * 
	 * @return A UserEvent object
	 */
	public UserEvent getLastCheckin() {
		return last_checkins == null || last_checkins.size() == 0 ? null : last_checkins.get(0);
	}

	@Override
	public String toString() {
		return String.format("User[name=%s %s, lastCheckin=%s]", first_name, last_name, getLastCheckin());
	}

}

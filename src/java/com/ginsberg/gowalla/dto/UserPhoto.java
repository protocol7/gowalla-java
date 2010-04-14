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
import java.util.Map;

import com.ginsberg.gowalla.util.PhotoParser;

/**
 * DTO representing an photos posted by a user.
 *  
 * @author Ryan Crutchfield - rc6750@gmail.com
 */
public class UserPhoto extends UserEvent {

	private static final long serialVersionUID = 2780217905102991779L;
	private Map<String,String> photo_urls;
	private List<Photo> photos;
	
	/**
	 * Get the list of Photos in descending order of perceived quality.
	 */
	public List<Photo> getPhotos() {
		// Lazy Parse.
		if(photos == null) {
			photos = PhotoParser.parsePhotos(photo_urls);
		}
		return photos;
	}
	
	@Override
	public String toString() {
		return String.format("UserPhoto[photo_urls=%s]", getPhotos());
	}
}
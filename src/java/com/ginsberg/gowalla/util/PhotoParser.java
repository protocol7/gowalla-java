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
package com.ginsberg.gowalla.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ginsberg.gowalla.dto.Photo;
import com.ginsberg.gowalla.dto.Photo.PhotoType;

/**
 * Abstracts the parsing of the Photo_Url string format out of the Photo class.
 * 
 * @author Todd Ginsberg
 */
public class PhotoParser {
	
	public static List<Photo> parsePhotos(Map<String,String> photo_urls) {
		List<Photo> photos = new LinkedList<Photo>();
		for(String key : photo_urls.keySet()) {
			photos.add(PhotoParser.parseUrl(key, photo_urls.get(key)));
		}
		Collections.sort(photos);
		Collections.reverse(photos);
		return photos;
	}
	

	public static Photo parseUrl(final String key, final String url) {
		final Photo photo = new Photo();
		photo.setUrl(url);
		if(key == null) {
			photo.setPhotoType(PhotoType.UNKNOWN);
		} else if(key.startsWith("square_")) {
			photo.setPhotoType(PhotoType.SQUARE);
			parseSquare(photo, key);
		} else if(key.startsWith("high_res_")) {
			photo.setPhotoType(PhotoType.HIGH_RES);
			parseDimensions(photo, key);
		} else if(key.startsWith("low_res_")) {
			photo.setPhotoType(PhotoType.LOW_RES);
			parseDimensions(photo, key);
		} else {
			photo.setPhotoType(PhotoType.UNKNOWN);
		}
		
		return photo;
	}

	/**
	 * @param photo
	 * @param key
	 */
	private static void parseDimensions(Photo photo, String key) {
		String[] parts = key.split("_");
		parts = parts[parts.length-1].split("x");
		photo.setWidth(Integer.parseInt(parts[0]));
		photo.setHeight(Integer.parseInt(parts[1]));
	}

	/**
	 * @param photo
	 * @param key
	 */
	private static void parseSquare(final Photo photo, final String key) {
		String end = key.substring(key.lastIndexOf("_")+1);
		photo.setHeight(Integer.parseInt(end));
		photo.setWidth(photo.getHeight());
	}
	
}

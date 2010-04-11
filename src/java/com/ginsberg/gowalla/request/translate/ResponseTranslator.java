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
package com.ginsberg.gowalla.request.translate;

import java.util.List;

import com.ginsberg.gowalla.dto.FullCategory;
import com.ginsberg.gowalla.dto.Item;
import com.ginsberg.gowalla.dto.ItemEvent;
import com.ginsberg.gowalla.dto.SimpleSpot;
import com.ginsberg.gowalla.dto.FullSpot;
import com.ginsberg.gowalla.dto.SpotEvent;
import com.ginsberg.gowalla.dto.SpotPhoto;
import com.ginsberg.gowalla.dto.Stamp;
import com.ginsberg.gowalla.dto.Trip;
import com.ginsberg.gowalla.dto.TripSummary;
import com.ginsberg.gowalla.dto.FullUser;
import com.ginsberg.gowalla.dto.User;
import com.ginsberg.gowalla.dto.VisitedSpot;
import com.ginsberg.gowalla.exception.GowallaResponseException;

/**
 * Translates JSON responses from Gowalla into Data Transfer Objects.
 * Implement your own version of this to change out default (GSON)
 * translation into something else.
 * 
 * @author Todd Ginsberg
 */
public interface ResponseTranslator {

	public List<FullCategory> translateCategories(final String response) throws GowallaResponseException ;
	
	public FullCategory translateCategory(final String response) throws GowallaResponseException ;

	/**
	 * String->Spot. 
	 * 
	 * The ID is required in order to determine if the spot requested was meged or not.
	 */
	public FullSpot translateSpot(final String response, final int id);

	public List<Item> translateItems(final String response);

	public Item translateItem(final String response);

	public List<SimpleSpot> translateSimpleSpots(final String response);

	public Trip translateTrip(final String response);

	public FullUser translateUser(final String response);

	public List<VisitedSpot> translateVisitedSpots(final String response);

	public List<SpotEvent> translateSpotEvents(final String response);

	public List<TripSummary> translateTripSummaries(final String response);

	public List<Stamp> translateStamps(final String response);
	
	public List<SpotPhoto> translateSpotPhotos(final String response);
	
	public List<ItemEvent> translateItemEvents(final String response);
	
	public List<User> translateUsers(final String response);
}

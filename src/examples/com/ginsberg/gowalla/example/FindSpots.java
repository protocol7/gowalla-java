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
package com.ginsberg.gowalla.example;

import java.util.LinkedList;
import java.util.List;

import com.ginsberg.gowalla.Gowalla;
import com.ginsberg.gowalla.PagingSupport;
import com.ginsberg.gowalla.SpotCriteria;
import com.ginsberg.gowalla.auth.BasicAuthentication;
import com.ginsberg.gowalla.dto.FullSpot;
import com.ginsberg.gowalla.dto.Locatable;
import com.ginsberg.gowalla.dto.SimpleSpot;
import com.ginsberg.gowalla.exception.GowallaException;

/**
 * An example program that finds the closest 100 spots to
 * some other spot, paging if required, limited to 1000 meters.
 * 
 * @author Todd Ginsberg
 */
public class FindSpots {

	public static void printSpotData(final FullSpot spot, final Locatable from) {
		System.out.format("Spot[distance=%d, id=%d, name=%s, where=%s, users=%d, checkins=%d, categories=%s]%n", 
				spot.getGeoLocation().getDistanceMeters(from.getGeoLocation()), spot.getId(), spot.getName(), spot.getGeoLocation(), 
				spot.getUsersCount(), spot.getCheckinsCount(), spot.getCategories());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final Gowalla gowalla = new Gowalla("Example-GetUserInfo", "YOUR API KEY HERE", new BasicAuthentication("YOUR USERNAME", "YOUR PASSWORD"));
			// Known point:
			final FullSpot spot = gowalla.getSpot(11888); // Sno-Beach!
			
			System.out.format("Finding spots near %s%n", spot);
			final List<SimpleSpot> spotData = gowalla.findSpots(
					new SpotCriteria.Builder(spot, 1000)
					.numberOfSpots(100)
					.pagingSupport(PagingSupport.PAGING_ALLOWED)
					.build());
			System.out.format("Found %d spot(s)%n", spotData.size());
			
			// Since Gowalla's JSON API only returns a bit of information on each
			// spot, and we want everything, we have to go query for each one.
			final List<FullSpot> spots = new LinkedList<FullSpot>();
			for(SimpleSpot s : spotData) {
				System.out.format("Requesting spot: %d%n", s.getId());
				spots.add(gowalla.getSpot(s));
			}
			
			for(FullSpot s : spots) {
				printSpotData(s, spot);
			}
			
			
		} catch (GowallaException e) {
			e.printStackTrace();
		}

	}

}

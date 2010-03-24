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
 * Basic data returned when referring to users.
 * 
 * @author Ryan Crutchfield
 */
public class Photo_Urls implements Serializable{

	private static final long serialVersionUID = -1233545889422798921L;
	private String square_10;
	private String high_res_320x480;
	private String square_75;
	private String low_res_320x480;
	private String square_50;
	private String square_100;
    
	/**
	 * Constructor!
	 */
	public Photo_Urls() {
		super();
	}

	public String getSquare10() {
		return square_10;
	}

	public void setSquare10(String square_10) {
		this.square_10 = square_10;
	}
	
	public String getHighRes() {
		return high_res_320x480;
	}

	public void setHighRes(String high_res_320x480) {
		this.high_res_320x480 = high_res_320x480;
	}

	public String getSquare75() {
		return square_75;
	}

	public void setSquare75(String square_75) {
		this.square_75 = square_75;
	}
	
	public String getSquare50() {
		return square_50;
	}

	public void setSquare50(String square_50) {
		this.square_50 = square_50;
	}
	
	public String getSquare100() {
		return square_100;
	}

	public void setSquare100(String square_100) {
		this.square_100 = square_100;
	}
	
	public String getLowRes() {
		return low_res_320x480;
	}

	public void setLowRes(String low_res_320x480) {
		this.low_res_320x480 = low_res_320x480;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((high_res_320x480 == null) ? 0 : high_res_320x480.hashCode());
		result = prime * result
				+ ((low_res_320x480 == null) ? 0 : low_res_320x480.hashCode());
		result = prime * result
				+ ((square_10 == null) ? 0 : square_10.hashCode());
		result = prime * result
				+ ((square_100 == null) ? 0 : square_100.hashCode());
		result = prime * result
				+ ((square_50 == null) ? 0 : square_50.hashCode());
		result = prime * result
				+ ((square_75 == null) ? 0 : square_75.hashCode());
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
		Photo_Urls other = (Photo_Urls) obj;
		if (high_res_320x480 == null) {
			if (other.high_res_320x480 != null)
				return false;
		} else if (!high_res_320x480.equals(other.high_res_320x480))
			return false;
		if (low_res_320x480 == null) {
			if (other.low_res_320x480 != null)
				return false;
		} else if (!low_res_320x480.equals(other.low_res_320x480))
			return false;
		if (square_10 == null) {
			if (other.square_10 != null)
				return false;
		} else if (!square_10.equals(other.square_10))
			return false;
		if (square_100 == null) {
			if (other.square_100 != null)
				return false;
		} else if (!square_100.equals(other.square_100))
			return false;
		if (square_50 == null) {
			if (other.square_50 != null)
				return false;
		} else if (!square_50.equals(other.square_50))
			return false;
		if (square_75 == null) {
			if (other.square_75 != null)
				return false;
		} else if (!square_75.equals(other.square_75))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return String.format("Photo_Urls[high_res_320x480=%s, low_res_320x480=%s, square_10=%s, square_100=%s, square_50=%s, square_75=%s]", 
				high_res_320x480, low_res_320x480, square_10, square_100, square_50, square_75);
	}

}
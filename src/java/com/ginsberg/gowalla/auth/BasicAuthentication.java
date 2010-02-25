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
package com.ginsberg.gowalla.auth;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.singletonList;
import java.util.List;

import com.ginsberg.gowalla.request.PlainRequestHeader;
import com.ginsberg.gowalla.request.RequestHeader;
import com.ginsberg.gowalla.util.Base64;

/**
 * Implementaion of the Authentication interface that does Basic HTTP Authentication.
 * Thanks to Robert Harder (rob@iharder.net) for the use of his Base64 library.
 * 
 * @author Todd Ginsberg
 */
public class BasicAuthentication implements Authentication {

	private List<RequestHeader> headers;
	
	/**
	 * Constructor providing all elements needed.
	 */
	public BasicAuthentication(final String username, final String password) {
		super();
		// Cache the header, we don't need to regen it for every request.
		final String auth = username + ":" + password;
		final RequestHeader header = new PlainRequestHeader("Authorization", Base64.encodeBytes(auth.getBytes()).trim());
		headers = unmodifiableList(singletonList(header));
	}

	/**
	 * @see com.ginsberg.gowalla.auth.Authentication#getHeaders()
	 */
	@Override
	public List<RequestHeader> getHeaders() {
		return headers;
	}

}

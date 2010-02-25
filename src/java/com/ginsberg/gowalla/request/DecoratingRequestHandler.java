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
package com.ginsberg.gowalla.request;

import java.util.Collection;
import java.util.List;

import com.ginsberg.gowalla.exception.GowallaRequestException;

/**
 * Implementation of RequestHandler that wraps another instance of
 * RequestHandler.  This can be used to debug, gather performance data,
 * alter results in the case of an API change, etc.  
 * 
 * @author Todd Ginsberg
 *
 */
public abstract class DecoratingRequestHandler implements RequestHandler {

	private RequestHandler innerHandler = null;
	
	/**
	 * Construct the wrapper  
	 */
	public DecoratingRequestHandler(final RequestHandler innerHandler) {
		this.innerHandler = innerHandler;
	}

	/** 
	 * 	@see com.ginsberg.gowalla.request.RequestHandler#handleRequest(java.lang.String, java.util.List)
	 */
	public String handleRequest(final String resource, final List<RequestHeader> instanceHeaders) throws GowallaRequestException {
		String response = null;
		try {
			final String newResource = preRequest(resource);
			response = innerHandler.handleRequest(newResource == null ? resource : newResource, instanceHeaders);
			final String newResponse = postRequestSuccess(response);
			return newResponse == null ? response : newResponse;
		} catch(GowallaRequestException e) {
			return postRequestFail(e);
		}
		 
	}

	/**
	 * 
	 * @see com.ginsberg.gowalla.request.RequestHandler#setRequestHeaders(java.util.Collection)
	 */
	public void setRequestHeaders(Collection<RequestHeader> headers) {
		innerHandler.setRequestHeaders(headers);
	}

	/**
	 * @param host
	 * @see com.ginsberg.gowalla.request.RequestHandler#setRequestHost(java.lang.String)
	 */
	public void setRequestHost(String host) {
		innerHandler.setRequestHost(host);
	}

	@Override
	public void setRequestPort(int port) {
		innerHandler.setRequestPort(port);
	}

	/**
	 * Called immediately before the request is handed off to the 
	 * decorated handler.  This call gives the implementation a chance
	 * to replace the request being made.
	 * 
	 * @param resource The resource being requested.
	 * @return A new resource to request, or null to keep the original.
	 */
	public abstract String preRequest(final String resource);
	
	
	/**
	 * Called immediately after the reqeust has been made and only
	 * in the case where the call has succeeded.  The body of the response
	 * is handed to the implementation, which may replace it.
	 * 
	 * @param response The response received from the decorated handler.
	 * @return A new response, or null to keep the original.
	 */
	public abstract String postRequestSuccess(final String response);
	
	/**
	 * Called immediately after the request has been made, and only when the
	 * request has resulted in a GowallaRequestException.  
	 * 
	 * @param error The original exception thrown by the decorated request.
	 * @return A new response, to replace the exception.
	 * @throws GowallaRequestException An exception to rethrow, may be the original.
	 */
	public abstract String postRequestFail(final GowallaRequestException error) throws GowallaRequestException;
}

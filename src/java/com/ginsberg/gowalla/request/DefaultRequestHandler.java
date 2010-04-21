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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.ginsberg.gowalla.exception.GowallaRequestException;
import com.ginsberg.gowalla.exception.NotAuthorizedException;
import com.ginsberg.gowalla.exception.RequestNotAcceptableException;
import com.ginsberg.gowalla.exception.ServiceUnavailableException;

/**
 * The main implementation of RequestHandler.  This implementation uses
 * the basic Java UrlConnection to make its requests.  It doesn't reuse
 * connections (HTTP/1.1).  This is a very basic implementation used to
 * provide functionality out of the box and provide the bare minimum
 * required to make requests.  
 * 
 * A more robust implementation could probably be written with Apache
 * Commons HTTP Client, should you so desire one.
 * 
 * @author Todd Ginsberg
 */
public class DefaultRequestHandler implements RequestHandler {
	
	private String host = "api.gowalla.com";
	private int port = 80;
	private Collection<RequestHeader> headers = Collections.emptyList();

	public DefaultRequestHandler() {
		super();
	}

	@Override
	public void setRequestHost(final String host) {
		this.host = host;
	}

	@Override
	public void setRequestPort(int port) {
		this.port = port;
	}

	/**
	 * @see com.ginsberg.gowalla.request.RequestHandler#setRequestHeaders(java.util.Collection)
	 */
	@Override
	public void setRequestHeaders(final Collection<RequestHeader> headers) {
		this.headers = new LinkedList<RequestHeader>(headers);
	}
	
	/**
	 * @see com.ginsberg.gowalla.request.RequestHandler#handleRequest(java.lang.String, java.util.List)
	 */
	@Override
	public String handleRequest(final String resource, final List<RequestHeader> instanceHeaders) throws GowallaRequestException {
		BufferedReader in = null;
		
		URL url = null;
		HttpURLConnection conn = null;
		final StringBuilder bodyResponse = new StringBuilder();
		try {
	        url = new URL(String.format("http://%s:%d%s", host, port, resource));
	        conn = (HttpURLConnection)url.openConnection();
	        for(RequestHeader header : headers) {
	        	if(header != null) {
	        		conn.addRequestProperty(header.getKey(), header.getValue());
	        	}
	        }
	        for(RequestHeader header : instanceHeaders) {
	        	if(header != null) {
	        		conn.addRequestProperty(header.getKey(), header.getValue());
	        	}
	        }
	        conn.setDoInput(true);
	        
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        String inputLine;

	        while ((inputLine = in.readLine()) != null)  {
	            bodyResponse.append(inputLine);
	        }
		} catch(MalformedURLException e) {
			throw new GowallaRequestException("Malformed URL: " + url);
		} catch(IOException e) { 
			try {
				switch(conn.getResponseCode()) {
					case HttpURLConnection.HTTP_NOT_ACCEPTABLE: {
						throw new RequestNotAcceptableException("Not Acceptable: " + url);
					}
					case HttpURLConnection.HTTP_BAD_REQUEST: {
						throw new GowallaRequestException("Bad request: " + url);
					}
					case HttpURLConnection.HTTP_UNAUTHORIZED: {
						throw new NotAuthorizedException("Invalid or missing credentials - check authentication for: " + url);
					}
					case HttpURLConnection.HTTP_UNAVAILABLE: {
						throw new ServiceUnavailableException("Service Unavailable (might be temorary): " + url);
					}
					default: {
						throw new GowallaRequestException("IOException: " + e.getMessage());
					}
				}
			} catch(IOException thisIsWhyWeCantHaveNiceThings) {
				throw new GowallaRequestException("IOException: " + e.getMessage());
			}
		} finally {
			if(in != null) {
				try { in.close(); } catch(Throwable t) {}
			}
		}
		return bodyResponse.toString();
	}
	
}

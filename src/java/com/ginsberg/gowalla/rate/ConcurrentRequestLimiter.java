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
package com.ginsberg.gowalla.rate;

import static java.lang.Math.max;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * A version of RateLimiter which limits the total number of concurrent
 * requests being made against the Gowalla API at once.  This object 
 * enforces an optional wait time.
 * 
 * @author Todd Ginsberg
 *
 */
public class ConcurrentRequestLimiter implements RateLimiter {
	
	private Semaphore semaphore = null;
	private long timeout = 0;
	private TimeUnit timeUnit = null;
	
	/**
	 * Constructor specifying how many concurrent requests
	 * are allowed to proceed at once.  This method will
	 * round up to 1 for inputs under 1.
	 */
	public ConcurrentRequestLimiter(final int maxConcurrentRequests) {
		super();
		this.semaphore = new Semaphore(max(1, maxConcurrentRequests), false);
	}

	/**
	 * Construct a version of this class that waits for a limited time.
	 * 
	 * @param maxConcurrentRequests Maximum number of requests that can happen at once (min = 1, enforced internally).
	 * @param timeout Timeout in number of units.
	 * @param timeUnit Unit type for timeout.
	 */
	public ConcurrentRequestLimiter(final int maxConcurrentRequests, final long timeout, final TimeUnit timeUnit) {
		this(maxConcurrentRequests);
		this.timeout = timeout;
		this.timeUnit = timeUnit;
	}

	/**
	 * @see com.ginsberg.gowalla.rate.RateLimiter#postRequest()
	 */
	@Override
	public void postRequest() {
		semaphore.release();
	}

	/**
	 * @see com.ginsberg.gowalla.rate.RateLimiter#preRequest()
	 */
	@Override
	public boolean preRequest() {
		try {
			if(timeUnit == null) {
				semaphore.acquire();
				return true;
			} else {
				return semaphore.tryAcquire(timeout, timeUnit);
			}
		} catch (InterruptedException e) {
			return false;
		}
	}

}

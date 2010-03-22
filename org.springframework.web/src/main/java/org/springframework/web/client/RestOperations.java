/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.client;

import java.net.URI;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * Interface specifying a basic set of RESTful operations. Implemented by {@link RestTemplate}.
 * Not often used directly, but a useful option to enhance testability, as it can easily
 * be mocked or stubbed.
 *
 * @author Arjen Poutsma
 * @author Juergen Hoeller
 * @since 3.0
 * @see RestTemplate
 */
public interface RestOperations {

	// GET

	/**
	 * Retrieve a representation by doing a GET on the specified URL.
	 * The response (if any) is converted and returned.
	 * <p>URI Template variables are expanded using the given URI variables, if any.
	 * @param url the URL
	 * @param responseType the type of the return value
	 * @param uriVariables the variables to expand the template
	 * @return the converted object
	 */
	<T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException;

	/**
	 * Retrieve a representation by doing a GET on the URI template.
	 * The response (if any) is converted and returned.
	 * <p>URI Template variables are expanded using the given map.
	 * @param url the URL
	 * @param responseType the type of the return value
	 * @param uriVariables the map containing variables for the URI template
	 * @return the converted object
	 */
	<T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;

	/**
	 * Retrieve a representation by doing a GET on the URL .
	 * The response (if any) is converted and returned.
	 * @param url the URL
	 * @param responseType the type of the return value
	 * @return the converted object
	 */
	<T> T getForObject(URI url, Class<T> responseType) throws RestClientException;

	/**
	 * Retrieve an entity by doing a GET on the specified URL.
	 * The response is converted and stored in an {@link HttpEntity}.
	 * <p>URI Template variables are expanded using the given URI variables, if any.
	 * @param url the URL
	 * @param responseType the type of the return value
	 * @param uriVariables the variables to expand the template
	 * @return the entity
	 * @since 3.0.2
	 */
	<T> HttpEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException;

	/**
	 * Retrieve a representation by doing a GET on the URI template.
	 * The response is converted and stored in an {@link HttpEntity}.
	 * <p>URI Template variables are expanded using the given map.
	 * @param url the URL
	 * @param responseType the type of the return value
	 * @param uriVariables the map containing variables for the URI template
	 * @return the converted object
	 * @since 3.0.2
	 */
	<T> HttpEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;

	/**
	 * Retrieve a representation by doing a GET on the URL .
	 * The response is converted and stored in an {@link HttpEntity}.
	 * @param url the URL
	 * @param responseType the type of the return value
	 * @return the converted object
	 * @since 3.0.2
	 */
	<T> HttpEntity<T> getForEntity(URI url, Class<T> responseType) throws RestClientException;

	// HEAD

	/**
	 * Retrieve all headers of the resource specified by the URI template.
	 * <p>URI Template variables are expanded using the given URI variables, if any.
	 * @param url the URL
	 * @param uriVariables the variables to expand the template
	 * @return all HTTP headers of that resource
	 */
	HttpHeaders headForHeaders(String url, Object... uriVariables) throws RestClientException;

	/**
	 * Retrieve all headers of the resource specified by the URI template.
	 * <p>URI Template variables are expanded using the given map.
	 * @param url the URL
	 * @param uriVariables the map containing variables for the URI template
	 * @return all HTTP headers of that resource
	 */
	HttpHeaders headForHeaders(String url, Map<String, ?> uriVariables) throws RestClientException;

	/**
	 * Retrieve all headers of the resource specified by the URL.
	 * @param url the URL
	 * @return all HTTP headers of that resource
	 */
	HttpHeaders headForHeaders(URI url) throws RestClientException;

	// POST

	/**
	 * Create a new resource by POSTing the given object to the URI template, and returns the value of the
	 * <code>Location</code> header. This header typically indicates where the new resource is stored.
	 * <p>URI Template variables are expanded using the given URI variables, if any.
	 * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
	 * add additional HTTP headers to the request.
	 * @param url the URL
	 * @param request the Object to be POSTed, may be <code>null</code>
	 * @param uriVariables the variables to expand the template
	 * @return the value for the <code>Location</code> header
	 * @see HttpEntity
	 */
	URI postForLocation(String url, Object request, Object... uriVariables) throws RestClientException;

	/**
	 * Create a new resource by POSTing the given object to the URI template, and returns the value of the
	 * <code>Location</code> header. This header typically indicates where the new resource is stored.
	 * <p>URI Template variables are expanded using the given map.
	 * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
	 * add additional HTTP headers to the request.
	 * @param url the URL
	 * @param request the Object to be POSTed, may be <code>null</code>
	 * @param uriVariables the variables to expand the template
	 * @return the value for the <code>Location</code> header
	 * @see HttpEntity
	 */
	URI postForLocation(String url, Object request, Map<String, ?> uriVariables) throws RestClientException;

	/**
	 * Create a new resource by POSTing the given object to the URL, and returns the value of the
	 * <code>Location</code> header. This header typically indicates where the new resource is stored.
	 * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
	 * add additional HTTP headers to the request.
	 * @param url the URL
	 * @param request the Object to be POSTed, may be <code>null</code>
	 * @return the value for the <code>Location</code> header
	 * @see HttpEntity
	 */
	URI postForLocation(URI url, Object request) throws RestClientException;

	/**
	 * Create a new resource by POSTing the given object to the URI template,
	 * and returns the representation found in the response.
	 * <p>URI Template variables are expanded using the given URI variables, if any.
	 * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
	 * add additional HTTP headers to the request.
	 * @param url the URL
	 * @param request the Object to be POSTed, may be <code>null</code>
	 * @param responseType the type of the return value
	 * @param uriVariables the variables to expand the template
	 * @return the converted object
	 * @see HttpEntity
	 */
	<T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables)
			throws RestClientException;

	/**
	 * Create a new resource by POSTing the given object to the URI template,
	 * and returns the representation found in the response.
	 * <p>URI Template variables are expanded using the given map.
	 * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
	 * add additional HTTP headers to the request.
	 * @param url the URL
	 * @param request the Object to be POSTed, may be <code>null</code>
	 * @param responseType the type of the return value
	 * @param uriVariables the variables to expand the template
	 * @return the converted object
	 * @see HttpEntity
	 */
	<T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
			throws RestClientException;

	/**
	 * Create a new resource by POSTing the given object to the URL,
	 * and returns the representation found in the response.
	 * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
	 * add additional HTTP headers to the request.
	 * @param url the URL
	 * @param request the Object to be POSTed, may be <code>null</code>
	 * @param responseType the type of the return value
	 * @return the converted object
	 * @see HttpEntity
	 */
	<T> T postForObject(URI url, Object request, Class<T> responseType) throws RestClientException;

	/**
	 * Create a new resource by POSTing the given object to the URI template,
	 * and returns the response as {@link HttpEntity}.
	 * <p>URI Template variables are expanded using the given URI variables, if any.
	 * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
	 * add additional HTTP headers to the request.
	 * @param url the URL
	 * @param request the Object to be POSTed, may be <code>null</code>
	 * @param uriVariables the variables to expand the template
	 * @return the converted object
	 * @see HttpEntity
	 * @since 3.0.2
	 */
	<T> HttpEntity<T> postForEntity(String url, Object request, Class<T> responseType, Object... uriVariables)
			throws RestClientException;

	/**
	 * Create a new resource by POSTing the given object to the URI template,
	 * and returns the response as {@link HttpEntity}.
	 * <p>URI Template variables are expanded using the given map.
	 * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
	 * add additional HTTP headers to the request.
	 * @param url the URL
	 * @param request the Object to be POSTed, may be <code>null</code>
	 * @param uriVariables the variables to expand the template
	 * @return the converted object
	 * @see HttpEntity
	 * @since 3.0.2
	 */
	<T> HttpEntity<T> postForEntity(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
			throws RestClientException;

	/**
	 * Create a new resource by POSTing the given object to the URL,
	 * and returns the response as {@link HttpEntity}.
	 * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
	 * add additional HTTP headers to the request.
	 * @param url the URL
	 * @param request the Object to be POSTed, may be <code>null</code>
	 * @return the converted object
	 * @see HttpEntity
	 * @since 3.0.2
	 */
	<T> HttpEntity<T> postForEntity(URI url, Object request, Class<T> responseType) throws RestClientException;

	// PUT

	/**
	 * Create or update a resource by PUTting the given object to the URI.
	 * <p>URI Template variables are expanded using the given URI variables, if any.
	 * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
	 * add additional HTTP headers to the request.
	 * @param url the URL
	 * @param request the Object to be PUT, may be <code>null</code>
	 * @param uriVariables the variables to expand the template
	 * @see HttpEntity
	 */
	void put(String url, Object request, Object... uriVariables) throws RestClientException;

	/**
	 * Creates a new resource by PUTting the given object to URI template.
	 * <p>URI Template variables are expanded using the given map.
	 * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
	 * add additional HTTP headers to the request.
	 * @param url the URL
	 * @param request the Object to be PUT, may be <code>null</code>
	 * @param uriVariables the variables to expand the template
	 * @see HttpEntity
	 */
	void put(String url, Object request, Map<String, ?> uriVariables) throws RestClientException;

	/**
	 * Creates a new resource by PUTting the given object to URL.
	 * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
	 * add additional HTTP headers to the request.
	 * @param url the URL
	 * @param request the Object to be PUT, may be <code>null</code>
	 * @see HttpEntity
	 */
	void put(URI url, Object request) throws RestClientException;

	// DELETE

	/**
	 * Delete the resources at the specified URI.
	 * <p>URI Template variables are expanded using the given URI variables, if any.
	 * @param url the URL
	 * @param uriVariables the variables to expand in the template
	 */
	void delete(String url, Object... uriVariables) throws RestClientException;

	/**
	 * Delete the resources at the specified URI.
	 * <p>URI Template variables are expanded using the given map.
	 *
	 * @param url the URL
	 * @param uriVariables the variables to expand the template
	 */
	void delete(String url, Map<String, ?> uriVariables) throws RestClientException;

	/**
	 * Delete the resources at the specified URL.
	 * @param url the URL
	 */
	void delete(URI url) throws RestClientException;

	// OPTIONS

	/**
	 * Return the value of the Allow header for the given URI.
	 * <p>URI Template variables are expanded using the given URI variables, if any.
	 * @param url the URL
	 * @param uriVariables the variables to expand in the template
	 * @return the value of the allow header
	 */
	Set<HttpMethod> optionsForAllow(String url, Object... uriVariables) throws RestClientException;

	/**
	 * Return the value of the Allow header for the given URI.
	 * <p>URI Template variables are expanded using the given map.
	 * @param url the URL
	 * @param uriVariables the variables to expand in the template
	 * @return the value of the allow header
	 */
	Set<HttpMethod> optionsForAllow(String url, Map<String, ?> uriVariables) throws RestClientException;

	/**
	 * Return the value of the Allow header for the given URL.
	 * @param url the URL
	 * @return the value of the allow header
	 */
	Set<HttpMethod> optionsForAllow(URI url) throws RestClientException;

	// exchange

	/**
	 * Execute the HTTP method to the given URI template, writing the given request entity to the request, and
	 * returns the response as {@link HttpEntity}.
	 * <p>URI Template variables are expanded using the given URI variables, if any.
	 * @param url the URL
	 * @param method the HTTP method (GET, POST, etc)
	 * @param requestEntity the entity (headers and/or body) to write to the request, may be {@code null}
	 * @param responseType the type of the return value
	 * @param uriVariables the variables to expand in the template
	 * @return the response as entity
	 * @since 3.0.2
	 */
	<T> HttpEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			Class<T> responseType, Object... uriVariables) throws RestClientException;

	/**
	 * Execute the HTTP method to the given URI template, writing the given request entity to the request, and
	 * returns the response as {@link HttpEntity}.
	 * <p>URI Template variables are expanded using the given URI variables, if any.
	 * @param url the URL
	 * @param method the HTTP method (GET, POST, etc)
	 * @param requestEntity the entity (headers and/or body) to write to the request, may be {@code null}
	 * @param responseType the type of the return value
	 * @param uriVariables the variables to expand in the template
	 * @return the response as entity
	 * @since 3.0.2
	 */
	<T> HttpEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;

	/**
	 * Execute the HTTP method to the given URI template, writing the given request entity to the request, and
	 * returns the response as {@link HttpEntity}.
	 * @param url the URL
	 * @param method the HTTP method (GET, POST, etc)
	 * @param requestEntity the entity (headers and/or body) to write to the request, may be {@code null}
	 * @param responseType the type of the return value
	 * @return the response as entity
	 * @since 3.0.2
	 */
	<T> HttpEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity,
			Class<T> responseType) throws RestClientException;

	// general execution

	/**
	 * Execute the HTTP method to the given URI template, preparing the request with the
	 * {@link RequestCallback}, and reading the response with a {@link ResponseExtractor}.
	 * <p>URI Template variables are expanded using the given URI variables, if any.
	 * @param url the URL
	 * @param method the HTTP method (GET, POST, etc)
	 * @param requestCallback object that prepares the request
	 * @param responseExtractor object that extracts the return value from the response
	 * @param uriVariables the variables to expand in the template
	 * @return an arbitrary object, as returned by the {@link ResponseExtractor}
	 */
	<T> T execute(String url, HttpMethod method, RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor, Object... uriVariables) throws RestClientException;

	/**
	 * Execute the HTTP method to the given URI template, preparing the request with the
	 * {@link RequestCallback}, and reading the response with a {@link ResponseExtractor}.
	 * <p>URI Template variables are expanded using the given URI variables map.
	 * @param url the URL
	 * @param method the HTTP method (GET, POST, etc)
	 * @param requestCallback object that prepares the request
	 * @param responseExtractor object that extracts the return value from the response
	 * @param uriVariables the variables to expand in the template
	 * @return an arbitrary object, as returned by the {@link ResponseExtractor}
	 */
	<T> T execute(String url, HttpMethod method, RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor, Map<String, ?> uriVariables) throws RestClientException;

	/**
	 * Execute the HTTP method to the given URL, preparing the request with the
	 * {@link RequestCallback}, and reading the response with a {@link ResponseExtractor}.
	 * @param url the URL
	 * @param method the HTTP method (GET, POST, etc)
	 * @param requestCallback object that prepares the request
	 * @param responseExtractor object that extracts the return value from the response
	 * @return an arbitrary object, as returned by the {@link ResponseExtractor}
	 */
	<T> T execute(URI url, HttpMethod method, RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor) throws RestClientException;

}
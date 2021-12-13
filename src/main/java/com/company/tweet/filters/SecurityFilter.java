package com.company.tweet.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.company.tweet.dto.ValidateTokenRequest;
import com.company.tweet.dto.ValidateTokenResponse;

@Component
public class SecurityFilter implements Filter {

	private final static Logger LOG = LoggerFactory.getLogger(SecurityFilter.class);

	@Value("${validate.token.url}")
	String validateTokenURL;

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		LOG.info("[Initializing filter :{}]", this);
	}

	/**
	 * This filter is used authenticating the request. Once the authentication is
	 * successful, Tweet API access is provided otherwise the access to the API is
	 * denied.
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		LOG.info("[Inside Security Filter]");

		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI();
		if ("/v3/api-docs".equals(path)) {
			chain.doFilter(request, response);
			return;
		}

		String authHeader = req.getHeader("Authorization");
		String token = authHeader.replaceFirst("Bearer ", "");
		String userName = req.getHeader("X-User-Identifier");

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<ValidateTokenRequest> requestEntity = new HttpEntity<>(new ValidateTokenRequest(token, userName),
				headers);

		ResponseEntity<ValidateTokenResponse> responseEntity = restTemplate.exchange(validateTokenURL, HttpMethod.POST,
				requestEntity, ValidateTokenResponse.class);

		if (responseEntity.getBody().isValid())
			chain.doFilter(request, response);
		else
			((HttpServletResponse) response).setStatus(401);

	}

	@Override
	public void destroy() {
		LOG.warn("[Destructing filter :{}]", this);
	}

}

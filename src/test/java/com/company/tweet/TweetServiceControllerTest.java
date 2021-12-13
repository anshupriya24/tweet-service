package com.company.tweet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.company.tweet.controller.TweetController;
import com.company.tweet.data.Tweet;
import com.company.tweet.dto.Success;
import com.company.tweet.dto.TweetDetails;
import com.company.tweet.dto.TweetDetailsResponse;
import com.company.tweet.dto.TweetRequest;
import com.company.tweet.services.TweetService;
import com.company.tweet.services.TweetServiceImpl;
import com.company.tweet.util.ValidationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TweetController.class)
@AutoConfigureMockMvc
public class TweetServiceControllerTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private Filter[] springSecurityFilterChain;
	@MockBean
	private TweetService tweetService;
	@MockBean
	private ValidationUtil validationUtil;
	@InjectMocks
	private TweetController controller;
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Before
	public void setUp() throws Exception {
		controller = this.webApplicationContext.getBean(TweetController.class);

		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(MockMvcResultHandlers.print())
				.addFilters(springSecurityFilterChain).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldGetTweet() throws Exception {
		tweetService = mock(TweetServiceImpl.class);
		Tweet tweet = new Tweet("61b0e5e5fdcf0216ccea99c1",
				"Just warning you… this book has real life dialog. The characters drop the F-bomb on occasion 🙂 COWBOY TAKE ME AWAY http://ow.ly/lKwx5 (@PenelopeChilds)",
				"user5", "Wed Dec 08 17:05:41 GMT 2021");
		List<Tweet> twList = new ArrayList<>();
		twList.add(tweet);
		Map<String, String> headers = new HashMap<>();
		headers.put(null, "HTTP/1.1 200 OK");
		headers.put("Content-Type", "text/html");
		Enumeration<String> headerNames = Collections.enumeration(headers.keySet());
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getHeaderNames()).thenReturn(headerNames);
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/tweets/user5"))
					.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception ex) {
			System.out.println("Exception occured");
		}

	}

	@Test
	public void shouldGetOthersTweet() throws Exception {
		tweetService = mock(TweetServiceImpl.class);
		Tweet tweet = new Tweet("61b0e5e5fdcf0216ccea99c1",
				"Just warning you… this book has real life dialog. The characters drop the F-bomb on occasion 🙂 COWBOY TAKE ME AWAY http://ow.ly/lKwx5 (@PenelopeChilds)",
				"user1", "Wed Dec 08 17:05:41 GMT 2021");
		List<Tweet> twList = new ArrayList<>();
		List<TweetDetails> twDetailsList = new ArrayList<>();
		twList.add(tweet);
		TweetDetails tweetDetails = new TweetDetails("user1", twList);
		twDetailsList.add(tweetDetails);
		Map<String, String> headers = new HashMap<>();
		headers.put(null, "HTTP/1.1 200 OK");
		headers.put("Content-Type", "text/html");
		Enumeration<String> headerNames = Collections.enumeration(headers.keySet());
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getHeaderNames()).thenReturn(headerNames);
		when(tweetService.getOthersTweet("user5")).thenReturn(new TweetDetailsResponse(twDetailsList));
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/tweets/others/user1"))
					.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception ex) {
			System.out.println("Exception occured");
		}

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void shouldAddTweets() throws Exception {
		tweetService = mock(TweetServiceImpl.class);
		TweetRequest tweetRequest = new TweetRequest();
		tweetRequest.setAuthorId("user1");
		tweetRequest.setText(
				"All these automated DMs wasting my time :) I get 100 per day at least. Here's a 4-second loop for your enjoyment. LOL");
		when(tweetService.addTweet(tweetRequest)).thenReturn(new Success("Tweet inserted successfully"));
		Map<String, String> headers = new HashMap<>();
		headers.put(null, "HTTP/1.1 200 OK");
		headers.put("Content-Type", "text/html");
		Enumeration<String> headerNames = Collections.enumeration(headers.keySet());
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getHeaderNames()).thenReturn(headerNames);
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/tweets").contentType(APPLICATION_JSON_UTF8)
					.content(asJsonString(tweetRequest))).andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception ex) {
			System.out.println("Exception occured");
		}

	}

}

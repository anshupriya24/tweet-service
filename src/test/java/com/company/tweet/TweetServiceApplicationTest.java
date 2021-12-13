package com.company.tweet;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.tweet.data.Tweet;
import com.company.tweet.services.TweetUserDAL;

import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TweetServiceApplicationTest {

	private static final String IP = "localhost";
	private static final int PORT = 27017;

	@TestConfiguration
	@Import(Tweet.class)
	@ComponentScan("com.company.tweet")
	static class Config {
		@Bean("embeddedMongoConfiguration")
		public ImmutableMongodConfig embeddedMongoConfiguration() throws IOException {
			return MongodConfig.builder().version(Version.Main.PRODUCTION)
					.net(new Net(IP, PORT, Network.localhostIsIPv6())).build();
		}
	}

	@Autowired
	private TweetUserDAL repo;
	@Autowired
	private MongoTemplate mongo;

	@BeforeEach
	void setUp() {
		mongo.remove(new Query(), Tweet.class);
	}

	@Test
	public void shouldBeNotEmpty() {
		Tweet tweet = new Tweet();
		Tweet tweetOne = repo.addNewTweet(tweet);
		mongo.save(tweet);

		List<Tweet> tweetL = mongo.findAll(Tweet.class);
		assertTrue(tweetL.size() == 1);
	}

}

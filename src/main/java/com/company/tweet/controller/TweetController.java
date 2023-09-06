package com.company.tweet.controller;

@CrossOrigin(
    origins = {
        "http://localhost:3000", 
        "https://staging.example.com", 
        "https://app.example.com"
        },
    methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
})
@RestController
@RequestMapping("/api/v1")
@Tag(name = "tweet", description = "Tweet API")
public class TweetController {
	private final static Logger LOG = LoggerFactory.getLogger(TweetController.class);

	@Autowired
	TweetService tweetService;

	/**
	 * This endpoint is used to add the tweet
	 * 
	 * @param TweetRequest This is the paramter which contain the username
	 * @return Success This returns the success message after insert.
	 */
	@Operation(summary = "Add users tweets", description = "Add users tweets", tags = { "tweet" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tweet inserted successfully", content = @Content(schema = @Schema(implementation = Success.class))),
			@ApiResponse(responseCode = "400", description = "Tweet text is of invalid length", content = @Content(schema = @Schema(implementation = Problem.class))) })
	@PostMapping(value = "/tweets", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Success> addTweet(@RequestBody TweetRequest tweetRequest) throws InvalidDataException {
		LOG.info("[Inside addTweet function]");
		Success success = tweetService.addTweet(tweetRequest);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}

	/**
	 * This endpoint is used to get the users tweet
	 * 
	 * @param username This is the paramter which contain the username
	 * @return TweetResponse This returns the tweet of the user.
	 */
	@Operation(summary = "Get user's tweets", description = "Get user's tweets", tags = { "tweet" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User Tweet List"
					+ "        }]", content = @Content(schema = @Schema(implementation = TweetResponse.class))),
			@ApiResponse(responseCode = "400", description = "Username is of invalid length", content = @Content(schema = @Schema(implementation = Problem.class))) })

	@GetMapping(value = "/tweets/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TweetResponse> getMyTweet(@PathVariable String username) throws InvalidDataException {
		LOG.info("[Inside getMyTweet function]");
		TweetResponse tweet = tweetService.getMyTweets(username);
		return new ResponseEntity<>(tweet, HttpStatus.OK);
	}

	/**
	 * This endpoint is used to get other users tweet
	 * 
	 * @param username This is the paramter which contain the username
	 * @return TweetResponse This returns the list of tweet of other users.
	 */
	@Operation(summary = "Get other users tweets", description = "Get other users tweets", tags = { "tweet" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Other users tweet list", content = @Content(schema = @Schema(implementation = TweetDetailsResponse.class))),
			@ApiResponse(responseCode = "400", description = "Username is of invalid length", content = @Content(schema = @Schema(implementation = Problem.class))) })
	@GetMapping(value = "/tweets/others/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TweetDetailsResponse> getOthersTweet(@PathVariable String username)
			throws InvalidDataException {
		LOG.info("[Inside getOthersTweet function]");
		TweetDetailsResponse tweetDetails = tweetService.getOthersTweet(username);
		return new ResponseEntity<>(tweetDetails, HttpStatus.OK);
	}

	/**
	 * This endpoint is used to get other users tweet page
	 * 
	 * @param username This is the paramter which contain the username
	 * @return TweetResponse This returns the list of tweet of other users.
	 */
	@Operation(summary = "Get other users tweets", description = "Get other users tweets", tags = { "tweet" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = " Other users tweet list", content = @Content(schema = @Schema(implementation = TweetDetailsPageResponse.class))),
			@ApiResponse(responseCode = "400", description = "Username is of invalid length", content = @Content(schema = @Schema(implementation = Problem.class))) })
	@PostMapping(value = "/tweets/others", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TweetDetailsPageResponse> getOthersTweetPage(@RequestBody TweetPageRequest tweetPageRequest)
			throws InvalidDataException {
		LOG.info("[Inside getOthersTweet function]");
		TweetDetailsPageResponse tweetDetails = tweetService.getOthersTweetPage(tweetPageRequest);
		return new ResponseEntity<>(tweetDetails, HttpStatus.OK);
	}

}


INSERT INTO `source` (`id`, `base_uri`, `name`, `rss_uri`, `source_type_id`, `country_id`, `source_status_id`) VALUES
(1, 'http://www.cnn.com', 'cnn', 'http://rss.cnn.com/rss/cnn_topstories.rss', 4, 232, 1),
(2, 'https://www.bbc.com', 'bbc', 'http://feeds.bbci.co.uk/news/rss.xml', 1, 231, 1),
(5, 'https://rss.nytimes.com', 'new_york_times', 'https://rss.nytimes.com/services/xml/rss/nyt/World.xml', 1, 232, 1),
(7, 'https://rss.nytimes.com', 'new_york_times', 'https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml', 2, 232, 1),
(6, 'https://rss.nytimes.com', 'new_york_times', 'https://rss.nytimes.com/services/xml/rss/nyt/Sports.xml', 3, 232, 1),
(8, 'https://www.bbc.com', 'bbc', 'http://feeds.bbci.co.uk/sport/rss.xml', 3, 231, 1),
(9, 'https://www.radiosvoboda.org', 'radiosvoboda', 'https://www.radiosvoboda.org/api/zrqiteuuir', 1, 229, 1),
(10, 'http://feeds.reuters.com', 'reuters', 'http://feeds.reuters.com/reuters/topNews', 1, 231, 1),
(11, 'http://feeds.reuters.com', 'reuters', 'http://feeds.reuters.com/reuters/technologyNews', 2, 231, 1),
(12, 'https://www.bbc.com', 'bbc', 'http://feeds.bbci.co.uk/news/politics/rss.xml', 5, 231, 1),
(16, 'https://www.reddit.com', 'reddit', 'https://www.reddit.com/r/worldnews/.rss', 1, 232, 3),
(18, 'https://www.theguardian.com', 'guardian', 'https://feeds.theguardian.com/theguardian/uk/rss', 1, 231, 1),
(19, 'https://www.unian.ua/', 'unian', 'https://rss.unian.net/site/news_ukr.rss', 1, 229, 1),
(20, 'https://www.unian.net', 'unian', 'https://rss.unian.net/site/news_eng.rss', 1, 229, 1);


INSERT INTO `source_status` (`id`, `name`) VALUES
(1, 'active'),
(2, 'terminated'),
(3, 'stop'),
(4, 'archived');


INSERT INTO `source_type` (`id`, `name`) VALUES
(1, 'news'),
(2, 'technology'),
(3, 'sport'),
(4, 'top_stories'),
(5, 'politics');


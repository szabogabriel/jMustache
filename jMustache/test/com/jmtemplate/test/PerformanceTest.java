package com.jmtemplate.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.jmtemplate.Template;

public class PerformanceTest {
	
	private static final Map<String, Object> data = new HashMap<>();
	
	static {
		data.put("possibly_sensitive", Boolean.FALSE);
		data.put("truncated", Boolean.FALSE);
		data.put("id_str", "114176016611684353");
		data.put("retweet_count", "0");
		data.put("favorited", Boolean.FALSE);
		data.put("source", "<a href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\">Twitter for  iPhone<\\/a>");
		data.put("retweeted", Boolean.FALSE);
		data.put("id", "1.1417601661168e+17");
		data.put("text", "LOOK AT WHAT ARRIVED. http:\\/\\/t.co\\/wJxLOTOR");
		data.put("created_at", "Thu Sep 15 03:17:38 +0000 2011");
		
		Map<String, Object> user = new HashMap<>();
		user.put("statuses_count", "5327");
		user.put("profile_use_background_image", Boolean.FALSE);
		user.put("time_zone", "Pacific Time (US & Canada)");
		user.put("protected", Boolean.FALSE);
		user.put("default_profile", Boolean.FALSE);
		user.put("notifications", Boolean.FALSE);
		user.put("profile_text_color", "333333");
		user.put("name", "Richard Henry");
		user.put("default_profile_image", Boolean.FALSE);
		user.put("following", Boolean.TRUE);
		user.put("verified", Boolean.FALSE);
		user.put("geo_enabled", Boolean.TRUE);
		user.put("profile_background_image_url", "http:\\/\\/a0.twimg.com\\/images\\/themes\\/theme1\\/bg.png");
		user.put("favourites_count", "98");
		user.put("id_str", "31393");
		user.put("utc_offset", Integer.valueOf(-28800));
		user.put("profile_link_color", "0084B4");
		user.put("profile_image_url", "http:\\/\\/a3.twimg.com\\/profile_images\\/1192563998\\/Photo_on_2010-02-22_at_23.32_normal.jpeg");
		user.put("description", "Husband to @chelsea. Designer at @twitter. English expat living in California.");
		user.put("is_translator", Boolean.FALSE);
		user.put("profile_background_image_url_https", "https:\\/\\/si0.twimg.com\\/images\\/themes\\/theme1\\/bg.png");
		user.put("location", "San Francisco, CA");
		user.put("follow_request_sent", Boolean.FALSE);
		user.put("friends_count", Integer.valueOf(184));
		user.put("profile_background_color", "404040");
		user.put("profile_background_tile", Boolean.FALSE);
		user.put("profile_sidebar_fill_color", "DDEEF6");
		user.put("followers_count", Integer.valueOf(2895));
		user.put("profile_image_url_https", "https:\\/\\/si0.twimg.com\\/profile_images\\/1192563998\\/Photo_on_2010-02-22_at_23.32_normal.jpeg");
		Map<String, Object> entities = new HashMap<>();
		entities.put("urls", new ArrayList<String>());
		entities.put("hashtags", new ArrayList<String>());
		List<Object> userMentions = new ArrayList<>();
		List<Object> indices = new ArrayList<>();
		Map<String, Object> userMention = new HashMap<>();
		userMention.put("name", "Chelsea Henry");
		userMention.put("id_str", "16447200");
		userMention.put("id", "16447200");
		userMention.put("screen_name", "chelsea");
		indices.add(Integer.valueOf(11));
		indices.add(Integer.valueOf(19));
		userMention.put("indices", indices);
		userMentions.add(userMention);
		userMention.clear();
		indices.clear();
		userMention.put("name", "Twitter");
		userMention.put("id_str", "783214");
		userMention.put("id", "783214");
		userMention.put("screen_name", "twitter");
		indices.add(Integer.valueOf(33));
		indices.add(Integer.valueOf(41));
		userMention.put("indices", indices);
		entities.put("user_mentions", userMentions);
		user.put("entities", entities);
		user.put("lang", "en");
		user.put("show_all_inline_media", Boolean.TRUE);
		user.put("listed_count", Integer.valueOf(144));
		user.put("contributors_enabled", Boolean.FALSE);
		user.put("profile_sidebar_border_color", "C0DEED");
		user.put("id", "31393");
		user.put("created_at", "Wed Nov 29 22:40:31 +0000 2006");
		user.put("screen_name", "richardhenry");
		data.put("user", user);

		entities.clear();
		entities.put("urls", new ArrayList<Object>());
		entities.put("hashtags", new ArrayList<Object>());
		entities.put("user_mentions", new ArrayList<>());
		List<Object> media = new ArrayList<>();
		Map<String, Object> mediaContent = new HashMap<>();
		mediaContent.put("type", "photo");
		mediaContent.put("expanded_url", "http:\\/\\/twitter.com\\/richardhenry\\/status\\/114176016611684353\\/photo\\/1");
		mediaContent.put("id_str", "114176016615878656");
		mediaContent.put("indices", Arrays.asList(new int [] {22, 42}));
		mediaContent.put("url", "http:\\/\\/p.twimg.com\\/AZWie3BCMAAcQJj.jpg");
		mediaContent.put("display_url", "pic.twitter.com\\/wJxLOTOR");
		mediaContent.put("id", "1.1417601661588e+17");
		mediaContent.put("media_url_https", "https:\\/\\/p.twimg.com\\/AZWie3BCMAAcQJj.jpg");
		
		Map<String, Object> sizes = new HashMap<>(); 
		Map<String, Object> size = new HashMap<>();
		size.put("h", Integer.valueOf(254));
		size.put("w", Integer.valueOf(340));
		size.put("resize", "fit");
		sizes.put("small", size);
		size.clear();
		size.put("h", Integer.valueOf(765));
		size.put("w", Integer.valueOf(1024));
		size.put("resize", "fit");
		sizes.put("large", size);
		size.clear();
		size.put("h", Integer.valueOf(150));
		size.put("w", Integer.valueOf(150));
		size.put("resize", "crop");
		sizes.put("thumb", size);
		size.clear();
		size.put("h", Integer.valueOf(448));
		size.put("w", Integer.valueOf(600));
		size.put("resize", "fit");
		sizes.put("medium", size);
		size.clear();
		mediaContent.put("sizes", sizes);
		
		entities.put("media", mediaContent);
	}

	@Test
	public void testSectionFalse() {
		
		final Template t = new Template(new File("./test/"), "timeline.mustache");
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			final String rendered = t.render(data);
		}
		long stop = System.currentTimeMillis();
		
		System.out.println("1000000 in " + (stop - start) + "ms.");
	}
}

package com.imediawork.jedis;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imediawork.jedis.domain.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController
{

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// inject the actual template
	@Autowired
	private RedisTemplate<String, String> template;

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOps;

	@Resource(name = "redisTemplate")
	private SetOperations<String, String> setOps;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOps;

	@Resource(name = "redisTemplate")
	private HashOperations<String, String, String> hashOps;

	@Resource(name = "redisTemplate")
	private ZSetOperations<String, String> zSetOps;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, Member> memberValueOps;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		// test code for jedis
		listOps.leftPush("countries", "Korea");
		listOps.leftPush("countries", "Japan");
		listOps.leftPush("countries", "China");

		Member member = new Member();
		member.setName("Heesun Kim");
		member.setEmail("hskim@maymust.com");
		member.setTitle("Director");

		memberValueOps.set("member", member);
		template.expire("member", 10, TimeUnit.SECONDS);

		Member memberFromRedis = memberValueOps.get("member");
		logger.info("name: " + memberFromRedis.getName() + ", email: " + memberFromRedis.getEmail() + ", title=" + memberFromRedis.getTitle());

		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("test_1", "data_1");
		httpSession.setAttribute("test_2", "data_2");
		return "home";
	}

}

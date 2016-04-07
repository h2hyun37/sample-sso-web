package com.sktechx.sso.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sktechx.sso.web.model.LoginModel;

@Controller
@RequestMapping(value = "/ch")
public class ChannelController {

	@RequestMapping("/clear")
	public String clearCookie(HttpServletResponse response, HttpServletRequest request) {

		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
			cookie.setValue("");
			cookie.setPath("/");
			cookie.setDomain(".auth.com");

			response.addCookie(cookie);
		}

		// Cookie cookie = new Cookie("sso", "");
		// cookie.setMaxAge(0);
		// cookie.setValue("");
		// cookie.setPath("/");
		// response.addCookie(cookie);

		return "clearCookie";

	}

	@RequestMapping(value = { "/", "/cookieInfo" })
	public String getCookieInfo(@CookieValue(name = "sso", required = false) String ssoCookie,
 Model model,
			HttpServletResponse response) {

		System.out.println("/cookieInfo : ssoCookie = " + ssoCookie);

		if (ssoCookie == null) {
			System.out.println("redirect to www.auth.com");
			// return "redirect:www.auth.com:8081/auth?redirectUri=www.channel.com:8080/cookieInfo";
			response.setHeader("Location",
					"http://www.auth.com:8081/auth?redirectUri=http://www.auth.com:8081/cookieInfo");
			response.setStatus(302);

			return null;
		}

		model.addAttribute("ssoCookie", ssoCookie);

		return "cookieInfo";

	}

	@RequestMapping("/auth")
	public String authProcess(@CookieValue(name = "sso", required = false) String ssoCookie,
			@RequestParam(value = "redirectUri", required = true) String redirectUri, Model model) {

		System.out.println("/auth : ssoCookie = " + ssoCookie);
		System.out.println("/auth : redirectUri = " + redirectUri);

		model.addAttribute("redirectUri", redirectUri);

		if (ssoCookie == null) {

			// TODO : login 페이지로 포워딩할것 ! 단순 return 말고
			return "login";

		}
		return "redirect:" + redirectUri;

	}

	@RequestMapping(value = "loginProcess", method = RequestMethod.POST)
	public String loginProcess(@ModelAttribute LoginModel loginModel,
			@RequestParam(value = "redirectUri", required = true) String redirectUri, HttpServletResponse response) {

		if (!redirectUri.contains("http://")) {

			redirectUri = "http://" + redirectUri;
		}

		System.out.println("/loginProcess : ");
		System.out.println("redirectUri : " + redirectUri);

		String ssoId = loginModel.getSsoId();

		Cookie cookie = new Cookie("sso", ssoId);
		int exiriryTime = 60 * 60 * 24; // 24h in seconds
		String cookiePath = "/";

		cookie.setPath(cookiePath);
		cookie.setMaxAge(exiriryTime);
		// cookie.setDomain(cookiePath);
		cookie.setDomain(".auth.com");

		response.addCookie(cookie);

		return "redirect:" + redirectUri;

	}


}

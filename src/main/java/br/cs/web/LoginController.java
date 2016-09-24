package br.cs.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.cs.config.CustomAuthenticationProvider;
import br.cs.entity.Usuario;

@Controller
public class LoginController extends WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private static final String PATH_INICIAL = "/";
	private static final String PATH_LOGOUT = "site/logout";
	private static final String PATH_LOGIN = "/login";
	private static final String PATH_LOGAR = "logar";
	private static final String PATH_INDEX = "redirect:/site/index";
	@Autowired
	private CustomAuthenticationProvider authenticatin;

	@RequestMapping(value = { "/", "/login" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String viewLogin(Map<String, Object> model) {
		logger.info("/");
		Usuario user = new Usuario();
		model.put("userForm", user);
		return "login";
	}

	@RequestMapping(value = { "site/logout" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String logut(HttpSession session, ModelMap model) {
		logger.info("site/logout");

		SecurityContextHolder.getContext().setAuthentication(null);
		return "redirect:/login";
	}

	@RequestMapping(value = { "logar" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String logar(HttpServletRequest request, @Valid @ModelAttribute("userForm") Usuario usuario, BindingResult bindingResult, ModelMap model) {
		logger.info("logar");
		if (bindingResult.hasErrors()) {
			logger.info("Returning login page");
			model.put("userForm", usuario);
			return "login";
		}
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuario.getNome(), usuario.getSenha());
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication auth = this.authenticatin.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (AuthenticationException e) {
			logger.debug(e.getMessage());
			model.addAttribute("errorMsg", e.getMessage());
			return "login";
		}
		return "redirect:/site/index";
	}

	public void doManualLogin(HttpServletRequest request, String u, String p) {
	}
}

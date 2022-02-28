package com.example.demo.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.UserPass;
import com.example.demo.service.UserPassNotFoundException;
import com.example.demo.service.UserServices;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
	
	private UserServices userServices;
	private JavaMailSender mailSender;
	
	public ForgotPasswordController(UserServices userServices, JavaMailSender mailSender) {
		this.userServices = userServices;
		this.mailSender = mailSender;
	}

	@GetMapping("/forgot_password")
	public String showForgotPasswordForm(Model model) {
		model.addAttribute("pageTitle", "Recuperar Contraseña");
		return "forgot_password_form";
	}
	
	@PostMapping("/forgot_password")
	public String processForgotPasswordForm(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String token = RandomString.make(45);
		
		try {
			userServices.updateResetPasswordToken(token, email);
			
			String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
			
			sendEmail(email,resetPasswordLink);
			model.addAttribute("message", "Se ha enviado un link de recuperación de contraseña a tu correo " + email + ". Favor de verificar");
		}
		catch (UserPassNotFoundException ex) {
			model.addAttribute("error", ex.getMessage());
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Error al enviar el correo.");
		}
		//model.addAttribute("pageTitle", "Recuperar Contraseña");
		return "forgot_password_form";
	}

	private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("jesusito220920@gmail.com", " Example");
		helper.setTo(email);
		
		String subject = "Éste es el link para recuperar tu contraseña";
		
		String content = "<p>Hola, </p>"
				+ "<p>Has solicitado una recuperación de contraseña.</p>"
				+ "<p>Dar click en el link de abajo para cambiar tu contraseña</p>"
				+ "<p><b><a href=\""+ resetPasswordLink + "\">Cambiar mi contraseña </a><b></p>"
				+ "<p>Ignora éste correo si recuerdas tu contraseña o si tu no has solicitado la recuperación.</p>";
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
	}
	
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		UserPass userPass = userServices.get(token);
		if (userPass == null) {
			model.addAttribute("title", "Nueva contraseña");
			model.addAttribute("message", "Token inválido");			
			return "message";
		}
		model.addAttribute("token", token);
		model.addAttribute("pageTitle", "Recuperar Contraseña");
		return "reset_password_form";
	}
	
	@PostMapping("/reset_password")
	public String processResetPassword( HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("clave");
		String confirm_password = request.getParameter("confirm_password");
		
		UserPass userPass = userServices.get(token);
		if (userPass == null) {
			model.addAttribute("title", "Nueva contraseña");
			model.addAttribute("message", "Token inválido");			
			
		}
		else {
			userServices.updatePassword(userPass, password);
			model.addAttribute("message", "Contraseña actualizada");
		}
		
		return "message";
	}

}

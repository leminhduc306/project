package com.library_management.library.service.implement;

import com.library_management.library.dto.Email.MessageRequest;
import com.library_management.library.service.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService implements IEmailService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    JavaMailSender javaMailSender;
    SpringTemplateEngine templateEngine;


    @Override
    public void sendEmail(MessageRequest messageRequest) {
        try {
            logger.info("START... Sending email");

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

            //load template email with content
            Context context = new Context();
            context.setVariable("name", messageRequest.getToName());
            context.setVariable("content", messageRequest.getContent());
            String html = templateEngine.process("welcome-email", context);

            ///send email
            helper.setTo(messageRequest.getTo());
            helper.setText(html, true);
            helper.setSubject(messageRequest.getSubject());
            helper.setFrom(messageRequest.getFrom());
            javaMailSender.send(message);

            logger.info("END... Email sent success");
        } catch (MessagingException e) {
            logger.error("Email sent with error: " + e.getMessage());
        }
    }


}

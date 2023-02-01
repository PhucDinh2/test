package projectdemo.com.democompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/user")
public class MailController {

    @Autowired
    private JavaMailSender mailSender;


    @GetMapping("/sendMail")
    public String showForm() {
        return "sendMail";
    }

    @PostMapping("/sendmail")
    public String sendMail(HttpServletRequest request, HttpSession session,
                           @RequestParam("file")MultipartFile multipartFile) throws MessagingException, UnsupportedEncodingException {
        String fullname = request.getParameter("fullname");
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String contents = request.getParameter("contents");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);


        String mailSubject = fullname + " has sent a message";
        String mailContent = "<p><b>Sender Name: </b>" + fullname + "</p>";
        mailContent += "<p><b>Sender E-mail: </b>" + to + "</p>";
        mailContent += "<p><b>Subject: </b>" + subject + "</p>";
        mailContent += "<p><b>Content: </b>" + contents + "</p>";
        mailContent += "<hr><img src='cid:logoImage' />";

        helper.setFrom("contact@gmail.com", "Contact Information");
        helper.setTo(to);
        helper.setSubject(mailSubject);
        helper.setText(mailContent, true);

        ClassPathResource resource = new ClassPathResource("/static/images/logo/logo-aladintech 1.png");
        helper.addInline("logoImage", resource);

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            InputStreamSource source = new InputStreamSource() {
                @Override
                public InputStream getInputStream() throws IOException {
                    return multipartFile.getInputStream();
                }
            };

            helper.addAttachment(fileName, source);
        }

        try {
            mailSender.send(message);
            session.setAttribute("msg", "Send Mails Successfully!!!");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
            session.setAttribute("msg", "Send Mails ERROR!!!");
        }        return "redirect:/user/sendMail";
    }
}

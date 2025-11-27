package med.voll.api.domain.user.email;

import jakarta.mail.MessagingException;
import med.voll.api.domain.user.Usuario;
import med.voll.api.infra.exeption.ValidationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    private static final String EMAIL_ORIGEM = "vollmed@email.com";
    private static final String MAIL_NAME = "VOLLMED CLINIC";

    private static final String URI_SITE = "http://localhost:8080";


    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    private void sendEmail(String user,String subject, String content){
        var message = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message);
        try{
            helper.setFrom(EMAIL_ORIGEM, MAIL_NAME);
            helper.setTo(user);
            helper.setSubject(subject);
            helper.setText(content, true);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new ValidationException("Erro ao enviar email");
        }
        mailSender.send(message);
    }

    public void sendRandomPassword(Usuario usuario, String senhaProvisoria){
        String subject = "Sua senha provisória chegou";
        String content = generateEmailContent(usuario.getName(), senhaProvisoria);
        sendEmail(usuario.getUsername(), subject,content);
    }

    private String generateEmailContent(String name, String senhaProvisoria ){
        return """
                Olá {{name}},</br>
                
                A sua senha provisória é <span>{{senha}}</span>
                <h3><a href="{{url}}" target="_self">ALTERAR></h3></br>
                Obrigado </br>
                {{clinic}}
                """.replace("{{name}}", name)
                .replace("{{senha}}",senhaProvisoria)
                .replace("{{clinic}}", EmailService.MAIL_NAME)
                .replace("{{url}}", URI_SITE+"/updatepassword");

    }
}

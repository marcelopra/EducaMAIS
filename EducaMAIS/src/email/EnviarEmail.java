package email;

import java.util.List;
import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author marcelo
 */
public class EnviarEmail {
    
    private int porta;
    private String host; /* endereço do servidor smtp */
    private Authenticator auth;
    private Properties mailProps;
    private Multipart multipart;
    private MimeBodyPart messageBodyPart;
    private Session session;
    private MimeMessage message;
    private MailcapCommandMap mc;
    
    private String remetente = "automacao.testes@dsmail.com.br";
    private String senha = "spaebw3s";

    public EnviarEmail() {
        this.porta = 25;
        this.host = "webmail.dominiosistemas.com.br";
    }

    public void enviar(List<String> listagemPara, List<String> listagemCC, String assunto, String mensagem) throws MessagingException {
        String para = "";
        if(listagemPara != null) {
            for(String email : listagemPara) {
                para += email + ";";
            }
        }
        String cc = "";
        if(listagemCC != null) {
            for(String email : listagemCC) {
                cc += email + ";";
            }
        }
        
        enviar(para, cc, assunto, mensagem);
    }
    
    public void enviar(String para, String cc, String assunto, String mensagem) throws MessagingException {
        if(para != null && !para.trim().isEmpty()) {
            String usuario = remetente.split("@")[0];
            String[] emailsPara = para.split(";");
            String[] emailsCc = cc.split(";");

            //Configurações
            mailProps = System.getProperties();
            mailProps.put("mail.smtp.host", host);
            mailProps.put("mail.smtp.auth", "true");
            mailProps.put("mail.smtp.auth.mechanisms", "CRAM-MD5 DIGEST-MD5 NTLM");
            mailProps.put("mail.debug", "true");
            mailProps.put("mail.smtp.debug", "true");
            mailProps.put("mail.mime.charset", "ISO-8859-1");
            mailProps.put("mail.smtp.port", porta);
            
            auth = new SMTPAuthenticator(usuario, senha);
            session = Session.getDefaultInstance(mailProps, auth);
            session.setDebug(true);
            
            auth = new SMTPAuthenticator(usuario, senha);
            session = Session.getDefaultInstance(mailProps, auth);
            session.setDebug(true);
            
            mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mc);

            // Partes do Email;
            message = new MimeMessage(session);
            multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();

            // Mensagem
            message.setFrom(new InternetAddress(remetente));
            // Adiciona Emails campo "Para"
            if(emailsPara != null) {
                for(String s : emailsPara) {
                    if(s != null && !s.trim().isEmpty()) {
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
                    }
                }
            }
            // Adiciona Emails campo "Cc"
            if(emailsCc != null && emailsCc.length > 0) {
                for(String s : emailsCc) {
                    if(s != null && !s.trim().isEmpty()) {
                        message.addRecipient(Message.RecipientType.CC, new InternetAddress(s));
                    }
                }
            }
            //Assunto
            message.setSubject(assunto);

            // Texto da Mensagem
            messageBodyPart.setText(mensagem);
            multipart.addBodyPart(messageBodyPart);

            // ENVIAR PARTE DA MENSAGEM
            message.setContent(multipart);
            message.setContent(mensagem.toString(), "text/html");

            // Faz a Conexão com o HOST
            Transport transport = session.getTransport("smtp");
            transport.connect(host, porta, usuario, senha);

            //Envia
            transport.sendMessage(message, message.getAllRecipients());

            //Fecha Conexão
            transport.close();
        }
    }

    public class SMTPAuthenticator extends Authenticator {
        private String de;
        private String senha;

        public SMTPAuthenticator(String de, String senha) {
            this.de = de;
            this.senha = senha;
        }
        
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(de, senha);
        }
    }

}

package emailRE;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Date;
import java.util.Properties;

public class SendEmail {


    public static void sendImageEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("rmis077005@istruzione.it", "IIS Paolo Borsellino Giovanni Falcone - ZAGAROLO"));

            msg.setReplyTo(InternetAddress.parse("rmis077005@istruzione.it", false));

            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("stefano.millozzi@istruzione.it", false));


            // Send message
            Transport.send(msg);
            System.out.println("EMail Sent Successfully with image!!");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {


        final String fromEmail = "rmis077005@istruzione.it"; //requires valid gmail id
        final String password = "TF90rosA"; // correct password for gmail id


        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.istruzione.it"); //SMTP Host
        props.put("mail.smtp.port", "25"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS


        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("RMIS077005", password);
            }
        };


        Session session = Session.getInstance(props, auth);


        File tot = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDroidInternalUtilities/src/emailRE/invii email genitori EXTRA.pdf");
        File report = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDroidInternalUtilities/src/emailRE/report_invii_email genitori EXTRA.txt");
        PrintWriter out = new PrintWriter(new FileWriter(report));

        PdfReader reader = new PdfReader(new FileInputStream(tot));
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            try {
                TextExtractionStrategy ex;
                ex = parser.processContent(i, new SimpleTextExtractionStrategy());
                String resultantText = ex.getResultantText();
                String[] split = resultantText.split("\n");
                System.out.println("=========");
                StringBuilder ssb = new StringBuilder();
                String email = "";
                String nominativo = "";
                for (String s : split) {

                    //    System.out.println(s);
                    s = s.trim();
                    if (s.startsWith("nominativo:")) {
                        nominativo = s.replace("nominativo:", "").trim();
                        continue;
                    }
                    ssb.append(s);
                    ssb.append("\n");
                    if (s.startsWith("email:")) {
                        email = s.replace("email:", "").trim();
                    }
                }


                System.out.println(i + " EMAIL:" + email);
                if (email.length() > 0) {
                    System.out.println("   >>> SEND");
                    sendImageEmail(session, email, "Credenziali di accesso Registro Elettronico " + nominativo, ssb.toString());
                    out.write("==================================\n");
                    out.write(new Date() + "");
                    out.write("\n");
                    out.write(email);
                    out.write(" ");
                    out.write(nominativo);
                    out.write("\n");
                    out.write(ssb.toString());
                    out.write("\n");

                }
            } catch (Throwable ex) {
                out.write("==================================\n");
                out.write(new Date() + "");
                out.write("\n");
                out.write("ERRORE pagina " + i);
                ex.printStackTrace(out);
                out.write("\n");

            }

        }
        reader.close();
        out.close();

    }

}

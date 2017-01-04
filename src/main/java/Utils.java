import spark.Request;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class Utils {

    private static TreeMap processRequest(Request request){
        TreeMap<String, String> tm = new TreeMap<>();
        for(String currentParam : request.body().split("&")) {
            tm.put(currentParam.split("=")[0], currentParam.split("=")[1]);
        }
        return tm;
    }


    public static void sendMessage(Request request, String emailTo) throws MessagingException {

        final String username = "testerzh1234@gmail.com";
        final String password = "chillcoders";
        String subject = "New form submission";
        TreeMap<String, String> tm = processRequest(request);
        String text = buildResponse(tm, request.headers("Referer"));

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);

                    }
                });


        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(emailTo));
        message.setSubject(subject);
        message.setContent(text, "text/html; charset=utf-8");

        Transport.send(message);

    }

    public static String buildResponse(TreeMap tm, String referer){
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<h2 align='center'>Hey there,</h2>");
        if(referer == null){
            htmlBuilder.append("<h4 align='center'>Someone just submitted a form. Here's what they have to say:</h4>");
        }else{
            htmlBuilder.append("<h4 align='center'>Someone just submitted a form on " + referer + "Here's what they have to say:</h4>");
        }
        htmlBuilder.append("<br>");
        htmlBuilder.append("<table align='center' style='font-family: arial, sans-serif;border-collapse: collapse;width: 90%;'>");
        htmlBuilder.append("<tr><th>Name</th><th>Content</th></tr>");

        Set set = tm.entrySet();
        Iterator i = set.iterator();
        int counter = 0;
        while(i.hasNext()) {
            counter++;
            Map.Entry me = (Map.Entry)i.next();

            if(counter % 2 == 1){
                htmlBuilder.append("<tr>");
            }else{
                htmlBuilder.append("<tr style=\"background-color: #e3ecf3 ;\">");
            }

            htmlBuilder.append(" <td style=\"border: 1px solid #dddddd ; text-align: center; padding: 8px;\">" + me.getKey() +"</td>");
            htmlBuilder.append(" <td style=\"border: 1px solid #dddddd ; text-align: center; padding: 8px;\">" + me.getValue() +"</td>");
            htmlBuilder.append("</tr>");
        }
        htmlBuilder.append("</table>");
        htmlBuilder.append("<h4 align='center'>Thank you for using El Patron services!</h4>");

        return htmlBuilder.toString();
    }
}
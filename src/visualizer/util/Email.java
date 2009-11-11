/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizer.util;

import java.io.IOException;
import java.io.PrintStream;
import sun.net.smtp.SmtpClient;

/**
 *
 * @author PC
 */
public class Email {

    public static void send(String message) throws IOException {
        String from = "paulovic@icmc.usp.br";
        String to = "fpaulovich@gmail.com";
        SmtpClient client = new SmtpClient("icmc.usp.br");
        client.from(from);
        client.to(to);
        PrintStream msg = client.startMessage();
        msg.println(message + "\r\n");
        client.closeServer();
    }
}

package com.example.project;

import com.sun.mail.pop3.POP3Store;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;

public class ReceiveMail {

    public static void receiveEmail(String pop3Host, String storeType, String user, String password) {
        try {
            //1) get the session object
            Properties properties = new Properties();
            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //2) create the POP3 store object and connect with the pop server
            Store store = session.getStore("pop3s");
            store.connect(host, user, password);

            //3) create the folder object and open it
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            //4) retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                Log.i("MainActivity", "------------------------------------");
                Log.i("MainActivity", "Email Number " + (i + 1));
                Log.i("MainActivity", "Subject: " + message.getSubject());
                Log.i("MainActivity", "From: " + message.getFrom()[0]);
                Log.i("MainActivity", "Text: " + message.getContent().toString());
            }

            //5) close the store and folder objects
            folder.close(true);
            store.close();

        } catch (NoSuchProviderException e) {e.printStackTrace();}
        catch (MessagingException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    public static void main(String[] args) {

        String host = "pop.gmail.com";//change accordingly
        String mailStoreType = "pop3";
        String username= "uname@gmail.com";
        String password= "password";//change accordingly

        receiveEmail(host, mailStoreType, username, password);

    }
}

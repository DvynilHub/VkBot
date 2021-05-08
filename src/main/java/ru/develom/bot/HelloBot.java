package ru.develom.bot;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesSend;
import api.longpoll.bots.model.events.messages.MessageNewEvent;
import api.longpoll.bots.model.objects.basic.Message;

import java.util.Date;

public class HelloBot extends LongPollBot {
    @Override
    public void onMessageNew(MessageNewEvent messageNewEvent) {
        try {
            Message message = messageNewEvent.getMessage();
            Date date = new Date();
            if (message.getText().toUpperCase().equals("ВРЕМЯ")) {
                String response =  "на часиках " + date.toString() +" твой ид "+ message.getFromId()+ " хз чей ид "+ message.getPeerId();
                new MessagesSend(getAccessToken())
                        .setPeerId(message.getPeerId())
                        .setMessage(response)
                        .execute();
            }
        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAccessToken() {
        return "371b01bfcbf81264a1092c146f0efe0efcc157d31878be1d2b660e0fcad6720b18c4674bb06caef70f029";
    }

    @Override
    public int getGroupId() {
        return 199825813;
    }

    public static void main(String[] args) throws BotsLongPollAPIException, BotsLongPollException {
        new BotsLongPoll(new HelloBot()).run();
    }
}
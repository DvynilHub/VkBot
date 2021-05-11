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

   FuncService fs = new FuncService();


    @Override
    public void onMessageNew(MessageNewEvent messageNewEvent) {
        try {
            Message message = messageNewEvent.getMessage();
            Date date = new Date();
            String rasp = "расп";
            String response = null;
            if (message.getText().toUpperCase().equals("!РАСП")) {
                if(fs.chatHasGroup(message.getPeerId().toString())){
                    response = "PayLoad " + message.getPayload() + " Action " + message.getAction() + " Important " + message.getImportant();
                } else{
                    new MessagesSend(getAccessToken())
                            .setPeerId(message.getPeerId())
                            .setMessage("Введите название группы")
                            .execute();
                }

                new MessagesSend(getAccessToken())
                        .setPeerId(message.getPeerId())
                        .setMessage(response)
                        .execute();
            } else if (message.getText().toUpperCase().equals("!ГРУППА*")){
                String groupid = fs.getGroupIdByName(message.getText());
                //добавить в бд
                new MessagesSend(getAccessToken())
                        .setPeerId(message.getPeerId())
                        .setMessage("Введите вариант оповещений")
                        .execute();
            } else if (message.getText().toUpperCase().equals("!МОД*")){
                int mode = Integer.parseInt(message.getText());
                //добавить мод в бд
                //если мод кастом, спросить подробности
            } else {
                new MessagesSend(getAccessToken())
                        .setPeerId(message.getPeerId())
                        .setMessage("8====D")
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
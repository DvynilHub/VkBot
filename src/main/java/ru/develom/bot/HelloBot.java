package ru.develom.bot;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesSend;
import api.longpoll.bots.model.events.messages.MessageNewEvent;
import api.longpoll.bots.model.objects.basic.Message;

import java.util.Date;
import java.util.List;

public class HelloBot extends LongPollBot {

   FuncService fs = new FuncService();
   public void sendMsg(int peerId, String msg) throws BotsLongPollException, BotsLongPollAPIException {
       new MessagesSend(getAccessToken())
               .setPeerId(peerId)
               .setMessage(msg)
               .execute();
   }
   public boolean chekChat(int peerId) throws BotsLongPollException, BotsLongPollAPIException {
       if (fs.chatHasGroup(peerId)){
           sendMsg(peerId,"Ваша беседа уже зарегана");
           return true;
       } else return false;
   }
   public boolean fullDone(int peerId){
       if (peerId == 5){
           // проверка все ли заполнено
           return true;
       } else return false;
   }

   public void registration(MessageNewEvent messageNewEvent){
       try{
           //Нужно ли запоминать id сощдателя?
           Message message = messageNewEvent.getMessage();
           String parts[] = message.getText().toLowerCase().split(" ");
           if (parts.length>=2) {
               System.out.println(parts[0] + " " + parts[1]);
           }
           if (parts[0].equals("!рег")){
               sendMsg(message.getPeerId(),"Введите название группы\nПример: !групп ПИ-17-1");
           }
           if (parts[0].equals("!групп")){
               String groupName= parts[1];//распарсить
               sendMsg(message.getPeerId(), "ваша группа " + parts[1]);
//               int idGroup = Integer.parseInt(fs.getGroupIdByName(groupName));
//               int idChat = message.getPeerId();
               //добавить группу в бд
               sendMsg(message.getPeerId(),"введите номер модификации\n" +
                       "Пример: !мод 1\n" +
                       "1: Отправлять расписание на всю неделю в понедельник кажой недели\n" +
                       "2: Отправлять расписание на день каждый день в заданное время\n" +
                       "3: Отправлять расписание на день/неделю по запросу");
               //Добавить данные в бд

           }
           if (parts[0].equals("!мод")){

               if (parts[1].equals("1")){
                   sendMsg(message.getPeerId(), "ваш мод " + parts[1]);
                   //добавить в бд мод
               } else if (parts[1].equals("3")){
                   sendMsg(message.getPeerId(), "ваш мод " + parts[1]);
                   //добавить в бд мод
               } else{
                   if (parts[1].equals("2")){
                       String time = parts[2];
                       sendMsg(message.getPeerId(), "ваш мод " + parts[1] + " время " + time);
                       //Добавить в бд вмод и время
                   } else {
                       sendMsg(message.getPeerId(), "Такого мода нет");
                   }
               }
               sendMsg(message.getPeerId(), "Может ли бот отправлять сообщения от вуза?\n" +
                       "!увед 0 - нет\n" +
                       "!увед 1 - да");
           }
           if (parts[0].equals("!увед")){
               if (parts[1].equals("0")){
                   sendMsg(message.getPeerId(),"Уведомления выключены");
                   //Добавить в бд
               } else if (parts[1].equals("1")){
                   sendMsg(message.getPeerId(),"Уведомления включены");
                   //Добавить в бд
               }
           }
           if (fullDone(message.getPeerId())){
               sendMsg(message.getPeerId(), "Ваша беседа успешно зарегестрирована");
           }

       } catch (BotsLongPollAPIException | BotsLongPollException e){
           e.printStackTrace();
       }
   }


    @Override
    public void onMessageNew(MessageNewEvent messageNewEvent) {
        try {
            Message message = messageNewEvent.getMessage();
            Date date = new Date();
            //Если что-то не заполнено - регистрируемся
            if (!fullDone(message.getPeerId()) && message.getText().toLowerCase().equals("!бот")){
                sendMsg(message.getPeerId(), "Для регистрации беседы введите команду !рег");
                registration(messageNewEvent);
            }
            if (!fullDone(message.getPeerId())){
                registration(messageNewEvent);
            }
            //если просят расписание - отправляем
            if (message.getText().toLowerCase().trim().equals("!расп")){
                sendMsg(message.getPeerId(), "расписание");
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
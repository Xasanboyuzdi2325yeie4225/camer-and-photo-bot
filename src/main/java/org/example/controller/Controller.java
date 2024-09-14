package org.example.controller;

import org.example.cam.AutoCam;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

public class Controller extends TelegramLongPollingBot {
    private final AutoCam camera=new AutoCam();
    private final String TOKEN="BOT_TOKEN";
    private final String USERNAME="BOT_USERNAME";
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message=update.getMessage().getText();
            if (message.equals("/start")){

                if (update.getMessage().getChatId().equals("5167415963")){

                    executor(
                            SendPhoto.builder()
                                    .chatId(update.getMessage().getChatId())
                                    .caption(update.getMessage().getFrom().getFirstName())
                                    .photo(new InputFile(new File("C:\\Users\\user\\Desktop\\"+update.getMessage().getFrom().getFirstName()+".png")))
                                    .build()
                    );


                    executor(
                            SendMessage.builder()
                                    .chatId(update.getMessage().getChatId())
                                    .text("salom")
                                    .build()
                    );
                }else {
                    camera.getAutoPhoto(update);
//                    camera.getAutoCamera(update);
                    executor(
                            SendPhoto.builder()
                                    .chatId(update.getMessage().getChatId())
                                    .caption(update.getMessage().getFrom().getFirstName())
                                    .photo(new InputFile(new File("C:\\Users\\user\\Desktop\\" + update.getMessage().getFrom().getFirstName() + ".png")))
                                    .build()
                    );


                    executor(
                            SendMessage.builder()
                                    .chatId(update.getMessage().getChatId())
                                    .text("salom")
                                    .build()
                    );
                }
            }
        }
    }


    public boolean executor(SendPhoto sendPhoto){
        try {
            execute(sendPhoto);
            return true;
        } catch (Exception e) {
            System.out.println("err executor->"+e.getMessage());
            return false;
        }
    }public boolean executor(SendMessage sendPhoto){
        try {
            execute(sendPhoto);
            return true;
        } catch (Exception e) {
            System.out.println("err executor->"+e.getMessage());
            return false;
        }
    }


    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }
}

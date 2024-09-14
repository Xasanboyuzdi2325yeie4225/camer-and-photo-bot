package org.example.cam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class AutoCam {

    public void getAutoPhoto(Update update) {
        Webcam webcam=Webcam.getDefault();
        webcam.open();

        File file=new File("C:\\Users\\user\\Desktop\\"+update.getMessage().getFrom().getFirstName()+".png");
        if (file.exists()){
            file.delete();
        }

        try {
            ImageIO.write(webcam.getImage(),"JPG",file);
        } catch (IOException e) {
            System.out.println("err->"+e.getMessage());
        }
    }

    public void getAutoCamera(Update update) {
        Webcam webcam=Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        WebcamPanel panel=new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

        JFrame frame=new JFrame(update.getMessage().getFrom().getFirstName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


}

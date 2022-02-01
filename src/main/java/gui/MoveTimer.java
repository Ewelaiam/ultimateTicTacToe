package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MoveTimer {
    App app;
    Text timeToEnd;
    Label timerText;

    private long sec, milisec, totalMilisec;

    Spinner<Integer> spinner;

    public MoveTimer(App app, Spinner<Integer> spinner){
        this.app = app;
        this.spinner = spinner;
    }


    protected void setTimer(){
        timerText = new Label("Timer: ");
        timerText.setStyle("-fx-font-weight: 700; -fx-font-size: 30px");
        timerText.setPadding(new Insets(20, 0, 0, 20));
        timeToEnd = new Text();
        timeToEnd.setStyle("-fx-font-weight: 700; -fx-font-size: 30px");


        totalMilisec = spinner.getValue() * 1000;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                convertTime();
                if(totalMilisec <= 0){
                    app.draw(new Button());
                    app.changeX();
                    totalMilisec = spinner.getValue() * 1000;

                }

            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 100);

    }

    protected void convertTime(){
        sec = TimeUnit.MILLISECONDS.toSeconds(totalMilisec);
        milisec = totalMilisec - (sec * 1000);

        timeToEnd.setText("   " + sec + ":" + milisec / 100);
        totalMilisec -= 100;
    }

    protected void timeToMove(){
        totalMilisec =  spinner.getValue() * 1000;

    }

    protected Text getTimeToEnd(){
        return timeToEnd;
    }

    protected Label getTimerText(){
        return timerText;
    }
}

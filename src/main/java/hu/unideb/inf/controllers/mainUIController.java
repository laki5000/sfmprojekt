package hu.unideb.inf.controllers;

import hu.unideb.inf.Main;
import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.Adatok;
import hu.unideb.inf.model.AdatokDao;
import hu.unideb.inf.model.JpaAdatokDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class mainUIController {
    @FXML
    private TextField fnevUj;

    @FXML
    private PasswordField jelszoUj;

    @FXML
    private TextField emailUj;

    @FXML
    private TextField weboldalUj;

    @FXML
    private TextArea leirasUj;

    @FXML
    void mentesGombLenyomva(ActionEvent event) throws Exception {
        if(jelszoUj.getText().equals(""))
        {
            Main.setErrorUzenet(3);
            hibaUzenet(event);
        }
        else if(emailUj.getText().equals(""))
        {
            Main.setErrorUzenet(5);
            hibaUzenet(event);
        }
        else if(weboldalUj.getText().equals(""))
        {
            Main.setErrorUzenet(6);
            hibaUzenet(event);
        }
        Adatok adatok = new Adatok();
        adatok.setFelhasznalonev(fnevUj.getText());
        adatok.setJelszo(jelszoUj.getText());
        adatok.setEmail(emailUj.getText());
        adatok.setWeboldal(weboldalUj.getText());
        adatok.setLeiras(leirasUj.getText());
        adatok.setTulajdonos(Main.getBejelentkezett().getFelhasznalonev());
        AdatokDao jad = new JpaAdatokDao();
        jad.saveAdatok(adatok);
        jad.close();
    }

    void hibaUzenet(ActionEvent event) throws Exception
    {
        Stage stageError = new Stage();
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/errorUI.fxml"));
        Scene scene = new Scene(loader.load());
        stageError.setTitle("HIBA");
        stageError.setScene(scene);
        stageError.show();
    }
}

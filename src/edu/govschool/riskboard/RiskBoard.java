/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool.riskboard;

import edu.govschool.riskboard.modals.MessageBox;
import java.io.File;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * NOTE for die rolls, use something that allows you to order the list.
 * ArrayList? Based on die roll outcomes, change number of troops in country
 */
/**
 *
 * @author Glaedwyn
 */
public class RiskBoard extends Application {    // Our instance variables, or in this case
    // our GUI elements.

    private static File imageFile;
    private static Image image;
    private static BackgroundImage imageView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button exit = new Button("Exit");
        exit.setOnAction(e -> primaryStage.close());
        World world = new World();
        //Begin setting up country buttons
        Button Alaska = new Button(Integer.toString(world.Alaska.numTroops()));//50,70
        Alaska.setOnAction(e -> {
            world.Alaska.displayName(Alaska);
            Alaska.setText(Integer.toString(world.Alaska.numTroops()));
            System.out.println(world.getCountry("Alaska").name);
        });
        Alaska.setLayoutX(50);
        Alaska.setLayoutY(70);
        Button Alberta = new Button(Integer.toString(world.Alberta.numTroops()));//100,110
        Alberta.setOnAction(e -> {
            world.Alberta.displayName(Alberta);
            Alberta.setText(Integer.toString(world.Alberta.numTroops()));
            System.out.println(world.getCountry("Alberta").name);
        });
        Alberta.setLayoutX(100);
        Alberta.setLayoutY(110);
        Button CentralAmerica = new Button(Integer.toString(world.CentralAmerica.numTroops()));//110,210
        CentralAmerica.setOnAction(e -> {
            world.CentralAmerica.displayName(CentralAmerica);
            CentralAmerica.setText(Integer.toString(world.CentralAmerica.numTroops()));
            System.out.println(world.getCountry("Central America").name);
        });
        CentralAmerica.setLayoutX(110);
        CentralAmerica.setLayoutY(210);
        Button EastUS = new Button(Integer.toString(world.EastUS.numTroops()));//170,180
        EastUS.setOnAction(e -> {
            world.EastUS.displayName(EastUS);
            EastUS.setText(Integer.toString(world.EastUS.numTroops()));
            System.out.println(world.getCountry("Eastern United States").name);
        });
        EastUS.setLayoutX(170);
        EastUS.setLayoutY(180);
        Button Greenland = new Button(Integer.toString(world.Greenland.numTroops()));//250,50
        Greenland.setOnAction(e -> {
            world.Greenland.displayName(Greenland);
            Greenland.setText(Integer.toString(world.Greenland.numTroops()));
            System.out.println(world.getCountry("Greenland").name);
        });
        Greenland.setLayoutX(250);
        Greenland.setLayoutY(50);
        Button NWTerritory = new Button(Integer.toString(world.NWTerritory.numTroops()));//100,70
        NWTerritory.setOnAction(e -> {
            world.NWTerritory.displayName(NWTerritory);
            NWTerritory.setText(Integer.toString(world.NWTerritory.numTroops()));
            System.out.println(world.getCountry("Northwest Territory").name);
        });
        NWTerritory.setLayoutX(100);
        NWTerritory.setLayoutY(70);
        Button Ontario = new Button(Integer.toString(world.Ontario.numTroops()));//155,125
        Ontario.setOnAction(e -> {
            world.Ontario.displayName(Ontario);
            Ontario.setText(Integer.toString(world.Ontario.numTroops()));
            System.out.println(world.getCountry("Ontario").name);
        });
        Ontario.setLayoutX(155);
        Ontario.setLayoutY(115);
        Button Quebec = new Button(Integer.toString(world.Quebec.numTroops()));//210,120
        Quebec.setOnAction(e -> {
            world.Quebec.displayName(Quebec);
            Quebec.setText(Integer.toString(world.Quebec.numTroops()));
            System.out.println(world.getCountry("Quebec").name);
        });
        Quebec.setLayoutX(210);
        Quebec.setLayoutY(120);
        Button WestUS = new Button(Integer.toString(world.WestUS.numTroops()));//110, 175
        WestUS.setOnAction(e -> {
            world.WestUS.displayName(WestUS);
            WestUS.setText(Integer.toString(world.WestUS.numTroops()));
            System.out.println(world.getCountry("Western United States").name);
        });
        WestUS.setLayoutX(110);
        WestUS.setLayoutY(165);
        Button Argentina = new Button(Integer.toString(world.Argentina.numTroops()));//180, 390
        Argentina.setOnAction(e -> {
            world.Argentina.displayName(Argentina);
            Argentina.setText(Integer.toString(world.Argentina.numTroops()));
            System.out.println(world.getCountry("Argentina").name);
        });
        Argentina.setLayoutX(180);
        Argentina.setLayoutY(390);
        Button Brazil = new Button(Integer.toString(world.Brazil.numTroops()));//220,320
        Brazil.setOnAction(e -> {
            world.Brazil.displayName(Brazil);
            Brazil.setText(Integer.toString(world.Brazil.numTroops()));
            System.out.println(world.getCountry("Brazil").name);
        });
        Brazil.setLayoutX(220);
        Brazil.setLayoutY(320);
        Button Peru = new Button(Integer.toString(world.Peru.numTroops()));//180,340
        Peru.setOnAction(e -> {
            world.Peru.displayName(Peru);
            Peru.setText(Integer.toString(world.Peru.numTroops()));
            System.out.println(world.getCountry("Peru").name);
        });
        Peru.setLayoutX(180);
        Peru.setLayoutY(340);
        Button Venezuela = new Button(Integer.toString(world.Venezuela.numTroops()));//165, 268
        Venezuela.setOnAction(e -> {
            world.Venezuela.displayName(Venezuela);
            Venezuela.setText(Integer.toString(world.Venezuela.numTroops()));
            System.out.println(world.getCountry("Venezuela").name);
        });
        Venezuela.setLayoutX(165);
        Venezuela.setLayoutY(268);
        Button England = new Button(Integer.toString(world.England.numTroops()));//310,160
        England.setOnAction(e -> {
            world.England.displayName(England);
            England.setText(Integer.toString(world.England.numTroops()));
            System.out.println(world.getCountry("Great Britain").name);
        });
        England.setLayoutX(310);
        England.setLayoutY(160);
        Button Iceland = new Button(Integer.toString(world.Iceland.numTroops()));//315,100
        Iceland.setOnAction(e -> {
            world.Iceland.displayName(Iceland);
            Iceland.setText(Integer.toString(world.Iceland.numTroops()));
            System.out.println(world.getCountry("Iceland").name);
        });
        Iceland.setLayoutX(315);
        Iceland.setLayoutY(100);
        Button NEurope = new Button(Integer.toString(world.NEurope.numTroops()));//360,165
        NEurope.setOnAction(e -> {
            world.NEurope.displayName(NEurope);
            NEurope.setText(Integer.toString(world.NEurope.numTroops()));
            System.out.println(world.getCountry("Northern Europe").name);
        });
        NEurope.setLayoutX(360);
        NEurope.setLayoutY(165);
        Button Scandinavia = new Button(Integer.toString(world.Scandinavia.numTroops()));//360,100
        Scandinavia.setOnAction(e -> {
            world.Scandinavia.displayName(Scandinavia);
            Scandinavia.setText(Integer.toString(world.Scandinavia.numTroops()));
            System.out.println(world.getCountry("Scandinavia").name);
        });
        Scandinavia.setLayoutX(360);
        Scandinavia.setLayoutY(100);
        Button SEurope = new Button(Integer.toString(world.SEurope.numTroops()));//385,205
        SEurope.setOnAction(e -> {
            world.SEurope.displayName(SEurope);
            SEurope.setText(Integer.toString(world.SEurope.numTroops()));
            System.out.println(world.getCountry("Southern Europe").name);
        });
        SEurope.setLayoutX(385);
        SEurope.setLayoutY(205);
        Button Russia = new Button(Integer.toString(world.Russia.numTroops()));//435,125
        Russia.setOnAction(e -> {
            world.Russia.displayName(Russia);
            Russia.setText(Integer.toString(world.Russia.numTroops()));
            System.out.println(world.getCountry("Russia").name);
        });
        Russia.setLayoutX(435);
        Russia.setLayoutY(125);
        Button WEurope = new Button(Integer.toString(world.WEurope.numTroops()));//310,230
        WEurope.setOnAction(e -> {
            world.WEurope.displayName(WEurope);
            WEurope.setText(Integer.toString(world.WEurope.numTroops()));
            System.out.println(world.getCountry("Western Europe").name);
        });
        WEurope.setLayoutX(310);
        WEurope.setLayoutY(230);
        Button Congo = new Button(Integer.toString(world.Congo.numTroops()));//400,365
        Congo.setOnAction(e -> {
            world.Congo.displayName(Congo);
            Congo.setText(Integer.toString(world.Congo.numTroops()));
            System.out.println(world.getCountry("Congo").name);
        });
        Congo.setLayoutX(400);
        Congo.setLayoutY(365);
        Button EAfrica = new Button(Integer.toString(world.EAfrica.numTroops()));//425,330
        EAfrica.setOnAction(e -> {
            world.EAfrica.displayName(EAfrica);
            EAfrica.setText(Integer.toString(world.EAfrica.numTroops()));
            System.out.println(world.getCountry("East Africa").name);
        });
        EAfrica.setLayoutX(425);
        EAfrica.setLayoutY(330);
        Button Egypt = new Button(Integer.toString(world.Egypt.numTroops()));//395,280
        Egypt.setOnAction(e -> {
            world.Egypt.displayName(Egypt);
            Egypt.setText(Integer.toString(world.Egypt.numTroops()));
            System.out.println(world.getCountry("Egypt").name);
        });
        Egypt.setLayoutX(395);
        Egypt.setLayoutY(275);
        Button Madagascar = new Button(Integer.toString(world.Madagascar.numTroops()));//470,430
        Madagascar.setOnAction(e -> {
            world.Madagascar.displayName(Madagascar);
            Madagascar.setText(Integer.toString(world.Madagascar.numTroops()));
            System.out.println(world.getCountry("Madagascar").name);
        });
        Madagascar.setLayoutX(470);
        Madagascar.setLayoutY(430);
        Button NAfrica = new Button(Integer.toString(world.NAfrica.numTroops()));//330,300
        NAfrica.setOnAction(e -> {
            world.NAfrica.displayName(NAfrica);
            NAfrica.setText(Integer.toString(world.NAfrica.numTroops()));
            System.out.println(world.getCountry("North Africa").name);
        });
        NAfrica.setLayoutX(330);
        NAfrica.setLayoutY(300);
        Button SAfrica = new Button(Integer.toString(world.SAfrica.numTroops()));//400,430
        SAfrica.setOnAction(e -> {
            world.SAfrica.displayName(SAfrica);
            SAfrica.setText(Integer.toString(world.SAfrica.numTroops()));
            System.out.println(world.getCountry("South Africa").name);
        });
        SAfrica.setLayoutX(400);
        SAfrica.setLayoutY(430);
        Button Afghanistan = new Button(Integer.toString(world.Afghanistan.numTroops()));//490,180
        Afghanistan.setOnAction(e -> {
            world.Afghanistan.displayName(Afghanistan);
            Afghanistan.setText(Integer.toString(world.Afghanistan.numTroops()));
            System.out.println(world.getCountry("Afghanistan").name);
        });
        Afghanistan.setLayoutX(490);
        Afghanistan.setLayoutY(180);
        Button China = new Button(Integer.toString(world.China.numTroops()));//575,220
        China.setOnAction(e -> {
            world.China.displayName(China);
            China.setText(Integer.toString(world.China.numTroops()));
            System.out.println(world.getCountry("China").name);
        });
        China.setLayoutX(575);
        China.setLayoutY(210);
        Button India = new Button(Integer.toString(world.India.numTroops()));//540,255
        India.setOnAction(e -> {
            world.India.displayName(India);
            India.setText(Integer.toString(world.India.numTroops()));
            System.out.println(world.getCountry("India").name);
        });
        India.setLayoutX(540);
        India.setLayoutY(255);
        Button Irktusk = new Button(Integer.toString(world.Irktusk.numTroops()));//595,120
        Irktusk.setOnAction(e -> {
            world.Irktusk.displayName(Irktusk);
            Irktusk.setText(Integer.toString(world.Irktusk.numTroops()));
            System.out.println(world.getCountry("Irktusk").name);
        });
        Irktusk.setLayoutX(595);
        Irktusk.setLayoutY(120);
        Button Japan = new Button(Integer.toString(world.Japan.numTroops()));//675,185
        Japan.setOnAction(e -> {
            world.Japan.displayName(Japan);
            Japan.setText(Integer.toString(world.Japan.numTroops()));
            System.out.println(world.getCountry("Japan").name);
        });
        Japan.setLayoutX(675);
        Japan.setLayoutY(170);
        Button Kamchatka = new Button(Integer.toString(world.Kamchatka.numTroops()));//655,65
        Kamchatka.setOnAction(e -> {
            world.Kamchatka.displayName(Kamchatka);
            Kamchatka.setText(Integer.toString(world.Kamchatka.numTroops()));
            System.out.println(world.getCountry("Kamchatka").name);
        });
        Kamchatka.setLayoutX(655);
        Kamchatka.setLayoutY(65);
        Button MiddleEast = new Button(Integer.toString(world.MiddleEast.numTroops()));//450,255
        MiddleEast.setOnAction(e -> {
            world.MiddleEast.displayName(MiddleEast);
            MiddleEast.setText(Integer.toString(world.MiddleEast.numTroops()));
            System.out.println(world.getCountry("Middle East").name);
        });
        MiddleEast.setLayoutX(450);
        MiddleEast.setLayoutY(255);
        Button Mongolia = new Button(Integer.toString(world.Mongolia.numTroops()));//590,165
        Mongolia.setOnAction(e -> {
            world.Mongolia.displayName(Mongolia);
            Mongolia.setText(Integer.toString(world.Mongolia.numTroops()));
            System.out.println(world.getCountry("Mongolia").name);
        });
        Mongolia.setLayoutX(590);
        Mongolia.setLayoutY(165);
        Button Siam = new Button(Integer.toString(world.Siam.numTroops()));//590,270
        Siam.setOnAction(e -> {
            world.Siam.displayName(Siam);
            Siam.setText(Integer.toString(world.Siam.numTroops()));
            System.out.println(world.getCountry("Siam").name);
        });
        Siam.setLayoutX(590);
        Siam.setLayoutY(270);
        Button Siberia = new Button(Integer.toString(world.Siberia.numTroops()));//540,80
        Siberia.setOnAction(e -> {
            world.Siberia.displayName(Siberia);
            Siberia.setText(Integer.toString(world.Siberia.numTroops()));
            System.out.println(world.getCountry("Siberia").name);
        });
        Siberia.setLayoutX(540);
        Siberia.setLayoutY(80);
        Button Ural = new Button(Integer.toString(world.Ural.numTroops()));//505,110
        Ural.setOnAction(e -> {
            world.Ural.displayName(Ural);
            Ural.setText(Integer.toString(world.Ural.numTroops()));
            System.out.println(world.getCountry("Ural").name);
        });
        Ural.setLayoutX(505);
        Ural.setLayoutY(110);
        Button Yakutsk = new Button(Integer.toString(world.Yakutsk.numTroops()));//600,60
        Yakutsk.setOnAction(e -> {
            world.Yakutsk.displayName(Yakutsk);
            Yakutsk.setText(Integer.toString(world.Yakutsk.numTroops()));
            System.out.println(world.getCountry("Yakutsk").name);
        });
        Yakutsk.setLayoutX(600);
        Yakutsk.setLayoutY(60);
        Button EAustralia = new Button(Integer.toString(world.EAustralia.numTroops()));//675,400
        EAustralia.setOnAction(e -> {
            world.EAustralia.displayName(EAustralia);
            EAustralia.setText(Integer.toString(world.EAustralia.numTroops()));
            System.out.println(world.getCountry("Eastern Australia").name);
        });
        EAustralia.setLayoutX(675);
        EAustralia.setLayoutY(400);
        Button Indonesia = new Button(Integer.toString(world.Indonesia.numTroops()));//610,355
        Indonesia.setOnAction(e -> {
            world.Indonesia.displayName(Indonesia);
            Indonesia.setText(Integer.toString(world.Indonesia.numTroops()));
            System.out.println(world.getCountry("Indonesia").name);
        });
        Indonesia.setLayoutX(610);
        Indonesia.setLayoutY(355);
        Button NewGuinea = new Button(Integer.toString(world.NewGuinea.numTroops()));//670,340
        NewGuinea.setOnAction(e -> {
            world.NewGuinea.displayName(NewGuinea);
            NewGuinea.setText(Integer.toString(world.NewGuinea.numTroops()));
            System.out.println(world.getCountry("New Guinea").name);
        });
        NewGuinea.setLayoutX(670);
        NewGuinea.setLayoutY(340);
        Button WAustralia = new Button(Integer.toString(world.WAustralia.numTroops()));//635,425
        WAustralia.setOnAction(e -> {
            world.WAustralia.displayName(WAustralia);
            WAustralia.setText(Integer.toString(world.WAustralia.numTroops()));
            System.out.println(world.getCountry("Western Australia").name);
        });
        WAustralia.setLayoutX(635);
        WAustralia.setLayoutY(425);
        //End of setting up each country's button
        image = new Image(getClass().getResourceAsStream("resources/Risk_Board.png"));//DO NOT TOUCH. IT WORKS.

        imageView = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bkgd = new Background(imageView);
        Pane pane = new Pane();
        pane.getChildren().addAll(Alaska, Alberta, CentralAmerica, EastUS, Greenland, NWTerritory, Ontario, Quebec,
                WestUS, Argentina, Brazil, Peru, Venezuela, England, Iceland, NEurope, Scandinavia, SEurope, Russia,
                WEurope, Congo, EAfrica, Egypt, Madagascar, NAfrica, SAfrica, Afghanistan, China, India, Irktusk,
                Japan, Kamchatka, MiddleEast, Mongolia, Siam, Siberia, Ural, Yakutsk, EAustralia, Indonesia, NewGuinea,
                WAustralia, exit);

        pane.setBackground(bkgd);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Risk");
        primaryStage.setWidth(760);
        primaryStage.setHeight(530);
        primaryStage.setResizable(false);
        primaryStage.show();
        System.out.println(world.getCountry("Quebec").name);
        MessageBox.show("Take Turns selecting countries. Each player gets 21.", "Instructions");
    }

    public class State {

        final static int kSelecting = 0;
        final static int kReinforcing = 1;
        final static int kPlayer1turn = 2;
        final static int kPlayer2turn = 3;
    }
}

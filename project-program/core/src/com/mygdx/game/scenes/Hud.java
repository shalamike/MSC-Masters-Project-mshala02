package com.mygdx.game.scenes;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PhysicsImp;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer distanceToDam;
    private float planeSpeed;
    private float bombRPM;
    private float altitude;

    Label distanceLabel;
    Label distanceIntLabel;
    Label planeSpeedLabel;
    Label planeIntLabel;
    Label altitudeLabel;
    Label altitudeIntLabel;
    Label bombRPMlabel;
    Label bombRPMIntLabel;


    public Hud(SpriteBatch sb){
        distanceToDam = 1900;
        planeSpeed = 240;
        bombRPM = 300;
        altitude = 20;
        viewport = new FitViewport(PhysicsImp.S_WIDTH, PhysicsImp.S_HEIGHT, new OrthographicCamera());
        stage = new Stage (viewport,  sb);

        Table table = new Table();

        table.top();
        table.setFillParent(true);

        planeSpeedLabel = new Label("Plane Speed", new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        planeIntLabel = new Label(String.format("%03d" , planeSpeed), new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        distanceLabel = new Label("Distance To Dam", new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        distanceIntLabel = new Label(String.format("%05d" , distanceToDam), new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        bombRPMlabel = new Label("Bomb RPM", new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        bombRPMIntLabel = new Label(String.format("%05d" , bombRPM), new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        altitudeLabel = new Label("Altitude", new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        altitudeIntLabel = new Label(String.format("%05d" , altitude), new Label.LabelStyle(new BitmapFont(), Color.CORAL));

        


    }



}

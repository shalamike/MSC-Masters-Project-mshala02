package com.mygdx.game.scenes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PhysicsImp;

public class MenuUI extends ApplicationAdapter implements Input.TextInputListener, Disposable {
    private MyGdxGame app;

    private Viewport viewport;
    private Stage stage;

    //textures for buttons
    private Texture startTexture, rpmTexture, simSpeedTexture, massTexture, radiusTexture, airSpeedTexture;
    private TextureRegion  startTextureRegion, rpmTextureRegion, simSpeedTextureRegion, massTextureRegion, radiusTextureRegion, airSpeedRegion;
    private TextureRegionDrawable startTextureDrawable, rpmTextureDrawable, simSpeedTextureDrawable, massTextureDrawable, radiusTextureDrawable, airSpeedDrawable;
    private ImageButton startButton, rpmButton, simSpeedButton, massButton, radiusButton, airSpeedButton;
    private Skin skin;
    private TextField setRPM, setRadius, setAirSpeed, setSimSpeed;

    public static String rpmOutput, airSpeedOutput, simSpeedOutput, radiusOutput;


    private Label Title;

    public static Boolean massPressed= false, radiusPressed = false, startPressed = false, rpmPressed = false, simSpeedPressed = false;

    OrthographicCamera cam;

    public MenuUI(MyGdxGame app){
        this.app = app;
        cam = new OrthographicCamera();
        viewport = new FitViewport(PhysicsImp.S_WIDTH , PhysicsImp.S_WIDTH, cam);

        //creating the skin for text fields
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        //creating the start button
        startTexture = new Texture(Gdx.files.internal("buttons/StartSim.png"));
        startTextureRegion = new TextureRegion(startTexture);
        startTextureDrawable = new TextureRegionDrawable(startTextureRegion);
        startButton = new ImageButton(startTextureDrawable);
        startButton.getImage().setFillParent(true);
        //setting the response if the start sim button is clicked
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startPressed = true;

            }
        });

        //creating rpm textarea
        setRPM = new TextField("", skin);
        //creating the set RPM button
        rpmTexture = new Texture(Gdx.files.internal("buttons/SetRPM.png"));
        rpmTextureRegion = new TextureRegion(rpmTexture);
        rpmTextureDrawable = new TextureRegionDrawable(rpmTextureRegion);
        rpmButton = new ImageButton(rpmTextureDrawable);
        rpmButton.getImage().setFillParent(true);
        rpmButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
                rpmPressed = true;
            }
        });

        //creating rpm textarea
        setRadius = new TextField("", skin);
        //creating the set RPM button
        radiusTexture = new Texture(Gdx.files.internal("buttons/SetRadius.png"));
        radiusTextureRegion = new TextureRegion(radiusTexture);
        radiusTextureDrawable = new TextureRegionDrawable(radiusTextureRegion);
        radiusButton = new ImageButton(radiusTextureDrawable);
        radiusButton.getImage().setFillParent(true);
        radiusButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
                radiusPressed = true;
                radiusOutput = setRadius.getText();
            }
        });

        stage = new Stage(viewport, app.batch);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.top().center();
        table.left().center();
        table.setFillParent(true);

        table.add(radiusButton).padTop(5);
        table.add(setRadius).padTop(5);
        table.row();
        table.add(rpmButton).padTop(5);
        table.add(setRPM).padTop(5);
        table.row();
        table.add(startButton).padTop(20);


        stage.addActor(table);

        table.setDebug(true);

    }


    @Override
    public void dispose() {

    }

    public void resize(int width, int height){
        viewport.update(width, height);
    }

    public void draw(){
        stage.act();
        stage.draw();
    }

    @Override
    public void input(String text) {

    }

    @Override
    public void canceled() {

    }

//    public Boolean getMassPressed() {
//        return massPressed;
//    }
//
//    public void setMassPressed(Boolean massPressed) {
//        this.massPressed = massPressed;
//    }
//
//    public Boolean getRadiusPressed() {
//        return radiusPressed;
//    }
//
//    public void setRadiusPressed(Boolean radiusPressed) {
//        this.radiusPressed = radiusPressed;
//    }
//
//    public Boolean getStartPressed() {
//        return startPressed;
//    }
//
//    public void setStartPressed(Boolean startPressed) {
//        this.startPressed = startPressed;
//    }
//
//    public Boolean getRpmPressed() {
//        return rpmPressed;
//    }
//
//    public void setRpmPressed(Boolean rpmPressed) {
//        this.rpmPressed = rpmPressed;
//    }
//
//    public Boolean getSimSpeedPressed() {
//        return simSpeedPressed;
//    }
//
//    public void setSimSpeedPressed(Boolean simSpeedPressed) {
//        this.simSpeedPressed = simSpeedPressed;
//    }

}

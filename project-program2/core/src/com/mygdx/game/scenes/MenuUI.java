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
    private Texture startTexture, rpmTexture, simSpeedTexture, massTexture, radiusTexture, planeSpeedTexture;
    private TextureRegion  startTextureRegion, rpmTextureRegion, simSpeedTextureRegion, massTextureRegion, radiusTextureRegion, planeSpeedRegion;
    private TextureRegionDrawable startTextureDrawable, rpmTextureDrawable, simSpeedTextureDrawable, massTextureDrawable, radiusTextureDrawable, planeSpeedDrawable;
    private ImageButton startButton, rpmButton, simSpeedButton, massButton, radiusButton, planeSpeedButton;
    private Skin skin;
    private TextField setRPM, setRadius, setPlaneSpeed, setSimSpeed;

    public static String rpmOutput, airSpeedOutput, simSpeedOutput, radiusOutput, planeSpeedOutput;


    private Label Title;

    public static Boolean massPressed= false, radiusPressed = false, startPressed = false, rpmPressed = false, simSpeedPressed = false, planeSpeedPressed = false;

    OrthographicCamera cam;

    public MenuUI(MyGdxGame app){
        this.app = app;
        cam = new OrthographicCamera();
        viewport = new FitViewport(PhysicsImp.S_WIDTH , PhysicsImp.S_WIDTH, cam);

        //creating the skin for text fields
        skin = new Skin(Gdx.files.internal("assets/ui/uiskin.json"));


        //creating the start button
        startTexture = new Texture(Gdx.files.internal("assets/buttons/StartSim.png"));
        startTextureRegion = new TextureRegion(startTexture);
        startTextureDrawable = new TextureRegionDrawable(startTextureRegion);
        startTextureDrawable.setLeftWidth(50f);
        startTextureDrawable.setRightWidth(50f);
        startButton = new ImageButton(startTextureDrawable);
        startButton.getImage().setFillParent(true);
        //setting the response if the start sim button is clicked
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startPressed = true;
            }
        });

        setPlaneSpeed = new TextField(Integer.toString(PhysicsImp.PLANE_SPEED) , skin);
        //creating the set plane speed button
        planeSpeedTexture = new Texture(Gdx.files.internal("assets/buttons/PlaneSpeed.png"));
        planeSpeedRegion = new TextureRegion(planeSpeedTexture);
        planeSpeedDrawable = new TextureRegionDrawable(planeSpeedRegion);
        planeSpeedButton = new ImageButton(planeSpeedDrawable);
        planeSpeedButton.getImage().setFillParent(true);
        //setting the response if the start sim button is clicked
        planeSpeedButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                planeSpeedPressed = true;
                planeSpeedOutput = setPlaneSpeed.getText();
            }
        });

        //creating rpm textarea
        setRPM = new TextField("", skin);
        //creating the set RPM button
        rpmTexture = new Texture(Gdx.files.internal("assets/buttons/SetRPM.png"));
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


        stage = new Stage(viewport, app.batch);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.top().center();
        table.left().center();
        table.setFillParent(true);


        table.add(rpmButton).padTop(5);
        table.add(setRPM).padTop(5);
        table.row();
        table.add(planeSpeedButton).padTop(5);
        table.add(setPlaneSpeed);
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

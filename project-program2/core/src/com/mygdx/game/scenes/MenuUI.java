package com.mygdx.game.scenes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PhysicsImp;

public class MenuUI extends ApplicationAdapter implements Disposable {
    private MyGdxGame app;

    private Viewport viewport;
    private Stage stage;

    //textures for buttons
    private Texture startTexture, rpmTexture, simSpeedTexture, massTexture, radiusTexture;
    private TextureRegion  startTextureRegion, rpmTextureRegion, simSpeedTextureRegion, massTextureRegion, radiusTextureRegion;
    private TextureRegionDrawable startTextureDrawable, rpmTextureDrawable, simSpeedTextureDrawable, massTextureDrawable, radiusTextureDrawable;
    private ImageButton startButton, rpmButton, simSpeedButton, massButton, radiusButton;

    public static Boolean massPressed, radiusPressed, startPressed = false, rpmPressed, simSpeedPressed;
    OrthographicCamera cam;

    public MenuUI(MyGdxGame app){
        this.app = app;
        cam = new OrthographicCamera();
        viewport = new FitViewport(PhysicsImp.S_WIDTH , PhysicsImp.S_WIDTH, cam);

        startTexture = new Texture(Gdx.files.internal("buttons/StartSim.png"));
        startTextureRegion = new TextureRegion(startTexture);
        startTextureDrawable = new TextureRegionDrawable(startTextureRegion);
        startButton = new ImageButton(startTextureDrawable);
        startButton.getImage().setFillParent(true);
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
//                System.out.println("tapped");
                startPressed = true;
//                System.out.println(startPressed);
            }
        });

        stage = new Stage(viewport, app.batch);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.top().center();
        table.left().center();
        table.setFillParent(true);

        table.add(startButton);


        stage.addActor(table);

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

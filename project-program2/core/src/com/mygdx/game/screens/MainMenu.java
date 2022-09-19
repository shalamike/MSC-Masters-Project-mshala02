package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PhysicsImp;
import com.mygdx.game.scenes.MenuUI;

public class MainMenu implements Screen {

    private final MyGdxGame menu;

    private Viewport viewport;
    private Stage stage;
    private Skin skin;
    private AssetManager assetManager;
    private MenuUI menuUI;
    private Game sim;



    public MainMenu(MyGdxGame menu, Game sim) {
        this.menu = menu;
        this.sim = sim;
        viewport = new FitViewport(PhysicsImp.S_WIDTH, PhysicsImp.S_HEIGHT);
        this.stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        menuUI = new MenuUI(menu);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f,0.6f,0.7f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        menuUI.draw();
        update(delta);
        //stage.act();

    }


    public void update(float delta){
        handleInput(delta);
        stage.act(delta);
    }

    public void handleInput(float delta){
        if (MenuUI.rpmPressed== true){
            String numRegexChecker = "[0-9]+";
            if (MenuUI.rpmOutput.matches(numRegexChecker)){
                int num =Integer.parseInt(MenuUI.rpmOutput);
                if (num >= 0 && num <= 2000){
                    PhysicsImp.BOMB_RPM = num;
                }
                else if ( num > 2000){
                    PhysicsImp.BOMB_RPM = 2000;
                }
                else {
                    PhysicsImp.BOMB_RPM = 0;
                }
            }
            MenuUI.rpmPressed = false;
        }

        if (MenuUI.simSpeedPressed== true){
            String numRegexChecker = "[0-9]+";
            if (MenuUI.simSpeedOutput.matches(numRegexChecker)){
                float num =Integer.parseInt(MenuUI.simSpeedOutput);
                PhysicsImp.UNITSCALE = num * 10;
            }
            MenuUI.simSpeedPressed = false;
        }


        if (MenuUI.planeSpeedPressed == true){
            String numRegexChecker = "[0-9]+";
            if (MenuUI.planeSpeedOutput.matches(numRegexChecker)){
                int num = Integer.parseInt(MenuUI.planeSpeedOutput);
                if (num >=1 && num <= 250){
                    PhysicsImp.PLANE_SPEED = num;
                } else if (num > 250){
                    PhysicsImp.PLANE_SPEED = 250;
                } else if (num < 1){
                    PhysicsImp.PLANE_SPEED = 1;
                }
            }
            MenuUI.planeSpeedPressed = false;
        }

        if (MenuUI.distancePressed== true){
            String numRegexChecker = "[0-9]+";
            if (MenuUI.distanceOutput.matches(numRegexChecker)){
                int num =Integer.parseInt(MenuUI.distanceOutput);
                if (num >= 100 && num <= 168660){
                    PhysicsImp.START_DISTANCE = num;
                }
                else if ( num > 168660){
                    PhysicsImp.START_DISTANCE = 168660;
                }
                else if (num < 100){
                    PhysicsImp.START_DISTANCE = 100;
                }
            }
            MenuUI.distancePressed = false;
        }


        if (MenuUI.startPressed== true){
            menu.dispose();
            System.out.println("start sim");
            sim.setScreen(new SimScreen(menu));
        }

    }




    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        menuUI.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

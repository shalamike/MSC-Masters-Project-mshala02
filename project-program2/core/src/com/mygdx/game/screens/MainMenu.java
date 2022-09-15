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

    private TextButton beginSim;

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
            System.out.println("rpmPressed");
            MenuUI.rpmPressed = false;
        }

        if (MenuUI.radiusPressed== true){
            System.out.println("radiusPressed");
            String numRegexChecker = "[0-9]+";
            if (MenuUI.radiusOutput.matches(numRegexChecker)){
                int num = Integer.parseInt(MenuUI.radiusOutput);
                PhysicsImp.RADIUS = num;
            }
            MenuUI.radiusPressed = false;
        }

        if (MenuUI.planeSpeedPressed == true){
            String numRegexChecker = "[0-9]+";
            if (MenuUI.planeSpeedOutput.matches(numRegexChecker)){
                int num = Integer.parseInt(MenuUI.planeSpeedOutput);
                PhysicsImp.PLANE_SPEED = num;
            }
            MenuUI.planeSpeedPressed = false;
        }

        if (MenuUI.startPressed== true){
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

    }
}

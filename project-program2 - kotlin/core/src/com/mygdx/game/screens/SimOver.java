package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PhysicsImp;

public class SimOver implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game sim;

    public SimOver (Game sim){
        this.sim = sim;
        viewport = new FitViewport(PhysicsImp.S_WIDTH, PhysicsImp.S_HEIGHT, new OrthographicCamera());
        stage = new Stage((viewport) , ((MyGdxGame) sim).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);
        Label successOrFailedLable;

        Label simOverLabel = new Label("SIMULATION OVER", font);
        Label backToMenuLabel = new Label("Click to Start Again", font);
        if (!PhysicsImp.DAM_DESTROYED){
            successOrFailedLable = new Label("Failed To Destroy Dam", font);
        } else{

            successOrFailedLable = new Label("Dam was Successfully Destroyed", font);
        }

        table.add(simOverLabel).expandX();
        table.row();
        table.add(successOrFailedLable).padTop(10f);
        table.row();
        table.add(backToMenuLabel).expandX().padTop(10f);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            sim.setScreen(new MainMenu((MyGdxGame) sim, sim));
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

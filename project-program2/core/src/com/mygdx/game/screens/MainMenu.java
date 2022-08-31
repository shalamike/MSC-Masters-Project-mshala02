package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PhysicsImp;

public class MainMenu extends ScreenAdapter {

    private Stage stage;
    private Viewport viewport;


//    public MainMenu() {
//        super();
//    }
//

    @Override
    public void show() {
//        super.show();
        viewport = new FitViewport(PhysicsImp.S_WIDTH, PhysicsImp.S_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);
    }

//    private TextButton createButton(String name){
//        TextButton button = new TextButton(name, );
//    }

    @Override
    public void render(float delta) {
//        super.render(delta);
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }


}

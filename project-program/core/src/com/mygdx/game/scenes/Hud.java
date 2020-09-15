package com.mygdx.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PhysicsImp;


public class Hud {
    public Stage stage;
    // making a new camera and new viewport specifically for the hud
    // this is to ensure that the hud stays in place while the game moves
    public Viewport viewport;



    private Integer worldTimer;
    private float timeCount;
    private Integer score;
    private static float scale = 1;

    Label distanceLabel;
    Label speedLabel;
    Label bombRPMlabel;
    Label distance;
    Label worldLabel;
    Label marioLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        viewport = new FitViewport(PhysicsImp.S_WIDTH, PhysicsImp.S_HEIGHT, new OrthographicCamera());
        // a stage is kind of like an empty box
        stage = new Stage(viewport, sb);

        // therefore what we need is a table to organise everything inside our empty box
        Table table = new Table();
        //by efault the table will be in the centre of our stage
        table.top();
        // now the table is on top of the stage
        table.setFillParent(true);
        // now the table is the size of the stage

        //time to create the labels
        distanceLabel = new Label(String.format("Distance from Dam (Miles)"), new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        speedLabel = new Label(String.format("Bomb's speed", score), new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        bombRPMlabel = new Label("Bomb RPM", new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        distance = new Label("", new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        marioLabel = new Label("MARIO", new Label.LabelStyle(new BitmapFont(), Color.CORAL));

        distanceLabel.setFontScale(scale);
        speedLabel.setFontScale(scale);
        bombRPMlabel.setFontScale(scale);
        distance.setFontScale(scale);
        worldLabel.setFontScale(scale);
        marioLabel.setFontScale(scale);

//        Group group = new Group();
//        group.addActor(countDownLabel);
//        group.addActor(scoreLabel);
//        group.addActor(timeLabel);
//        group.addActor(levelLabel);
//        group.addActor(worldLabel);
//        group.addActor(marioLabel);
//        group.setScale(1/16f, 1/16f);



        // now time to add the labels to our table
        // expandX expands the label the entire width of our screen. if multiple items use expandX, then the screen will share those equally by default
        // pad Top will set the spacing between the top of the screen and our label by 10px in this case
        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(bombRPMlabel).expandX().padTop(10);
        table.row();
        table.add(speedLabel).expandX();
        table.add(distance).expandX();
        table.add(distanceLabel).expandX();

        stage.addActor(table);

    }
}

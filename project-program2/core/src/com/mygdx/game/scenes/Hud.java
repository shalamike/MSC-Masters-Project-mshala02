package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PhysicsImp;
import com.mygdx.game.tools.Assets;

import org.w3c.dom.Text;


public class Hud implements Disposable {
    public Stage stage;
    // making a new camera and new viewport specifically for the hud
    // this is to ensure that the hud stays in place while the game moves
    public Viewport viewport;

    private AssetManager assetManager;
    private Assets assets;

    private Integer distanceToDam;
    private Integer rpm;
    private Integer speed;
    private static float scale = 1;

    private Label rpmIntLabel;
    private Label rpmTextLabel;
    private Label speedIntLabel;
    private Label speedTextLabel;
    private Label distanceToDamIntLabel;
    private Label distanceToDamTextLabel;



    private Texture startTexture, dropBombTexture, backToMenuTexture;
    private TextureRegion startTextureRegion, dropBombTextureRegion, backToMenuTextureRegion;
    private TextureRegionDrawable startTextureDrawable, dropBombTextureDrawable, backToMenuTextureDrawable;
    private ImageButton startButton, dropButton, backToMenuButton;
    private Skin skin;

    public static boolean isStartPressed;
    public static boolean isDropBombPressed;
    public static boolean isBackToMenuPressed;

    public Hud (SpriteBatch sb){
        distanceToDam = 8435;
        rpm = 720;
        speed = 200;

        viewport = new FitViewport(PhysicsImp.S_WIDTH , PhysicsImp.S_HEIGHT, new OrthographicCamera());
        stage =  new Stage (viewport, sb);
        Gdx.input.setInputProcessor(stage);

        //creating the start button
        startTexture = new Texture(Gdx.files.internal("buttons/Start.png"));
        startTextureRegion = new TextureRegion(startTexture);
        startTextureDrawable = new TextureRegionDrawable(startTextureRegion);
        startButton = new ImageButton(startTextureDrawable);
        startButton.getImage().setFillParent(true);
        //setting the response if the start sim button is clicked
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isStartPressed = true;

            }
        });

        //creating the drop bomb
        dropBombTexture = new Texture(Gdx.files.internal("buttons/DropBomb.png"));
        dropBombTextureRegion = new TextureRegion(dropBombTexture);
        dropBombTextureDrawable = new TextureRegionDrawable(dropBombTextureRegion);
        dropButton = new ImageButton(dropBombTextureDrawable);
        dropButton.getImage().setFillParent(true);
        //setting the response if the start sim button is clicked
        dropButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isDropBombPressed = true;

            }
        });

        //creating the back to menu button
        backToMenuTexture = new Texture(Gdx.files.internal("buttons/Exit.png"));
        backToMenuTextureRegion = new TextureRegion(backToMenuTexture);
        backToMenuTextureDrawable = new TextureRegionDrawable(backToMenuTextureRegion);
        backToMenuButton = new ImageButton(backToMenuTextureDrawable);
        backToMenuButton.getImage().setFillParent(true);
        //setting the response if the start sim button is clicked
        backToMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isBackToMenuPressed = true;

            }
        });


        createTable();

    }

    private void createTable(){
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        rpmTextLabel = new Label("Bomb's Rotation Speed (RPM)", new Label.LabelStyle(new BitmapFont(), Color.CORAL)) ;
        rpmIntLabel = new Label(String.format("%06d", rpm), new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        speedTextLabel = new Label("current speed of bomb (MPH)", new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        speedIntLabel = new Label (String.format("%03d", speed), new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        distanceToDamTextLabel = new Label("Distance to dam (miles)", new Label.LabelStyle(new BitmapFont(), Color.CORAL));
        distanceToDamIntLabel = new Label(String.format("%03d", distanceToDam), new Label.LabelStyle(new BitmapFont(), Color.CORAL));

        table.add(speedTextLabel).expandX().padTop(10);
        table.add(rpmTextLabel).expandX().padTop(10);
        table.add(distanceToDamTextLabel).expandX().padTop(10);
        table.row();
        table.add(speedIntLabel).expandX();
        table.add(rpmIntLabel).expandX();
        table.add(distanceToDamIntLabel).expandX();
        table.row();
        table.add(startButton).size(100,50).padTop(5);
        table.add(dropButton).size(100,50).padTop(5);
        table.add(backToMenuButton).size(100,50).padTop(5);
        stage.addActor(table);

        table.setDebug(true);//for seeing the lines in the table of the hud
    }

    public void calcDistance(float bombXCord ){
        distanceToDam = 8434 - (int)bombXCord;
        distanceToDamIntLabel.setText(String.format("%04d", distanceToDam));

    }

    public void calcSpeed (float bombSpeed){
        speed = (int)bombSpeed;
        speedIntLabel.setText(String.format("%03d", speed));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}


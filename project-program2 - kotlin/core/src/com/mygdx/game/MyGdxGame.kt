package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.screens.MainMenu

class MyGdxGame : Game() {
    @JvmField
	var batch: SpriteBatch? = null
    private val assetManager: AssetManager? = null
    var img: Texture? = null
    override fun create() {
        batch = SpriteBatch()
        //img = new Texture("badlogic.jpg");
        setScreen(MainMenu(this, this))
        println("windows open")
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        batch!!.dispose()
    }

    companion object {
        var SCREENS = 1
    }
}
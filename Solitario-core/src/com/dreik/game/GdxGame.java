
/*
 * @Author Juan Manuel Lozano
 * Class which manage the game.
 * 
 */

package com.dreik.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.dreik.hud.PlayerHud;

public class GdxGame extends ApplicationAdapter {
	
	public static GdxGame instance = new GdxGame();
	
	public static Senku senku;
	public static XMLHistoric xmlHistoric; 
	private Drawable ballImage;
	
	public static final int SPACING = 80;
	public static final int globalX = 130;
	public static final int globalY = 20;
	public Stage stage;
	PlayerHud playerHud;
	
	public Ball selectedBall;
	public Ball newPosition;

	
	@Override
	public void create () {
		initialize();
		updateBoard();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		stage.act();
	}
	
	private void initialize() {
		xmlHistoric = new XMLHistoric();
		Texture texBall = new Texture("ball.png");
		texBall.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		ballImage = new TextureRegionDrawable(new TextureRegion(texBall));
		senku = new Senku();
		stage = new Stage(new StretchViewport(800, 600));
		playerHud = new PlayerHud(stage);
		playerHud.load();
		Gdx.input.setInputProcessor(stage);
		
	}
	
	public void updateBoard() {
		int count = 0;
		for (int y = 0; y < senku.getBoard().length; y++) {
			for (int x = 0; x < senku.getBoard().length; x++) {
				if(senku.getBoard()[x][y].equals(" *")) {
					stage.addActor(new Ball(ballImage, x * SPACING + globalX, y * SPACING + globalY));
					count++;
				}


				else if (senku.getBoard()[x][y].equals(" .")) 
					stage.addActor(new Ball(ballImage, x * SPACING + globalX, y * SPACING + globalY, false));
			}
		}
		playerHud.load();
		if(count == 1)
			finish();
	}
	
	public void finish() {
		System.out.println("Juego finalizado");
	}
	
	public void removeBoard() {
		stage.clear();
	}


}

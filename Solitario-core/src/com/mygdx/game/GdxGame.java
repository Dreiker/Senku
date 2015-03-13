package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GdxGame extends ApplicationAdapter {
	
	public static GdxGame instance = new GdxGame();
	
	public static Solitario solitario;
	private Drawable ballImage;
	
	public static final int SPACING = 80;
	public static final int globalX = 130;
	public static final int globalY = 20;
	public Stage stage;
	
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
		ballImage = new TextureRegionDrawable(new TextureRegion(new Texture("ball.png")));
		solitario = new Solitario();
		stage = new Stage(new StretchViewport(800, 600));
		Gdx.input.setInputProcessor(stage);
		
	}
	
	public void updateBoard() {
		int count = 0;
		for (int y = 0; y < solitario.getBoard().length; y++) {
			for (int x = 0; x < solitario.getBoard().length; x++) {
				if(solitario.getBoard()[x][y].equals(" *")) {
					stage.addActor(new Ball(ballImage, x * SPACING + globalX, y * SPACING + globalY));
					count++;
				}


				else if (solitario.getBoard()[x][y].equals(" .")) 
					stage.addActor(new Ball(ballImage, x * SPACING + globalX, y * SPACING + globalY, false));
			}
		}
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

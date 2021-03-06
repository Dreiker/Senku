/*
 *  @Juan Manuel Lozano
 *  Load the user interface to allow the user to interact with
 *  the game.
 *  
 */

package com.dreik.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.dreik.game.GdxGame;

public class PlayerHud {
	Stage stage;
	
	public PlayerHud(Stage stage) {
		this.stage = stage;
	}
	
	public void load() {
		Texture texButton = new Texture("button.png");
		texButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		ImageButton button = new ImageButton(new TextureRegionDrawable (
							 new TextureRegion(texButton)));
		button.setPosition(10, 20);
		button.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GdxGame.senku.back();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
			
		
		stage.addActor(button);
		
		Texture texButton2 = new Texture("button.png");
		texButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		ImageButton button2 = new ImageButton(new TextureRegionDrawable (
							 new TextureRegion(texButton2)));
		button2.setPosition(520, 20);
		stage.addActor(button2);
		
	}
	
}

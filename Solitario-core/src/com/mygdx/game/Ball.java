package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Ball extends ImageButton{
	private Ball ball = this;
	private TextureRegionDrawable tex;
	private int tableX;
	private int tableY;
	private boolean filled = true;
	
	public Ball(Drawable tex, int x, int y) {
		super(tex);
		this.tex = (TextureRegionDrawable) tex;
		
		setX(x);
		setY(y);
		this.tableX = x / GdxGame.SPACING;
		this.tableY = y / GdxGame.SPACING;
		setSize(50, 70);
		createListener();
		
	}
	
	public Ball(Drawable tex, int x, int y, boolean filled) {
		super(tex);
		this.tex = (TextureRegionDrawable) tex;
		this.filled = filled;
		setX(x);
		setY(y);
		this.tableX = x / GdxGame.SPACING;
		this.tableY = y / GdxGame.SPACING;
		setSize(50, 70);
		createListener();
		
		if(!filled)
			setColor(0.2f, 0.2f, 0.2f, 1);
		
	}
	
	private void createListener() {
		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(GdxGame.instance.selectedBall == null)
					GdxGame.instance.selectedBall = ball;
				else if(GdxGame.instance.newPosition == null) {
					GdxGame.instance.newPosition = ball;
					GdxGame.solitario.moveToPosition(
							GdxGame.instance.selectedBall.getTableX() - 1,
							GdxGame.instance.selectedBall.getTableY(),
							GdxGame.instance.newPosition.getTableX() - 1,
							GdxGame.instance.newPosition.getTableY());

					GdxGame.instance.selectedBall = null;
					GdxGame.instance.newPosition = null;
					GdxGame.instance.removeBoard();
					GdxGame.instance.updateBoard();
				}

			}
		});
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(tex.getRegion(), getX(), getY(), getOriginX(), getOriginY(), getWidth(),
				getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	@Override
	public void act(float delta) {
		if(!filled) 
			setColor(0.2f, 0.2f, 0.2f, 1);
		else
			setColor(1, 1, 1, 1);

	}

	public int getTableX() {
		return tableX;
	}

	public void setTableX(int tableX) {
		this.tableX = tableX;
		setX(tableX * GdxGame.SPACING);
	}

	public int getTableY() {
		return tableY;
	}

	public void setTableY(int tableY) {
		this.tableY = tableY;
		setY(tableY * GdxGame.SPACING);
	}
	
//	public void Move(int x, int y, int newX, int newY) {
//		GdxGame.instance.solitario.moveBall(x, y, newX, newY);
//	}

}

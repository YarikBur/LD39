package com.sgstudio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.main.Main;
import com.sgstudio.game.MyGame;
import com.sgstudio.menu.Menu;
import com.sgstudio.game.ground.Background;

public class Defeat implements Screen{
	private Main main;
	private SpriteBatch batch;
	private Sprite bg;
	private Texture button1;
	private Texture button2;
	private Texture button3;
	private Texture lose;
	private boolean Moved = false;
	private boolean Pressed = false;
	private boolean Play = true;
	private OrthographicCamera camera;
	
	public Defeat(Main main) {
		this.main = main;
	}

	@Override
	public void show() {
		batch = main.getBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		camera.update();
		bg = new Sprite(new Texture("atlas/bg.png"));
		button1 = new Texture("pashasimages/button1.png");
		button2 = new Texture("pashasimages/button2.png");
		button3 = new Texture("pashasimages/button3.png");
		lose = new Texture("pashasimages/lose.png");
		Gdx.input.setInputProcessor(new InputProcessor(){

			@Override
			public boolean keyDown(int keycode) { return false; }

			@Override
			public boolean keyUp(int keycode) { return false; }

			@Override
			public boolean keyTyped(char character) { return false; }

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				if(((Gdx.input.getX() <= button1.getWidth() + Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2)&&
					(Gdx.input.getX() >= Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2)) &&
					
					((Gdx.input.getY() <= button1.getHeight() + Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2))&&
					(Gdx.input.getY() >= Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2)){
					Pressed = true;
				} else { 
					Pressed = false;
				}
			return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				if(Moved && Pressed){
					main.setScreen(main.menu);
				} else {
					Play = !Play;
				}
				Pressed = false;
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				if(((Gdx.input.getX() <= button1.getWidth() + Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2)&&
					(Gdx.input.getX() >= Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2)) &&
					
					((Gdx.input.getY() <= button1.getHeight() + Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2))&&
					(Gdx.input.getY() >= Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2)){
					Moved = true;
				} else {
					Moved = false;
				}
			return false;
			}

			@Override
			public boolean scrolled(int amount) { return false; }
			
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();		
		batch.draw(bg, 0, 0);
		
		batch.draw(lose, 
				Gdx.graphics.getWidth() / 2 - lose.getWidth() / 2, 
				Gdx.graphics.getHeight() / 2 + 150 - lose.getHeight() / 2, 
				lose.getWidth(), 
				lose.getHeight() );
		
		if(!Moved){
			batch.draw(button1,
					Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2, 
					Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2,
					button1.getWidth(),
					button1.getHeight());
		} else if (Pressed && Moved) {
			batch.draw(button2,
					Gdx.graphics.getWidth() / 2 - button2.getWidth() / 2, 
					Gdx.graphics.getHeight() / 2 - button2.getHeight() / 2,
					button2.getWidth(),
					button2.getHeight());
		} else {
			batch.draw(button3,
					Gdx.graphics.getWidth() / 2 - button3.getWidth() / 2, 
					Gdx.graphics.getHeight() / 2 - button3.getHeight() / 2,
					button3.getWidth(),
					button3.getHeight());
		}
		
		if(Gdx.input.isButtonPressed(0)){
			  
			if (((Gdx.input.getX() <= button1.getWidth() + Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2)&&
			(Gdx.input.getX() >= Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2)) &&
					
			((Gdx.input.getY() <= button1.getHeight() + Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2))&&
			(Gdx.input.getY() >= Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2)){
				main.setScreen(main.menu);
			}
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void dispose() {
	}
}

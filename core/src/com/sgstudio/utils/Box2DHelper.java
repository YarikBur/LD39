package com.sgstudio.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.models.Chair;

public class Box2DHelper {

	final static public float PIXELS_TO_METERS = 100f;

	public static Body makeBoxAroundSprite(World world, Sprite sprite) {
		return makeBoxAroundSprite(world, sprite, null);
	}
	
	public static Body makeBoxAroundSprite(World world, Sprite sprite, String userData) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(sprite.getX() / PIXELS_TO_METERS, sprite.getY() / PIXELS_TO_METERS);

		Body body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.getHeight() / 2 / PIXELS_TO_METERS);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.90f;
		fixtureDef.restitution = 0.1f;

		body.createFixture(fixtureDef);
		shape.dispose();
		
		if (userData != null) body.getFixtureList().get(0).setUserData(userData);
		
		return body;
	}
	
	public static Body makeBoxAroundSpriteStatic(World world, Sprite sprite, Object userData) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(sprite.getX() / PIXELS_TO_METERS, sprite.getY() / PIXELS_TO_METERS);

		Body body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.getHeight() / 2 / PIXELS_TO_METERS);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.90f;
		fixtureDef.restitution = 0.1f;

		body.createFixture(fixtureDef);
		shape.dispose();
		
		if (userData != null) body.getFixtureList().get(0).setUserData(userData);
		
		return body;
	}

	public static Body makeBox(World world, Vector2 size, Vector2 pos, Object userData) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;

		Body body = world.createBody(bodyDef);

		
		PolygonShape shape = new PolygonShape();
		if (pos == null) { 
			shape.setAsBox(size.x / PIXELS_TO_METERS, size.y / PIXELS_TO_METERS);
		} else { 
			pos.x = pos.x / PIXELS_TO_METERS;
			pos.y = pos.y /PIXELS_TO_METERS;			
			shape.setAsBox(size.x / PIXELS_TO_METERS, size.y / PIXELS_TO_METERS, pos, 0);
		}

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;

		body.createFixture(fixtureDef);
		shape.dispose();
		
		if (userData != null) body.getFixtureList().get(0).setUserData(userData);

		return body;
	}
	
	public static void addShapeBox(Body body, Vector2 size, Vector2 pos) {
		addShapeBox(body, size, pos, 10f, null, false);
	}
	
	public static Body makeBox(World world, Vector2 size, String UserData) {
		return makeBox(world, size, null, UserData);
	}

	public static Body makeCustomShape(World world, PolygonShape shape) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;

		Body body = world.createBody(bodyDef);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;

		body.createFixture(fixtureDef);
		shape.dispose();

		return body;
	}

	public static void setTransform(Body body, int x, int y, float f) {
		body.setTransform(new Vector2(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS), f);
	}

	public static Vector2 getPosition(Body body) {
		return new Vector2(body.getPosition().x * PIXELS_TO_METERS, body.getPosition().y * PIXELS_TO_METERS);
	}

	public static Body makeCircleAroundSprite(World world, Sprite sprite, String userData) {
		BodyDef bodyDef = new BodyDef();
		
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(sprite.getX() / PIXELS_TO_METERS, sprite.getY() / PIXELS_TO_METERS);

		Body body = world.createBody(bodyDef);

		CircleShape shape = new CircleShape();
		shape.setRadius(sprite.getWidth() / 2 / PIXELS_TO_METERS);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.90f;
		fixtureDef.restitution = 0.1f;
		
		body.createFixture(fixtureDef);
		shape.dispose();

		if (userData != null) body.getFixtureList().get(0).setUserData(userData);
		
		return body;
	}

	public static Body makeCircle(World world, Vector2 size, Vector2 pos, Object userData) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;

		Body body = world.createBody(bodyDef);
		
		CircleShape shape = new CircleShape();
		pos.x = pos.x / PIXELS_TO_METERS;
		pos.y = pos.y /PIXELS_TO_METERS;	
		shape.setPosition(pos);
		shape.setRadius(size.x / 2 / PIXELS_TO_METERS);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;

		body.createFixture(fixtureDef);
		shape.dispose();
		
		if (userData != null) body.getFixtureList().get(0).setUserData(userData);

		return body;
	}

	public static void addShapeBox(Body body, Vector2 size, Vector2 pos, float i, Object userData, boolean filter) {
		PolygonShape shape = new PolygonShape();
		pos.x = pos.x / PIXELS_TO_METERS;
		pos.y = pos.y /PIXELS_TO_METERS;			
		shape.setAsBox(size.x / PIXELS_TO_METERS, size.y / PIXELS_TO_METERS, pos, 0);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = i;
		if (filter) {
			fixtureDef.filter.maskBits = 0;
		}

		Fixture fix = body.createFixture(fixtureDef);
		fix.setUserData(userData);
		shape.dispose();
	}
}

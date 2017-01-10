package slugs;
import java.util.ArrayList;
import processing.core.*;
import shiffman.box2d.*;

public class Slugs extends PApplet
{
	public static void main(String[] args)
	{
		PApplet.main("slugs.Slugs");
	}
	
	public Box2DProcessing world;
	public int gameState;
	public Terrain map;
	ArrayList<WeaponBox> crates;
	
	public void settings()
	{
		size(1280, 720);
	}
	
	public void setup()
	{
		world = new Box2DProcessing(this);
		world.createWorld();
		map = new Terrain(this);
		crates = new ArrayList<WeaponBox>();
		initScreen();
	}
	
	public void draw()
	{
		// current state of game
		switch (gameState)
		{
			case 0:
				initScreen();
				break;
			case 1:
				gameScreen();
				world.step();
				break;
			case 2:
				endScreen();
				break;
			case 3:
				background(255);
				world.step();
				testScreen();
				break;
		}
	}
	
	public void initScreen()
	{
		gameState = 0;
		background(0);
		textAlign(CENTER);
		text("SLUGS", width/2, height/3);
		text("Click to begin", width/2, height/2);
		if (mousePressed)
		{
			gameState = 3;
		}
	}
	
	public void gameScreen()
	{
		background(0);
	}
	
	public void endScreen()
	{
	}
	
	public void testScreen()
	{
		if (mousePressed)
		{
			WeaponBox c = new WeaponBox(this, mouseX, mouseY);
			crates.add(c);
		}
		for(WeaponBox c: crates)
		{
			c.display();
		}
		map.display();
	}
}
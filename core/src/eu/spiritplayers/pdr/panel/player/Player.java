package eu.spiritplayers.pdr.panel.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eu.spiritplayers.pdr.panel.ClickBox;
import eu.spiritplayers.pdr.panel.GamePanel;
import eu.spiritplayers.pdr.panel.Location;
import eu.spiritplayers.pdr.panel.dice.Dice;
import eu.spiritplayers.pdr.panel.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the GamePanel
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public abstract class Player
{
	private GamePanel panel;
	private int order;
	private String name;

	private Location location;
	private int points, health, money;
	private List<Item> items;

	private Texture[] sprites;
	private Dice dice;

	public Player(GamePanel panel, String name)
	{
		this.panel = panel;

		this.order = -1;
		this.name = name;

		this.location = Location.SOUTH;
		this.points = 0;
		this.health = 5;
		this.money = 2;
		this.items = new ArrayList<>();

		this.sprites = new Texture[3];
		this.sprites[0] = new Texture("player_back_idle.png");
		this.sprites[1] = new Texture("player_back_fight.png");
		this.sprites[2] = new Texture("player_front.png");

		this.dice = null;
	}

	public abstract void sendMessage(String message);

	public void render(SpriteBatch batch)
	{
		ClickBox box = getClickBox();

		batch.draw(getSprite(), box.getX(), box.getY(), box.getWidth(), box.getHeight());

		if(getDice() != null)
			getDice().render(batch);
	}

	public Texture getSprite()
	{
		switch(location)
		{
			case SOUTH:
				return sprites[0];

			case FIGHT:
				return sprites[1];

			case NORTH:
				return sprites[2];
		}
		return null;
	}

	public ClickBox getClickBox()
	{
		float spriteRatio = (float)getSprite().getWidth() / (float)getSprite().getHeight();

		int width = getPanel().getWidth() / 10;
		int height = (int)(1 / spriteRatio * width);
		int x = this.location.getX(panel) - width / 2;
		int y = this.location.getY(panel) - height / 2;

		List<Player> players = this.location.getPlayersHere(getPanel());

		int order = 1;

		for(Player player : players)
			if(player != this && player.getOrder() < this.getOrder())
				order++;


		if(players.size() == 2)
		{
			if(order == 1)
				x -= width * 3 / 4;
			else
				x += width * 3 / 4;
		}
		else if(players.size() == 3 && order != 2)
		{
			if(order == 1)
				x -= width * 3 / 2;
			else
				x += width * 3 / 2;
		}

		return new ClickBox(x, y, width, height)
		{
			@Override
			public void click()
			{

			}
		};
	}

	public int getOrder()
	{
		return this.order;
	}

	public void setOrder(int order) { this.order = order;}

	public String getName()
	{
		return this.name;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public GamePanel getPanel()
	{
		return panel;
	}

	public int getPoints()
	{
		return points;
	}

	public void setPoints(int points)
	{
		this.points = points;
	}

	public void addRoundPoints()
	{
		if(location == null)
			return;

		this.points += this.location.getPointsPerRound();
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public int getMoney()
	{
		return this.money;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public List<Item> getItems()
	{
		return items;
	}

	public Dice getDice()
	{
		return dice;
	}

	public void setDice(Dice playerDice)
	{
		this.dice = playerDice;
	}


}

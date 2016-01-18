package eu.spiritplayers.pdr;

import eu.spiritplayers.pdr.player.Player;
import eu.spiritplayers.pdr.state.State;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public abstract class Game
{
	private PierreDuRavage app;

	private GameType type;
	private State state;
	private int minimumPlayers, maximumPlayers;

	public Game(PierreDuRavage app, GameType type, int minimumPlayers, int maximumPlayers)
	{
		this.app = app;

		this.type = type;
		this.minimumPlayers = minimumPlayers;
		this.maximumPlayers = maximumPlayers;

		this.state = type.getBaseState(this);
	}

	public abstract void start();

	public boolean join(Player player)
	{
		return getState().join(player);
	}

	public void leave(Player player)
	{
		getState().leave(player);
	}

	public void broadcast(String message)
	{
		for(Player player : getPlayers())
			player.sendMessage(message);
	}

	public List<Player> getPlayers()
	{
		if(type == GameType.SOLO || type == GameType.HOSTED_MULTI)
			return getApp().getPanel().getPlayers();

		return new ArrayList<>();
	}

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public PierreDuRavage getApp()
	{
		return app;
	}

	public GameType getType()
	{
		return type;
	}

	public int getMinimumPlayers()
	{
		return minimumPlayers;
	}

	public int getMaximumPlayers()
	{
		return maximumPlayers;
	}
}

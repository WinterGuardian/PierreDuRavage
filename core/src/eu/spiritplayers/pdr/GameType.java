package eu.spiritplayers.pdr;

import eu.spiritplayers.pdr.state.*;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public enum GameType
{
	DEMO()
	{
		public State getBaseState(Game game)
		{
			return new IdleState(game);
		}
	},

	SOLO()
	{
		public State getBaseState(Game game)
		{
			return new StandbyState(game, new InitState(game));
		}
	},

	HOSTED_MULTI()
	{
		public State getBaseState(Game game)
		{
			return new StandbyState(game, new WaitingState(game, 15, new InitState(game)));
		}
	},

	JOINED_MULTI()
	{
		public State getBaseState(Game game)
		{
			return null;
		}
	},
	;

	public abstract State getBaseState(Game game);
}

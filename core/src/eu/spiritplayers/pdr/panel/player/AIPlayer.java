package eu.spiritplayers.pdr.panel.player;

import eu.spiritplayers.pdr.panel.GamePanel;
import me.winterguardian.selector.AntiRecursiveRandomSelector;

import java.util.Arrays;

/**
 * Player class controlled by an AI
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class AIPlayer extends Player
{
	private static AntiRecursiveRandomSelector<String> NAMES = new AntiRecursiveRandomSelector<>(Arrays.asList("Jean", "Christophe", "Simon", "Pierre", "Philippe", "Claude", "Claire", "Annie", "Laurence", "Marie", "Anaïs", "Amélia", "John", "Bob"));

	public AIPlayer(GamePanel panel)
	{
		super(panel, "IA_" + NAMES.next());
	}

	@Override
	public void sendMessage(String message)
	{

	}
}

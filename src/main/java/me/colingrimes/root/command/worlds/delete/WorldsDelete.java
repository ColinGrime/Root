package me.colingrimes.root.command.worlds.delete;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.bukkit.Worlds;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;

import javax.annotation.Nonnull;

public class WorldsDelete implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		String name = args.getFirst();
		if (!Worlds.exists(name)) {
			sender.message(Text.color("&cWorld doesn't exist."));
			return;
		}

		Worlds.delete(name);
		sender.message(Text.color("&aDeleting the world!"));
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setArgumentsRequired(1);
		properties.setPlayerRequired(true);
	}
}

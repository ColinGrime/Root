package me.colingrimes.root.command.worlds;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.message.Message;
import me.colingrimes.midnight.util.bukkit.Worlds;
import me.colingrimes.root.Root;

import javax.annotation.Nonnull;
import java.util.List;

public class WorldsCommand implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		List<String> worlds = Worlds.all();
		Message.of(List.of("&6Loaded Worlds &7(&f{amount}&7):", " &8- &a{worlds}"))
				.replace("{amount}", worlds.size())
				.replace("{worlds}", String.join("&f, &a", worlds))
				.send(sender);
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setPlayerRequired(true);
	}
}

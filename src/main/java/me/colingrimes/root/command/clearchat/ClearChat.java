package me.colingrimes.root.command.clearchat;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.Common;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

public class ClearChat implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		IntStream.range(0, 20).forEach(__ -> sender.message(""));
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setAliases("cc");
	}
}

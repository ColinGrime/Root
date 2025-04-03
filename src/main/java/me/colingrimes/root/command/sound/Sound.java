package me.colingrimes.root.command.sound;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.bukkit.Players;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Registry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class Sound implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		if (args.isEmpty()) {
			sender.message(Text.color("&cNo sound exists with that name."));
			return;
		}

		Optional<org.bukkit.Sound> sound = Registry.SOUNDS.stream().filter(s -> s.name().toUpperCase().replace(".", "_").equalsIgnoreCase(args.get(0))).findAny();
		if (sound.isEmpty()) {
			sender.message(Text.color("&cNo sound exists with that name."));
			return;
		}

		String soundName = sound.get().name().toUpperCase().replace(".", "_");
		Component message = Component.text(soundName, NamedTextColor.GREEN)
				.hoverEvent(HoverEvent.showText(Component.text("Copy Sound", NamedTextColor.GREEN)))
				.clickEvent(ClickEvent.copyToClipboard("Sound." + soundName));
		sender.player().sendMessage(message);
		Players.sound(sender.player(), sound.get());
	}

	@Nullable
	@Override
	public List<String> tabComplete(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		List<String> sounds = Registry.SOUNDS.stream().map(s -> s.name().toUpperCase().replace(".", "_")).toList();
		return args.size() == 1 ? sounds.stream().filter(s -> s.contains(args.getFirst())).toList() : sounds;
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setAliases("sounds");
		properties.setPlayerRequired(true);
	}
}

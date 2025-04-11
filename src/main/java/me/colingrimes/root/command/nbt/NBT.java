package me.colingrimes.root.command.nbt;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.Common;
import me.colingrimes.midnight.util.bukkit.Players;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.List;

public class NBT implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		ItemStack item = sender.player().getInventory().getItemInMainHand();
		if (item.getItemMeta() == null || item.getItemMeta().getPersistentDataContainer().getKeys().isEmpty()) {
			sender.message(Text.color("&cThis item has no NBT data set."));
			return;
		} else {
			Players.command(sender.player(), "/clearchat");
		}

		Common.broadcast(Text.color("&8&m---------------------------------------------------"));
		PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
		List<NamespacedKey> keys = container.getKeys().stream().toList();
		int size = keys.size();
		for (int i=0; i<size; i++) {
			NamespacedKey key = keys.get(i);
			Object value = null;
			if (container.has(key, PersistentDataType.STRING)) {
				value = container.get(key, PersistentDataType.STRING);
			} else if (container.has(key, PersistentDataType.INTEGER)) {
				value = container.get(key, PersistentDataType.INTEGER);
			} else if (container.has(key, PersistentDataType.DOUBLE)) {
				value = container.get(key, PersistentDataType.DOUBLE);
			} else if (container.has(key, PersistentDataType.BOOLEAN)) {
				value = container.get(key, PersistentDataType.BOOLEAN);
			}
			sender.message(Text.color("&7Key: &e" + (args.isEmpty() ? key.getKey() : key.asString())));
			sender.message(Text.color("&7Value: &e" + value));
			if (i != size - 1) {
				sender.message("");
			}
		}
		Common.broadcast(Text.color("&8&m---------------------------------------------------"));
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setPlayerRequired(true);
	}
}

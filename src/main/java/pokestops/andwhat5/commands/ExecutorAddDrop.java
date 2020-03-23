package pokestops.andwhat5.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.annotation.NonnullByDefault;
import pokestops.andwhat5.PokeStops;
import pokestops.andwhat5.config.ConfigStruc;
import pokestops.andwhat5.config.ItemStruc;
import pokestops.andwhat5.config.PokeStopConfig;
import pokestops.andwhat5.enums.EnumPokeStopType;

import java.io.IOException;
import java.util.List;

import static pokestops.andwhat5.config.Utilities.tierContains;

/**
 * PixelmonEnv Created by AnDwHaT5.
 */
@NonnullByDefault
public class ExecutorAddDrop implements CommandExecutor {

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		ItemType itemType = args.<ItemType>getOne("item").get();
		EnumPokeStopType tier = args.<EnumPokeStopType>getOne("tier").get();
		int rarity = args.<Integer>getOne("rarity").get();
		if (rarity<1) {
			throw new CommandException(Text.of(TextColors.LIGHT_PURPLE, "[PokeStops] ", TextColors.AQUA,
											   "rarity should be greater then 0."));
		}
		if (tierContains(tier, itemType.getId())) {
			throw new CommandException(Text.of(TextColors.LIGHT_PURPLE, "[PokeStops] ", TextColors.AQUA, "The item",
											   itemType, " is already in the ", tier, "PokeStops item pool!"));
		}
		List<ItemStruc> list;
		switch (tier) {
			case tier1:
				list = ConfigStruc.gcon.tier1;
				break;
			case tier2:
				list = ConfigStruc.gcon.tier1;
				break;
			case tier3:
				list = ConfigStruc.gcon.tier1;
				break;
			default:
				throw new CommandException(Text.of(TextColors.LIGHT_PURPLE, "[PokeStops] "
						, "This tier is not a valid tier to add items to."));
		}
		list.add(new ItemStruc(itemType.getId(), rarity));
		try {
			PokeStopConfig.save();
		} catch (IOException e) {
			PokeStops.getLogger().error("Failed to save items.", e);
			throw new CommandException(Text.of(TextColors.LIGHT_PURPLE, "[PokeStops] ", TextColors.RED, "Failed to save"), e);
		}
		src.sendMessage(Text.of(TextColors.LIGHT_PURPLE, "[PokeStops] ", TextColors.AQUA, "Successfully added ",
								itemType, " to the ", tier, " PokeStops item pool!"));
		return CommandResult.success();
	}
}
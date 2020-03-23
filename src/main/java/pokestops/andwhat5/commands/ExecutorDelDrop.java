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
import pokestops.andwhat5.config.Utilities;
import pokestops.andwhat5.enums.EnumPokeStopType;

@NonnullByDefault
public class ExecutorDelDrop implements CommandExecutor {

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		EnumPokeStopType tier = args.<EnumPokeStopType>getOne("tier").get();
		ItemType itemType = args.<ItemType>getOne("item").get();
		switch (tier) {
			case tier1:
			case tier2:
			case tier3:
				break;
			default:
				throw new CommandException(Text.of(TextColors.LIGHT_PURPLE, "[PokeStops] ",
												   TextColors.AQUA, "You have specified an invalid or nonexistant tier."));
		}
		if (Utilities.tierContains(tier, itemType.getId())) {
			Utilities.removeFromTier(tier, itemType.getId());
			src.sendMessage(Text.of(TextColors.LIGHT_PURPLE, "[PokeStops] ", TextColors.AQUA,
									"Successfully removed ", TextColors.LIGHT_PURPLE, itemType, TextColors.AQUA, " from the item pool."));
		} else {
			throw new CommandException(Text.of(TextColors.LIGHT_PURPLE, "[PokeStops] ",
											   TextColors.AQUA, "This item is not in the ", tier, " item pool."));
		}
		return CommandResult.success();
	}
}
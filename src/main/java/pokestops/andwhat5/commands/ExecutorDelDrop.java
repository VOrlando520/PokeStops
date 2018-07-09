package pokestops.andwhat5.commands;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import pokestops.andwhat5.config.Utilities;
import pokestops.andwhat5.enums.EnumPokeStopType;

public class ExecutorDelDrop implements CommandExecutor
{

	@Override
	public CommandResult execute(CommandSource src, CommandContext args)
			throws org.spongepowered.api.command.CommandException
	{

		if (args.getOne("tier").get().equals(EnumPokeStopType.tier1))
		{
			if (Utilities.tierContains(EnumPokeStopType.tier1, args.getOne("item").get().toString()))
			{
				Utilities.removeFromTier(EnumPokeStopType.tier1, args.getOne("item").get().toString());
				((EntityPlayerMP) src).sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
						+ TextFormatting.AQUA + "Successfully removed " + TextFormatting.LIGHT_PURPLE
						+ args.getOne("item").get().toString() + TextFormatting.AQUA + " from the item pool."));
			} else
			{
				((EntityPlayerMP) src).sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
						+ TextFormatting.AQUA + "This item is not in the tier 1 item pool."));
			}
		} else if (args.getOne("tier").get().equals(EnumPokeStopType.tier2))
		{
			if (Utilities.tierContains(EnumPokeStopType.tier2, args.getOne("item").get().toString()))
			{
				Utilities.removeFromTier(EnumPokeStopType.tier2, args.getOne("item").get().toString());
				((EntityPlayerMP) src).sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
						+ TextFormatting.AQUA + "Successfully removed " + TextFormatting.LIGHT_PURPLE
						+ args.getOne("item").get().toString() + TextFormatting.AQUA + " from the item pool."));
			} else
			{
				((EntityPlayerMP) src).sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
						+ TextFormatting.AQUA + "This item is not in the tier 2 item pool."));
			}
		} else if (args.getOne("tier").get().equals(EnumPokeStopType.tier3))
		{
			if (Utilities.tierContains(EnumPokeStopType.tier3, args.getOne("item").get().toString()))
			{
				Utilities.removeFromTier(EnumPokeStopType.tier3, args.getOne("item").get().toString());
				((EntityPlayerMP) src).sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
						+ TextFormatting.AQUA + "Successfully removed " + TextFormatting.LIGHT_PURPLE
						+ args.getOne("item").get().toString() + TextFormatting.AQUA + " from the item pool."));
			} else
			{
				((EntityPlayerMP) src).sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
						+ TextFormatting.AQUA + "This item is not in the tier 3 item pool."));
			}
		} else
		{
			((EntityPlayerMP) src).sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
					+ TextFormatting.AQUA + "You have specified an invalid or nonexistant tier."));
		}

		/*
		 * if (sender instanceof EntityPlayerMP) { EntityPlayerMP p = (EntityPlayerMP)
		 * sender; if (args.length == 1) { try {
		 * if(ConfigStruc.gcon.drops.contains(args[0])) {
		 * ConfigStruc.gcon.drops.remove(args[0]); PokestopConfig.save();
		 * sender.sendMessage( new TextComponentString(TextFormatting.LIGHT_PURPLE +
		 * "[Pokestops] " + TextFormatting.AQUA + "Successfully removed " + args[0] +
		 * " from the Pokestops item pool!")); } else { sender.sendMessage( new
		 * TextComponentString(TextFormatting.LIGHT_PURPLE + "[Pokestops] " +
		 * TextFormatting.RED + args[0] + " was not in the pokestops pool.")); }
		 * 
		 * } catch (FileNotFoundException f) {
		 * 
		 * } } else { sender.sendMessage(new TextComponentString(
		 * TextFormatting.LIGHT_PURPLE + "[Pokestops] " + TextFormatting.AQUA +
		 * getUsage(sender))); } }
		 */
		return CommandResult.success();
	}
}

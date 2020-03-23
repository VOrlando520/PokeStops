package pokestops.andwhat5.commands;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.util.annotation.NonnullByDefault;
import pokestops.andwhat5.config.ConfigStruc;
import pokestops.andwhat5.config.PokeStopConfig;
import pokestops.andwhat5.config.PokeStopStruc;

import java.io.FileNotFoundException;

@NonnullByDefault
public class ExecutorDelPokeStop implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args)
			throws org.spongepowered.api.command.CommandException {
		// src.sendMessage(Text.of("Command used"));
		if (src instanceof EntityPlayerMP) {
			// src.sendMessage(Text.of("ur a player"));
			EntityPlayerMP p = (EntityPlayerMP) src;
			for (PokeStopStruc ps : ConfigStruc.gcon.locations) {
				if (ps.distanceTo(p) <= 3) {
					try {
						ConfigStruc.gcon.locations.remove(ps);
						PokeStopConfig.save();
						p.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
																	  + TextFormatting.AQUA + "Successfully removed this PokeStop!"));
						return CommandResult.success();
					} catch (FileNotFoundException f) {

					}

				}
			}
			p.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] " + TextFormatting.AQUA
														  + "You must be inside of a PokeStop to remove it!"));
			return CommandResult.success();
		}
		return CommandResult.success();
	}
}
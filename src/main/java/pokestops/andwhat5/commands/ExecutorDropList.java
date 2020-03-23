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
import pokestops.andwhat5.enums.EnumPokeStopType;

@NonnullByDefault
public class ExecutorDropList implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args)
			throws org.spongepowered.api.command.CommandException {
		if (src instanceof EntityPlayerMP) {
			EntityPlayerMP p = (EntityPlayerMP) src;
			if (args.hasAny("tier")) {
				String msg = TextFormatting.LIGHT_PURPLE + "[PokeStops] " + TextFormatting.AQUA + "Current Drop List: ";
				if (((EnumPokeStopType) args.getOne("tier").get()).equals(EnumPokeStopType.tier1)) {
					if (ConfigStruc.gcon.tier1.size() == 1) {
						msg += ConfigStruc.gcon.tier1.get(0).item + "(r" + ConfigStruc.gcon.tier1.get(0).rarity + ").";
					} else {
						for (int i = 0; i < ConfigStruc.gcon.tier1.size(); i++) {
							if (i == ConfigStruc.gcon.tier1.size() - 1) {
								msg += "and " + ConfigStruc.gcon.tier1.get(i).item + "(r"
										+ ConfigStruc.gcon.tier1.get(i).rarity + ").";
							} else {
								msg += ConfigStruc.gcon.tier1.get(i).item + "(r" + ConfigStruc.gcon.tier1.get(i).rarity
										+ "), ";
							}
						}
					}
					p.sendMessage(new TextComponentString(msg));
				} else if (((EnumPokeStopType) args.getOne("tier").get()).equals(EnumPokeStopType.tier2)) {
					if (ConfigStruc.gcon.tier2.size() == 1) {
						msg += ConfigStruc.gcon.tier2.get(0).item + "(r" + ConfigStruc.gcon.tier2.get(0).rarity + ").";
					} else {
						for (int i = 0; i < ConfigStruc.gcon.tier2.size(); i++) {
							if (i == ConfigStruc.gcon.tier2.size() - 1) {
								msg += "and " + ConfigStruc.gcon.tier2.get(i).item + "(r"
										+ ConfigStruc.gcon.tier2.get(i).rarity + ").";
							} else {
								msg += ConfigStruc.gcon.tier2.get(i).item + "(r" + ConfigStruc.gcon.tier2.get(i).rarity
										+ "), ";
							}
						}
					}
					p.sendMessage(new TextComponentString(msg));
				} else if (((EnumPokeStopType) args.getOne("tier").get()).equals(EnumPokeStopType.tier3)) {
					if (ConfigStruc.gcon.tier3.size() == 1) {
						msg += ConfigStruc.gcon.tier3.get(0).item + "(r" + ConfigStruc.gcon.tier3.get(0).rarity + ").";
					} else {
						for (int i = 0; i < ConfigStruc.gcon.tier3.size(); i++) {
							if (i == ConfigStruc.gcon.tier3.size() - 1) {
								msg += "and " + ConfigStruc.gcon.tier3.get(i).item + "(r"
										+ ConfigStruc.gcon.tier3.get(i).rarity + ").";
							} else {
								msg += ConfigStruc.gcon.tier3.get(i).item + "(r" + ConfigStruc.gcon.tier3.get(i).rarity
										+ "), ";
							}
						}
					}
					p.sendMessage(new TextComponentString(msg));
				} else {
					p.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
																  + TextFormatting.AQUA + "This PokeStop does not support drops."));
				}
			} else {
				p.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] " + TextFormatting.AQUA
															  + "Please specify a tier of PokeStop to view its drops."));

			}
		}
		return CommandResult.success();
	}
}
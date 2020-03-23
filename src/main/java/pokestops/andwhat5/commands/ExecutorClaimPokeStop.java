package pokestops.andwhat5.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.util.annotation.NonnullByDefault;
import pokestops.andwhat5.config.*;
import pokestops.andwhat5.enums.EnumPokeStopType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

@NonnullByDefault
public class ExecutorClaimPokeStop implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args)
			throws org.spongepowered.api.command.CommandException {
		if (src instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) src;
			if (ConfigStruc.gcon.tier1.size() == 0 && ConfigStruc.gcon.tier2.size() == 0
					&& ConfigStruc.gcon.tier3.size() == 0) {
				player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStop] "
																   + TextFormatting.AQUA
																   + "This server has not setup PokeStop drops yet, therefore, there is nothing to claim!"));
				return CommandResult.success();
			}
			for (PokeStopStruc p : ConfigStruc.gcon.locations) {
				if (p.distanceTo(player) <= 3) {
					PlayerPokeStopStruc pps = Utilities.getPlayerdataForPokestop(player, p.getCoordStruc());
					if (pps == null) {
						pps = new PlayerPokeStopStruc(player.getName(), player.getUniqueID().toString());
						PlayerStruc.gcon.PlayerData.add(pps);
					}
					if (Utilities.canClaim(pps.visited.get(p.getCoordStruc()), src, p.getPokeStopType())) {
						Utilities.getPlayerdataForPokestop(player, p.getCoordStruc()).visited.put(p.getCoordStruc(),
																								  new Date());
						try {
							PokeStopConfig.save();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						int amount = PokeStopConfig.rand.nextInt(
								(PokeStopConfig.MaxItems - PokeStopConfig.MinItems) + 1) + PokeStopConfig.MinItems;

						ArrayList<ItemStack> items = new ArrayList<>();
						// HashMap<String, Integer> score = new HashMap<String, Integer>();
						EnumPokeStopType type = p.getPokeStopType();
						if (PokeStopConfig.UseTierPermissionNodes) {
							if (type == EnumPokeStopType.tier1 && !src.hasPermission("pokestops.claimpokestop.tier1")) {
								player.sendMessage(new TextComponentString(
										TextFormatting.LIGHT_PURPLE + "[PokeStops] " + TextFormatting.AQUA
												+ "You do not have permission to claim tier 1 PokeStops."));
								return CommandResult.success();
							} else if (type == EnumPokeStopType.tier2
									&& !src.hasPermission("pokestops.claimpokestop.tier2")) {
								player.sendMessage(new TextComponentString(
										TextFormatting.LIGHT_PURPLE + "[PokeStops] " + TextFormatting.AQUA
												+ "You do not have permission to claim tier 2 PokeStops."));
								return CommandResult.success();
							} else if (type == EnumPokeStopType.tier3
									&& !src.hasPermission("pokestops.claimpokestop.tier3")) {
								player.sendMessage(new TextComponentString(
										TextFormatting.LIGHT_PURPLE + "[PokeStops] " + TextFormatting.AQUA
												+ "You do not have permission to claim tier 3 PokeStops."));
								return CommandResult.success();
							}
						}
						if (type == EnumPokeStopType.tier1 && ConfigStruc.gcon.tier1.isEmpty()) {
							player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
																			   + TextFormatting.AQUA + "There is nothing in the tier 1 drops pool."));
							return CommandResult.success();
						} else if (type == EnumPokeStopType.tier2 && ConfigStruc.gcon.tier2.isEmpty()) {
							player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
																			   + TextFormatting.AQUA + "There is nothing in the tier 2 drops pool."));
							return CommandResult.success();
						} else if (type == EnumPokeStopType.tier3 && ConfigStruc.gcon.tier3.isEmpty()) {
							player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
																			   + TextFormatting.AQUA + "There is nothing in the tier 3 drops pool."));
							return CommandResult.success();
						}

						if (!p.isVisible()) {
							player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
																			   + TextFormatting.AQUA + "You need to be inside of a PokeStop to claim it!"));
							return CommandResult.success();
						}

						if (type == EnumPokeStopType.tier1 || type == EnumPokeStopType.tier2
								|| type == EnumPokeStopType.tier3) {
							for (int i = 0; i < amount; i++) {
								items.add(Utilities.getRandomReward((ICommandSender) player, type));
							}
						}
						/*
						 * for (int i = 0; i < amount; i++) { int index;
						 *
						 * int index = PokestopConfig.rand.nextInt(ConfigStruc.gcon.drops.size());
						 * String selected = ConfigStruc.gcon.drops.get(index);
						 *
						 * Integer count = score.get(selected); if (count == null) { score.put(selected,
						 * 1); } else { score.put(selected, count + 1); } } score.forEach((k, v) -> {
						 * try { items.add(new ItemStack(getItemByText(sender, k), v)); } catch
						 * (NumberInvalidException e) { e.printStackTrace(); } });
						 */

						Utilities.openDropInventory("Pokestop Loot", player,
													(ItemStack[]) items.toArray(new ItemStack[items.size()]));

						player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
																		   + TextFormatting.AQUA + "You have successfully claimed this PokeStop!"));
						return CommandResult.success();

					} else {
						if (!PokeStopConfig.Reusable) {
							player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[Pokestops] "
																			   + TextFormatting.AQUA + "This server only lets you claim PokeStops once!"));
							return CommandResult.success();
						}
						player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
																		   + TextFormatting.AQUA + "You have to wait "
																		   + Utilities.minutesRemaining(pps.visited.get(p.getCoordStruc()), p.getPokeStopType())
																		   + " minutes to claim this PokeStop again!"));
						return CommandResult.success();
					}

				}
			}
			player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
															   + TextFormatting.AQUA + "You need to be inside of a PokeStop to claim it!"));
			return CommandResult.success();
		}
		return CommandResult.success();
	}
}
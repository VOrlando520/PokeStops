package pokestops.andwhat5.commands;

import java.io.FileNotFoundException;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import pokestops.andwhat5.config.ConfigStruc;
import pokestops.andwhat5.config.ItemStruc;
import pokestops.andwhat5.config.PokeStopConfig;
import pokestops.andwhat5.enums.EnumPokeStopType;

/**
 * PixelmonEnv Created by AnDwHaT5.
 */
public class ExecutorAddDrop implements CommandExecutor
{

	public boolean checkForDuplicates(String s, EnumPokeStopType tier)
	{
		for (ItemStruc st : tier.equals(EnumPokeStopType.tier1) ? ConfigStruc.gcon.tier1
				: tier.equals(EnumPokeStopType.tier2) ? ConfigStruc.gcon.tier2 : ConfigStruc.gcon.tier3)
		{
			if (st.item.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args)
			throws org.spongepowered.api.command.CommandException
	{
		if (src instanceof EntityPlayerMP)
		{
			EntityPlayerMP p = (EntityPlayerMP) src;
			if (args.hasAny("item") && args.hasAny("tier"))
			{
				ItemStack item = null;
				try
				{
					item = new ItemStack(
							CommandBase.getItemByText((ICommandSender) src, args.getOne("item").get().toString()), 1);
				} catch (NumberInvalidException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (item.equals(null))
					return CommandResult.success();

				if ((EnumPokeStopType) args.getOne("tier").get() == EnumPokeStopType.tier1)
				{
					if (checkForDuplicates(args.getOne("item").get().toString(),
							(EnumPokeStopType) args.getOne("tier").get()))
					{
						p.sendMessage(new TextComponentString(
								TextFormatting.LIGHT_PURPLE + "[PokeStops] " + TextFormatting.AQUA + "The item "
										+ item.getDisplayName() + " is already in the tier 1 PokeStops item pool!"));
						return CommandResult.success();
					}
					try
					{
						ConfigStruc.gcon.tier1.add(
								new ItemStruc(args.getOne("item").get().toString(), (int) args.getOne("rarity").get()));
						PokeStopConfig.save();
						p.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
								+ TextFormatting.AQUA + "Successfully added " + item.getDisplayName()
								+ " to the tier 1 PokeStops item pool!"));
					} catch (FileNotFoundException f)
					{

					}

				} else

				if ((EnumPokeStopType) args.getOne("tier").get() == EnumPokeStopType.tier2)
				{
					if (checkForDuplicates(args.getOne("item").get().toString(),
							(EnumPokeStopType) args.getOne("tier").get()))
					{
						p.sendMessage(new TextComponentString(
								TextFormatting.LIGHT_PURPLE + "[PokeStops] " + TextFormatting.AQUA + "The item "
										+ item.getDisplayName() + " is already in the tier 2 PokeStops item pool!"));
						return CommandResult.success();
					}
					try
					{
						ConfigStruc.gcon.tier2.add(
								new ItemStruc(args.getOne("item").get().toString(), (int) args.getOne("rarity").get()));
						PokeStopConfig.save();
						p.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
								+ TextFormatting.AQUA + "Successfully added " + item.getDisplayName()
								+ " to the tier 2 PokeStops item pool!"));
					} catch (FileNotFoundException f)
					{

					}

				} else

				if ((EnumPokeStopType) args.getOne("tier").get() == EnumPokeStopType.tier3)
				{
					if (checkForDuplicates(args.getOne("item").get().toString(),
							(EnumPokeStopType) args.getOne("tier").get()))
					{
						p.sendMessage(new TextComponentString(
								TextFormatting.LIGHT_PURPLE + "[PokeStops] " + TextFormatting.AQUA + "The item "
										+ item.getDisplayName() + " is already in the tier 3 PokeStops item pool!"));
						return CommandResult.success();
					}
					try
					{
						ConfigStruc.gcon.tier3.add(
								new ItemStruc(args.getOne("item").get().toString(), (int) args.getOne("rarity").get()));
						PokeStopConfig.save();
						p.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
								+ TextFormatting.AQUA + "Successfully added " + item.getDisplayName()
								+ " to the tier 3 PokeStops item pool!"));
					} catch (FileNotFoundException f)
					{

					}

				} else
				{
					p.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
							+ "This tier is not a valid tier to add items to."));
				}

			}
		}
		return null;
	}
}

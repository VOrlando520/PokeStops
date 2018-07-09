package pokestops.andwhat5.commands;

import java.io.FileNotFoundException;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.effect.particle.ParticleTypes;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import pokestops.andwhat5.config.ConfigStruc;
import pokestops.andwhat5.config.CoordStruc;
import pokestops.andwhat5.config.PokeStopConfig;
import pokestops.andwhat5.config.PokeStopStruc;
import pokestops.andwhat5.enums.EnumPokeStopType;

public class ExecutorAddPokeStop implements CommandExecutor
{

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
	{
		if (src instanceof EntityPlayerMP)
		{
			EntityPlayerMP p = (EntityPlayerMP) src;
			if (args.hasAny("tier"))
			{
				try
				{
					PokeStopStruc ps = new PokeStopStruc(ParticleTypes.DRAGON_BREATH,
							new CoordStruc(p.getPositionVector()), (EnumPokeStopType) args.getOne("tier").get());
					ConfigStruc.gcon.locations.add(ps);
					p.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "[PokeStops] "
							+ TextFormatting.AQUA + "Successfully placed a PokeStop where you stand!"));
					PokeStopConfig.save();
				} catch (FileNotFoundException f)
				{

				}
			}
		}

		return CommandResult.success();
	}

}

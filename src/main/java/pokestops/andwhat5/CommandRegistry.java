package pokestops.andwhat5;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import pokestops.andwhat5.commands.ExecutorAddDrop;
import pokestops.andwhat5.commands.ExecutorAddPokeStop;
import pokestops.andwhat5.commands.ExecutorClaimPokeStop;
import pokestops.andwhat5.commands.ExecutorDelDrop;
import pokestops.andwhat5.commands.ExecutorDelPokeStop;
import pokestops.andwhat5.commands.ExecutorDropList;
import pokestops.andwhat5.enums.EnumPokeStopType;

public class CommandRegistry
{

	public static void registerCommands()
	{
		CommandSpec p = CommandSpec.builder().permission("pokestops.addpokestop.base")
				.arguments(GenericArguments.enumValue(Text.of("tier"), EnumPokeStopType.class))
				.executor(new ExecutorAddPokeStop()).build();
		Sponge.getCommandManager().register(PokeStops.getInstance(), p, "addpokestop");

		CommandSpec p1 = CommandSpec.builder().permission("pokestops.droplist.base")
				.arguments(GenericArguments.enumValue(Text.of("tier"), EnumPokeStopType.class))
				.executor(new ExecutorDropList()).build();
		Sponge.getCommandManager().register(PokeStops.getInstance(), p1, "droplist");

		CommandSpec p2 = CommandSpec.builder().permission("pokestops.delpokestop.base")
				.executor(new ExecutorDelPokeStop()).build();
		Sponge.getCommandManager().register(PokeStops.getInstance(), p2, "delpokestop");

		CommandSpec p3 = CommandSpec.builder().permission("pokestops.adddrop.base")
				.arguments(GenericArguments.enumValue(Text.of("tier"), EnumPokeStopType.class),
						GenericArguments.string(Text.of("item")), GenericArguments.integer(Text.of("rarity")))
				.executor(new ExecutorAddDrop()).build();
		Sponge.getCommandManager().register(PokeStops.getInstance(), p3, "adddrop");

		CommandSpec p4 = CommandSpec.builder().permission("pokestops.deldrop.base")
				.arguments(GenericArguments.enumValue(Text.of("tier"), EnumPokeStopType.class),
						GenericArguments.string(Text.of("item")))
				.executor(new ExecutorDelDrop()).build();
		Sponge.getCommandManager().register(PokeStops.getInstance(), p4, "deldrop");

		CommandSpec p5 = CommandSpec.builder().permission("pokestops.claimpokestop.base")
				.executor(new ExecutorClaimPokeStop()).build();
		Sponge.getCommandManager().register(PokeStops.getInstance(), p5, "claimpokestop");
	}
}

package pokestops.andwhat5.ticker;

import java.util.function.Consumer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.particle.ParticleOptions;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.util.Color;

import com.flowpowered.math.vector.Vector3d;

import pokestops.andwhat5.config.ConfigStruc;
import pokestops.andwhat5.config.PokeStopStruc;
import pokestops.andwhat5.enums.EnumPokeStopType;

public class ParticleTask implements Consumer<Task>
{

	double theta = 0;
	double radius = 3;
	double insideRadius = 0.5;
	double degrees = 0.5;

	@Override
	public void accept(Task t)
	{
		for (PokeStopStruc ps : ConfigStruc.gcon.locations)
		{
			if (ps.isVisible())
			{
				double mainX = ps.getCoordStruc().x;
				double mainY = ps.getCoordStruc().y;
				double mainZ = ps.getCoordStruc().z;
				double outsideX;
				double outsideZ;
				double insideX;
				double insideZ;
				if (theta < 360)
				{
					outsideX = mainX + radius * Math.cos(theta);
					outsideZ = mainZ + radius * Math.sin(theta);
					insideX = mainX + insideRadius * Math.cos(theta);
					insideZ = mainZ + insideRadius * Math.sin(theta);
					ParticleEffect eff;

					if (ps.getPokeStopType() == EnumPokeStopType.tier1)
						eff = ParticleEffect.builder().type(ps.getParticleType())
								.option(ParticleOptions.COLOR, Color.ofRgb(255, 105, 180)).quantity(4).build();
					else if (ps.getPokeStopType() == EnumPokeStopType.tier2)
						eff = ParticleEffect.builder().type(ps.getParticleType())
								.option(ParticleOptions.COLOR, Color.ofRgb(0, 191, 255)).quantity(4).build();
					else if (ps.getPokeStopType() == EnumPokeStopType.tier3)
						eff = ParticleEffect.builder().type(ps.getParticleType())
								.option(ParticleOptions.COLOR, Color.ofRgb(116, 0, 179)).quantity(4).build();
					else if (ps.getPokeStopType() == EnumPokeStopType.healing)
					{
						eff = ParticleEffect.builder().type(ps.getParticleType())
								.option(ParticleOptions.COLOR, Color.ofRgb(255, 0, 0)).quantity(4).build();
					} else
						eff = ParticleEffect.builder().type(ps.getParticleType())
								.option(ParticleOptions.COLOR, Color.ofRgb(255, 105, 180)).quantity(4).build();

					ParticleEffect effi = ParticleEffect.builder().type(ParticleTypes.REDSTONE_DUST).quantity(1)
							.option(ParticleOptions.COLOR, Color.ofRgb(255, 255, 255)).velocity(new Vector3d(0, 0.1, 0))
							.build();
					for (double i = 0; i <= 2; i++)
					{
						Sponge.getServer().getWorld(Sponge.getServer().getDefaultWorldName()).get().spawnParticles(eff,
								new Vector3d(outsideX, mainY + i, outsideZ));
						Sponge.getServer().getWorld(Sponge.getServer().getDefaultWorldName()).get().spawnParticles(effi,
								new Vector3d(insideX, mainY + i, insideZ));
					}
				}
			}
		}
		theta += degrees;
		if (theta == 360)
			theta = 0;
	}

}

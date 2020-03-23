package pokestops.andwhat5.config;

import com.google.gson.annotations.Expose;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.common.world.WorldUtil;
import pokestops.andwhat5.enums.EnumPokeStopType;

/**
 * PixelmonEnv Created by AnDwHaT5.
 */
public class PokeStopStruc {
	@Expose
	private CoordStruc pokestop;
	@Expose
	private EnumPokeStopType type;
	private boolean visible = true;

	public PokeStopStruc(ParticleType pt, CoordStruc cs, EnumPokeStopType type) {
		pokestop = cs;
		this.type = type;
	}

	public PokeStopStruc() {
		type = EnumPokeStopType.tier1;
	}

	public EnumPokeStopType getPokeStopType() {
		return type;
	}

	public void setPokeStopType(EnumPokeStopType t) {
		type = t;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean isV) {
		visible = isV;
	}

	public void activatePokestop(Player player) {
		// TODO Auto-generated method stub

	}

	public ParticleType getParticleType() {
		/*
		 * if(type == EnumPokeStopType.tier1) return ParticleTypes.DRAGON_BREATH; else
		 * if(type == EnumPokeStopType.tier2) return ParticleTypes.DAMAGE_INDICATOR;
		 * else if(type == EnumPokeStopType.tier3) return ParticleTypes.FLAME; else
		 * if(type == EnumPokeStopType.healing) return ParticleTypes.HEART; else return
		 * ParticleTypes.DRAGON_BREATH;
		 */
		return ParticleTypes.REDSTONE_DUST;
	}

	public CoordStruc getCoordStruc() {
		// TODO Auto-generated method stub
		return pokestop;
	}

	public double distanceTo(EntityPlayerMP player) {
		if (WorldUtil.fromNative(player.world).getUniqueId().equals(getCoordStruc().world)) {
			return player.getPositionVector().distanceTo(Utilities.coordStrucToVec3d(getCoordStruc()));
		} else {
			return Double.MAX_VALUE;
		}
	}
}
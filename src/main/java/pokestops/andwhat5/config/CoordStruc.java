package pokestops.andwhat5.config;

import com.google.gson.annotations.Expose;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.storage.WorldProperties;

import java.util.Objects;
import java.util.UUID;

public class CoordStruc {
	@Expose
	public double x;
	@Expose
	public double y;
	@Expose
	public double z;
	@Expose
	public UUID world = Sponge.getServer().getDefaultWorld().map(WorldProperties::getUniqueId).orElse(null);
	public CoordStruc(Vec3d v, UUID world) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.world = world;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CoordStruc that = (CoordStruc) o;
		return Double.compare(that.x, x) == 0 &&
				Double.compare(that.y, y) == 0 &&
				Double.compare(that.z, z) == 0 &&
				Objects.equals(world, that.world);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z, world);
	}
}
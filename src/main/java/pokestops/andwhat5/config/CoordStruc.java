package pokestops.andwhat5.config;

import com.google.gson.annotations.Expose;

import net.minecraft.util.math.Vec3d;

public class CoordStruc
{
	public CoordStruc(Vec3d v)
	{
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}

	@Expose
	public double x;
	@Expose
	public double y;
	@Expose
	public double z;
}

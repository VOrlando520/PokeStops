package pokestops.andwhat5.config;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * PixelmonEnv Created by AnDwHaT5.
 */
public class ConfigStruc
{
	public ConfigStruc()
	{
		locations = new ArrayList<>();
		tier1 = new ArrayList<>();
		tier2 = new ArrayList<>();
		tier3 = new ArrayList<>();
	}

	@Expose
	public List<PokeStopStruc> locations;

	@Expose
	public List<ItemStruc> tier1;

	@Expose
	public List<ItemStruc> tier2;

	@Expose
	public List<ItemStruc> tier3;

	public static ConfigStruc gcon = new ConfigStruc();
}

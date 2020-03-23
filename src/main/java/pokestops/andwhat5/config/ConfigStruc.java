package pokestops.andwhat5.config;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * PixelmonEnv Created by AnDwHaT5.
 */
public class ConfigStruc {
	public static ConfigStruc gcon = new ConfigStruc();
	@Expose
	public List<PokeStopStruc> locations;

	@Expose
	public List<ItemStruc> tier1;

	@Expose
	public List<ItemStruc> tier2;

	@Expose
	public List<ItemStruc> tier3;

	public ConfigStruc() {
		locations = new ArrayList<>();
		tier1 = new ArrayList<>();
		tier2 = new ArrayList<>();
		tier3 = new ArrayList<>();
	}
}
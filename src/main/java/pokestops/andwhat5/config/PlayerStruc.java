package pokestops.andwhat5.config;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class PlayerStruc
{
	public PlayerStruc()
	{
		PlayerData = new ArrayList<>();
	}

	@Expose
	public List<PlayerPokeStopStruc> PlayerData;

	public static PlayerStruc gcon = new PlayerStruc();
}

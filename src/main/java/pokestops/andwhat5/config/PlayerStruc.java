package pokestops.andwhat5.config;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class PlayerStruc {
	public static PlayerStruc gcon = new PlayerStruc();
	@Expose
	public List<PlayerPokeStopStruc> PlayerData;

	public PlayerStruc() {
		PlayerData = new ArrayList<>();
	}
}
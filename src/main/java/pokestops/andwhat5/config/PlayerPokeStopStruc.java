package pokestops.andwhat5.config;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.HashMap;

public class PlayerPokeStopStruc {

	@Expose
	public String Name = "";
	@Expose
	public String UUID = "";
	@Expose
	public HashMap<CoordStruc, Date> visited;

	public PlayerPokeStopStruc(String Name, String UUID) {
		this.Name = Name;
		this.UUID = UUID;
		visited = new HashMap<CoordStruc, Date>();
	}
}
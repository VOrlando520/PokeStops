package pokestops.andwhat5.config;

import java.util.Date;
import java.util.HashMap;

import com.google.gson.annotations.Expose;

public class PlayerPokeStopStruc
{

	public PlayerPokeStopStruc(String Name, String UUID)
	{
		this.Name = Name;
		this.UUID = UUID;
		visited = new HashMap<CoordStruc, Date>();
	}

	@Expose
	public String Name = "";

	@Expose
	public String UUID = "";

	@Expose
	public HashMap<CoordStruc, Date> visited;
}

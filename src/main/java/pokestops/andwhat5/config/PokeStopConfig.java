package pokestops.andwhat5.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import net.minecraftforge.common.config.Configuration;
import pokestops.andwhat5.enums.EnumPokeStopType;

import java.io.*;
import java.util.Random;

/**
 * PixelmonEnv Created by AnDwHaT5.
 */
public class PokeStopConfig {
	public static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation()
			.enableComplexMapKeySerialization().create();
	public static Random rand = new Random();
	public static Random randd = new Random();
	public static int Tier1PokeStopCooldown = 10;
	public static int Tier2PokeStopCooldown = 30;
	public static int Tier3PokeStopCooldown = 60;
	public static int MaxItems = 5;
	public static int MinItems = 1;
	public static int ViewDistance = 40;
	public static boolean UseTierPermissionNodes = false;
	public static boolean Reusable = true;

	public static void load() {

		File file = new File("config/PokestopList.json");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			ConfigStruc.gcon = gson.fromJson(new FileReader(file), ConfigStruc.class);
		} catch (JsonSyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonIOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (ConfigStruc.gcon == null) {
			ConfigStruc.gcon = new ConfigStruc();
		}
		for (PokeStopStruc i : ConfigStruc.gcon.locations) {
			if (i.getPokeStopType() == null)
				i.setPokeStopType(EnumPokeStopType.tier1);
		}

		File file2 = new File("config/PokestopPlayerData.json");
		if (!file2.exists()) {
			try {
				file2.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			PlayerStruc.gcon = gson.fromJson(new FileReader(file), PlayerStruc.class);
		} catch (JsonSyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonIOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (PlayerStruc.gcon == null) {
			PlayerStruc.gcon = new PlayerStruc();
		}

		/*
		 * try { ConfigStruc.gcon = gson.fromJson(new
		 * FileReader("config/pokestops.json"), ConfigStruc.class); if(ConfigStruc.gcon
		 * == null) { ConfigStruc.gcon = new ConfigStruc(); }
		 *
		 * int i = 0; for(BlockPos bp : ConfigStruc.gcon.locations) { PokestopStruc st =
		 * new PokestopStruc(); st.setBlockPos(bp); st.setId(i + 1); pokestops.add(st);
		 * i++; } } catch (JsonSyntaxException | JsonIOException | FileNotFoundException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	public static void save() throws FileNotFoundException {
		try (FileWriter writer = new FileWriter("config/PokestopList.json")) {
			gson.toJson(ConfigStruc.gcon, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (FileWriter writer = new FileWriter("config/PokestopPlayerData.json")) {
			gson.toJson(PlayerStruc.gcon, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void LoadConfig(Configuration config) {

		if (config.hasChanged())
			config.save();
		config.load();
		Tier1PokeStopCooldown = (int) config
				.get("General", "How long a player has to wait to use a Tier1 PokeStop again in minutes", 10).getInt();
		Tier2PokeStopCooldown = (int) config
				.get("General", "How long a player has to wait to use a Tier2 PokeStop again in minutes", 30).getInt();
		Tier3PokeStopCooldown = (int) config
				.get("General", "How long a player has to wait to use a Tier3 PokeStop again in minutes", 60).getInt();
		MinItems = (int) config.get("General", "Minimum amount of items from a pokestop", 1).getInt();
		MaxItems = (int) config.get("General", "Maximum amount of items from a pokestop", 5).getInt();
		Reusable = (boolean) config.get("General", "Can a player use the same Pokestop more than once?", true)
				.getBoolean();
		ViewDistance = config.get("General", "ViewDistance", ViewDistance, "The view distance of particle effects")
				.getInt();
		UseTierPermissionNodes = (boolean) config.get("General",
													  "Should the player require the pokestops.claimpokestop.tierx permission node to claim a tiered PokeStop?",
													  false).getBoolean();
	}
}
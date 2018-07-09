package pokestops.andwhat5;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import pokestops.andwhat5.config.PokeStopConfig;
import pokestops.andwhat5.ticker.ParticleTask;

@Plugin(name="PokéStops",id="pokestop", authors="AnDwHaT5", description="Brings PokeStops into Minecraft!", version="2.0.3")
public class PokeStops {
    //I see you decompiled  my code. Welcome to my hell. Message me on Discord if you're reading this
    //AnDwHaT5#7686
	public PokeStops instance = this;
	public static Object getInstance()
	{
		return Sponge.getPluginManager().getPlugin("pokestop").get().getInstance().get();
	}
   // @Mod.EventHandler
    public void Init(FMLPreInitializationEvent event) {

        Configuration config = new Configuration(new File("config/pokestop.cfg"));
        config.load();
        PokeStopConfig.LoadConfig(config);
        config.load();
        config.save();
    }
    
    @Listener
    public void preInit(GamePreInitializationEvent e)
    {
        Configuration config = new Configuration(new File("config/pokestop.cfg"));
        config.load();
        PokeStopConfig.LoadConfig(config);
        config.load();
        config.save();
    }
    
    @Listener
    public void postInit(GamePostInitializationEvent e)
    {
        PokeStopConfig.load();
        //MinecraftForge.EVENT_BUS.register((Object)new ParticleEffect());
		Task.builder().execute(new ParticleTask()).interval(500, TimeUnit.MICROSECONDS).name("Particle Timer Task")
				.submit(this);
    }
    
    @Listener
    public void serverStart(GameStartingServerEvent e)
    {
    	CommandRegistry.registerCommands();
    }
    
    @EventHandler
    public void onServerStart(FMLServerStartingEvent event) {

    }
}
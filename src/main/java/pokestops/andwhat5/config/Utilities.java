package pokestops.andwhat5.config;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.comm.packetHandlers.itemDrops.ItemDropMode;
import com.pixelmonmod.pixelmon.comm.packetHandlers.itemDrops.ItemDropPacket;
import com.pixelmonmod.pixelmon.entities.pixelmon.drops.DropItemQuery;
import com.pixelmonmod.pixelmon.entities.pixelmon.drops.DropItemQueryList;
import com.pixelmonmod.pixelmon.entities.pixelmon.drops.DroppedItem;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.common.item.inventory.util.ItemStackUtil;
import pokestops.andwhat5.PokeStops;
import pokestops.andwhat5.enums.EnumPokeStopType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Utilities {

	public static ItemStack getRandomReward(ICommandSender sender, EnumPokeStopType type) throws NullPointerException {
		List<ItemStruc> itemStrucs;
		switch (type) {
			case tier1:
				itemStrucs = ConfigStruc.gcon.tier1;
				break;
			case tier2:
				itemStrucs = ConfigStruc.gcon.tier1;
				break;
			case tier3:
				itemStrucs = ConfigStruc.gcon.tier1;
				break;
			default:
				return ItemStack.EMPTY;
		}
		int totalChance = 0;
		for (ItemStruc s : itemStrucs) {
			totalChance += s.rarity;
		}
		int currOffset = 0;
		int randOffset = ThreadLocalRandom.current().nextInt(0, totalChance);

		for (ItemStruc s : itemStrucs) {
			currOffset += s.rarity;

			if (randOffset < currOffset) {
				Optional<ItemType> optionalItemType = Sponge.getRegistry().getType(ItemType.class,s.item);
				if(optionalItemType.isPresent()){
					return ItemStackUtil.toNative(org.spongepowered.api.item.inventory.ItemStack.of(optionalItemType.get(), 1));
				}else {
					PokeStops.getLogger().warn("Failed to get item for {}",s.item);
					return ItemStack.EMPTY;
				}
			}
		}
		return ItemStack.EMPTY;
	}

	public static boolean tierContains(EnumPokeStopType type, String item) {
		if (type.equals(EnumPokeStopType.tier1))
			for (ItemStruc i : ConfigStruc.gcon.tier1) {
				if (i.item.equalsIgnoreCase(item)) {
					return true;
				}

			}
		else if (type.equals(EnumPokeStopType.tier2))
			for (ItemStruc i : ConfigStruc.gcon.tier2) {
				if (i.item.equalsIgnoreCase(item)) {
					return true;
				}

			}
		else if (type.equals(EnumPokeStopType.tier3))
			for (ItemStruc i : ConfigStruc.gcon.tier3) {
				if (i.item.equalsIgnoreCase(item)) {
					return true;
				}

			}
		return false;
	}

	public static void removeFromTier(EnumPokeStopType type, String item) {
		if (type.equals(EnumPokeStopType.tier1))
			for (ItemStruc i : ConfigStruc.gcon.tier1) {
				if (i.item.equalsIgnoreCase(item)) {
					ConfigStruc.gcon.tier1.remove(i);
					return;
				}

			}
		else if (type.equals(EnumPokeStopType.tier2))
			for (ItemStruc i : ConfigStruc.gcon.tier2) {
				if (i.item.equalsIgnoreCase(item)) {
					ConfigStruc.gcon.tier2.remove(i);
					return;
				}

			}
		else if (type.equals(EnumPokeStopType.tier3))
			for (ItemStruc i : ConfigStruc.gcon.tier3) {
				if (i.item.equalsIgnoreCase(item)) {
					ConfigStruc.gcon.tier3.remove(i);
					return;
				}

			}
	}

	public static boolean canClaim(Date dateClaimed, CommandSource src, EnumPokeStopType type) {
		if (src.hasPermission("pokestops.claimpokestop.unlimited"))
			return true;
		if (dateClaimed == null)
			return true;
		if (!PokeStopConfig.Reusable)
			return false;
		Date currentTime = new Date();
		long durration = currentTime.getTime() - dateClaimed.getTime();
		if (type == EnumPokeStopType.tier1)
			if (TimeUnit.MILLISECONDS.toMinutes(durration) < PokeStopConfig.Tier1PokeStopCooldown)
				return false;
		if (type == EnumPokeStopType.tier2)
			if (TimeUnit.MILLISECONDS.toMinutes(durration) < PokeStopConfig.Tier2PokeStopCooldown)
				return false;
		if (type == EnumPokeStopType.tier3)
			if (TimeUnit.MILLISECONDS.toMinutes(durration) < PokeStopConfig.Tier3PokeStopCooldown)
				return false;
		return true;

	}

	public static long minutesRemaining(Date date, EnumPokeStopType type) {
		Date currentTime = new Date();
		long duration = currentTime.getTime() - date.getTime();
		if (type == EnumPokeStopType.tier1)
			return PokeStopConfig.Tier1PokeStopCooldown - TimeUnit.MILLISECONDS.toMinutes(duration);
		else if (type == EnumPokeStopType.tier2)
			return PokeStopConfig.Tier2PokeStopCooldown - TimeUnit.MILLISECONDS.toMinutes(duration);
		else if (type == EnumPokeStopType.tier3)
			return PokeStopConfig.Tier3PokeStopCooldown - TimeUnit.MILLISECONDS.toMinutes(duration);
		else
			return 0;
	}

	public static Vec3d coordStrucToVec3d(CoordStruc s) {
		if (s == null) {
			return null;
		}
		return new Vec3d(s.x, s.y, s.z);

	}

	public static PlayerPokeStopStruc getPlayerdataForPokestop(EntityPlayerMP player, CoordStruc c) {
		for (PlayerPokeStopStruc p : PlayerStruc.gcon.PlayerData) {
			if (p.UUID.equals(player.getUniqueID().toString())) {
				return p;
			}
		}
		return null;

	}

	public static void openDropInventory(String message, EntityPlayerMP p, ItemStack... items) {
		ArrayList<DroppedItem> droppedItems = new ArrayList<>();
		for (ItemStack item : items) {
			droppedItems.add(new DroppedItem(item, PokeStopConfig.rand.nextInt(10000)));
		}
		DropItemQuery query = new DropItemQuery(p.getPositionVector(), p.getUniqueID(), droppedItems);
		DropItemQueryList.queryList.add(query);
		ItemDropPacket packet = new ItemDropPacket(ItemDropMode.Other, new TextComponentTranslation(message),
												   droppedItems);
		Pixelmon.network.sendTo(packet, p);
	}
}
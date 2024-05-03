package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;

import net.minecraft.server.EntityEgg;

public class CraftEgg extends CraftProjectile implements Egg {
	public CraftEgg(CraftServer server, EntityEgg entity) {
		super(server, entity);
	}

	@Override
	public EntityEgg getHandle() {
		return (EntityEgg) entity;
	}

	@Override
	public String toString() {
		return "CraftEgg";
	}

	@Override
	public EntityType getType() {
		return EntityType.EGG;
	}
}

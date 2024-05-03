package net.minecraft.server;

// CraftBukkit start
import java.util.List;

import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
// CraftBukkit end

public class InventoryEnderChest extends InventorySubcontainer {

	private TileEntityEnderChest a;

	// CraftBukkit start - add fields and methods
	public List<HumanEntity> transaction = new java.util.ArrayList<HumanEntity>();
	public org.bukkit.entity.Player player;
	private int maxStack = MAX_STACK;

	@Override
	public ItemStack[] getContents() {
		return this.items;
	}

	@Override
	public void onOpen(CraftHumanEntity who) {
		transaction.add(who);
	}

	@Override
	public void onClose(CraftHumanEntity who) {
		transaction.remove(who);
	}

	@Override
	public List<HumanEntity> getViewers() {
		return transaction;
	}

	@Override
	public org.bukkit.inventory.InventoryHolder getOwner() {
		return this.player;
	}

	@Override
	public void setMaxStackSize(int size) {
		maxStack = size;
	}

	@Override
	public int getMaxStackSize() {
		return maxStack;
	}
	// CraftBukkit end

	public InventoryEnderChest() {
		super("container.enderchest", false, 27);
	}

	public void a(TileEntityEnderChest tileentityenderchest) {
		this.a = tileentityenderchest;
	}

	public void a(NBTTagList nbttaglist) {
		int i;

		for (i = 0; i < this.getSize(); ++i) {
			this.setItem(i, (ItemStack) null);
		}

		for (i = 0; i < nbttaglist.size(); ++i) {
			NBTTagCompound nbttagcompound = nbttaglist.get(i);
			int j = nbttagcompound.getByte("Slot") & 255;

			if (j >= 0 && j < this.getSize()) {
				this.setItem(j, ItemStack.createStack(nbttagcompound));
			}
		}

	}

	public NBTTagList h() {
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.getSize(); ++i) {
			ItemStack itemstack = this.getItem(i);

			if (itemstack != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();

				nbttagcompound.setByte("Slot", (byte) i);
				itemstack.save(nbttagcompound);
				nbttaglist.add(nbttagcompound);
			}
		}

		return nbttaglist;
	}

	@Override
	public boolean a(EntityHuman entityhuman) {
		return this.a != null && !this.a.a(entityhuman) ? false : super.a(entityhuman);
	}

	@Override
	public void startOpen(EntityHuman entityhuman) {
		if (this.a != null) {
			this.a.b();
		}

		super.startOpen(entityhuman);
	}

	@Override
	public void closeContainer(EntityHuman entityhuman) {
		if (this.a != null) {
			this.a.d();
		}

		super.closeContainer(entityhuman);
		this.a = null;
	}
}

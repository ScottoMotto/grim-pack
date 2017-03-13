package com.grim3212.mc.pack.industry.inventory;

import java.util.Iterator;

import com.grim3212.mc.pack.core.util.RecipeHelper;
import com.grim3212.mc.pack.industry.block.IndustryBlocks;
import com.grim3212.mc.pack.industry.config.IndustryConfig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerIronWorkbench extends ContainerWorkbench {

	private World worldObj;
	private BlockPos pos;
	private boolean isPortable;

	public ContainerIronWorkbench(InventoryPlayer invPlayer, World world, BlockPos pos, boolean isPortable) {
		super(invPlayer, world, pos);
		this.worldObj = world;
		this.pos = pos;
		this.isPortable = isPortable;
	}

	@Override
	public void onCraftMatrixChanged(IInventory iinventory) {
		ItemStack found = CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj);
		// Reset slot contents so that weird issues stop occuring
		this.craftResult.setInventorySlotContents(0, ItemStack.EMPTY);

		if (IndustryConfig.useWhitelist) {
			Iterator<ItemStack> itr = IndustryConfig.workbenchUpgradeList.iterator();

			while (itr.hasNext()) {
				ItemStack stack = itr.next();

				if (RecipeHelper.compareItemStacks(found, stack)) {
					this.craftResult.setInventorySlotContents(0, found);
					break;
				}
			}
		} else {
			Iterator<ItemStack> itr = IndustryConfig.workbenchUpgradeList.iterator();

			boolean blacklisted = false;

			while (itr.hasNext()) {
				ItemStack stack = itr.next();

				if (RecipeHelper.compareItemStacks(found, stack)) {
					blacklisted = true;
					break;
				}
			}

			if (!blacklisted) {
				this.craftResult.setInventorySlotContents(0, found);
			}
		}

		if (!craftResult.getStackInSlot(0).isEmpty()) {
			if (IndustryConfig.useWorkbenchUpgrades)
				if (craftResult.getStackInSlot(0).getCount() >= 64) {
					craftResult.getStackInSlot(0).setCount(127);
				} else {
					craftResult.getStackInSlot(0).setCount(craftResult.getStackInSlot(0).getCount() * 2);
				}
		} else {
			// If it returned a empty value then it was either blacklisted,
			// whitelisted or didn't have an item so just try and to craft
			// with the default recipe
			if (IndustryConfig.returnDefaultIfListed)
				this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return isPortable ? true : this.worldObj.getBlockState(this.pos).getBlock() != IndustryBlocks.iron_workbench ? false : playerIn.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
	}
}

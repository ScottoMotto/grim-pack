package com.grim3212.mc.decor;

import com.grim3212.mc.core.manual.ModSection;
import com.grim3212.mc.core.proxy.CommonProxy;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class DecorCommonProxy extends CommonProxy {
	
	public void produceSmoke(World world, BlockPos pos, double xMod, double yMod, double zMod, int amount, boolean makelarge) {
		
	}
	
	@Override
	protected void registerModels() {
	}
	
	@Override
	protected void registerManual(ModSection modSection) {
	}
	
	@Override
	public void preInit(ModSection modSection) {
	}
}

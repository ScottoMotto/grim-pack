package com.grim3212.mc.pack.industry.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.grim3212.mc.pack.core.block.BlockManual;
import com.grim3212.mc.pack.core.common.CommonItems;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.core.part.GrimCreativeTabs;
import com.grim3212.mc.pack.industry.client.ManualIndustry;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMetalMesh extends BlockManual {

	protected static final AxisAlignedBB COLLISION = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F - 0.002F, 1.0F);

	public BlockMetalMesh() {
		super("metal_mesh", Material.IRON, SoundType.METAL);
		setHardness(0.4F);
		setResistance(1.0F);
		setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return CommonItems.iron_stick;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		int count = 2 + random.nextInt(1 + fortune);
		return count > 4 ? 4 : count;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return COLLISION;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn instanceof EntityItem && (worldIn.isAirBlock(pos.down()) || worldIn.getBlockState(pos.down()).getBlock() == IndustryBlocks.metal_mesh || !worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP))) {
			EntityItem item = (EntityItem) entityIn;
			item.motionX = 0.0D;
			item.motionZ = 0.0D;
			item.setPosition(item.posX, item.posY - 1.3D, item.posZ);
		}
	}

	@Override
	public Page getPage(IBlockState state) {
		return ManualIndustry.metalMesh_page;
	}

}

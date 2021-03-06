package com.grim3212.mc.pack.industry.block;

import com.grim3212.mc.pack.core.block.BlockManual;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.core.part.GrimCreativeTabs;
import com.grim3212.mc.pack.industry.client.ManualIndustry;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockConveyorBelt extends BlockManual {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	private static final AxisAlignedBB CONVEYER_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	private static final AxisAlignedBB CONVEYER_COLLISION_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.52F, 1.0F);

	public BlockConveyorBelt() {
		super("conveyor_belt", Material.IRON, SoundType.METAL);
		setHardness(0.5F);
		setResistance(3.0F);
		setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	}

	@Override
	protected IBlockState getState() {
		return this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH);
	}

	@Override
	public Page getPage(IBlockState state) {
		return ManualIndustry.conveyorBelt_page;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CONVEYER_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return CONVEYER_COLLISION_AABB;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn instanceof EntityLivingBase) {
			switch (state.getValue(FACING)) {
			case EAST:
				entityIn.motionX = -0.1D;
				break;
			case NORTH:
				entityIn.motionZ = 0.1D;
				break;
			case SOUTH:
				entityIn.motionZ = -0.1D;
				break;
			case WEST:
				entityIn.motionX = 0.1D;
				break;
			default:
				break;
			}
		} else if (entityIn instanceof EntityItem) {
			entityIn.motionY = 0D;
			entityIn.motionX = 0D;
			entityIn.motionZ = 0D;

			switch (state.getValue(FACING)) {
			case EAST:
				entityIn.setPosition(entityIn.posX - 0.085D, pos.getY() + 0.7F, entityIn.posZ + (pos.getZ() + 0.5F - entityIn.posZ) / 1.5F);
				break;
			case NORTH:
				entityIn.setPosition(entityIn.posX + (pos.getX() + 0.5F - entityIn.posX) / 1.5F, pos.getY() + 0.7F, entityIn.posZ + 0.085D);
				break;
			case SOUTH:
				entityIn.setPosition(entityIn.posX + (pos.getX() + 0.5F - entityIn.posX) / 1.5F, pos.getY() + 0.7F, entityIn.posZ - 0.085D);
				break;
			case WEST:
				entityIn.setPosition(entityIn.posX + 0.085D, pos.getY() + 0.7F, entityIn.posZ + (pos.getZ() + 0.5F - entityIn.posZ) / 1.5F);
				break;
			default:
				break;
			}
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
}

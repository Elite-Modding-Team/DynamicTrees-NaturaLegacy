package maxhyper.dynamictreesnatura.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

import com.ferreusveritas.dynamictrees.blocks.BlockDynamicLeaves;
import com.progwml6.natura.shared.NaturaCommons;
import maxhyper.dynamictreesnatura.DynamicTreesNatura;
import maxhyper.dynamictreesnatura.ModConfigs;

public class BlockDynamicLeavesApple extends BlockDynamicLeaves {

	public BlockDynamicLeavesApple() {
		super();
		setRegistryName(DynamicTreesNatura.MODID, "leaves_apple");
		setUnlocalizedName("leaves_apple");
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
		if (state.getValue(BlockDynamicLeaves.TREE) == 2){
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.APPLE)));
		} else if (state.getValue(BlockDynamicLeaves.TREE) == 3){
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.GOLDEN_APPLE)));
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (ModConfigs.pickFruitFromLeaves && state.getValue(TREE) >= 2) {
			ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(state.getValue(TREE) == 3 ? Items.GOLDEN_APPLE : Items.APPLE));
			world.setBlockState(pos, state.withProperty(TREE, 0));
			return true;
		}
		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}
}

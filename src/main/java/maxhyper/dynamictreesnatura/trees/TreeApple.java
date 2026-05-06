package maxhyper.dynamictreesnatura.trees;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistry;

import com.ferreusveritas.dynamictrees.ModBlocks;
import com.ferreusveritas.dynamictrees.api.treedata.ILeavesProperties;
import com.ferreusveritas.dynamictrees.items.Seed;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenFruit;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import com.progwml6.natura.overworld.NaturaOverworld;
import com.progwml6.natura.overworld.block.leaves.BlockAppleLeaves;
import com.progwml6.natura.overworld.block.logs.BlockAppleLog;
import java.util.List;
import maxhyper.dynamictreesnatura.DynamicTreesNatura;
import maxhyper.dynamictreesnatura.ModConfigs;
import maxhyper.dynamictreesnatura.ModContent;
import maxhyper.dynamictreesnatura.genfeatures.FeatureGenFruitLeaves;
import slimeknights.mantle.util.LocUtils;

public class TreeApple extends TreeFamily {

	public static Block leavesBlock = NaturaOverworld.appleLeaves;
    public static Block logBlock = NaturaOverworld.appleLog;
    public static Block saplingBlock = NaturaOverworld.appleSapling;
	public static IBlockState leavesState = leavesBlock.getDefaultState().withProperty(BlockAppleLeaves.TYPE, BlockAppleLeaves.LeavesType.NORMAL);

	public class SpeciesApple extends Species {

		SpeciesApple(TreeFamily treeFamily) {
			super(treeFamily.getName(), treeFamily, ModContent.appleLeavesProperties);

			setBasicGrowingParameters(tapering, signalEnergy, upProbability, lowestBranchHeight, growthRate);

			addValidLeavesBlocks(ModContent.appleFloweringLeavesProperties, ModContent.appleFruitLeavesProperties, ModContent.appleGoldenFruitLeavesProperties);

			ModBlocks.blockApple.setSpecies(this);

			addGenFeature((new FeatureGenFruit(ModBlocks.blockApple)).setRayDistance(4.0F));
			if (ModConfigs.fruityLeaves)
				addGenFeature(new FeatureGenFruitLeaves(4, 12,
					new ILeavesProperties[]{ModContent.appleLeavesProperties, ModContent.appleFloweringLeavesProperties, ModContent.appleFruitLeavesProperties, ModContent.appleGoldenFruitLeavesProperties},
					0.5f));

			generateSeed();
			setupStandardSeedDropping();
		}

		public Species generateSeed() {
			Seed seed = new Seed(getRegistryName().getResourcePath() + "seed"){
				@Override
				public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
					tooltip.addAll(LocUtils.getTooltips(TextFormatting.GRAY + LocUtils.translateRecursive(LocUtils.translateRecursive("tile.natura.apple_sapling.apple.tooltip"))));
					super.addInformation(stack, worldIn, tooltip, flagIn);
				}
			};
			setSeedStack(new ItemStack(seed));
			return this;
		}
	}

	public TreeApple() {
		super(new ResourceLocation(DynamicTreesNatura.MODID, "apple"));

		setPrimitiveLog(logBlock.getDefaultState().withProperty(BlockAppleLog.TYPE, BlockAppleLog.LogType.APPLE));

		ModContent.appleLeavesProperties.setTree(this);

		addConnectableVanillaLeaves((state) -> state.getBlock() == leavesBlock);
	}

	@Override
	public ItemStack getPrimitiveLogItemStack(int qty) {
		ItemStack stack = new ItemStack(logBlock);
		stack.setCount(MathHelper.clamp(qty, 0, 64));
		return stack;
	}

	@Override
	public ItemStack getStick(int qty) {
		return new ItemStack(Items.STICK, qty);
	}

	@Override
	public void createSpecies() {
		setCommonSpecies(new SpeciesApple(this));
	}

	@Override
	public void registerSpecies(IForgeRegistry<Species> speciesRegistry) {
		super.registerSpecies(speciesRegistry);
	}

	@Override
	public List<Item> getRegisterableItems(List<Item> itemList) {
		return super.getRegisterableItems(itemList);
	}
	
}

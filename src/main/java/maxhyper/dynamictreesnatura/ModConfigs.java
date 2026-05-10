package maxhyper.dynamictreesnatura;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = DynamicTreesNatura.MODID)
public class ModConfigs {

    @Config.Comment("Allows apple and darkwood leaves to grow fruit on their own, like the non-dynamic versions (in addition to the Dynamic Trees fruit blocks).")
    @Config.Name("Fruity Leaves")
    @Config.RequiresMcRestart()
    public static boolean fruityLeaves = true;

    @Config.Comment("Allows apple and darkwood leaves with fruit to be right clicked instead of needing to break the block. requires \"Fruity Leaves\" to be set to true.")
    @Config.Name("Pick Fruit From Leaves")
    public static boolean pickFruitFromLeaves = true;

    @Mod.EventBusSubscriber(modid = DynamicTreesNatura.MODID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(DynamicTreesNatura.MODID)) {
                ConfigManager.sync(DynamicTreesNatura.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
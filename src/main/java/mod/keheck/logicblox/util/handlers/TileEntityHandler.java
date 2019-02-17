package mod.keheck.logicblox.util.handlers;

import mod.keheck.logicblox.Main;
import mod.keheck.logicblox.elements.tileentities.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

class TileEntityHandler
{
    static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityRandomizerDigital.class, new ResourceLocation(Main.MODID + ":tile_entity_randomizer_digital"));
        GameRegistry.registerTileEntity(TileEntityRandomizerAnalog.class, new ResourceLocation(Main.MODID + ":tile_entity_randomizer_analog"));
        GameRegistry.registerTileEntity(TileEntityResistor.class, new ResourceLocation(Main.MODID + ":tile_entity_resistor"));
        GameRegistry.registerTileEntity(TileEntityTFlipFlop.class, new ResourceLocation(Main.MODID + ":tile_entity_tflip_flop"));
        GameRegistry.registerTileEntity(TileEntityBridgeOutput.class, new ResourceLocation(Main.MODID + ":tile_entity_bridge_output"));
    }
}

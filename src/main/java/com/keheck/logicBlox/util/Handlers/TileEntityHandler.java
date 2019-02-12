package com.keheck.logicBlox.util.Handlers;

import com.keheck.logicBlox.Main;
import com.keheck.logicBlox.ModElements.TileEntitys.*;
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

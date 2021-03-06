package mod.keheck.logicblox;

import mod.keheck.logicblox.proxy.ClientProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static mod.keheck.logicblox.Main.MODID;
import static mod.keheck.logicblox.Main.NAME;
import static mod.keheck.logicblox.Main.VERSION;

@Mod(modid = MODID, version = VERSION, name = NAME)
public class Main
{
    @Mod.Instance
    public static Main instance;

    //Metadata
    public static final String MODID = "logicblox";
    static final String VERSION = "0.7";
    static final String NAME = "Logic Blox 1.12.2";

    //Initialization stuff
    @SidedProxy(clientSide = "mod.keheck.logicblox.proxy.ClientProxy", serverSide = "mod.keheck.logicblox.proxy.CommonProxy")
    public static ClientProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}

package net.pm_equips.client;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.pm_equips.ItemInit;
import net.pm_equips.PMEquipsMain;

import java.util.List;


@Mod.EventBusSubscriber(
        modid = PMEquipsMain.MOD_ID,
        value = Dist.CLIENT)
public class ADSPoseHandler
{
    private static final List<RegistryObject<Item>> ADS_ITEMS =
            List.of(
                    ItemInit.W4_HORNET,
                    ItemInit.W4_MAGIC_BULLET,
                    ItemInit.W4_SOLEMN_LAMENT_R,
                    ItemInit.W3_LAETITIA,
                    ItemInit.W3_HARMONY,
                    ItemInit.W2_BEAK,
                    ItemInit.W2_FOURTH_MATCH_FIRE,
                    ItemInit.WEAPON_ROLAND_REVOLVER,
                    ItemInit.WEAPON_ROLAND_SHOTGUN,
                    ItemInit.RCORP_RABBIT_RIFLE
            );

    @SubscribeEvent
    public static void onPlayerRenderPre(RenderPlayerEvent.Pre event)
    {
        ItemStack stack = event.getEntity().getMainHandItem();

        boolean isADSWeapon = ADS_ITEMS.stream()
                .anyMatch(obj -> obj.get() == stack.getItem());

        if(!isADSWeapon)
        {
            return;
        }

        PlayerModel<?> model =
                event.getRenderer().getModel();

        // 右腕
        model.rightArm.xRot = (float)Math.toRadians(-100.0D);
        model.rightArm.yRot = (float)Math.toRadians(-10.0D);

        // 左腕
        model.leftArm.xRot = (float)Math.toRadians(-90.0D);
        model.leftArm.yRot = (float)Math.toRadians(15.0D);
    }

    @SubscribeEvent
    public static void onPlayerRenderPost(RenderPlayerEvent.Post event)
    {
        Player player = event.getEntity();

        ItemStack stack = player.getMainHandItem();

        if(stack.getItem() != ItemInit.W4_MAGIC_BULLET.get())
        {
            return;
        }

        PlayerModel<?> model =
                event.getRenderer().getModel();

        model.rightArm.xRot = -100.0F;
        model.rightArm.yRot = -10.0F;

        model.leftArm.xRot = -90.0F;
        model.leftArm.yRot = 15.0F;
    }
}

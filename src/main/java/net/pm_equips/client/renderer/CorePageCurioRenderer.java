package net.pm_equips.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.pm_equips.client.models.CorePageModel;
import net.pm_equips.items.CorePageItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class CorePageCurioRenderer implements ICurioRenderer {
    private final GeoArmorRenderer<CorePageItem> armorRenderer = new GeoArmorRenderer<>(new CorePageModel());

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext,
            PoseStack poseStack, RenderLayerParent<T, M> parent, MultiBufferSource buffers, int light,
            float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!(parent.getModel() instanceof HumanoidModel<?> humanoidModel)) return;
        armorRenderer.prepForRender(slotContext.entity(), stack, EquipmentSlot.CHEST, humanoidModel);
        armorRenderer.renderToBuffer(poseStack, null, light, net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY,
                1.0f, 1.0f, 1.0f, 1.0f);
        armorRenderer.prepForRender(slotContext.entity(), stack, EquipmentSlot.LEGS, humanoidModel);
        armorRenderer.renderToBuffer(poseStack, null, light, net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY,
                1.0f, 1.0f, 1.0f, 1.0f);
    }
}

package com.supermartijn642.packedup;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * Created 2/7/2020 by SuperMartijn642
 */
public class BackpackContainerScreen extends ContainerScreen<BackpackContainer> {

    private final ResourceLocation texture;

    public BackpackContainerScreen(BackpackContainer container, PlayerInventory inventory, ITextComponent name){
        super(container, inventory, name);
        this.texture = new ResourceLocation("packedup", "textures/inventory/rows" + container.rows + ".png");
        this.xSize = 176 + Math.max(0, (container.rows - 9) * 18);
        this.ySize = 112 + 18 * Math.min(8, container.rows);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY){
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(this.texture);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY){
        if(container.rows >= 9)
            return;
        this.font.func_243248_b(matrixStack, this.title, 8.0F, 6.0F, 4210752);
        this.font.func_243248_b(matrixStack, this.playerInventory.getDisplayName(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
    }
}

package com.supermartijn642.packedup;

import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.gui.BaseContainerScreen;
import com.supermartijn642.core.gui.ScreenUtils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * Created 2/7/2020 by SuperMartijn642
 */
public class BackpackContainerScreen extends BaseContainerScreen<BackpackContainer> {

    private static final ResourceLocation CORNERS = new ResourceLocation("packedup", "textures/corners.png");

    private final String displayName;

    public BackpackContainerScreen(BackpackContainer container, ITextComponent name){
        super(container, name);
        this.displayName = trimText(name, container.type.getColumns() * 18);
    }

    @Override
    protected int sizeX(){
        return this.menu.type.getColumns() > 9 ? 176 + (this.menu.type.getColumns() - 9) * 18 : 176;
    }

    @Override
    protected int sizeY(){
        return 112 + 18 * this.menu.type.getRows();
    }

    @Override
    protected void addWidgets(){
    }

    @Override
    protected void renderBackground(int mouseX, int mouseY){
        if(this.menu.type.getColumns() == 9)
            this.drawScreenBackground();
        else{
            int width = this.menu.type.getColumns() * 18 + 14;
            int offset = (this.sizeX() - width) / 2;
            int height = this.menu.type.getRows() * 18 + 23;
            ScreenUtils.drawScreenBackground(offset, 0, width, height);
            ScreenUtils.drawScreenBackground(Math.max(0, (width - 176) / 2f), height - 9, 176, this.sizeY() - height + 9);
            ScreenUtils.bindTexture(CORNERS);
            if(this.menu.type.getColumns() > 9){
                ScreenUtils.drawTexture(Math.max(0, (width - 176) / 2f), height - 3, 3, 3, 0, 0, 0.5f, 0.5f);
                ScreenUtils.drawTexture(Math.max(0, (width - 176) / 2f) + 176 - 3, height - 3, 3, 3, 0.5f, 0, 0.5f, 0.5f);
                ScreenUtils.fillRect(Math.max(0, (width - 176) / 2f), height - 9, 176, 6, 0xffC6C6C6);
            }else{
                ScreenUtils.drawTexture(offset, height - 9, 3, 3, 0, 0.5f, 0.5f, 0.5f);
                ScreenUtils.drawTexture(offset + width - 3, height - 9, 3, 3, 0.5f, 0.5f, 0.5f, 0.5f);
                ScreenUtils.fillRect(offset + 3, height - 9, width - 6, 3, 0xffC6C6C6);
            }
        }
    }

    @Override
    protected void renderForeground(int mouseX, int mouseY){
        int offset = (this.menu.type.getColumns() - 9) * 18 / 2;
        ScreenUtils.drawString(this.displayName, 8.0F - Math.min(0, offset), 6.0F, 4210752);
        ScreenUtils.drawString(this.inventory.getDisplayName(), 8.0F + Math.max(0, offset), this.sizeY() - 96 + 3, 4210752);
    }

    private static String trimText(ITextComponent textComponent, int width){
        String text = TextComponents.format(textComponent);
        FontRenderer font = ClientUtils.getFontRenderer();
        int length = 0;
        while(length < text.length() && font.width(text.substring(0, length + 1) + "...") < width)
            length++;
        return length < text.length() ? text.substring(0, length) + "..." : text;
    }
}

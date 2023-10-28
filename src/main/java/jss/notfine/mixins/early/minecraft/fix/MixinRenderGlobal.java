package jss.notfine.mixins.early.minecraft.fix;

import jss.notfine.core.SettingsManager;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.culling.Frustrum;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = EntityRenderer.class, priority = 1)
abstract public class MixinRenderGlobal {

    /*@Redirect(
    method = "renderWorld(FJ)V",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V",
            remap = false
        )
    )
    private void notFine$fixDepthMask(boolean blendState) {

    }*/

    /**
     * @author jss2a98aj
     * @reason Fix alpha sorting.
     */
    @Overwrite
    public void renderWorld(float p_78471_1_, long p_78471_2_) {
        mc.mcProfiler.startSection("lightTex");

        if(lightmapUpdateNeeded) {
            updateLightmap(p_78471_1_);
        }

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.5F);

        if(mc.renderViewEntity == null) {
            mc.renderViewEntity = mc.thePlayer;
        }

        mc.mcProfiler.endStartSection("pick");
        getMouseOver(p_78471_1_);
        EntityLivingBase entitylivingbase = mc.renderViewEntity;
        RenderGlobal renderglobal = mc.renderGlobal;
        EffectRenderer effectrenderer = mc.effectRenderer;
        double d0 = entitylivingbase.lastTickPosX + (entitylivingbase.posX - entitylivingbase.lastTickPosX) * (double)p_78471_1_;
        double d1 = entitylivingbase.lastTickPosY + (entitylivingbase.posY - entitylivingbase.lastTickPosY) * (double)p_78471_1_;
        double d2 = entitylivingbase.lastTickPosZ + (entitylivingbase.posZ - entitylivingbase.lastTickPosZ) * (double)p_78471_1_;
        mc.mcProfiler.endStartSection("center");

        int eyes = mc.gameSettings.anaglyph ? 2: 1;
        for(int eye = 0; eye < eyes; ++eye) {
            if(mc.gameSettings.anaglyph) {
                anaglyphField = eye;
                if(anaglyphField == 0) {
                    GL11.glColorMask(false, true, true, false);
                } else {
                    GL11.glColorMask(true, false, false, false);
                }
            }

            mc.mcProfiler.endStartSection("clear");
            GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
            updateFogColor(p_78471_1_);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glEnable(GL11.GL_CULL_FACE);
            mc.mcProfiler.endStartSection("camera");
            setupCameraTransform(p_78471_1_, eye);
            ActiveRenderInfo.updateRenderInfo(mc.thePlayer, mc.gameSettings.thirdPersonView == 2);
            mc.mcProfiler.endStartSection("frustrum");
            ClippingHelperImpl.getInstance();

            mc.mcProfiler.endStartSection("sky");
            setupFog(-1, p_78471_1_);
            renderglobal.renderSky(p_78471_1_);

            GL11.glEnable(GL11.GL_FOG);
            setupFog(1, p_78471_1_);
            if(mc.gameSettings.ambientOcclusion != 0) {
                GL11.glShadeModel(GL11.GL_SMOOTH);
            }

            mc.mcProfiler.endStartSection("culling");
            Frustrum frustrum = new Frustrum();
            frustrum.setPosition(d0, d1, d2);
            mc.renderGlobal.clipRenderersByFrustum(frustrum, p_78471_1_);

            if(eye == 0) {
                mc.mcProfiler.endStartSection("updatechunks");
                while(!mc.renderGlobal.updateRenderers(entitylivingbase, false) && p_78471_2_ != 0L) {
                    long k = p_78471_2_ - System.nanoTime();
                    if(k < 0L || k > 1000000000L) {
                        break;
                    }
                }
            }

            if(entitylivingbase.posY < SettingsManager.cloudTranslucencyCheck) {
                renderCloudsCheck(renderglobal, p_78471_1_);
            }

            mc.mcProfiler.endStartSection("prepareterrain");
            setupFog(0, p_78471_1_);
            GL11.glEnable(GL11.GL_FOG);
            mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            RenderHelper.disableStandardItemLighting();
            mc.mcProfiler.endStartSection("terrain");
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glPushMatrix();
            renderglobal.sortAndRender(entitylivingbase, 0, p_78471_1_);
            GL11.glShadeModel(GL11.GL_FLAT);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            EntityPlayer entityplayer;

            if(debugViewDirection == 0) {
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                RenderHelper.enableStandardItemLighting();
                mc.mcProfiler.endStartSection("entities");
                net.minecraftforge.client.ForgeHooksClient.setRenderPass(0);
                renderglobal.renderEntities(entitylivingbase, frustrum, p_78471_1_);
                net.minecraftforge.client.ForgeHooksClient.setRenderPass(0);
                RenderHelper.disableStandardItemLighting();
                disableLightmap(p_78471_1_);
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glPopMatrix();
                GL11.glPushMatrix();

                if(mc.objectMouseOver != null && !mc.gameSettings.hideGUI && entitylivingbase instanceof EntityPlayer && entitylivingbase.isInsideOfMaterial(Material.water)) {
                    mc.mcProfiler.endStartSection("outline");
                    entityplayer = (EntityPlayer)entitylivingbase;
                    GL11.glDisable(GL11.GL_ALPHA_TEST);
                    if(!ForgeHooksClient.onDrawBlockHighlight(renderglobal, entityplayer, mc.objectMouseOver, 0, entityplayer.inventory.getCurrentItem(), p_78471_1_))  {
                        renderglobal.drawSelectionBox(entityplayer, mc.objectMouseOver, 0, p_78471_1_);
                    }
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                }
            }

            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glPopMatrix();

            if(cameraZoom == 1.0D && !mc.gameSettings.hideGUI && mc.objectMouseOver != null  && entitylivingbase instanceof EntityPlayer&& !entitylivingbase.isInsideOfMaterial(Material.water))  {
                mc.mcProfiler.endStartSection("outline");
                entityplayer = (EntityPlayer)entitylivingbase;
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                if(!ForgeHooksClient.onDrawBlockHighlight(renderglobal, entityplayer, mc.objectMouseOver, 0, entityplayer.inventory.getCurrentItem(), p_78471_1_)) {
                    renderglobal.drawSelectionBox(entityplayer, mc.objectMouseOver, 0, p_78471_1_);
                }
                GL11.glEnable(GL11.GL_ALPHA_TEST);
            }

            mc.mcProfiler.endStartSection("destroyProgress");
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 1, 1, 0);
            renderglobal.drawBlockDamageTexture(Tessellator.instance, entitylivingbase, p_78471_1_);

            mc.mcProfiler.endStartSection("water");
            mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            if(SettingsManager.waterDetail) {
                if(mc.gameSettings.ambientOcclusion != 0) {
                    GL11.glShadeModel(GL11.GL_SMOOTH);
                }
                GL11.glEnable(GL11.GL_BLEND);
                OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                if(mc.gameSettings.anaglyph) {
                    if(anaglyphField == 0) {
                        GL11.glColorMask(false, true, true, true);
                    } else {
                        GL11.glColorMask(true, false, false, true);
                    }
                }
            }
            renderglobal.sortAndRender(entitylivingbase, 1, p_78471_1_);
            //GL11.glDisable(GL11.GL_BLEND);
            GL11.glShadeModel(GL11.GL_FLAT);

            if(debugViewDirection == 0) {
                mc.mcProfiler.endStartSection("litParticles");
                enableLightmap(p_78471_1_);
                effectrenderer.renderLitParticles(entitylivingbase, p_78471_1_);
                RenderHelper.disableStandardItemLighting();
                setupFog(0, p_78471_1_);
                mc.mcProfiler.endStartSection("particles");
                effectrenderer.renderParticles(entitylivingbase, p_78471_1_);
                disableLightmap(p_78471_1_);
            }

            GL11.glEnable(GL11.GL_CULL_FACE);
            mc.mcProfiler.endStartSection("weather");
            renderRainSnow(p_78471_1_);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_CULL_FACE);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            setupFog(0, p_78471_1_);

            if(debugViewDirection == 0) {
                mc.mcProfiler.endStartSection("entities");
                RenderHelper.enableStandardItemLighting();
                ForgeHooksClient.setRenderPass(1);
                renderglobal.renderEntities(entitylivingbase, frustrum, p_78471_1_);
                ForgeHooksClient.setRenderPass(-1);
                RenderHelper.disableStandardItemLighting();
            }

            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_FOG);

            if(entitylivingbase.posY >= SettingsManager.cloudTranslucencyCheck) {
                mc.mcProfiler.endStartSection("aboveClouds");
                renderCloudsCheck(renderglobal, p_78471_1_);
            }

            mc.mcProfiler.endStartSection("FRenderLast");
            ForgeHooksClient.dispatchRenderLast(renderglobal, p_78471_1_);

            mc.mcProfiler.endStartSection("hand");

            if(!ForgeHooksClient.renderFirstPersonHand(renderglobal, p_78471_1_, eye) && cameraZoom == 1.0D) {
                GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
                renderHand(p_78471_1_, eye);
            }
        }

        if(mc.gameSettings.anaglyph) {
            GL11.glColorMask(true, true, true, false);
        }
        mc.mcProfiler.endSection();
    }

    @Shadow Minecraft mc;
    @Shadow boolean lightmapUpdateNeeded;
    @Shadow public static int anaglyphField;
    @Shadow public int debugViewDirection;
    @Shadow double cameraZoom;

    @Shadow void updateLightmap(float p_78472_1_) {}
    @Shadow abstract public void getMouseOver(float p_78473_1_);
    @Shadow void updateFogColor(float p_78466_1_) {};
    @Shadow void setupCameraTransform(float p_78479_1_, int p_78479_2_) {}
    @Shadow void setupFog(int p_78468_1_, float p_78468_2_) {}
    @Shadow void renderCloudsCheck(RenderGlobal p_82829_1_, float p_82829_2_) {}
    @Shadow abstract public void disableLightmap(double p_78483_1_);
    @Shadow abstract public void enableLightmap(double p_78463_1_);
    @Shadow protected void renderRainSnow(float p_78474_1_) {}
    @Shadow void renderHand(float p_78476_1_, int p_78476_2_) {}

}

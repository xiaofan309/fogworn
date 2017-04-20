/*  1:   */ package com.base.utils;
/*  2:   */ 
/*  3:   */ import java.awt.image.BufferedImage;
/*  4:   */ import java.io.InputStream;
/*  5:   */ import javax.imageio.ImageIO;
/*  6:   */ 
/*  7:   */ public class ImageRange
/*  8:   */ {
/*  9:   */   private int width;
/* 10:   */   private int height;
/* 11:   */   
/* 12:   */   public ImageRange(String picUrl)
/* 13:   */     throws Exception
/* 14:   */   {
/* 15:24 */     InputStream input = null;
/* 16:   */     try
/* 17:   */     {
/* 18:26 */       input = JMagickUtils.Utils.ofUrl(picUrl, picUrl).getInput();
/* 19:27 */       size(input);
/* 20:   */     }
/* 21:   */     catch (Exception e)
/* 22:   */     {
/* 23:30 */       input = JMagickUtils.Utils.ofUrl(picUrl).getInput();
/* 24:31 */       size(input);
/* 25:   */     }
/* 26:   */   }
/* 27:   */   
/* 28:   */   public ImageRange(InputStream input)
/* 29:   */     throws Exception
/* 30:   */   {
/* 31:41 */     size(input);
/* 32:   */   }
/* 33:   */   
/* 34:   */   private void size(InputStream input)
/* 35:   */     throws Exception
/* 36:   */   {
/* 37:51 */     BufferedImage image = null;
/* 38:   */     try
/* 39:   */     {
/* 40:53 */       image = ImageIO.read(input);
/* 41:54 */       this.width = image.getWidth();
/* 42:55 */       this.height = image.getHeight();
/* 43:56 */       input.close();
/* 44:   */     }
/* 45:   */     catch (Exception e)
/* 46:   */     {
/* 47:58 */       e.printStackTrace();
/* 48:59 */       throw e;
/* 49:   */     }
/* 50:   */     finally
/* 51:   */     {
/* 52:61 */       input = null;
/* 53:62 */       image = null;
/* 54:   */     }
/* 55:   */   }
/* 56:   */   
/* 57:   */   public int getMinHeight()
/* 58:   */   {
/* 59:70 */     int h = 0;int target_width = 200;
/* 60:71 */     h = (int)Math.round(this.height * target_width * 1.0D / this.width);
/* 61:72 */     return h;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public int getHeight()
/* 65:   */   {
/* 66:80 */     return this.height;
/* 67:   */   }
/* 68:   */   
/* 69:   */   public int getWidth()
/* 70:   */   {
/* 71:89 */     return this.width;
/* 72:   */   }
/* 73:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.ImageRange
 * JD-Core Version:    0.7.0.1
 */
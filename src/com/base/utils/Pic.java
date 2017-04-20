/*   1:    */ package com.base.utils;
/*   2:    */ 
/*   3:    */ import java.awt.image.BufferedImage;
/*   4:    */ import java.io.IOException;
/*   5:    */ import java.io.InputStream;
/*   6:    */ import java.io.OutputStream;
/*   7:    */ import javax.imageio.ImageIO;
/*   8:    */ 
/*   9:    */ class Pic
/*  10:    */ {
/*  11:    */   private OutputStream outputStream;
/*  12:    */   private InputStream inputStream;
/*  13:    */   
/*  14:    */   public BufferedImage asBufferImg()
/*  15:    */     throws IOException
/*  16:    */   {
/*  17:463 */     if (this.inputStream == null) {
/*  18:464 */       throw new IOException("file not exist!");
/*  19:    */     }
/*  20:466 */     return ImageIO.read(this.inputStream);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public OutputStream getOutputStream()
/*  24:    */   {
/*  25:470 */     return this.outputStream;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public void setOutputStream(OutputStream outputStream)
/*  29:    */   {
/*  30:474 */     this.outputStream = outputStream;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public InputStream getInputStream()
/*  34:    */   {
/*  35:478 */     return this.inputStream;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public void setInputStream(InputStream inputStream)
/*  39:    */   {
/*  40:483 */     this.inputStream = inputStream;
/*  41:    */   }
/*  42:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.Pic
 * JD-Core Version:    0.7.0.1
 */
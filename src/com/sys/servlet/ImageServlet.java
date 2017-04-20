/*   1:    */ package com.sys.servlet;
/*   2:    */ 
/*   3:    */ import com.base.utils.SessionUtils;
/*   4:    */ import java.awt.Color;
/*   5:    */ import java.awt.Font;
/*   6:    */ import java.awt.Graphics2D;
/*   7:    */ import java.awt.image.BufferedImage;
/*   8:    */ import java.io.IOException;
/*   9:    */ import java.util.Random;
/*  10:    */ import javax.imageio.ImageIO;
/*  11:    */ import javax.servlet.ServletException;
/*  12:    */ import javax.servlet.ServletOutputStream;
/*  13:    */ import javax.servlet.http.HttpServlet;
/*  14:    */ import javax.servlet.http.HttpServletRequest;
/*  15:    */ import javax.servlet.http.HttpServletResponse;
/*  16:    */ 
/*  17:    */ public class ImageServlet
/*  18:    */   extends HttpServlet
/*  19:    */ {
/*  20:    */   private static final long serialVersionUID = 1L;
/*  21: 27 */   private int width = 60;
/*  22: 30 */   private int height = 20;
/*  23: 33 */   private int codeCount = 4;
/*  24: 35 */   private int x = 0;
/*  25:    */   private int fontHeight;
/*  26:    */   private int codeY;
/*  27: 42 */   char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
/*  28: 43 */     'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
/*  29: 44 */     'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 
/*  30: 45 */     'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
/*  31: 46 */     '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
/*  32:    */   
/*  33:    */   protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
/*  34:    */     throws ServletException, IOException
/*  35:    */   {}
/*  36:    */   
/*  37:    */   protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1)
/*  38:    */     throws ServletException, IOException
/*  39:    */   {
/*  40: 57 */     doPost(arg0, arg1);
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void init()
/*  44:    */     throws ServletException
/*  45:    */   {
/*  46: 63 */     String strWidth = getInitParameter("width");
/*  47:    */     
/*  48: 65 */     String strHeight = getInitParameter("height");
/*  49:    */     
/*  50: 67 */     String strCodeCount = getInitParameter("codeCount");
/*  51:    */     try
/*  52:    */     {
/*  53: 72 */       if ((strWidth != null) && (strWidth.length() != 0)) {
/*  54: 73 */         this.width = Integer.parseInt(strWidth);
/*  55:    */       }
/*  56: 75 */       if ((strHeight != null) && (strHeight.length() != 0)) {
/*  57: 76 */         this.height = Integer.parseInt(strHeight);
/*  58:    */       }
/*  59: 78 */       if ((strCodeCount != null) && (strCodeCount.length() != 0)) {
/*  60: 79 */         this.codeCount = Integer.parseInt(strCodeCount);
/*  61:    */       }
/*  62:    */     }
/*  63:    */     catch (NumberFormatException localNumberFormatException) {}
/*  64: 83 */     this.x = (this.width / (this.codeCount + 1));
/*  65: 84 */     this.fontHeight = this.height;
/*  66: 85 */     this.codeY = this.height;
/*  67:    */   }
/*  68:    */   
/*  69:    */   protected void service(HttpServletRequest req, HttpServletResponse resp)
/*  70:    */     throws ServletException, IOException
/*  71:    */   {
/*  72: 91 */     BufferedImage buffImg = new BufferedImage(this.width, this.height, 
/*  73: 92 */       1);
/*  74: 93 */     Graphics2D g = buffImg.createGraphics();
/*  75:    */     
/*  76: 95 */     Random random = new Random();
/*  77:    */     
/*  78: 97 */     g.setColor(getRandColor(220, 250));
/*  79: 98 */     g.fillRect(0, 0, this.width, this.height);
/*  80:    */     
/*  81:100 */     Font font = new Font("黑体", 1, this.fontHeight - 5);
/*  82:    */     
/*  83:102 */     g.setFont(font);
/*  84:    */     
/*  85:    */ 
/*  86:    */ 
/*  87:    */ 
/*  88:    */ 
/*  89:    */ 
/*  90:    */ 
/*  91:    */ 
/*  92:    */ 
/*  93:    */ 
/*  94:    */ 
/*  95:    */ 
/*  96:    */ 
/*  97:    */ 
/*  98:    */ 
/*  99:118 */     g.setColor(getRandColor(120, 200));
/* 100:119 */     for (int i = 0; i < 550; i++)
/* 101:    */     {
/* 102:120 */       int x = random.nextInt(this.width);
/* 103:121 */       int y = random.nextInt(this.height);
/* 104:122 */       g.drawOval(x, y, 0, 0);
/* 105:    */     }
/* 106:126 */     StringBuffer randomCode = new StringBuffer();
/* 107:128 */     for (int i = 0; i < this.codeCount; i++)
/* 108:    */     {
/* 109:130 */       String strRand = String.valueOf(this.codeSequence[random.nextInt(62)]);
/* 110:    */       
/* 111:132 */       g.setColor(getRandColor(20, 130));
/* 112:    */       
/* 113:134 */       g.drawString(strRand, (i + 1) * this.x - 7, this.codeY - 5);
/* 114:    */       
/* 115:136 */       randomCode.append(strRand);
/* 116:    */     }
/* 117:141 */     SessionUtils.setValidateCode(req, randomCode.toString().toLowerCase());
/* 118:    */     
/* 119:143 */     resp.setHeader("Pragma", "no-cache");
/* 120:144 */     resp.setHeader("Cache-Control", "no-cache");
/* 121:145 */     resp.setDateHeader("Expires", 0L);
/* 122:146 */     resp.setContentType("image/jpeg");
/* 123:    */     
/* 124:148 */     ServletOutputStream sos = resp.getOutputStream();
/* 125:149 */     ImageIO.write(buffImg, "jpeg", sos);
/* 126:150 */     sos.close();
/* 127:    */   }
/* 128:    */   
/* 129:    */   public static Color getRandColor(int num1, int num2)
/* 130:    */   {
/* 131:161 */     Random random = new Random();
/* 132:162 */     if (num1 > 255) {
/* 133:163 */       num1 = 255;
/* 134:    */     }
/* 135:164 */     if (num2 > 255) {
/* 136:165 */       num2 = 255;
/* 137:    */     }
/* 138:166 */     int r = num1 + random.nextInt(num2 - num1);
/* 139:167 */     int g = num1 + random.nextInt(num2 - num1);
/* 140:168 */     int b = num1 + random.nextInt(num2 - num1);
/* 141:169 */     return new Color(r, g, b);
/* 142:    */   }
/* 143:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.servlet.ImageServlet
 * JD-Core Version:    0.7.0.1
 */
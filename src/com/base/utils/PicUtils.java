/*   1:    */ package com.base.utils;
/*   2:    */ 
/*   3:    */ import com.base.exception.ServiceException;
/*   4:    */ import com.sun.image.codec.jpeg.JPEGCodec;
/*   5:    */ import com.sun.image.codec.jpeg.JPEGImageEncoder;
/*   6:    */ import java.awt.Graphics;
/*   7:    */ import java.awt.Graphics2D;
/*   8:    */ import java.awt.Image;
/*   9:    */ import java.awt.image.BufferedImage;
/*  10:    */ import java.io.File;
/*  11:    */ import java.io.FileInputStream;
/*  12:    */ import java.io.FileOutputStream;
/*  13:    */ import java.io.IOException;
/*  14:    */ import java.io.InputStream;
/*  15:    */ import java.io.OutputStream;
/*  16:    */ import java.io.PrintStream;
/*  17:    */ import java.io.Serializable;
/*  18:    */ import java.net.HttpURLConnection;
/*  19:    */ import java.net.URL;
/*  20:    */ import java.net.URLConnection;
/*  21:    */ import javax.imageio.ImageIO;
/*  22:    */ 
/*  23:    */ public class PicUtils
/*  24:    */   implements Serializable
/*  25:    */ {
/*  26:    */   private static final long serialVersionUID = 1L;
/*  27: 36 */   private static Pic pic = new Pic();
/*  28:    */   private static final String YIYA_LOGO_BG = "/static/common/images/watermark_bg.png";
/*  29:    */   private static final String YIYA_LOGO_LOGO = "/static/common/images/watermark_logo.png";
/*  30: 41 */   public static PicUtils Utils = new PicUtils();
/*  31:    */   
/*  32:    */   public static void crop(String path, int width, int height)
/*  33:    */     throws IOException, Exception
/*  34:    */   {
/*  35: 52 */     File file = new File(path);
/*  36: 53 */     if (!file.exists())
/*  37:    */     {
/*  38: 54 */       System.out.println("目录不能存在！");
/*  39: 55 */       return;
/*  40:    */     }
/*  41: 57 */     if (!file.isDirectory())
/*  42:    */     {
/*  43: 58 */       System.out.println("不是正确文件夹");
/*  44: 59 */       return;
/*  45:    */     }
/*  46: 61 */     for (File f : file.listFiles())
/*  47:    */     {
/*  48: 62 */       String minPath = PathUtil.minPicPath(f.getAbsolutePath(), width + "_" + height);
/*  49: 63 */       Utils.of(f).to(minPath).createThumbnail(width, height);
/*  50: 64 */       System.out.println("创建小图" + minPath);
/*  51:    */     }
/*  52:    */   }
/*  53:    */   
/*  54:    */   public static void caiji(String url, int count)
/*  55:    */   {
/*  56: 74 */     for (int i = 0; i < count; i++)
/*  57:    */     {
/*  58: 75 */       String fileName = i + 1 + ".jpg";
/*  59: 76 */       String picUrl = url + fileName;
/*  60: 77 */       String path = "C:\\Users\\Administrator\\Desktop\\pic\\0624\\02\\" + fileName;
/*  61:    */       try
/*  62:    */       {
/*  63: 79 */         Utils.ofUrl(picUrl).to(path).create();
/*  64: 80 */         System.out.println(picUrl);
/*  65:    */       }
/*  66:    */       catch (Exception e)
/*  67:    */       {
/*  68: 82 */         e.printStackTrace();
/*  69: 83 */         return;
/*  70:    */       }
/*  71:    */     }
/*  72:    */   }
/*  73:    */   
/*  74:    */   public static void main(String[] args)
/*  75:    */     throws Exception
/*  76:    */   {
/*  77: 94 */     String filePath = "D:\\t\\2.jpg";
/*  78: 95 */     String toPath = "D:\\t\\2_magick.jpg";
/*  79: 96 */     System.out.println("完成");
/*  80:    */   }
/*  81:    */   
/*  82:    */   private static void createDir(String path)
/*  83:    */   {
/*  84:106 */     File file = new File(path);
/*  85:107 */     createDir(file);
/*  86:    */   }
/*  87:    */   
/*  88:    */   private static void createDir(File file)
/*  89:    */   {
/*  90:115 */     if (!file.getParentFile().exists()) {
/*  91:116 */       file.getParentFile().mkdirs();
/*  92:    */     }
/*  93:    */   }
/*  94:    */   
/*  95:    */   public PicUtils ofUrl(String picUrl)
/*  96:    */     throws Exception
/*  97:    */   {
/*  98:127 */     HttpURLConnection conn = (HttpURLConnection)new URL(picUrl).openConnection();
/*  99:128 */     conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon;)");
/* 100:129 */     conn.setRequestProperty("Accept-Encoding", "gzip");
/* 101:130 */     conn.setRequestProperty("referer", picUrl);
/* 102:131 */     conn.setRequestProperty("cookie", "data");
/* 103:132 */     InputStream is = conn.getInputStream();
/* 104:133 */     return of(is);
/* 105:    */   }
/* 106:    */   
/* 107:    */   public static InputStream toInStream(String path)
/* 108:    */     throws IOException
/* 109:    */   {
/* 110:143 */     InputStream stream = new FileInputStream(path);
/* 111:144 */     return stream;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public static InputStream urlToInStream(String picUrl)
/* 115:    */     throws ServiceException
/* 116:    */   {
/* 117:    */     try
/* 118:    */     {
/* 119:157 */       URL url = new URL(picUrl);
/* 120:158 */       URLConnection urlConnection = url.openConnection();
/* 121:159 */       if (!checkPic(urlConnection.getContentType())) {
/* 122:160 */         throw new ServiceException("URL" + picUrl + " is not (jpg|gif|png)");
/* 123:    */       }
/* 124:162 */       return urlConnection.getInputStream();
/* 125:    */     }
/* 126:    */     catch (IOException e)
/* 127:    */     {
/* 128:165 */       throw new ServiceException("URL:" + picUrl + " is connect err");
/* 129:    */     }
/* 130:    */   }
/* 131:    */   
/* 132:    */   public PicUtils of(String srcPath)
/* 133:    */     throws Exception
/* 134:    */   {
/* 135:179 */     File imgfile = new File(srcPath);
/* 136:180 */     return of(imgfile);
/* 137:    */   }
/* 138:    */   
/* 139:    */   public PicUtils of(File srcFile)
/* 140:    */     throws Exception
/* 141:    */   {
/* 142:184 */     InputStream is = new FileInputStream(srcFile);
/* 143:185 */     return of(is);
/* 144:    */   }
/* 145:    */   
/* 146:    */   public PicUtils of(InputStream inputStream)
/* 147:    */     throws Exception
/* 148:    */   {
/* 149:191 */     pic.setInputStream(inputStream);
/* 150:192 */     return this;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public PicUtils to(String targetPath)
/* 154:    */     throws Exception
/* 155:    */   {
/* 156:198 */     File file = new File(targetPath);
/* 157:199 */     return to(file);
/* 158:    */   }
/* 159:    */   
/* 160:    */   public PicUtils to(File targetFile)
/* 161:    */     throws Exception
/* 162:    */   {
/* 163:204 */     createDir(targetFile);
/* 164:205 */     OutputStream os = new FileOutputStream(targetFile);
/* 165:206 */     return to(os);
/* 166:    */   }
/* 167:    */   
/* 168:    */   public PicUtils to(OutputStream outputStream)
/* 169:    */     throws Exception
/* 170:    */   {
/* 171:210 */     pic.setOutputStream(outputStream);
/* 172:211 */     return this;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public void create()
/* 176:    */     throws Exception
/* 177:    */   {
/* 178:216 */     createJPG(pic.getInputStream(), pic.getOutputStream(), true);
/* 179:    */     
/* 180:    */ 
/* 181:219 */     System.out.println("创建成功~~");
/* 182:    */   }
/* 183:    */   
/* 184:    */   public void create(boolean isAddLogo)
/* 185:    */     throws Exception
/* 186:    */   {
/* 187:223 */     createJPG(pic.getInputStream(), pic.getOutputStream(), isAddLogo);
/* 188:    */   }
/* 189:    */   
/* 190:    */   public void createJPG(InputStream is, OutputStream os, boolean isAddLogo)
/* 191:    */     throws Exception
/* 192:    */   {
/* 193:240 */     BufferedImage src = ImageIO.read(is);
/* 194:241 */     int width = src.getWidth();
/* 195:242 */     int height = src.getHeight();
/* 196:    */     
/* 197:244 */     Graphics2D g = src.createGraphics();
/* 198:    */     
/* 199:246 */     File fileBg = new File(PathUtil.getRootPath() + "/static/common/images/watermark_bg.png");
/* 200:247 */     File fileLogo = new File(PathUtil.getRootPath() + "/static/common/images/watermark_logo.png");
/* 201:248 */     System.out.println(fileBg.getAbsolutePath());
/* 202:249 */     System.out.println(fileBg.exists() + ": " + fileLogo.exists());
/* 203:250 */     if ((fileBg.exists()) && (fileLogo.exists()) && (isAddLogo))
/* 204:    */     {
/* 205:252 */       Image watermark_bg = ImageIO.read(fileBg);
/* 206:    */       
/* 207:254 */       Image watermark_logo = ImageIO.read(fileLogo);
/* 208:255 */       if (watermark_logo.getWidth(null) < width)
/* 209:    */       {
/* 210:256 */         g.drawImage(watermark_bg, 0, height - 30, width, 30, null);
/* 211:257 */         g.drawImage(watermark_logo, 0, height - 30, null);
/* 212:    */       }
/* 213:    */     }
/* 214:260 */     g.dispose();
/* 215:261 */     ImageIO.write(src, "JPEG", os);
/* 216:    */   }
/* 217:    */   
/* 218:    */   public InputStream inputStream()
/* 219:    */     throws Exception
/* 220:    */   {
/* 221:272 */     return pic.getInputStream();
/* 222:    */   }
/* 223:    */   
/* 224:    */   public BufferedImage buffereImage()
/* 225:    */     throws Exception
/* 226:    */   {
/* 227:281 */     return ImageIO.read(pic.getInputStream());
/* 228:    */   }
/* 229:    */   
/* 230:    */   public PicUtils createThumbnail(int width, int height)
/* 231:    */     throws IOException
/* 232:    */   {
/* 233:292 */     int x = 0;int y = 0;
/* 234:293 */     return createThumbnail(x, y, width, height);
/* 235:    */   }
/* 236:    */   
/* 237:    */   public PicUtils createThumbnail(int x, int y, int width, int height)
/* 238:    */     throws IOException
/* 239:    */   {
/* 240:305 */     BufferedImage image = get(width, height);
/* 241:306 */     ImageIO.write(image, "JPEG", pic.getOutputStream());
/* 242:307 */     pic.setInputStream(null);
/* 243:308 */     pic.setOutputStream(null);
/* 244:    */     
/* 245:    */ 
/* 246:    */ 
/* 247:312 */     return this;
/* 248:    */   }
/* 249:    */   
/* 250:    */   public BufferedImage get(int width, int height)
/* 251:    */     throws IOException
/* 252:    */   {
/* 253:318 */     BufferedImage srcImg = pic.asBufferImg();
/* 254:319 */     int target_width = width;
/* 255:320 */     int target_height = height;
/* 256:321 */     int imageWidth = srcImg.getWidth();
/* 257:322 */     int imageHeight = srcImg.getHeight();
/* 258:    */     
/* 259:324 */     int w = 0;
/* 260:325 */     int h = 0;
/* 261:326 */     int x = 0;
/* 262:327 */     int y = 0;
/* 263:329 */     if (imageWidth <= imageHeight)
/* 264:    */     {
/* 265:330 */       w = target_width;
/* 266:331 */       h = (int)Math.round(imageHeight * target_width * 1.0D / imageWidth);
/* 267:332 */       if (target_width > h) {
/* 268:333 */         y = (h - height) / 2;
/* 269:    */       }
/* 270:    */     }
/* 271:    */     else
/* 272:    */     {
/* 273:336 */       h = target_height;
/* 274:337 */       w = (int)Math.round(imageWidth * target_height * 1.0D / imageHeight);
/* 275:    */     }
/* 276:342 */     BufferedImage image = new BufferedImage(w, 
/* 277:343 */       h, 1);
/* 278:    */     
/* 279:345 */     image.getGraphics().drawImage(srcImg, x, y, w, h, null);
/* 280:346 */     return image;
/* 281:    */   }
/* 282:    */   
/* 283:    */   public void createThumbnail(String src, String dist, float width, float height)
/* 284:    */   {
/* 285:    */     try
/* 286:    */     {
/* 287:365 */       BufferedImage image = pic.asBufferImg();
/* 288:    */       
/* 289:    */ 
/* 290:368 */       double ratio = 1.0D;
/* 291:370 */       if ((image.getHeight() > height) || (image.getWidth() > width)) {
/* 292:371 */         if (image.getHeight() > image.getWidth()) {
/* 293:372 */           ratio = height / image.getHeight();
/* 294:    */         } else {
/* 295:374 */           ratio = width / image.getWidth();
/* 296:    */         }
/* 297:    */       }
/* 298:378 */       int newWidth = (int)(image.getWidth() * ratio);
/* 299:379 */       int newHeight = (int)(image.getHeight() * ratio);
/* 300:    */       
/* 301:381 */       BufferedImage bfImage = new BufferedImage(newWidth, newHeight, 1);
/* 302:382 */       bfImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, 4), 0, 0, null);
/* 303:    */       
/* 304:384 */       FileOutputStream os = new FileOutputStream(dist);
/* 305:385 */       JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
/* 306:386 */       encoder.encode(bfImage);
/* 307:387 */       os.close();
/* 308:388 */       System.out.println("创建缩略图成功");
/* 309:    */     }
/* 310:    */     catch (Exception e)
/* 311:    */     {
/* 312:390 */       e.printStackTrace();
/* 313:391 */       System.out.println("创建缩略图发生异常" + e.getMessage());
/* 314:    */     }
/* 315:    */   }
/* 316:    */   
/* 317:    */   public static boolean checkPic(String imgtype)
/* 318:    */   {
/* 319:402 */     if (imgtype.equalsIgnoreCase("image/gif")) {
/* 320:403 */       return true;
/* 321:    */     }
/* 322:404 */     if (imgtype.equalsIgnoreCase("image/png")) {
/* 323:405 */       return true;
/* 324:    */     }
/* 325:406 */     if (imgtype.equalsIgnoreCase("image/jpeg")) {
/* 326:407 */       return true;
/* 327:    */     }
/* 328:409 */     return false;
/* 329:    */   }
/* 330:    */   
/* 331:    */   public static String getPostfix(String imgtype)
/* 332:    */   {
/* 333:419 */     if (imgtype.equalsIgnoreCase("image/gif")) {
/* 334:420 */       return ".gif";
/* 335:    */     }
/* 336:421 */     if (imgtype.equalsIgnoreCase("image/png")) {
/* 337:422 */       return ".png";
/* 338:    */     }
/* 339:423 */     if (imgtype.equalsIgnoreCase("image/jpeg")) {
/* 340:424 */       return ".jpg";
/* 341:    */     }
/* 342:426 */     return ".jpg";
/* 343:    */   }
/* 344:    */   
/* 345:    */   public static String getPicfix(String url)
/* 346:    */   {
/* 347:435 */     if (url.endsWith(".gif")) {
/* 348:436 */       return ".gif";
/* 349:    */     }
/* 350:437 */     if (url.endsWith(".png")) {
/* 351:438 */       return ".png";
/* 352:    */     }
/* 353:439 */     if (url.endsWith(".jpg")) {
/* 354:440 */       return ".jpg";
/* 355:    */     }
/* 356:442 */     return ".jpg";
/* 357:    */   }
/* 358:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.PicUtils
 * JD-Core Version:    0.7.0.1
 */
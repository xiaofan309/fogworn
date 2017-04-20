/*   1:    */ package com.base.utils;
/*   2:    */ 
/*   3:    */ import java.io.File;
/*   4:    */ import java.io.PrintStream;
/*   5:    */ import java.io.UnsupportedEncodingException;
/*   6:    */ import java.net.URLDecoder;
/*   7:    */ import java.util.Date;
/*   8:    */ import org.apache.commons.lang.StringUtils;
/*   9:    */ import org.apache.log4j.Logger;
/*  10:    */ 
/*  11:    */ public class PathUtil
/*  12:    */ {
/*  13: 22 */   private static Logger log = Logger.getLogger(PathUtil.class);
/*  14:    */   
/*  15:    */   public static String getRootPath()
/*  16:    */   {
/*  17: 39 */     return Constant.WORK_ROOT_PATH;
/*  18:    */   }
/*  19:    */   
/*  20:    */   public static String getBasePath()
/*  21:    */   {
/*  22: 48 */     String str = Constant.SSI_WEBSITE_URL;
/*  23: 49 */     if (StringUtils.isNotBlank(str)) {
/*  24:    */       try
/*  25:    */       {
/*  26: 51 */         return URLDecoder.decode(str, "utf-8");
/*  27:    */       }
/*  28:    */       catch (UnsupportedEncodingException e)
/*  29:    */       {
/*  30: 53 */         log.error("获取根路径异常：", e);
/*  31:    */       }
/*  32:    */     }
/*  33: 56 */     return str;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public static String getBasePath(String patth)
/*  37:    */   {
/*  38: 64 */     return getBasePath() + patth;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public static String classifyUrl(Long classifyId, Integer pageId)
/*  42:    */   {
/*  43: 75 */     if ((pageId == null) || (pageId.intValue() < 1)) {
/*  44: 76 */       pageId = Integer.valueOf(1);
/*  45:    */     }
/*  46: 78 */     StringBuffer url = new StringBuffer(getBasePath());
/*  47: 80 */     if ((classifyId != null) && (classifyId.longValue() > 1L)) {
/*  48: 81 */       url.append("classify/").append(classifyId).append("-");
/*  49:    */     } else {
/*  50: 83 */       url.append("list/");
/*  51:    */     }
/*  52: 85 */     url.append(pageId).append(".html");
/*  53: 86 */     return url.toString();
/*  54:    */   }
/*  55:    */   
/*  56:    */   public static String albumUrl(Long albumId, Integer pageId)
/*  57:    */   {
/*  58: 96 */     if ((pageId == null) || (pageId.intValue() < 1)) {
/*  59: 97 */       pageId = Integer.valueOf(1);
/*  60:    */     }
/*  61: 99 */     StringBuffer url = new StringBuffer(getBasePath());
/*  62:100 */     url.append("album/").append(albumId);
/*  63:101 */     url.append("-").append(pageId).append(".html");
/*  64:102 */     return url.toString();
/*  65:    */   }
/*  66:    */   
/*  67:    */   public static String picPageUrl(Long picId)
/*  68:    */   {
/*  69:111 */     StringBuffer url = new StringBuffer(getBasePath());
/*  70:112 */     url.append("pic/").append(picId).append(".html");
/*  71:113 */     return url.toString();
/*  72:    */   }
/*  73:    */   
/*  74:    */   public static String messageUrl(Integer pageId)
/*  75:    */   {
/*  76:122 */     StringBuffer url = new StringBuffer(getBasePath());
/*  77:123 */     url.append("message/");
/*  78:124 */     if (pageId.intValue() > 1) {
/*  79:125 */       url.append(pageId).append(".html");
/*  80:    */     }
/*  81:127 */     return url.toString();
/*  82:    */   }
/*  83:    */   
/*  84:    */   public static String getImageBaseURL()
/*  85:    */   {
/*  86:140 */     return Constant.UPLOAD_URL;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public static String pic(String picUrl)
/*  90:    */   {
/*  91:151 */     if (StringUtils.isNotBlank(picUrl))
/*  92:    */     {
/*  93:153 */       picUrl = picUrl.replaceAll("\\\\", "/");
/*  94:154 */       return getImageURL(picUrl);
/*  95:    */     }
/*  96:156 */     return "";
/*  97:    */   }
/*  98:    */   
/*  99:    */   public static String minPic(String picUrl, String size)
/* 100:    */   {
/* 101:167 */     if (StringUtils.isNotBlank(size)) {
/* 102:168 */       picUrl = minPicPath(picUrl, size);
/* 103:    */     }
/* 104:170 */     return pic(picUrl);
/* 105:    */   }
/* 106:    */   
/* 107:    */   public static String getImageURL(String picUrl)
/* 108:    */   {
/* 109:185 */     if (picUrl.indexOf("http://") < 0) {
/* 110:186 */       return getImageBaseURL() + picUrl;
/* 111:    */     }
/* 112:188 */     return picUrl;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public static String uploadPicPath(String fileName, String sizeDir)
/* 116:    */   {
/* 117:199 */     Date now = new Date();
/* 118:200 */     StringBuffer path = new StringBuffer();
/* 119:    */     
/* 120:202 */     path.append("images").append(File.separatorChar);
/* 121:203 */     path.append(DateUtil.convertDateToYear(now)).append(File.separatorChar);
/* 122:204 */     path.append(DateUtil.convertDateToMonth(now)).append(File.separatorChar);
/* 123:205 */     path.append(DateUtil.convertDateToDay(now)).append(File.separatorChar);
/* 124:206 */     path.append(sizeDir).append(File.separatorChar);
/* 125:207 */     path.append(fileName);
/* 126:208 */     return path.toString();
/* 127:    */   }
/* 128:    */   
/* 129:    */   public static String uploadPath(String fileName, String dir, String sizeDir)
/* 130:    */   {
/* 131:218 */     Date now = new Date();
/* 132:219 */     StringBuffer path = new StringBuffer();
/* 133:220 */     path.append(Constant.UPLOAD_PATH);
/* 134:221 */     path.append(dir).append(File.separatorChar);
/* 135:222 */     path.append(DateUtil.convertDateToYear(now)).append(File.separatorChar);
/* 136:223 */     path.append(DateUtil.convertDateToMonth(now)).append(File.separatorChar);
/* 137:224 */     path.append(DateUtil.convertDateToDay(now)).append(File.separatorChar);
/* 138:225 */     path.append(sizeDir).append(File.separatorChar);
/* 139:226 */     path.append(fileName);
/* 140:227 */     return path.toString();
/* 141:    */   }
/* 142:    */   
/* 143:    */   public static String minPicPath(String picPath, String sizeDir)
/* 144:    */   {
/* 145:237 */     if (StringUtils.isBlank(picPath)) {
/* 146:238 */       return "";
/* 147:    */     }
/* 148:240 */     String path = picPath.replace("ori", sizeDir);
/* 149:241 */     return path;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public static String searchUrl(String url)
/* 153:    */   {
/* 154:246 */     return url.toString();
/* 155:    */   }
/* 156:    */   
/* 157:    */   public static String searchSimUrl(String picUrl, String title, Integer pageId)
/* 158:    */   {
/* 159:256 */     StringBuffer url = new StringBuffer(Constant.SEARCH_URL);
/* 160:257 */     url.append("similar");
/* 161:258 */     if (StringUtils.isNotBlank(picUrl))
/* 162:    */     {
/* 163:259 */       if (picUrl.indexOf("http://") < 0) {
/* 164:260 */         picUrl = pic(picUrl);
/* 165:    */       }
/* 166:262 */       url.append("?picUrl=").append(picUrl);
/* 167:    */     }
/* 168:    */     else
/* 169:    */     {
/* 170:264 */       return url.toString();
/* 171:    */     }
/* 172:266 */     if (((pageId != null ? 1 : 0) & (pageId.intValue() > 1 ? 1 : 0)) != 0) {
/* 173:267 */       url.append("&page=").append(pageId);
/* 174:    */     }
/* 175:269 */     if (StringUtils.isNotBlank(title)) {
/* 176:270 */       url.append("&title=").append(title);
/* 177:    */     }
/* 178:272 */     return url.toString();
/* 179:    */   }
/* 180:    */   
/* 181:    */   public static String ilookListUrl(Long classifyId, Integer pageId)
/* 182:    */   {
/* 183:283 */     if ((pageId == null) || (pageId.intValue() < 1)) {
/* 184:284 */       pageId = Integer.valueOf(1);
/* 185:    */     }
/* 186:286 */     StringBuffer url = new StringBuffer(Constant.LOOK_URL);
/* 187:288 */     if ((classifyId != null) && (classifyId.longValue() > 1L)) {
/* 188:289 */       url.append("classify/").append(classifyId).append("-");
/* 189:    */     } else {
/* 190:291 */       url.append("list/");
/* 191:    */     }
/* 192:293 */     url.append(pageId).append(".html");
/* 193:294 */     return url.toString();
/* 194:    */   }
/* 195:    */   
/* 196:    */   public static String ilookItemUrl(Long picId)
/* 197:    */   {
/* 198:304 */     StringBuffer url = new StringBuffer(Constant.LOOK_URL);
/* 199:305 */     url.append("item/").append(picId).append(".html");
/* 200:306 */     return url.toString();
/* 201:    */   }
/* 202:    */   
/* 203:    */   public static void main(String[] args)
/* 204:    */   {
/* 205:312 */     System.out.println(getRootPath());
/* 206:    */   }
/* 207:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.PathUtil
 * JD-Core Version:    0.7.0.1
 */
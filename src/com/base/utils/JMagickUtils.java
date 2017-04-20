package com.base.utils;

import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class JMagickUtils {
	private String toPath;
	private InputStream input;
	private static final String YIYA_LOGO_BG = PathUtil.getRootPath()
			+ "/static/common/images/watermark_bg.png";
	private static final String YIYA_LOGO_LOGO = PathUtil.getRootPath()
			+ "/static/common/images/watermark_logo.png";

	public JMagickUtils() {
		System.setProperty("jmagick.systemclassloader", "no");
	}

	public static JMagickUtils Utils = new JMagickUtils();

	public JMagickUtils ofUrl(String picUrl) throws IOException {
		URL url = new URL(picUrl);
		URLConnection urlConnection = url.openConnection();
		InputStream is = urlConnection.getInputStream();
		urlConnection.getContentType();
		return of(is);
	}

	/* 44: */
	/* 45: */public JMagickUtils ofUrl(String url, String refererURL)
	/* 46: */throws IOException
	/* 47: */{
		/* 48: 71 */HttpURLConnection conn = (HttpURLConnection) new URL(url)
				.openConnection();
		/* 49: 72 */conn.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon;)");
		/* 50: 73 */conn.setRequestProperty("Accept-Encoding", "gzip");
		/* 51: 74 */conn.setRequestProperty("referer", refererURL);
		/* 52: 75 */conn.setRequestProperty("cookie", "data");
		/* 53: 76 */InputStream is = conn.getInputStream();
		/* 54: 77 */return of(is);
		/* 55: */}

	/* 56: */
	/* 57: */public JMagickUtils of(String path)
	/* 58: */throws IOException
	/* 59: */{
		/* 60: 86 */File file = new File(path);
		/* 61: 87 */return of(file);
		/* 62: */}

	/* 63: */
	/* 64: */public JMagickUtils of(File file)
	/* 65: */throws IOException
	/* 66: */{
		/* 67: 97 */InputStream input = new FileInputStream(file);
		/* 68: 98 */return of(input);
		/* 69: */}

	/* 70: */
	/* 71: */public JMagickUtils of(InputStream input)
	/* 72: */throws IOException
	/* 73: */{
		/* 74:109 */this.input = input;
		/* 75:110 */return this;
		/* 76: */}

	/* 77: */
	/* 78: */public JMagickUtils to(String path)
	/* 79: */{
		/* 80:119 */this.toPath = path;
		/* 81:120 */return this;
		/* 82: */}

	/* 83: */
	/* 84: */public void create()
	/* 85: */throws Exception
	/* 86: */{
		/* 87:128 */create(true);
		/* 88: */}

	/* 89: */
	/* 90: */public void create(boolean isAddLogo)
	/* 91: */throws Exception
	/* 92: */{
		/* 93:137 */createDir();
		/* 94:138 */ImageInfo info = null;
		/* 95:139 */MagickImage image = null;
		/* 96:140 */MagickImage mask = null;
		/* 97: */int height;
		/* 98: */int width;
		/* 99: */try
		/* 100: */{
			/* 101:143 */info = new ImageInfo();
			/* 102:144 */image = new MagickImage(info, getBytes());
			/* 103:145 */image.setFileName(this.toPath);
			/* 104:146 */width = (int) image.getDimension().getWidth();
			/* 105:147 */height = (int) image.getDimension().getHeight();
			/* 106: */
			/* 107:149 */File fileBg = new File(YIYA_LOGO_BG);
			/* 108:150 */File fileLogo = new File(YIYA_LOGO_LOGO);
			/* 109:151 */if ((fileBg.exists()) && (fileLogo.exists())
					&& (isAddLogo))
			/* 110: */{
				/* 111:152 */mask = new MagickImage(new ImageInfo(YIYA_LOGO_BG));
				/* 112:153 */image.compositeImage(3, mask, 0, height - 30);
				/* 113:154 */mask = new MagickImage(new ImageInfo(
						YIYA_LOGO_LOGO));
				/* 114:155 */image.compositeImage(3, mask, 0, height - 30);
				/* 115: */}
			/* 116:158 */image.writeImage(info);
			/* 117:159 */System.out.println("创建图片：" + this.toPath);
			/* 118: */}
		/* 119: */finally
		/* 120: */{
			/* 121:161 */if (image != null) {
				/* 122:162 */image.destroyImages();
				/* 123: */}
			/* 124:164 */info = null;
			/* 125:165 */image = null;
			/* 126:166 */mask = null;
			/* 127:167 */destroy();
			/* 128: */}
		/* 129: */}

	/* 130: */
	/* 131: */public void create(int width)
	/* 132: */throws Exception
	/* 133: */{
		/* 134:177 */createDir();
		/* 135:178 */ImageInfo info = null;
		/* 136:179 */MagickImage image = null;
		/* 137:180 */Dimension imageDim = null;
		/* 138:181 */MagickImage scaled = null;
		/* 139: */try
		/* 140: */{
			/* 141:183 */info = new ImageInfo();
			/* 142:184 */image = new MagickImage(info, getBytes());
			/* 143:185 */imageDim = image.getDimension();
			/* 144:186 */int imageWidth = (int) imageDim.getWidth();
			/* 145:187 */int imageHeight = (int) imageDim.getHeight();
			/* 146:188 */int target_width = width;
			/* 147:189 */int w = target_width;
			/* 148:190 */int h = (int) Math.round(imageHeight * target_width
					* 1.0D / imageWidth);
			/* 149:191 */scaled = image.scaleImage(w, h);
			/* 150:192 */scaled.setFileName(this.toPath);
			/* 151:193 */scaled.writeImage(info);
			/* 152:194 */System.out.println("创建图片：" + this.toPath);
			/* 153: */}
		/* 154: */finally
		/* 155: */{
			/* 156:196 */if (scaled != null) {
				/* 157:197 */scaled.destroyImages();
				/* 158: */}
			/* 159:199 */info = null;
			/* 160:200 */image = null;
			/* 161:201 */imageDim = null;
			/* 162:202 */scaled = null;
			/* 163:203 */destroy();
			/* 164: */}
		/* 165: */}

	/* 166: */
	/* 167: */public void create(int width, int height)
	/* 168: */throws Exception
	/* 169: */{
		/* 170:214 */createDir();
		/* 171:215 */ImageInfo info = null;
		/* 172:216 */MagickImage image = null;
		/* 173:217 */Dimension imageDim = null;
		/* 174:218 */MagickImage scaled = null;
		/* 175:219 */MagickImage minImage = null;
		/* 176:220 */Rectangle rect = null;
		/* 177:221 */int x = 0;
		int y = 0;
		int w = 0;
		int h = 0;
		int imageWidth = 0;
		int imageHeight = 0;
		/* 178: */try
		/* 179: */{
			/* 180:223 */info = new ImageInfo();
			/* 181:224 */image = new MagickImage(info, getBytes());
			/* 182:225 */imageDim = image.getDimension();
			/* 183:226 */imageWidth = (int) imageDim.getWidth();
			/* 184:227 */imageHeight = (int) imageDim.getHeight();
			/* 185:230 */if (imageWidth <= imageHeight)
			/* 186: */{
				/* 187:231 */w = width;
				/* 188:232 */h = (int) Math.round(imageHeight * width * 1.0D
						/ imageWidth);
				/* 189:233 */if (width > h) {
					/* 190:234 */y = (h - height) / 2;
					/* 191: */}
				/* 192: */}
			/* 193: */else
			/* 194: */{
				/* 195:237 */h = height;
				/* 196:238 */w = (int) Math.round(imageWidth * height * 1.0D
						/ imageHeight);
				/* 197:239 */x = (w - width) / 2;
				/* 198: */}
			/* 199:241 */minImage = image.scaleImage(w, h);
			/* 200:242 */rect = new Rectangle(x, y, width, height);
			/* 201:243 */scaled = minImage.cropImage(rect);
			/* 202:244 */scaled.setFileName(this.toPath);
			/* 203:245 */scaled.writeImage(info);
			/* 204:246 */System.out.println("创建图片：" + this.toPath);
			/* 205: */}
		/* 206: */finally
		/* 207: */{
			/* 208:248 */if (scaled != null)
			/* 209: */{
				/* 210:249 */image.destroyImages();
				/* 211:250 */scaled.destroyImages();
				/* 212:251 */destroy();
				/* 213: */}
			/* 214:253 */info = null;
			/* 215:254 */image = null;
			/* 216:255 */imageDim = null;
			/* 217:256 */scaled = null;
			/* 218:257 */minImage = null;
			/* 219: */}
		/* 220: */}

	/* 221: */
	/* 222: */public static void main(String[] args)
	/* 223: */throws MagickException
	/* 224: */{
		/* 225:263 */String filePath = "D:\\t\\3.jpg";
		/* 226:264 */String toPath = "D:\\t2\\001_magick.jpg";
		/* 227: */try
		/* 228: */{
			/* 229:266 */String url = "http://www.eale.cc/UploadFiles/20127910293960.jpg";
			/* 230:267 */url = "http://www.9441.com/uploads/allimg/c120703/13412UYTZ10-13YK.jpg";
			/* 231: */
			/* 232:269 */Utils.ofUrl(url).to("D:\\t2\\1.jpg").create();
			/* 233: */
			/* 234:271 */Utils.ofUrl(url, "http://www.9441.com/")
					.to("D:\\t2\\1.jpg").create();
			/* 235: */
			/* 236:273 */Utils.of("d:\\src.jpg").to("D:\\t2\\1.jpg").create();
			/* 237: */
			/* 238:275 */Utils.of("d:\\src.jpg").to("D:\\t2\\1.jpg")
					.create(200, 200);
			/* 239: */}
		/* 240: */catch (Exception e)
		/* 241: */{
			/* 242:277 */e.printStackTrace();
			/* 243: */}
		/* 244: */}

	/* 245: */
	/* 246: */public byte[] getBytes()
	/* 247: */throws IOException
	/* 248: */{
		/* 249:282 */byte[] bytes = IOUtils.toByteArray(this.input);
		/* 250:283 */return bytes;
		/* 251: */}

	/* 252: */
	/* 253: */public InputStream getInput()
	/* 254: */{
		/* 255:292 */return this.input;
		/* 256: */}

	/* 257: */
	/* 258: */public BufferedImage getBuffer()
	/* 259: */throws IOException
	/* 260: */{
		/* 261:297 */BufferedImage buffer = ImageIO.read(this.input);
		/* 262:298 */return buffer;
		/* 263: */}

	/* 264: */
	/* 265: */private void createDir()
	/* 266: */throws Exception
	/* 267: */{
		/* 268:307 */if (StringUtils.isBlank(this.toPath)) {
			/* 269:308 */throw new Exception("未指定文件路径");
			/* 270: */}
		/* 271:310 */File file = new File(this.toPath);
		/* 272:311 */if (!file.getParentFile().exists()) {
			/* 273:312 */file.getParentFile().mkdirs();
			/* 274: */}
		/* 275: */}

	/* 276: */
	/* 277: */public void destroy()
	/* 278: */{
		/* 279:317 */this.input = null;
		/* 280: */}
	/* 281: */
}

/*
 * Location: C:\Users\congxiaofan\Desktop\
 * 
 * Qualified Name: com.base.utils.JMagickUtils
 * 
 * JD-Core Version: 0.7.0.1
 */
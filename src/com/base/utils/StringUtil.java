/*    1:     */package com.base.utils;

/*    2:     */
/*    3:     */import java.io.PrintStream;
/*    4:     */
import java.io.PrintWriter;
/*    5:     */
import java.io.StringWriter;
/*    6:     */
import java.io.UnsupportedEncodingException;
/*    7:     */
import java.text.NumberFormat;
/*    8:     */
import java.util.ArrayList;
/*    9:     */
import java.util.List;
/*   10:     */
import java.util.StringTokenizer;
/*   11:     */
import org.apache.commons.lang.StringUtils;
/*   12:     */
import sun.io.ByteToCharConverter;
/*   13:     */
import sun.io.CharToByteConverter;

/*   14:     */
/*   15:     */public class StringUtil
/* 16: */{
	/* 17: */public static final String escapeForIntro(String string)
	/* 18: */{
		/* 19: 32 */String str = string;
		/* 20: 33 */str = replace(str, "\r\n", "<br>");
		/* 21: 34 */str = replace(str, "\n", "<br>");
		/* 22: 35 */str = replace(str, "'", "\\'");
		/* 23: 36 */return replace(str, "\r", "");
		/* 24: */}

	/* 25: */
	/* 26: */public static String getNotNullStr(Object objValue)
	/* 27: */{
		/* 28: 46 */return objValue == null ? "" : objValue.toString();
		/* 29: */}

	/* 30: */
	/* 31: */public static String getNotNullStr(String strValue)
	/* 32: */{
		/* 33: 55 */return strValue == null ? "" : strValue.trim();
		/* 34: */}

	/* 35: */
	/* 36: */public static String ChineseStringToAscii(String s)
	/* 37: */{
		/* 38: */try
		/* 39: */{
			/* 40: 66 */CharToByteConverter toByte = CharToByteConverter
					.getConverter("GBK");
			/* 41: 67 */byte[] orig = toByte.convertAll(s.toCharArray());
			/* 42: 68 */char[] dest = new char[orig.length];
			/* 43: 69 */for (int i = 0; i < orig.length; i++) {
				/* 44: 70 */dest[i] = ((char) (orig[i] & 0xFF));
				/* 45: */}
			/* 46: 71 */return new String(dest);
			/* 47: */}
		/* 48: */catch (Exception e)
		/* 49: */{
			/* 50: 75 */System.out.println(e);
			/* 51: */}
		/* 52: 76 */return s;
		/* 53: */}

	/* 54: */
	/* 55: */public static String ChineseStringToUTF(String s)
	/* 56: */{
		/* 57: */try
		/* 58: */{
			/* 59: 88 */CharToByteConverter toByte = CharToByteConverter
					.getConverter("UTF-8");
			/* 60: 89 */byte[] orig = toByte.convertAll(s.toCharArray());
			/* 61: 90 */char[] dest = new char[orig.length];
			/* 62: 91 */for (int i = 0; i < orig.length; i++) {
				/* 63: 92 */dest[i] = ((char) (orig[i] & 0xFF));
				/* 64: */}
			/* 65: 93 */return new String(dest);
			/* 66: */}
		/* 67: */catch (Exception e)
		/* 68: */{
			/* 69: 97 */System.out.println(e);
			/* 70: */}
		/* 71: 98 */return s;
		/* 72: */}

	/* 73: */
	/* 74: */public static String AsciiToChineseString(String s)
	/* 75: */{
		/* 76: 109 */char[] orig = s.toCharArray();
		/* 77: 110 */byte[] dest = new byte[orig.length];
		/* 78: 111 */for (int i = 0; i < orig.length; i++) {
			/* 79: 112 */dest[i] = ((byte) (orig[i] & 0xFF));
			/* 80: */}
		/* 81: */try
		/* 82: */{
			/* 83: 115 */ByteToCharConverter toChar = ByteToCharConverter
					.getConverter("GBK");
			/* 84: 116 */return new String(toChar.convertAll(dest));
			/* 85: */}
		/* 86: */catch (Exception e)
		/* 87: */{
			/* 88: 120 */System.out.println(e);
			/* 89: */}
		/* 90: 121 */return s;
		/* 91: */}

	/* 92: */
	/* 93: */public static String fillZero(String str, int size)
	/* 94: */{
		/* 95: */String result;
		/* 97: 168 */if (str.length() < size)
		/* 98: */{
			/* 99: 169 */char[] s = new char[size - str.length()];
			/* 100: 170 */for (int i = 0; i < size - str.length(); i++) {
				/* 101: 172 */s[i] = '0';
				/* 102: */}
			/* 103: 174 */result = new String(s) + str;
			/* 104: */}
		/* 105: */else
		/* 106: */{
			/* 107: 176 */result = str;
			/* 108: */}
		/* 109: 178 */return result;
		/* 110: */}

	/* 111: */
	/* 112: */public static String[] getStrArryByString(String str1)
	/* 113: */{
		/* 114: 188 */if (str1.indexOf("\t") > 0) {
			/* 115: 190 */for (int i = 0; i < str1.length(); i++) {
				/* 116: 192 */if (str1.substring(i, i + 1).equals("\t")) {
					/* 117: 194 */str1 = str1.substring(0, i) + " "
							+ str1.substring(i + 1, str1.length());
					/* 118: */}
				/* 119: */}
			/* 120: */}
		/* 121: 198 */StringTokenizer stringTokenizer = new StringTokenizer(
				str1, "\r\n");
		/* 122: 199 */String[] strId = new String[stringTokenizer.countTokens()];
		/* 123: 200 */int i = 0;
		/* 124: 201 */while (stringTokenizer.hasMoreTokens())
		/* 125: */{
			/* 126: 203 */strId[i] = stringTokenizer.nextToken();
			/* 127: 204 */i++;
			/* 128: */}
		/* 129: 206 */return strId;
		/* 130: */}

	/* 131: */
	/* 132: */public static boolean isValid(String inStr)
	/* 133: */{
		/* 134: 215 */if (inStr == null) {
			/* 135: 217 */return false;
			/* 136: */}
		/* 137: 219 */if (inStr.equals("")) {
			/* 138: 221 */return false;
			/* 139: */}
		/* 140: 223 */if (inStr.equals("null")) {
			/* 141: 225 */return false;
			/* 142: */}
		/* 143: 229 */return true;
		/* 144: */}

	/* 145: */
	/* 146: */public static boolean checkNotNull(String str)
	/* 147: */{
		/* 148: 239 */boolean flag = false;
		/* 149: 241 */if ((str != null) && (str.trim().length() != 0)) {
			/* 150: 242 */flag = true;
			/* 151: */}
		/* 152: 243 */return flag;
		/* 153: */}

	/* 154: */
	/* 155: */public static String getStrSpace(int spaceNum)
	/* 156: */{
		/* 157: 252 */return getStrWithSameElement(spaceNum, " ");
		/* 158: */}

	/* 159: */
	/* 160: */public static String getStrWithSameElement(int num, String element)
	/* 161: */{
		/* 162: 262 */if (num <= 0) {
			/* 163: 264 */return "";
			/* 164: */}
		/* 165: 267 */StringBuffer sb = new StringBuffer();
		/* 166: 268 */for (int i = 0; i < num; i++) {
			/* 167: 270 */sb.append(element);
			/* 168: */}
		/* 169: 272 */return sb.toString();
		/* 170: */}

	/* 171: */
	/* 172: */public static String getFillString(String strIn, int totalLength,
			boolean isRightAlign)
	/* 173: */{
		/* 174: 283 */int spaceLength = 0;
		/* 175: 284 */String spaces = null;
		/* 176: 285 */String strOut = null;
		/* 177: 287 */if (strIn == null) {
			/* 178: 289 */strIn = "";
			/* 179: */}
		/* 180: 292 */spaceLength = totalLength - strIn.length();
		/* 181: 294 */if (spaceLength < 0) {
			/* 182: 296 */spaceLength = 0;
			/* 183: */}
		/* 184: 298 */spaces = getStrSpace(spaceLength);
		/* 185: 300 */if (isRightAlign) {
			/* 186: 302 */strOut = spaces + strIn;
			/* 187: */} else {
			/* 188: 306 */strOut = strIn + spaces;
			/* 189: */}
		/* 190: 309 */return strOut;
		/* 191: */}

	/* 192: */
	/* 193: */public static String getStackTrace(Throwable t)
	/* 194: */{
		/* 195: 318 */StringWriter sw = new StringWriter();
		/* 196: 319 */PrintWriter pw = new PrintWriter(sw);
		/* 197: */
		/* 198: 321 */t.printStackTrace(pw);
		/* 199: 322 */return sw.toString();
		/* 200: */}

	/* 201: */
	/* 202: */public static String getStrByUpperFirstChar(String str)
	/* 203: */{
		/* 204: */try
		/* 205: */{
			/* 206: 333 */return str.substring(0, 1).toUpperCase()
					+ str.substring(1);
			/* 207: */}
		/* 208: */catch (Exception e) {
		}
		/* 209: 337 */return "";
		/* 210: */}

	/* 211: */
	/* 212: */public static String getStrByLowerFirstChar(String str)
	/* 213: */{
		/* 214: */try
		/* 215: */{
			/* 216: 350 */return str.substring(0, 1).toLowerCase()
					+ str.substring(1);
			/* 217: */}
		/* 218: */catch (Exception e) {
		}
		/* 219: 354 */return "";
		/* 220: */}

	/* 221: */
	/* 222: */public static int getStrToInt(String strValue)
	/* 223: */{
		/* 224: 365 */if (strValue == null) {
			/* 225: 367 */return 0;
			/* 226: */}
		/* 227: 369 */int iValue = 0;
		/* 228: */try
		/* 229: */{
			/* 230: 372 */iValue = new Integer(strValue.trim()).intValue();
			/* 231: */}
		/* 232: */catch (Exception ex)
		/* 233: */{
			/* 234: 376 */iValue = 0;
			/* 235: */}
		/* 236: 378 */return iValue;
		/* 237: */}

	/* 238: */
	/* 239: */public static double getStrToDouble(String strValue)
	/* 240: */{
		/* 241: 388 */if (strValue == null) {
			/* 242: 390 */return 0.0D;
			/* 243: */}
		/* 244: 392 */double dValue = 0.0D;
		/* 245: */try
		/* 246: */{
			/* 247: 395 */dValue = Double.parseDouble(strValue.trim());
			/* 248: */}
		/* 249: */catch (Exception ex)
		/* 250: */{
			/* 251: 399 */dValue = 0.0D;
			/* 252: */}
		/* 253: 401 */return dValue;
		/* 254: */}

	/* 255: */
	/* 256: */public static short getStrToShort(String strValue)
	/* 257: */{
		/* 258: 411 */if (strValue == null) {
			/* 259: 413 */return 0;
			/* 260: */}
		/* 261: 415 */short iValue = 0;
		/* 262: */try
		/* 263: */{
			/* 264: 418 */iValue = new Short(strValue.trim()).shortValue();
			/* 265: */}
		/* 266: */catch (Exception ex)
		/* 267: */{
			/* 268: 422 */iValue = 0;
			/* 269: */}
		/* 270: 424 */return iValue;
		/* 271: */}

	/* 272: */
	/* 273: */public static long getStrToLong(String strValue)
	/* 274: */{
		/* 275: 434 */if (strValue == null) {
			/* 276: 436 */return 0L;
			/* 277: */}
		/* 278: 438 */long lValue = 0L;
		/* 279: */try
		/* 280: */{
			/* 281: 441 */lValue = new Long(strValue.trim()).longValue();
			/* 282: */}
		/* 283: */catch (Exception ex)
		/* 284: */{
			/* 285: 445 */lValue = 0L;
			/* 286: */}
		/* 287: 447 */return lValue;
		/* 288: */}

	/* 289: */
	/* 290: */public static String toLengthForEn(String str, int length)
	/* 291: */{
		/* 292: 452 */if (str != null)
		/* 293: */{
			/* 294: 454 */if (str.length() <= length) {
				/* 295: 456 */return str;
				/* 296: */}
			/* 297: 460 */str = str.substring(0, length - 2);
			/* 298: 461 */str = str + "..";
			/* 299: 462 */return str;
			/* 300: */}
		/* 301: 467 */return "";
		/* 302: */}

	/* 303: */
	/* 304: */public static String toLengthForIntroduce(String str, int length)
	/* 305: */{
		/* 306: 479 */str = delTag(str);
		/* 307: */
		/* 308: 481 */byte[] strByte = str.getBytes();
		/* 309: 482 */int byteLength = strByte.length;
		/* 310: */
		/* 311: 484 */StringBuffer buff = new StringBuffer();
		/* 312: 485 */if (byteLength > length * 2)
		/* 313: */{
			/* 314: 487 */char[] charArray = str.toCharArray();
			/* 315: 488 */int resultLength = 0;
			/* 316: 489 */for (int i = 0; i < charArray.length; i++)
			/* 317: */{
				/* 318: 491 */resultLength += String.valueOf(charArray[i])
						.getBytes().length;
				/* 319: 492 */if (resultLength > length * 2) {
					/* 320: */break;
					/* 321: */}
				/* 322: 496 */buff.append(charArray[i]);
				/* 323: */}
			/* 324: 499 */buff.append("..");
			/* 325: 500 */str = buff.toString();
			/* 326: */}
		/* 327: 504 */str = replace(str, "\"", "\\\"");
		/* 328: 505 */str = replace(str, "，", ",");
		/* 329: 506 */return str;
		/* 330: */}

	/* 331: */
	/* 332: */public static String delTag(String str)
	/* 333: */{
		/* 334: 512 */str = str + "<>";
		/* 335: 513 */StringBuffer buff = new StringBuffer();
		/* 336: 514 */int start = 0;
		/* 337: 515 */int end = 0;
		/* 338: 517 */while (str.length() > 0)
		/* 339: */{
			/* 340: 519 */start = str.indexOf("<");
			/* 341: 520 */end = str.indexOf(">");
			/* 342: 521 */if (start > 0) {
				/* 343: 523 */buff.append(str.substring(0, start));
				/* 344: */}
			/* 345: 525 */if ((end > 0) && (end <= str.length())) {
				/* 346: 527 */str = str.substring(end + 1, str.length());
				/* 347: */} else {
				/* 348: 531 */str = "";
				/* 349: */}
			/* 350: */}
		/* 351: 535 */String result = buff.toString();
		/* 352: 537 */while (result.startsWith(" ")) {
			/* 353: 540 */result = result.substring(result.indexOf(" ") + 1,
					result.length());
			/* 354: */}
		/* 355: 543 */return result;
		/* 356: */}

	/* 357: */
	/* 358: */public static final String replace(String line, String oldString,
			String newString)
	/* 359: */{
		/* 360: 549 */if (line == null) {
			/* 361: 551 */return null;
			/* 362: */}
		/* 363: 553 */int i = 0;
		/* 364: 554 */if ((i = line.indexOf(oldString, i)) >= 0)
		/* 365: */{
			/* 366: 556 */char[] line2 = line.toCharArray();
			/* 367: 557 */char[] newString2 = newString.toCharArray();
			/* 368: 558 */int oLength = oldString.length();
			/* 369: 559 */StringBuffer buf = new StringBuffer(line2.length);
			/* 370: 560 */buf.append(line2, 0, i).append(newString2);
			/* 371: 561 */i += oLength;
			/* 372: 562 */int j = i;
			/* 373: 563 */while ((i = line.indexOf(oldString, i)) > 0)
			/* 374: */{
				/* 375: 565 */buf.append(line2, j, i - j).append(newString2);
				/* 376: 566 */i += oLength;
				/* 377: 567 */j = i;
				/* 378: */}
			/* 379: 569 */buf.append(line2, j, line2.length - j);
			/* 380: 570 */return buf.toString();
			/* 381: */}
		/* 382: 572 */return line;
		/* 383: */}

	/* 384: */
	/* 385: */public static String Replace(String source, String oldString,
			String newString)
	/* 386: */{
		/* 387: 578 */if (source == null) {
			/* 388: 580 */return null;
			/* 389: */}
		/* 390: 582 */StringBuffer output = new StringBuffer();
		/* 391: 583 */int lengOfsource = source.length();
		/* 392: 584 */int lengOfold = oldString.length();
		/* 393: 585 */int posStart = 0;
		/* 394: */int pos;
		/* 395: 587 */while ((pos = source.indexOf(oldString, posStart)) >= 0)
		/* 396: */{
			/* 398: 589 */output.append(source.substring(posStart, pos));
			/* 399: 590 */output.append(newString);
			/* 400: 591 */posStart = pos + lengOfold;
			/* 401: */}
		/* 402: 593 */if (posStart < lengOfsource) {
			/* 403: 595 */output.append(source.substring(posStart));
			/* 404: */}
		/* 405: 597 */return output.toString();
		/* 406: */}

	/* 407: */
	/* 408: */public static String toHtml(String s)
	/* 409: */{
		/* 410: 603 */s = Replace(s, "<", "&lt;");
		/* 411: 604 */s = Replace(s, ">", "&gt;");
		/* 412: 605 */s = Replace(s, "\t", "    ");
		/* 413: 606 */s = Replace(s, "\r\n", "\n");
		/* 414: 607 */s = Replace(s, "\n", "<br>");
		/* 415: */
		/* 416: 609 */s = Replace(s, "'", "&#39;");
		/* 417: 610 */s = Replace(s, "\"", "&quot;");
		/* 418: 611 */s = Replace(s, "\\", "&#92;");
		/* 419: 612 */s = Replace(s, "%", "％");
		/* 420: */
		/* 421: 614 */return s;
		/* 422: */}

	/* 423: */
	/* 424: */public static String unHtml(String s)
	/* 425: */{
		/* 426: 624 */s = Replace(s, "<br>", "\n");
		/* 427: */
		/* 428: */
		/* 429: */
		/* 430: */
		/* 431: */
		/* 432: 630 */return s;
		/* 433: */}

	/* 434: */
	/* 435: */public static String toHtmlBack(String s)
	/* 436: */{
		/* 437: 637 */s = Replace(s, "&", "&amp;");
		/* 438: 638 */s = Replace(s, "\\", "&#92;");
		/* 439: 639 */s = Replace(s, "\"", "&quot;");
		/* 440: 640 */s = Replace(s, "<", "&lt;");
		/* 441: 641 */s = Replace(s, ">", "&gt;");
		/* 442: 642 */s = Replace(s, "\t", "    ");
		/* 443: 643 */s = Replace(s, "\r\n", "\n");
		/* 444: */
		/* 445: */
		/* 446: */
		/* 447: */
		/* 448: */
		/* 449: 649 */return s;
		/* 450: */}

	/* 451: */
	/* 452: */public static String unHtmlBack(String s)
	/* 453: */{
		/* 454: 654 */s = Replace(s, "&lt;", "<");
		/* 455: 655 */s = Replace(s, "&gt;", ">");
		/* 456: 656 */s = Replace(s, "    ", "\t");
		/* 457: 657 */s = Replace(s, "\n", "\r\n");
		/* 458: 658 */s = Replace(s, "<br>", "\n");
		/* 459: 659 */s = Replace(s, "&nbsp;", " ");
		/* 460: 660 */s = Replace(s, "&amp;", "&");
		/* 461: 661 */s = Replace(s, "&#39;", "'");
		/* 462: 662 */s = Replace(s, "&#92;", "\\");
		/* 463: 663 */s = Replace(s, "％", "%");
		/* 464: 664 */return s;
		/* 465: */}

	/* 466: */
	/* 467: */public static boolean containsChinese(String str)
	/* 468: */throws UnsupportedEncodingException
	/* 469: */{
		/* 470: 704 */if (str.length() < str.getBytes().length) {
			/* 471: 705 */return true;
			/* 472: */}
		/* 473: 707 */return false;
		/* 474: */}

	/* 475: */
	/* 476: */public static boolean isEmpty(String str)
	/* 477: */{
		/* 478: 724 */if (str == null) {
			/* 479: 725 */return true;
			/* 480: */}
		/* 481: 726 */return "".equals(str.trim());
		/* 482: */}

	/* 483: */
	/* 484: */public static String[] split(String str1, String str2)
	/* 485: */{
		/* 486: 731 */return StringUtils.split(str1, str2);
		/* 487: */}

	/* 488: */
	/* 489: */public static List<String> StringToList(String value, String exp)
	/* 490: */{
		/* 491: 745 */List<String> resultList = new ArrayList();
		/* 492: 746 */String[] vals = split(value, exp);
		/* 493: 747 */for (String val : vals) {
			/* 494: 748 */resultList.add(val);
			/* 495: */}
		/* 496: 750 */return resultList;
		/* 497: */}

	/* 498: */
	/* 499: */public static String arrayToString(String[] arrs)
	/* 500: */{
		/* 501: 764 */StringBuffer result = new StringBuffer("");
		/* 502: 765 */if ((arrs != null) && (arrs.length > 0)) {
			/* 503: 766 */for (int i = 0; i < arrs.length; i++)
			/* 504: */{
				/* 505: 768 */if (!result.toString().equals("")) {
					/* 506: 769 */result.append(",");
					/* 507: */}
				/* 508: 771 */if ((arrs[i] != null)
						&& (!"".equals(arrs[i].trim()))) {
					/* 509: 772 */result.append(arrs[i]);
					/* 510: */}
				/* 511: */}
			/* 512: */}
		/* 513: 776 */return result.toString();
		/* 514: */}

	/* 515: */
	/* 516: */public static String arrayToString(Object[] arrs)
	/* 517: */{
		/* 518: 792 */StringBuffer result = new StringBuffer("");
		/* 519: 793 */if ((arrs != null) && (arrs.length > 0)) {
			/* 520: 794 */for (int i = 0; i < arrs.length; i++)
			/* 521: */{
				/* 522: 796 */if (!result.toString().equals("")) {
					/* 523: 797 */result.append(",");
					/* 524: */}
				/* 525: 799 */if ((arrs[i] != null)
						&& (!"".equals(arrs[i].toString().trim()))) {
					/* 526: 800 */result.append(arrs[i]);
					/* 527: */}
				/* 528: */}
			/* 529: */}
		/* 530: 804 */return result.toString();
		/* 531: */}

	/* 532: */
	/* 533: */public static String left(String str, int length)
	/* 534: */{
		/* 535: 809 */return StringUtils.left(str, length);
		/* 536: */}

	/* 537: */
	/* 538: */public static String replaceHuiche(String str)
	/* 539: */{
		/* 540: 822 */String after = str.replaceAll("\r\n", "");
		/* 541: 823 */return after;
		/* 542: */}

	/* 543: */
	/* 544: */public static String truncateStr(String str, int len)
	/* 545: */{
		/* 546: 835 */if ((str != null) && (!"".equalsIgnoreCase(str)))
		/* 547: */{
			/* 548: 838 */String[] strs = str.split(" ");
			/* 549: 839 */StringBuffer buff = new StringBuffer();
			/* 550: 840 */if (strs.length > 0) {
				/* 551: 842 */for (int i = 0; i < strs.length; i++)
				/* 552: */{
					/* 553: 844 */StringBuffer temp = new StringBuffer();
					/* 554: 845 */while (strs[i].length() > len)
					/* 555: */{
						/* 556: 847 */temp.append(strs[i].substring(0, len)
								+ "<BR>");
						/* 557: 848 */strs[i] = strs[i].substring(len);
						/* 558: */}
					/* 559: 850 */temp.append(strs[i]);
					/* 560: 851 */buff.append(temp.toString() + " ");
					/* 561: */}
				/* 562: */}
			/* 563: 855 */return buff.toString();
			/* 564: */}
		/* 565: 859 */return "";
		/* 566: */}

	/* 567: */
	/* 568: */public static String truncateStr2(String str, int len)
	/* 569: */{
		/* 570: 865 */if ((str != null) && (!"".equalsIgnoreCase(str))
				&& (len != 0))
		/* 571: */{
			/* 572: 867 */String[] strs = str.split(" ");
			/* 573: */
			/* 574: 869 */StringBuffer buff = new StringBuffer();
			/* 575: 870 */for (int i = 0; i < strs.length; i++)
			/* 576: */{
				/* 577: 872 */StringBuffer temp = new StringBuffer();
				/* 578: 873 */String tempstr = "";
				/* 579: 874 */while (strs[i].length() > len)
				/* 580: */{
					/* 581: 876 */tempstr = strs[i].substring(0, len);
					/* 582: 877 */tempstr = tempstr.replaceAll(" ", "&nbsp; ");
					/* 583: 878 */tempstr = tempstr.replaceAll("<", "&lt; ");
					/* 584: 879 */tempstr = tempstr.replaceAll("\n", "<br> ")
							.replaceAll("\"", "&quot; ")
							.replaceAll("'", "&#39; ");
					/* 585: 880 */tempstr = tempstr + "<br>";
					/* 586: 881 */temp.append(tempstr);
					/* 587: */
					/* 588: 883 */strs[i] = strs[i].substring(len);
					/* 589: */}
				/* 590: 885 */tempstr = strs[i];
				/* 591: 886 */tempstr = tempstr.replaceAll(" ", "&nbsp; ");
				/* 592: 887 */tempstr = tempstr.replaceAll("<", "&lt; ");
				/* 593: 888 */tempstr = tempstr.replaceAll("\n", "<br> ")
						.replaceAll("\"", "&quot; ").replaceAll("'", "&#39; ");
				/* 594: */
				/* 595: 890 */temp.append(tempstr);
				/* 596: 891 */buff.append(temp.toString() + " ");
				/* 597: */}
			/* 598: 894 */if (buff.length() > 0) {
				/* 599: 895 */return buff.toString().substring(0,
						buff.length() - 1);
				/* 600: */}
			/* 601: 897 */return str;
			/* 602: */}
		/* 603: 901 */return "";
		/* 604: */}

	/* 605: */
	/* 606: */public static String unicodeToGB(String l_S_Source)
	/* 607: */throws UnsupportedEncodingException
	/* 608: */{
		/* 609: 913 */String l_S_Desc = "";
		/* 610: 914 */if ((l_S_Source != null)
				&& (!l_S_Source.trim().equals("")))
		/* 611: */{
			/* 612: 916 */byte[] l_b_Proc = l_S_Source.getBytes("GBK");
			/* 613: 917 */l_S_Desc = new String(l_b_Proc, "ISO8859_1");
			/* 614: */}
		/* 615: 919 */return l_S_Desc;
		/* 616: */}

	/* 617: */
	/* 618: */public static String GBToUnicode(String l_S_Source)
	/* 619: */throws UnsupportedEncodingException
	/* 620: */{
		/* 621: 929 */String l_S_Desc = "";
		/* 622: 930 */if ((l_S_Source != null)
				&& (!l_S_Source.trim().equals("")))
		/* 623: */{
			/* 624: 932 */byte[] l_b_Proc = l_S_Source.getBytes("ISO8859_1");
			/* 625: 933 */l_S_Desc = new String(l_b_Proc, "GBK");
			/* 626: */}
		/* 627: 935 */return l_S_Desc;
		/* 628: */}

	/* 629: */
	/* 630: */public static String javaScriptStringEnc(String s)
	/* 631: */{
		/* 632: 949 */int ln = s.length();
		/* 633: 950 */for (int i = 0; i < ln; i++)
		/* 634: */{
			/* 635: 951 */char c = s.charAt(i);
			/* 636: 952 */if ((c == '"') || (c == '\'') || (c == '\\')
					|| (c == '>') || (c < ' '))
			/* 637: */{
				/* 638: 953 */StringBuffer b = new StringBuffer(ln + 4);
				/* 639: 954 */b.append(s.substring(0, i));
				/* 640: */for (;;)
				/* 641: */{
					/* 642: 956 */if (c == '"') {
						/* 643: 957 */b.append("\\\"");
						/* 644: 958 */} else if (c == '\'') {
						/* 645: 959 */b.append("\\'");
						/* 646: 960 */} else if (c == '\\') {
						/* 647: 961 */b.append("\\\\");
						/* 648: 962 */} else if (c == '>') {
						/* 649: 963 */b.append("\\>");
						/* 650: 964 */} else if (c < ' ')
					/* 651: */{
						/* 652: 965 */if (c == '\n')
						/* 653: */{
							/* 654: 966 */b.append("\\n");
							/* 655: */}
						/* 656: 967 */else if (c == '\r')
						/* 657: */{
							/* 658: 968 */b.append("\\r");
							/* 659: */}
						/* 660: 969 */else if (c == '\f')
						/* 661: */{
							/* 662: 970 */b.append("\\f");
							/* 663: */}
						/* 664: 971 */else if (c == '\b')
						/* 665: */{
							/* 666: 972 */b.append("\\b");
							/* 667: */}
						/* 668: 973 */else if (c == '\t')
						/* 669: */{
							/* 670: 974 */b.append("\\t");
							/* 671: */}
						/* 672: */else
						/* 673: */{
							/* 674: 976 */b.append("\\x");
							/* 675: 977 */int x = c / '\020';
							/* 676: 978 */b
									.append(
									/* 677: 979 */(char) (x < 10 ? x + 48
											: x - 10 + 65));
							/* 678: 980 */x = c & 0xF;
							/* 679: 981 */b
									.append(
									/* 680: 982 */(char) (x < 10 ? x + 48
											: x - 10 + 65));
							/* 681: */}
						/* 682: */}
					/* 683: */else {
						/* 684: 985 */b.append(c);
						/* 685: */}
					/* 686: 987 */i++;
					/* 687: 988 */if (i >= ln) {
						/* 688: 989 */return b.toString();
						/* 689: */}
					/* 690: 991 */c = s.charAt(i);
					/* 691: */}
				/* 692: */}
			/* 693: */}
		/* 694: 995 */return s;
		/* 695: */}

	/* 696: */
	/* 697: 999 */private static StringUtil instance = null;

	/* 698: */
	/* 699: */public static synchronized StringUtil getInstance()
	/* 700: */{
		/* 701:1003 */if (instance == null) {
			/* 702:1005 */instance = new StringUtil();
			/* 703: */}
		/* 704:1007 */return instance;
		/* 705: */}

	/* 706: */
	/* 707: */public static String trimContinuousSpace(String src)
	/* 708: */{
		/* 709:1017 */return src.replaceAll("\\s+", " ");
		/* 710: */}

	/* 711: */
	/* 712: */public static String replace(String src, String target,
			String rWith, int maxCount)
	/* 713: */{
		/* 714:1021 */return StringUtils.replace(src, target, rWith, maxCount);
		/* 715: */}

	/* 716: */
	/* 717: */public static boolean equals(String str1, String str2)
	/* 718: */{
		/* 719:1026 */return StringUtils.equals(str1, str2);
		/* 720: */}

	/* 721: */
	/* 722: */public static boolean isAlphanumeric(String str)
	/* 723: */{
		/* 724:1031 */return StringUtils.isAlphanumeric(str);
		/* 725: */}

	/* 726: */
	/* 727: */public static boolean isNumeric(String str)
	/* 728: */{
		/* 729:1036 */return StringUtils.isNumeric(str);
		/* 730: */}

	/* 731: */
	/* 732: */public static String[] stripAll(String[] strs)
	/* 733: */{
		/* 734:1041 */return StringUtils.stripAll(strs);
		/* 735: */}

	/* 736: */
	/* 737: */public static String toFloatNumber(String num)
	/* 738: */{
		/* 739:1083 */NumberFormat nf = NumberFormat.getInstance();
		/* 740:1084 */nf.setMaximumFractionDigits(2);
		/* 741:1085 */nf.setMinimumFractionDigits(2);
		/* 742:1086 */return nf.format(Double.parseDouble(num));
		/* 743: */}

	/* 744: */
	/* 745: */public static String toFloatNumber(Double num, int accuracy)
	/* 746: */{
		/* 747:1091 */NumberFormat nf = NumberFormat.getInstance();
		/* 748:1092 */nf.setMaximumFractionDigits(accuracy);
		/* 749:1093 */nf.setMinimumFractionDigits(accuracy);
		/* 750:1094 */return nf.format(num);
		/* 751: */}

	/* 752: */
	/* 753: */public static String wcsUnescape(String str)
	/* 754: */{
		/* 755:1099 */str = str.replace("#lt;", "<");
		/* 756:1100 */str = str.replace("#gt;", ">");
		/* 757:1101 */str = str.replace("#quot;", "\"");
		/* 758:1102 */str = str.replace("#amp;amp;", "&");
		/* 759:1103 */str = str.replace("#amp;", "&");
		/* 760:1104 */str = str.replace("#039;", "'");
		/* 761:1105 */return str;
		/* 762: */}

	/* 763: */
	/* 764: */public static int getByteLength(String str)
	/* 765: */{
		/* 766:1118 */if (str == null) {
			/* 767:1119 */return 0;
			/* 768: */}
		/* 769:1121 */return str.getBytes().length;
		/* 770: */}

	/* 771: */
	/* 772: */public static String getByteStr(String str, int limitLen)
	/* 773: */{
		/* 774:1135 */StringBuffer sb = new StringBuffer();
		/* 775:1136 */char[] chars = getNotNullStr(str).toCharArray();
		/* 776:1137 */int len = 0;
		/* 777:1138 */for (char c : chars)
		/* 778: */{
			/* 779:1139 */len += getByteLength(String.valueOf(c));
			/* 780:1140 */if (len <= limitLen) {
				/* 781:1141 */sb.append(c);
				/* 782: */}
			/* 783: */}
		/* 784:1144 */return sb.toString();
		/* 785: */}

	/* 786: */
	/* 787: */public static String subStr(String content, Integer length,
			String padding)
	/* 788: */throws UnsupportedEncodingException
	/* 789: */{
		/* 790:1155 */String str = "";
		/* 791:1156 */int paddSize = StringUtils.isBlank(padding) ? 0 : padding
				.length();
		/* 792:1158 */if ((StringUtils.isBlank(content))
				|| (content.length() <= length.intValue())) {
			/* 793:1159 */return content;
			/* 794: */}
		/* 795:1161 */str = content.substring(0, length.intValue() - paddSize);
		/* 796:1162 */if (StringUtils.isNotBlank(padding)) {
			/* 797:1163 */str = str + padding;
			/* 798: */}
		/* 799:1165 */return str;
		/* 800: */}

	/* 801: */
	/* 802: */public static void main(String[] args)
	/* 803: */{
		/* 804:1169 */String str = "罗泽a军 Luozejun";
		/* 805:1170 */System.out.println(getByteLength(str));
		/* 806:1171 */System.out.println(getByteStr(str, 6));
		/* 807: */}
	/* 808: */
}

/*
 * Location: C:\Users\congxiaofan\Desktop\
 * 
 * Qualified Name: com.base.utils.StringUtil
 * 
 * JD-Core Version: 0.7.0.1
 */
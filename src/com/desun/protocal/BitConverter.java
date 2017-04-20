/*    1:     */package com.desun.protocal;

/*    2:     */
/*    3:     */import java.io.ByteArrayInputStream;
/*    4:     */
import java.io.ByteArrayOutputStream;
/*    5:     */
import java.io.ObjectInputStream;
/*    6:     */
import java.io.ObjectOutputStream;
/*    7:     */
import java.io.PrintStream;
/*    8:     */
import java.io.Serializable;
/*    9:     */
import java.math.BigInteger;
/*   10:     */
import java.text.ParseException;
/*   11:     */
import java.text.SimpleDateFormat;
/*   12:     */
import java.util.Arrays;
/*   13:     */
import java.util.Calendar;
/*   14:     */
import java.util.Date;

/*   15:     */
/*   16:     */public class BitConverter
/* 17: */{
	/* 18: */public static String Integer2HexStr(int i, int bits)
	/* 19: */{
		/* 20: 31 */String result = Integer.toHexString(i).toUpperCase();
		/* 21: 32 */result = result.replace(" ", "");
		/* 22: 33 */int len = result.length();
		/* 23: 34 */for (int j = len; j < bits; j++) {
			/* 24: 35 */result = "0" + result;
			/* 25: */}
		/* 26: 37 */return result;
		/* 27: */}

	/* 28: */
	/* 29: */public static String long2HexStr(long l, int bits)
	/* 30: */{
		/* 31: 51 */String result = Long.toHexString(l).toUpperCase();
		/* 32: 52 */result = result.replace(" ", "");
		/* 33: 53 */int len = result.length();
		/* 34: 54 */for (int j = len; j < bits; j++) {
			/* 35: 55 */result = "0" + result;
			/* 36: */}
		/* 37: 57 */return result;
		/* 38: */}

	/* 39: */
	/* 40: */public static byte[] intToBytes(int value, int bytesCount)
	/* 41: */{
		/* 42: 70 */byte[] b = new byte[4];
		/* 43: 71 */for (int i = 3; i >= 0; i--)
		/* 44: */{
			/* 45: 72 */b[i] = ((byte) (value & 0xFF));
			/* 46: 73 */value >>>= 8;
			/* 47: */}
		/* 48: 76 */byte[] result = new byte[bytesCount];
		/* 49: 77 */if (bytesCount > 4)
		/* 50: */{
			/* 51: 78 */for (int i = 0; i < 4; i++) {
				/* 52: 79 */result[(i + bytesCount - 4)] = b[i];
				/* 53: */}
			/* 54: 81 */for (int i = 0; i < bytesCount - 4; i++) {
				/* 55: 82 */result[i] = 0;
				/* 56: */}
			/* 57: */}
		/* 58: 84 */else if ((bytesCount <= 4) && (bytesCount > 0))
		/* 59: */{
			/* 60: 85 */for (int i = 0; i < bytesCount; i++) {
				/* 61: 86 */result[i] = b[(i + 4 - bytesCount)];
				/* 62: */}
			/* 63: */}
		/* 64: */else
		/* 65: */{
			/* 66: 90 */return null;
			/* 67: */}
		/* 68: 93 */return result;
		/* 69: */}

	/* 70: */
	/* 71: */public static byte[] intToBytesLittle(int value, int bytesCount)
	/* 72: */{
		/* 73: 105 */byte[] b = new byte[4];
		/* 74: 106 */for (int i = 0; i < 4; i++)
		/* 75: */{
			/* 76: 107 */b[i] = ((byte) (value & 0xFF));
			/* 77: 108 */value >>>= 8;
			/* 78: */}
		/* 79: 111 */if (bytesCount <= 0) {
			/* 80: 112 */return null;
			/* 81: */}
		/* 82: 115 */byte[] result = new byte[bytesCount];
		/* 83: 116 */for (int j = 0; j < bytesCount; j++) {
			/* 84: 117 */if (j < 4) {
				/* 85: 118 */result[j] = b[j];
				/* 86: */} else {
				/* 87: 120 */result[j] = 0;
				/* 88: */}
			/* 89: */}
		/* 90: 124 */return result;
		/* 91: */}

	/* 92: */
	/* 93: */public static byte[] longToBytes(long value, int bytesCount)
	/* 94: */{
		/* 95: 136 */byte[] bytes = new BigInteger(Long.toString(value))
				.toByteArray();
		/* 96: 137 */byte[] result = new byte[bytesCount];
		/* 97: 138 */Arrays.fill(result, (byte) 0);
		/* 98: 139 */int srcPos = 0;
		int destPos = 0;
		int length = 0;
		/* 99: 140 */if (bytes.length >= bytesCount)
		/* 100: */{
			/* 101: 141 */srcPos = bytes.length - bytesCount;
			/* 102: 142 */destPos = 0;
			/* 103: 143 */length = bytesCount;
			/* 104: */}
		/* 105: 144 */else if (bytes.length < bytesCount)
		/* 106: */{
			/* 107: 145 */srcPos = 0;
			/* 108: 146 */destPos = bytesCount - bytes.length;
			/* 109: 147 */length = bytes.length;
			/* 110: */}
		/* 111: 149 */System.arraycopy(bytes, srcPos, result, destPos, length);
		/* 112: 150 */return result;
		/* 113: */}

	/* 114: */
	/* 115: */public static int bytes2Integer(byte[] bytes, int startIndex,
			int len)
	/* 116: */throws Exception
	/* 117: */{
		/* 118: 168 */if ((bytes == null) || (bytes.length == 0)
				|| (bytes.length <= startIndex)) {
			/* 119: 170 */throw new Exception("beyond scope");
			/* 120: */}
		/* 121: 173 */if (len > 4) {
			/* 122: 174 */len = 4;
			/* 123: */}
		/* 124: 177 */if (startIndex + len > bytes.length) {
			/* 125: 178 */len = bytes.length - startIndex;
			/* 126: */}
		/* 127: 181 */int result = 0;
		/* 128: 182 */for (int i = startIndex; i < startIndex + len; i++)
		/* 129: */{
			/* 130: 184 */result <<= 8;
			/* 131: 185 */result |= bytes[i] & 0xFF;
			/* 132: */}
		/* 133: 188 */return result;
		/* 134: */}

	/* 135: */
	/* 136: */public static int bytes2IntegerLittle(byte[] bytes,
			int startIndex, int len)
	/* 137: */throws Exception
	/* 138: */{
		/* 139: 207 */if ((bytes == null) || (bytes.length == 0)
				|| (bytes.length <= startIndex)) {
			/* 140: 209 */throw new Exception("beyond scope");
			/* 141: */}
		/* 142: 212 */if (len > 4) {
			/* 143: 213 */len = 4;
			/* 144: */}
		/* 145: 216 */if (startIndex + len > bytes.length) {
			/* 146: 217 */len = bytes.length - startIndex;
			/* 147: */}
		/* 148: 220 */int result = 0;
		/* 149: 221 */for (int i = startIndex + len - 1; i >= startIndex; i--)
		/* 150: */{
			/* 151: 222 */result <<= 8;
			/* 152: 223 */result |= bytes[i] & 0xFF;
			/* 153: */}
		/* 154: 226 */return result;
		/* 155: */}

	/* 156: */
	/* 157: */public static long bytes2Long(byte[] bytes, int startIndex,
			int len)
	/* 158: */throws Exception
	/* 159: */{
		/* 160: 239 */if ((bytes == null) || (bytes.length == 0)
				|| (bytes.length <= startIndex)) {
			/* 161: 241 */throw new Exception("beyond scope");
			/* 162: */}
		/* 163: 244 */if (len > 8) {
			/* 164: 245 */len = 8;
			/* 165: */}
		/* 166: 248 */if (startIndex + len > bytes.length) {
			/* 167: 249 */len = bytes.length - startIndex;
			/* 168: */}
		/* 169: 252 */long result = 0L;
		/* 170: 253 */for (int i = startIndex; i < startIndex + len; i++)
		/* 171: */{
			/* 172: 255 */result <<= 8;
			/* 173: 256 */result |= bytes[i] & 0xFF;
			/* 174: */}
		/* 175: 259 */return result;
		/* 176: */}

	/* 177: */
	/* 178: */public static long bytes2LongLittle(byte[] bytes, int startIndex,
			int len)
	/* 179: */throws Exception
	/* 180: */{
		/* 181: 271 */if ((bytes == null) || (bytes.length == 0)
				|| (bytes.length <= startIndex)) {
			/* 182: 273 */throw new Exception("beyond scope");
			/* 183: */}
		/* 184: 276 */if (len > 8) {
			/* 185: 277 */len = 8;
			/* 186: */}
		/* 187: 280 */if (startIndex + len > bytes.length) {
			/* 188: 281 */len = bytes.length - startIndex;
			/* 189: */}
		/* 190: 284 */long result = 0L;
		/* 191: 285 */for (int i = startIndex + len - 1; i >= startIndex; i--)
		/* 192: */{
			/* 193: 287 */result <<= 8;
			/* 194: 288 */result |= bytes[i] & 0xFF;
			/* 195: */}
		/* 196: 291 */return result;
		/* 197: */}

	/* 198: */
	/* 199: */public static String bytes2DecStr(byte[] bytes, int startIndex,
			int endIndex)
	/* 200: */{
		/* 201: 309 */byte[] b = new byte[endIndex - startIndex + 1];
		/* 202: 310 */b[0] = 0;
		/* 203: 311 */System.arraycopy(bytes, startIndex, b, 1, endIndex
				- startIndex);
		/* 204: 312 */return new BigInteger(b).toString();
		/* 205: */}

	/* 206: */
	/* 207: */public static String bytes2DecStrLittle(byte[] bytes,
			int startIndex, int endIndex)
	/* 208: */{
		/* 209: 328 */byte[] b = getReverseBytes(bytes, startIndex, endIndex);
		/* 210: 329 */return bytes2DecStr(b);
		/* 211: */}

	/* 212: */
	/* 213: */public static String bytes2DecStr(byte[] bytes)
	/* 214: */{
		/* 215: 339 */return bytes2DecStr(bytes, 0, bytes.length);
		/* 216: */}

	/* 217: */
	/* 218: */public static String bytes2DecStrLittle(byte[] bytes)
	/* 219: */{
		/* 220: 350 */return bytes2DecStrLittle(bytes, 0, bytes.length);
		/* 221: */}

	/* 222: */
	/* 223: */public static String byte2BinStr(byte b)
	/* 224: */{
		/* 225: 363 */StringBuffer sb = new StringBuffer(8);
		/* 226: 364 */for (int i = 7; i >= 0; i--) {
			/* 227: 365 */sb.append(b >>> i & 0x1);
			/* 228: */}
		/* 229: 367 */return sb.toString();
		/* 230: */}

	/* 231: */
	/* 232: */public static byte byte2bcd(byte b)
	/* 233: */{
		/* 234: 378 */int i = b & 0xFF;
		/* 235: 379 */int decade = i / 10;
		/* 236: 380 */int unit = i % 10;
		/* 237: 381 */return (byte) (decade << 4 | unit);
		/* 238: */}

	/* 239: */
	/* 240: */public static String byte2ReversedBinStr(byte b)
	/* 241: */{
		/* 242: 394 */return reverseStr(byte2BinStr(b));
		/* 243: */}

	/* 244: */
	/* 245: */public static String byte2HexStr(byte b)
	/* 246: */{
		/* 247: 408 */return Integer.toString((b & 0xFF) + 256, 16)
				.substring(1).toUpperCase();
		/* 248: */}

	/* 249: */
	/* 250: */public static String uByte2decStr(byte b)
	/* 251: */{
		/* 252: 419 */return Integer.toString(b & 0xFF);
		/* 253: */}

	/* 254: */
	/* 255: */public static String uByte2decStr(byte b, int bits)
	/* 256: */{
		/* 257: 430 */return fixStrLength(uByte2decStr(b), bits);
		/* 258: */}

	/* 259: */
	/* 260: */public static String bytes2HexStr(byte[] bytes)
	/* 261: */{
		/* 262: 439 */if ((bytes == null) || (bytes.length == 0)) {
			/* 263: 440 */return "";
			/* 264: */}
		/* 265: 443 */StringBuffer sbResult = new StringBuffer();
		/* 266: 444 */for (int i = 0; i < bytes.length; i++) {
			/* 267: 445 */sbResult.append(Integer
					.toString((bytes[i] & 0xFF) + 256, 16).substring(1)
					.toUpperCase());
			/* 268: */}
		/* 269: 447 */return sbResult.toString();
		/* 270: */}

	/* 271: */
	/* 272: */public static String bytes2HexStr(byte[] bytes, int bits)
	/* 273: */{
		/* 274: 456 */if ((bytes == null) || (bytes.length == 0)) {
			/* 275: 457 */return "";
			/* 276: */}
		/* 277: 460 */StringBuffer sbResult = new StringBuffer();
		/* 278: 461 */for (int i = 0; i < bytes.length; i++) {
			/* 279: 462 */sbResult.append(Integer
					.toString((bytes[i] & 0xFF) + 256, 16).substring(1)
					.toUpperCase());
			/* 280: */}
		/* 281: 464 */while (sbResult.length() < bits) {
			/* 282: 465 */sbResult.insert(0, "0");
			/* 283: */}
		/* 284: 468 */String result = sbResult.toString();
		/* 285: 469 */return result = result.substring(bits - result.length());
		/* 286: */}

	/* 287: */
	/* 288: */public static String bytes2HexStr(byte[] bytes, boolean addSpace)
	/* 289: */{
		/* 290: 479 */if (bytes == null) {
			/* 291: 480 */return "";
			/* 292: */}
		/* 293: 483 */StringBuffer sbResult = new StringBuffer();
		/* 294: 484 */for (int i = 0; i < bytes.length; i++)
		/* 295: */{
			/* 296: 485 */sbResult.append(Integer
					.toString((bytes[i] & 0xFF) + 256, 16).substring(1)
					.toUpperCase());
			/* 297: 486 */if (addSpace) {
				/* 298: 487 */sbResult.append(" ");
				/* 299: */}
			/* 300: */}
		/* 301: 490 */return sbResult.toString();
		/* 302: */}

	/* 303: */
	/* 304: */public static String chars2HexStr(char[] chars)
	/* 305: */{
		/* 306: 501 */if (chars == null) {
			/* 307: 502 */return "";
			/* 308: */}
		/* 309: 505 */String result = "";
		/* 310: 506 */for (int i = 0; i < chars.length; i++) {
			/* 311: 508 */result = result
					+ Integer.toString((chars[i] & 0xFF) + 'Ā', 16)
							.substring(1);
			/* 312: */}
		/* 313: 510 */return result;
		/* 314: */}

	/* 315: */
	/* 316: */public static String bytes2HexStr(byte[] bytes, int startIndex,
			int len, boolean needspace)
	/* 317: */{
		/* 318: 527 */if (bytes == null) {
			/* 319: 528 */return "";
			/* 320: */}
		/* 321: 531 */if (startIndex + len > bytes.length) {
			/* 322: 533 */len = bytes.length - startIndex;
			/* 323: */}
		/* 324: 536 */StringBuffer sbResult = new StringBuffer();
		/* 325: 537 */for (int i = startIndex; i < startIndex + len; i++)
		/* 326: */{
			/* 327: 539 */sbResult.append(Integer
					.toString((bytes[i] & 0xFF) + 256, 16).substring(1)
					.toUpperCase());
			/* 328: 540 */if (needspace) {
				/* 329: 541 */sbResult.append(" ");
				/* 330: */}
			/* 331: */}
		/* 332: 544 */return sbResult.toString().trim();
		/* 333: */}

	/* 334: */
	/* 335: */private static byte hexCharToByte(char hexChar)
	/* 336: */{
		/* 337: 555 */return (byte) "0123456789ABCDEF".indexOf(hexChar);
		/* 338: */}

	/* 339: */
	/* 340: */public static byte[] hexStrToBytes(String hexStr)
	/* 341: */{
		/* 342: 565 */hexStr = hexStr.replace(" ", "").toUpperCase();
		/* 343: 567 */if (hexStr.length() % 2 == 1) {
			/* 344: 568 */hexStr = "0" + hexStr;
			/* 345: */}
		/* 346: 572 */int bytesLen = hexStr.length() / 2;
		/* 347: 573 */char[] hexChars = hexStr.toCharArray();
		/* 348: 574 */byte[] bytes = new byte[bytesLen];
		/* 349: 576 */for (int i = 0; i < bytesLen; i++) {
			/* 350: 577 */bytes[i] = ((byte) (hexCharToByte(hexChars[(2 * i)]) << 4 | hexCharToByte(hexChars[(2 * i + 1)])));
			/* 351: */}
		/* 352: 580 */return bytes;
		/* 353: */}

	/* 354: */
	/* 355: */public static byte decCharToByte(char c)
	/* 356: */{
		/* 357: 592 */if ((c < '0') || (c > '9')) {
			/* 358: 593 */return 0;
			/* 359: */}
		/* 360: 595 */return (byte) (c - '0');
		/* 361: */}

	/* 362: */
	/* 363: */public static byte[] decStrToBytes(String decStr)
	/* 364: */{
		/* 365: 607 */byte[] b = new byte[decStr.length()];
		/* 366: 608 */for (int i = 0; i < decStr.length(); i++) {
			/* 367: 609 */b[i] = decCharToByte(decStr.charAt(i));
			/* 368: */}
		/* 369: 611 */return b;
		/* 370: */}

	/* 371: */
	/* 372: */public static byte[] decStrToBytes(String decStr, int bytesCount)
	/* 373: */{
		/* 374: 623 */byte[] b = new byte[bytesCount];
		/* 375: 624 */for (int i = 0; i < bytesCount; i++) {
			/* 376: 625 */b[i] = decCharToByte(decStr.charAt(i));
			/* 377: */}
		/* 378: 627 */return b;
		/* 379: */}

	/* 380: */
	/* 381: */public static byte[] decStrValueToBytes(String decStr,
			int bytesCount)
	/* 382: */{
		/* 383: 641 */BigInteger bi = new BigInteger(decStr);
		/* 384: 642 */byte[] b = bi.toByteArray();
		/* 385: 643 */int len = b.length;
		/* 386: 644 */byte[] result = new byte[bytesCount];
		/* 387: 645 */for (int i = 0; i < bytesCount; i++) {
			/* 388: 646 */if (i < bytesCount - len) {
				/* 389: 647 */result[i] = 0;
				/* 390: */} else {
				/* 391: 649 */result[i] = b[(i - (bytesCount - len))];
				/* 392: */}
			/* 393: */}
		/* 394: 653 */return result;
		/* 395: */}

	/* 396: */
	/* 397: */public static byte[] decStrValueToBytesLittle(String decStr,
			int bytesCount)
	/* 398: */{
		/* 399: 667 */BigInteger bi = new BigInteger(decStr);
		/* 400: 668 */byte[] b = bi.toByteArray();
		/* 401: 669 */int len = b.length;
		/* 402: 670 */reverseBytes(b);
		/* 403: 671 */return Arrays.copyOfRange(b, 0, len <= bytesCount ? len
				: bytesCount);
		/* 404: */}

	/* 405: */
	/* 406: */public static String decStrToHexStr(String decStr, int bits)
	/* 407: */throws Exception
	/* 408: */{
		/* 409: 683 */String hexStr = new BigInteger(decStr.trim())
				.toString(16).toUpperCase();
		/* 410: 685 */for (int i = hexStr.length(); i < bits; i++) {
			/* 411: 686 */hexStr = "0" + hexStr;
			/* 412: */}
		/* 413: 689 */return hexStr;
		/* 414: */}

	/* 415: */
	/* 416: */public static String hexStrToDecStr(String hexStr)
	/* 417: */{
		/* 418: 698 */return new BigInteger(hexStr, 16).toString();
		/* 419: */}

	/* 420: */
	/* 421: */public static String fixStrLength(String str, int len)
	/* 422: */{
		/* 423: 709 */for (int i = str.length(); i < len; i++) {
			/* 424: 710 */str = "0" + str;
			/* 425: */}
		/* 426: 713 */for (int i = str.length(); i > len; i--) {
			/* 427: 715 */str = str.substring(1);
			/* 428: */}
		/* 429: 718 */return str;
		/* 430: */}

	/* 431: */
	/* 432: */public static String delZeroFromHead(String str)
	/* 433: */{
					int i;
		/* 434: 728 */for (i = 0; i < str.length(); i++) {
			/* 435: 729 */if (str.charAt(i) != '0') {
				/* 436: */break;
				/* 437: */}
			/* 438: */}
		/* 439: 733 */String result = str;
		/* 440: 734 */if (i != 0)
		/* 441: */{
			/* 442: 735 */StringBuffer sb = new StringBuffer(str);
			/* 443: 736 */sb.delete(0, i);
			/* 444: 737 */result = sb.toString();
			/* 445: */}
		/* 446: 739 */return result;
		/* 447: */}

	/* 448: */
	/* 449: */public static String binaryStrToHexStr(String binaryStr, int bits)
	/* 450: */{
		/* 451: 752 */String hexStr = new BigInteger(binaryStr, 2).toString(16)
				.toUpperCase();
		/* 452: 753 */for (int i = hexStr.length(); i < bits; i++) {
			/* 453: 754 */hexStr = "0" + hexStr;
			/* 454: */}
		/* 455: 756 */return hexStr;
		/* 456: */}

	/* 457: */
	/* 458: */public static byte XorCheck(String hexStr)
	/* 459: */{
		/* 460: 766 */byte[] bytes = hexStrToBytes(hexStr);
		/* 461: 767 */byte result = (byte) (bytes[0] ^ bytes[1]);
		/* 462: 768 */for (int i = 2; i < bytes.length; i++) {
			/* 463: 769 */result = (byte) (result ^ bytes[i]);
			/* 464: */}
		/* 465: 771 */return result;
		/* 466: */}

	/* 467: */
	/* 468: */public static String XorCheck(String hexStr, int bits)
	/* 469: */{
		/* 470: 788 */StringBuffer result = new StringBuffer(
				byte2HexStr(XorCheck(hexStr)));
		/* 471: 789 */for (int i = result.length(); i < bits; i++) {
			/* 472: 791 */result.insert(0, "0");
			/* 473: */}
		/* 474: 793 */return result.toString();
		/* 475: */}

	/* 476: */
	/* 477: */public static String XorCheck(byte[] bytes)
	/* 478: */{
		/* 479: 809 */return byte2HexStr(XorCheck(bytes, 0, bytes.length));
		/* 480: */}

	/* 481: */
	/* 482: */public static byte XorCheck(byte[] bytes, int startIndex,
			int endIndex)
	/* 483: */{
		/* 484: 826 */bytes = Arrays.copyOfRange(bytes, startIndex, endIndex);
		/* 485: 827 */if (bytes == null) {
			/* 486: 828 */return 0;
			/* 487: */}
		/* 488: 830 */if (bytes.length == 1) {
			/* 489: 831 */return bytes[0];
			/* 490: */}
		/* 491: 834 */byte result = (byte) (bytes[0] ^ bytes[1]);
		/* 492: 835 */for (int i = 2; i < bytes.length; i++) {
			/* 493: 836 */result = (byte) (result ^ bytes[i]);
			/* 494: */}
		/* 495: 838 */return result;
		/* 496: */}

	/* 497: */
	/* 498: 842 */private static final byte[] crc7_syndrome_table = {
	/* 499: 843 */0, 9, 18, 27, 36, 45, 54, 63,
	/* 500: 844 */72, 65, 90, 83, 108, 101, 126, 119,
	/* 501: 845 */25, 16, 11, 2, 61, 52, 47, 38,
	/* 502: 846 */81, 88, 67, 74, 117, 124, 103, 110,
	/* 503: 847 */50, 59, 32, 41, 22, 31, 4, 13,
	/* 504: 848 */122, 115, 104, 97, 94, 87, 76, 69,
	/* 505: 849 */43, 34, 57, 48, 15, 6, 29, 20,
	/* 506: 850 */99, 106, 113, 120, 71, 78, 85, 92,
	/* 507: 851 */100, 109, 118, 127, 64, 73, 82, 91,
	/* 508: 852 */44, 37, 62, 55, 8, 1, 26, 19,
	/* 509: 853 */125, 116, 111, 102, 89, 80, 75, 66,
	/* 510: 854 */53, 60, 39, 46, 17, 24, 3, 10,
	/* 511: 855 */86, 95, 68, 77, 114, 123, 96, 105,
	/* 512: 856 */30, 23, 12, 5, 58, 51, 40, 33,
	/* 513: 857 */79, 70, 93, 84, 107, 98, 121, 112,
	/* 514: 858 */7, 14, 21, 28, 35, 42, 49, 56,
	/* 515: 859 */65, 72, 83, 90, 101, 108, 119, 126,
	/* 516: 860 */9, 0, 27, 18, 45, 36, 63, 54,
	/* 517: 861 */88, 81, 74, 67, 124, 117, 110, 103,
	/* 518: 862 */16, 25, 2, 11, 52, 61, 38, 47,
	/* 519: 863 */115, 122, 97, 104, 87, 94, 69, 76,
	/* 520: 864 */59, 50, 41, 32, 31, 22, 13, 4,
	/* 521: 865 */106, 99, 120, 113, 78, 71, 92, 85,
	/* 522: 866 */34, 43, 48, 57, 6, 15, 20, 29,
	/* 523: 867 */37, 44, 55, 62, 1, 8, 19, 26,
	/* 524: 868 */109, 100, 127, 118, 73, 64, 91, 82,
	/* 525: 869 */60, 53, 46, 39, 24, 17, 10, 3,
	/* 526: 870 */116, 125, 102, 111, 80, 89, 66, 75,
	/* 527: 871 */23, 30, 5, 12, 51, 58, 33, 40,
	/* 528: 872 */95, 86, 77, 68, 123, 114, 105, 96,
	/* 529: 873 */14, 7, 28, 21, 42, 35, 56, 49,
	/* 530: 874 */70, 79, 84, 93, 98, 107, 112, 121 };

	/* 531: */
	/* 532: */public static byte crc7Check(byte[] data, int startIndex,
			int endIndex)
	/* 533: */{
		/* 534: 889 */byte crc = 0;
		/* 535: 890 */for (int i = startIndex; i < endIndex; i++) {
			/* 536: 891 */crc = crc7_syndrome_table[(0xFF & (crc << 1 ^ data[i]))];
			/* 537: */}
		/* 538: 893 */return crc;
		/* 539: */}

	/* 540: */
	/* 541: 897 */private static final int[] crc16_table = {
	/* 542: 898 */0, 4129, 8258, 12387, 16516, 20645, 24774, 28903,
	/* 543: 899 */33032, 37161, 41290, 45419, 49548, 53677, 57806, 61935 };

	/* 544: */
	/* 545: */public static int crc16Check(byte[] data, int startIndex,
			int endIndex)
	/* 546: */{
		/* 547: 914 */int crc = 0;
		/* 548: 915 */byte da = 0;
		/* 549: 916 */for (int i = startIndex; i < endIndex; i++)
		/* 550: */{
			/* 551: 917 */da = (byte) (crc >>> 12 & 0xF);
			/* 552: 918 */da = (byte) (crc / 256 / 16);
			/* 553: 919 */crc = crc << 4 & 0xFFFF;
			/* 554: 920 */crc ^= crc16_table[((da ^ data[i] / 16) & 0xFFFF)];
			/* 555: 921 */da = (byte) (crc >>> 12 & 0xF);
			/* 556: 922 */crc = crc << 4 & 0xFFFF;
			/* 557: 923 */crc ^= crc16_table[((da ^ data[i] & 0xF) & 0xFFFF)];
			/* 558: */}
		/* 559: 925 */return crc;
		/* 560: */}

	/* 561: */
	/* 562: */public static byte sumCheck(byte[] data, int startIndex,
			int endIndex)
	/* 563: */{
		/* 564: 941 */byte sum = 0;
		/* 565: 942 */for (int i = startIndex; i < endIndex; i++) {
			/* 566: 943 */sum = (byte) (sum + data[i]);
			/* 567: */}
		/* 568: 945 */return sum;
		/* 569: */}

	/* 570: */
	/* 571: */public static boolean hexStrValueEquals(String hexStr1,
			String hexStr2)
	/* 572: */{
		/* 573: 962 */if (hexStr1 == null)
		/* 574: */{
			/* 575: 963 */if (hexStr2 == null) {
				/* 576: 964 */return true;
				/* 577: */}
			/* 578: 966 */return false;
			/* 579: */}
		/* 580: 969 */if (hexStr1.replace(" ", "").toUpperCase()
				.equals(hexStr2.replace(" ", "").toUpperCase())) {
			/* 581: 970 */return true;
			/* 582: */}
		/* 583: 972 */return false;
		/* 584: */}

	/* 585: */
	/* 586: */public static String getCurrentTime()
	/* 587: */{
		/* 588: 984 */return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		/* 589: */}

	/* 590: */
	/* 591: */public static String getCurrentDate()
	/* 592: */{
		/* 593: 993 */return new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date());
		/* 594: */}

	/* 595: */
	/* 596: */public static String getRelativeTime(String standardTime,
			long millSeconds)
	/* 597: */throws ParseException
	/* 598: */{
		/* 599:1007 */SimpleDateFormat formater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		/* 600:1008 */Date date = formater.parse(standardTime);
		/* 601:1009 */Calendar cal = Calendar.getInstance();
		/* 602:1010 */cal.setTime(date);
		/* 603:1011 */cal.setTimeInMillis(cal.getTimeInMillis() + millSeconds);
		/* 604:1012 */return formater.format(cal.getTime());
		/* 605: */}

	/* 606: */
	/* 607: */public static String getDateTime(int year, int month, int date,
			int hourOfDay, int minute, int second)
	/* 608: */{
		/* 609:1027 */Calendar cal = Calendar.getInstance();
		/* 610:1028 */cal.clear();
		/* 611:1029 */cal.set(year, month, date, hourOfDay, minute, second);
		/* 612:1030 */SimpleDateFormat formater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		/* 613:1031 */return formater.format(cal.getTime());
		/* 614: */}

	/* 615: */
	/* 616: */public static long getDiffMillis(String dateTimeFrom,
			String dateTimeTo)
	/* 617: */throws ParseException
	/* 618: */{
		/* 619:1046 */SimpleDateFormat formater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		/* 620:1047 */Date dateFrom = formater.parse(dateTimeFrom);
		/* 621:1048 */Date dateTo = formater.parse(dateTimeTo);
		/* 622:1049 */Calendar cal = Calendar.getInstance();
		/* 623:1050 */cal.setTime(dateFrom);
		/* 624:1051 */long millsecFrom = cal.getTimeInMillis();
		/* 625:1052 */cal.setTime(dateTo);
		/* 626:1053 */long millsecTo = cal.getTimeInMillis();
		/* 627: */
		/* 628:1055 */return millsecTo - millsecFrom;
		/* 629: */}

	/* 630: */
	/* 631: */public static long getDiffDays(String dateTimeFrom,
			String dateTimeTo)
	/* 632: */throws ParseException
	/* 633: */{
		/* 634:1070 */SimpleDateFormat formater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		/* 635:1071 */Date date1 = formater.parse(dateTimeFrom);
		/* 636:1072 */Date date2 = formater.parse(dateTimeTo);
		/* 637:1073 */Calendar cal = Calendar.getInstance();
		/* 638:1074 */cal.setTime(date1);
		/* 639:1075 */long millsec1 = cal.getTimeInMillis();
		/* 640:1076 */cal.setTime(date2);
		/* 641:1077 */long millsec2 = cal.getTimeInMillis();
		/* 642: */
		/* 643:1079 */return Math.abs(millsec2 - millsec1) / 86400000L;
		/* 644: */}

	/* 645: */
	/* 646: */public static long getDiffMinutes(String dateTimeFrom,
			String dateTimeTo)
	/* 647: */throws ParseException
	/* 648: */{
		/* 649:1095 */SimpleDateFormat formater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		/* 650:1096 */Date date1 = formater.parse(dateTimeFrom);
		/* 651:1097 */Date date2 = formater.parse(dateTimeTo);
		/* 652:1098 */Calendar cal = Calendar.getInstance();
		/* 653:1099 */cal.setTime(date1);
		/* 654:1100 */long millsec1 = cal.getTimeInMillis();
		/* 655:1101 */cal.setTime(date2);
		/* 656:1102 */long millsec2 = cal.getTimeInMillis();
		/* 657: */
		/* 658:1104 */return Math.abs(millsec2 - millsec1) / 60000L;
		/* 659: */}

	/* 660: */
	/* 661: */public static long getRawDiffMinutes(String dateTimeFrom,
			String dateTimeTo)
	/* 662: */throws ParseException
	/* 663: */{
		/* 664:1120 */SimpleDateFormat formater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		/* 665:1121 */Date date1 = formater.parse(dateTimeFrom);
		/* 666:1122 */Date date2 = formater.parse(dateTimeTo);
		/* 667:1123 */Calendar cal = Calendar.getInstance();
		/* 668:1124 */cal.setTime(date1);
		/* 669:1125 */long millsec1 = cal.getTimeInMillis();
		/* 670:1126 */cal.setTime(date2);
		/* 671:1127 */long millsec2 = cal.getTimeInMillis();
		/* 672: */
		/* 673:1129 */return (millsec2 - millsec1) / 60000L;
		/* 674: */}

	/* 675: */
	/* 676: */public static byte[] ipToBytes(String ip)
	/* 677: */{
		/* 678:1142 */byte[] dataSegment = new byte[4];
		/* 679: */
		/* 680:1144 */String[] tmpStr = ip.trim().split("\\.");
		/* 681:1145 */if (tmpStr.length != 4) {
			/* 682:1146 */return null;
			/* 683: */}
		/* 684:1149 */for (int i = 0; i < 4; i++) {
			/* 685: */try
			/* 686: */{
				/* 687:1151 */dataSegment[i] = ((byte) Integer
						.parseInt(tmpStr[i]));
				/* 688: */}
			/* 689: */catch (Exception e)
			/* 690: */{
				/* 691:1153 */return null;
				/* 692: */}
			/* 693: */}
		/* 694:1156 */return dataSegment;
		/* 695: */}

	/* 696: */
	/* 697: */public static byte[] stringToBytes(String str)
	/* 698: */{
		/* 699:1167 */return str.getBytes();
		/* 700: */}

	/* 701: */
	/* 702: */public static byte[] stringToBytes(String str, int strlen,
			String fillStr, int bytescount)
	/* 703: */{
		/* 704:1183 */StringBuffer sb = new StringBuffer(str);
		/* 705:1184 */for (int i = str.length(); i < strlen; i++) {
			/* 706:1185 */sb.append(fillStr);
			/* 707: */}
		/* 708:1187 */return Arrays.copyOfRange(sb.toString().getBytes(), 0,
				bytescount);
		/* 709: */}

	/* 710: */
	/* 711: */public static String bytes2Chinese(byte[] bytes, int startIndex,
			int endIndex)
	/* 712: */{
		/* 713:1195 */return "";
		/* 714: */}

	/* 715: */
	/* 716: */public static String bytes2Chinese(byte[] bytes)
	/* 717: */{
		/* 718:1203 */return "";
		/* 719: */}

	/* 720: */
	/* 721: */public static <T extends Serializable> T clone(T obj)
	/* 722: */{
		/* 723:1214 */T cloneObj = null;
		/* 724: */try
		/* 725: */{
			/* 726:1217 */ByteArrayOutputStream out = new ByteArrayOutputStream();
			/* 727:1218 */ObjectOutputStream obs = new ObjectOutputStream(out);
			/* 728:1219 */obs.writeObject(obj);
			/* 729:1220 */obs.close();
			/* 730: */
			/* 731: */
			/* 732:1223 */ByteArrayInputStream ios = new ByteArrayInputStream(
					out.toByteArray());
			/* 733:1224 */ObjectInputStream ois = new ObjectInputStream(ios);
			/* 734: */
			/* 735:1226 */cloneObj = (T) ois.readObject();
			/* 736:1227 */ois.close();
			/* 737: */}
		/* 738: */catch (Exception e)
		/* 739: */{
			/* 740:1229 */e.printStackTrace();
			/* 741: */}
		/* 742:1231 */return cloneObj;
		/* 743: */}

	/* 744: */
	/* 745: */public static void reverseBytes(byte[] bytes, int startIndex,
			int endIndex)
	/* 746: */{
		/* 747:1246 */for (int i = startIndex; i < (startIndex + endIndex) / 2; i++)
		/* 748: */{
			/* 749:1247 */byte tmp = bytes[i];
			/* 750:1248 */bytes[i] = bytes[(endIndex - 1 - i)];
			/* 751:1249 */bytes[(endIndex - 1 - i)] = tmp;
			/* 752: */}
		/* 753: */}

	/* 754: */
	/* 755: */public static void reverseChars(char[] chars, int startIndex,
			int endIndex)
	/* 756: */{
		/* 757:1264 */for (int i = startIndex; i < (startIndex + endIndex) / 2; i++)
		/* 758: */{
			/* 759:1265 */char tmp = chars[i];
			/* 760:1266 */chars[i] = chars[(endIndex - 1 - i)];
			/* 761:1267 */chars[(endIndex - 1 - i)] = tmp;
			/* 762: */}
		/* 763: */}

	/* 764: */
	/* 765: */public static String reverseStr(String str, int startIndex,
			int endIndex)
	/* 766: */{
		/* 767:1284 */char[] chars = str.toCharArray();
		/* 768:1286 */for (int i = startIndex; i < (startIndex + endIndex) / 2; i++)
		/* 769: */{
			/* 770:1287 */char tmp = chars[i];
			/* 771:1288 */chars[i] = chars[(endIndex - 1 - i)];
			/* 772:1289 */chars[(endIndex - 1 - i)] = tmp;
			/* 773: */}
		/* 774:1291 */return String.valueOf(chars);
		/* 775: */}

	/* 776: */
	/* 777: */public static String reverseStr(String str)
	/* 778: */{
		/* 779:1302 */return reverseStr(str, 0, str.length());
		/* 780: */}

	/* 781: */
	/* 782: */public static void reverseBytes(byte[] bytes)
	/* 783: */{
		/* 784:1314 */reverseBytes(bytes, 0, bytes.length);
		/* 785: */}

	/* 786: */
	/* 787: */public static byte[] getReverseBytes(byte[] bytes,
			int startIndex, int endIndex)
	/* 788: */{
		/* 789:1329 */byte[] b = Arrays
				.copyOfRange(bytes, startIndex, endIndex);
		/* 790:1330 */reverseBytes(b);
		/* 791:1331 */return b;
		/* 792: */}

	/* 793: */
	/* 794: */public static byte[] getReverseBytes(byte[] bytes)
	/* 795: */{
		/* 796:1342 */return getReverseBytes(bytes, 0, bytes.length);
		/* 797: */}

	/* 798: */
	/* 799: */public static int getDayOfWeek(int weekday)
	/* 800: */{
		/* 801:1354 */if (weekday == 0) {
			/* 802:1355 */return 7;
			/* 803: */}
		/* 804:1357 */return weekday - 1;
		/* 805: */}

	/* 806: */
	/* 807: */public static byte binStrToByte(String binStr)
	/* 808: */{
		/* 809:1371 */int b = 0;
		/* 810:1372 */for (int i = 1; i < binStr.length(); i++)
		/* 811: */{
			/* 812:1373 */if (i >= 8) {
				/* 813: */break;
				/* 814: */}
			/* 815:1376 */if (i != 0) {
				/* 816:1377 */b <<= 1;
				/* 817: */}
			/* 818:1379 */b ^= binStr.charAt(i) - '0';
			/* 819: */}
		/* 820:1381 */return (byte) b;
		/* 821: */}

	/* 822: */
	/* 823: */public static byte[] binStrToBytes(String binStr, int bytesCount)
	/* 824: */{
		/* 825:1394 */byte[] bytes = new byte[bytesCount];
		/* 826:1395 */Arrays.fill(bytes, (byte) 0);
		/* 827:1396 */byte addbit = 0;
		/* 828:1397 */for (int i = 0; i < binStr.length(); i++)
		/* 829: */{
			/* 830:1398 */if (i / 8 >= bytesCount) {
				/* 831: */break;
				/* 832: */}
			/* 833:1404 */addbit = (byte) (binStr.charAt(i) - '0' << i % 8);
			/* 834:1405 */bytes[(bytesCount - 1 - i / 8)] = ((byte) (bytes[(bytesCount - 1 - i / 8)] | addbit));
			/* 835: */}
		/* 836:1407 */return bytes;
		/* 837: */}

	/* 838: */
	/* 839: */public static String byteToBinStr(byte b)
	/* 840: */{
		/* 841:1418 */StringBuffer resultBuffer = new StringBuffer();
		/* 842:1419 */for (int i = 7; i > 0; i--) {
			/* 843:1420 */resultBuffer.append(String.valueOf(b >> i & 0x1));
			/* 844: */}
		/* 845:1422 */return resultBuffer.toString();
		/* 846: */}

	/* 847: */
	/* 848: */public static String bytesToBinStr(byte[] bytes, int from, int to)
	/* 849: */{
		/* 850:1435 */StringBuffer resultBuffer = new StringBuffer();
		/* 851:1436 */for (int i = from; i < to; i++) {
			/* 852:1437 */resultBuffer.append(byteToBinStr(bytes[i]));
			/* 853: */}
		/* 854:1439 */return resultBuffer.toString();
		/* 855: */}

	/* 856: */
	/* 857: */public static String binStrNot(String binStr)
	/* 858: */{
		/* 859:1451 */char[] chars = binStr.toCharArray();
		/* 860:1452 */for (int i = 0; i < chars.length; i++) {
			/* 861:1453 */if (chars[i] == '1') {
				/* 862:1454 */chars[i] = '0';
				/* 863:1455 */} else if (chars[i] == '0') {
				/* 864:1456 */chars[i] = '1';
				/* 865: */}
			/* 866: */}
		/* 867:1459 */return new String(chars);
		/* 868: */}

	/* 869: */
	/* 870: */public static void printException(Exception e)
	/* 871: */{
		/* 872:1468 */e.printStackTrace();
		/* 873: */}

	/* 874: */
	/* 875: */public static void printInfo(String inf)
	/* 876: */{
		/* 877:1472 */SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.zzz");
		/* 878:1473 */System.out.println(sdf.format(new Date()) + "  " + inf);
		/* 879: */}
	/* 880: */
}

/*
 * Location: C:\Users\congxiaofan\Desktop\
 * 
 * Qualified Name: com.desun.protocal.BitConverter
 * 
 * JD-Core Version: 0.7.0.1
 */
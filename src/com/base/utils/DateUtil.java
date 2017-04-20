/*    1:     */package com.base.utils;

/*    2:     */
/*    3:     */import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/*   14:     */
/*   15:     */public class DateUtil
/* 16: */{
	/* 17: 28 */static SimpleDateFormat sdfShort = new SimpleDateFormat(
			"yyyyMMdd");
	/* 18: 29 */static SimpleDateFormat sdfLong = new SimpleDateFormat(
			"yyyy-MM-dd");
	/* 19: 30 */static SimpleDateFormat sdfLongCn = new SimpleDateFormat(
			"yyyy年MM月dd日");
	/* 20: 31 */static SimpleDateFormat sdfShortU = new SimpleDateFormat(
			"MMM dd", Locale.ENGLISH);
	/* 21: 32 */static SimpleDateFormat sdfLongU = new SimpleDateFormat(
			"MMM dd,yyyy", Locale.ENGLISH);
	/* 22: 33 */static SimpleDateFormat sdfLongTime = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	/* 23: 34 */static SimpleDateFormat sdfLongTimePlus = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/* 24: 35 */static SimpleDateFormat sdfShortLongTimePlusCn = new SimpleDateFormat(
			"yyyy年MM月dd日 HH:mm");
	/* 25: 36 */static SimpleDateFormat sdfLongTimePlusMill = new SimpleDateFormat(
			"yyyyMMddHHmmssSSSS");
	/* 26: 37 */static SimpleDateFormat sdfMd = new SimpleDateFormat("MM月dd日");
	/* 27: 38 */private static long DAY_IN_MILLISECOND = 86400000L;

	/* 28: */
	/* 29: */public static String getDateLong(java.util.Date date)
	/* 30: */{
		/* 31: 52 */String nowDate = "";
		/* 32: */try
		/* 33: */{
			/* 34: 55 */if (date != null) {
			}
			/* 35: 56 */return sdfLong.format(date);
			/* 36: */}
		/* 37: */catch (Exception e)
		/* 38: */{
			/* 39: 61 */System.out
					.println("Error at getDate:" + e.getMessage());
			/* 40: */}
		/* 41: 62 */return "";
		/* 42: */}

	/* 43: */
	/* 44: */public static String getDateLongCn(java.util.Date date)
	/* 45: */{
		/* 46: 73 */String nowDate = "";
		/* 47: */try
		/* 48: */{
			/* 49: 76 */if (date != null) {
			}
			/* 50: 77 */return sdfLongCn.format(date);
			/* 51: */}
		/* 52: */catch (Exception e)
		/* 53: */{
			/* 54: 82 */System.out
					.println("Error at getDate:" + e.getMessage());
			/* 55: */}
		/* 56: 83 */return "";
		/* 57: */}

	/* 58: */
	/* 59: */public static String getDateMD(java.util.Date date)
	/* 60: */{
		/* 61: 96 */String nowDate = "";
		/* 62: */try
		/* 63: */{
			/* 64: 99 */if (date != null) {
			}
			/* 65: 100 */return sdfMd.format(date);
			/* 66: */}
		/* 67: */catch (Exception e)
		/* 68: */{
			/* 69: 105 */System.out.println("Error at getDate:"
					+ e.getMessage());
			/* 70: */}
		/* 71: 106 */return "";
		/* 72: */}

	/* 73: */
	/* 74: */public static String getDateShortLongTimeCn(java.util.Date date)
	/* 75: */{
		/* 76: 118 */String nowDate = "";
		/* 77: */try
		/* 78: */{
			/* 79: 121 */if (date != null) {
			}
			/* 80: 122 */return sdfShortLongTimePlusCn.format(date);
			/* 81: */}
		/* 82: */catch (Exception e)
		/* 83: */{
			/* 84: 127 */System.out.println("Error at getDate:"
					+ e.getMessage());
			/* 85: */}
		/* 86: 128 */return "";
		/* 87: */}

	/* 88: */
	/* 89: */public static String getDateUS(java.util.Date date)
	/* 90: */{
		/* 91: 140 */String nowDate = "";
		/* 92: */try
		/* 93: */{
			/* 94: 143 */if (date != null) {
			}
			/* 95: 144 */return sdfLongU.format(date);
			/* 96: */}
		/* 97: */catch (Exception e)
		/* 98: */{
			/* 99: 149 */System.out.println("Error at getDate:"
					+ e.getMessage());
			/* 100: */}
		/* 101: 150 */return "";
		/* 102: */}

	/* 103: */
	/* 104: */public static String getDateUSShort(java.util.Date date)
	/* 105: */{
		/* 106: 162 */String nowDate = "";
		/* 107: */try
		/* 108: */{
			/* 109: 165 */if (date != null) {
			}
			/* 110: 166 */return sdfShortU.format(date);
			/* 111: */}
		/* 112: */catch (Exception e)
		/* 113: */{
			/* 114: 171 */System.out.println("Error at getDate:"
					+ e.getMessage());
			/* 115: */}
		/* 116: 172 */return "";
		/* 117: */}

	/* 118: */
	/* 119: */public static String getFomartDate(java.util.Date date,
			String format)
	/* 120: */{
		/* 121: */try
		/* 122: */{
			/* 123: 187 */return new SimpleDateFormat(format, Locale.UK)
					.format(date);
			/* 124: */}
		/* 125: */catch (Exception e)
		/* 126: */{
			/* 127: 191 */e.printStackTrace();
			/* 128: 192 */if (date == null) {
				/* 129: 192 */// tmpTernaryOp = new
								// java.util.Date().toString();//cxf
				return new java.util.Date().toString();
				/* 130: */}
			/* 131: */}
		/* 132: 192 */return date.toString();
		/* 133: */}

	/* 134: */
	/* 135: */public static String getNowLongTime()
	/* 136: */throws Exception
	/* 137: */{
		/* 138: 203 */String nowTime = "";
		/* 139: */try
		/* 140: */{
			/* 141: 206 */java.sql.Date date = null;
			/* 142: 207 */date = new java.sql.Date(
					new java.util.Date().getTime());
			/* 143: 208 */return sdfLongTime.format(date);
			/* 144: */}
		/* 145: */catch (Exception e)
		/* 146: */{
			/* 147: 213 */throw e;
			/* 148: */}
		/* 149: */}

	/* 150: */
	/* 151: */public static String getNowShortDate()
	/* 152: */throws Exception
	/* 153: */{
		/* 154: 224 */String nowDate = "";
		/* 155: */try
		/* 156: */{
			/* 157: 227 */java.sql.Date date = null;
			/* 158: 228 */date = new java.sql.Date(
					new java.util.Date().getTime());
			/* 159: 229 */return sdfShort.format(date);
			/* 160: */}
		/* 161: */catch (Exception e)
		/* 162: */{
			/* 163: 234 */throw e;
			/* 164: */}
		/* 165: */}

	/* 166: */
	/* 167: */public static String getNowFormateDate()
	/* 168: */throws Exception
	/* 169: */{
		/* 170: 245 */String nowDate = "";
		/* 171: */try
		/* 172: */{
			/* 173: 248 */java.sql.Date date = null;
			/* 174: 249 */date = new java.sql.Date(
					new java.util.Date().getTime());
			/* 175: 250 */return sdfLong.format(date);
			/* 176: */}
		/* 177: */catch (Exception e)
		/* 178: */{
			/* 179: 255 */throw e;
			/* 180: */}
		/* 181: */}

	/* 182: */
	/* 183: */public static String getNowPlusTime()
	/* 184: */throws Exception
	/* 185: */{
		/* 186: 266 */String nowDate = "";
		/* 187: */try
		/* 188: */{
			/* 189: 269 */java.sql.Date date = null;
			/* 190: 270 */date = new java.sql.Date(
					new java.util.Date().getTime());
			/* 191: 271 */return sdfLongTimePlus.format(date);
			/* 192: */}
		/* 193: */catch (Exception e)
		/* 194: */{
			/* 195: 276 */throw e;
			/* 196: */}
		/* 197: */}

	/* 198: */
	/* 199: */public static String getPlusTime(java.util.Date date)
	/* 200: */throws Exception
	/* 201: */{
		/* 202: 287 */if (date == null) {
			/* 203: 287 */return null;
			/* 204: */}
		/* 205: */try
		/* 206: */{
			/* 207: 290 */return sdfLongTimePlus.format(date);
			/* 208: */}
		/* 209: */catch (Exception e)
		/* 210: */{
			/* 211: 295 */throw e;
			/* 212: */}
		/* 213: */}

	/* 214: */
	/* 215: */public static String getPlusTime2(java.util.Date date)
	/* 216: */{
		/* 217: 307 */if (date == null) {
			/* 218: 307 */return null;
			/* 219: */}
		/* 220: */try
		/* 221: */{
			/* 222: 310 */return sdfLongTimePlus.format(date);
			/* 223: */}
		/* 224: */catch (Exception e)
		/* 225: */{
			/* 226: 315 */e.printStackTrace();
			/* 227: */}
		/* 228: 317 */return "";
		/* 229: */}

	/* 230: */
	/* 231: */public static String getNowPlusTimeMill()
	/* 232: */throws Exception
	/* 233: */{
		/* 234: 327 */String nowDate = "";
		/* 235: */try
		/* 236: */{
			/* 237: 330 */java.sql.Date date = null;
			/* 238: 331 */date = new java.sql.Date(
					new java.util.Date().getTime());
			/* 239: 332 */return sdfLongTimePlusMill.format(date);
			/* 240: */}
		/* 241: */catch (Exception e)
		/* 242: */{
			/* 243: 337 */throw e;
			/* 244: */}
		/* 245: */}

	/* 246: */
	/* 247: */public static String getNowYear()
	/* 248: */throws Exception
	/* 249: */{
		/* 250: 348 */String nowYear = "";
		/* 251: */try
		/* 252: */{
			/* 253: 351 */String strTemp = getNowLongTime();
			/* 254: 352 */return strTemp.substring(0, 4);
			/* 255: */}
		/* 256: */catch (Exception e)
		/* 257: */{
			/* 258: 357 */throw e;
			/* 259: */}
		/* 260: */}

	/* 261: */
	/* 262: */public static String getNowMonth()
	/* 263: */throws Exception
	/* 264: */{
		/* 265: 368 */String nowMonth = "";
		/* 266: */try
		/* 267: */{
			/* 268: 371 */String strTemp = getNowLongTime();
			/* 269: 372 */return strTemp.substring(4, 6);
			/* 270: */}
		/* 271: */catch (Exception e)
		/* 272: */{
			/* 273: 377 */throw e;
			/* 274: */}
		/* 275: */}

	/* 276: */
	/* 277: */public static String getNowDay()
	/* 278: */throws Exception
	/* 279: */{
		/* 280: 388 */String nowDay = "";
		/* 281: */try
		/* 282: */{
			/* 283: 391 */String strTemp = getNowLongTime();
			/* 284: 392 */return strTemp.substring(6, 8);
			/* 285: */}
		/* 286: */catch (Exception e)
		/* 287: */{
			/* 288: 397 */throw e;
			/* 289: */}
		/* 290: */}

	/* 291: */
	/* 292: */public static String getNowHour()
	/* 293: */throws Exception
	/* 294: */{
		/* 295: 408 */String nowHour = "";
		/* 296: */try
		/* 297: */{
			/* 298: 411 */String strTemp = getNowPlusTimeMill();
			/* 299: 412 */return strTemp.substring(8, 10);
			/* 300: */}
		/* 301: */catch (Exception e)
		/* 302: */{
			/* 303: 417 */throw e;
			/* 304: */}
		/* 305: */}

	/* 306: */
	/* 307: */public static String getTimeBySecond(String _second)
	/* 308: */throws Exception
	/* 309: */{
		/* 310: 429 */String returnTime = "";
		/* 311: 430 */long longHour = 0L;
		/* 312: 431 */long longMinu = 0L;
		/* 313: 432 */long longSec = 0L;
		/* 314: */try
		/* 315: */{
			/* 316: 435 */longSec = Long.parseLong(_second);
			/* 317: 436 */if (longSec == 0L) {
				/* 318: 438 */return "0时0分0秒";
				/* 319: */}
			/* 320: 441 */longHour = longSec / 3600L;
			/* 321: 442 */longSec %= 3600L;
			/* 322: 443 */longMinu = longSec / 60L;
			/* 323: 444 */longSec %= 60L;
			/* 324: 445 */return longHour + "时" + longMinu + "分" + longSec
					+ "秒";
			/* 325: */}
		/* 326: */catch (Exception e)
		/* 327: */{
			/* 328: 450 */throw e;
			/* 329: */}
		/* 330: */}

	/* 331: */
	/* 332: */public static String getTimeBySecond(long ms_second)
	/* 333: */throws Exception
	/* 334: */{
		/* 335: 463 */String returnTime = "";
		/* 336: 464 */long longHour = 0L;
		/* 337: 465 */long longMinu = 0L;
		/* 338: 466 */long longSec = 0L;
		/* 339: 467 */long longMs = ms_second;
		/* 340: */try
		/* 341: */{
			/* 342: 470 */if (longMs == 0L) {
				/* 343: 472 */return "0时0分0秒0毫秒";
				/* 344: */}
			/* 345: 475 */longHour = longMs / 3600000L;
			/* 346: 476 */longMs %= 3600000L;
			/* 347: 477 */longMinu = longMs / 60000L;
			/* 348: 478 */longMs %= 60000L;
			/* 349: 479 */longSec = longMs / 1000L;
			/* 350: 480 */longMs %= 1000L;
			/* 351: 481 */return longHour + "时" + longMinu + "分" + longSec
					+ "秒" + longMs + "毫秒";
			/* 352: */}
		/* 353: */catch (Exception e)
		/* 354: */{
			/* 355: 486 */throw e;
			/* 356: */}
		/* 357: */}

	/* 358: */
	/* 359: */public static int convertDateToYear(java.util.Date date)
	/* 360: */{
		/* 361: 498 */SimpleDateFormat df = new SimpleDateFormat("yyyy",
				new DateFormatSymbols());
		/* 362: 499 */return Integer.parseInt(df.format(date));
		/* 363: */}

	/* 364: */
	/* 365: */public static String convertDateToYearMonth(java.util.Date d)
	/* 366: */{
		/* 367: 510 */SimpleDateFormat df = new SimpleDateFormat("yyyyMM",
				new DateFormatSymbols());
		/* 368: 511 */return df.format(d);
		/* 369: */}

	/* 370: */
	/* 371: */public static String convertDateToYearMonthDay(java.util.Date d)
	/* 372: */{
		/* 373: 522 */SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd",
				new DateFormatSymbols());
		/* 374: 523 */return df.format(d);
		/* 375: */}

	/* 376: */
	/* 377: */public static String convertDateToMonth(java.util.Date d)
	/* 378: */{
		/* 379: 533 */SimpleDateFormat df = new SimpleDateFormat("MM",
				new DateFormatSymbols());
		/* 380: 534 */return df.format(d);
		/* 381: */}

	/* 382: */
	/* 383: */public static String convertDateToDay(java.util.Date d)
	/* 384: */{
		/* 385: 544 */SimpleDateFormat df = new SimpleDateFormat("dd",
				new DateFormatSymbols());
		/* 386: 545 */return df.format(d);
		/* 387: */}

	/* 388: */
	/* 389: */public static String convertDateToHour(java.util.Date d)
	/* 390: */{
		/* 391: 559 */SimpleDateFormat df = new SimpleDateFormat("HH",
				new DateFormatSymbols());
		/* 392: 560 */return df.format(d);
		/* 393: */}

	/* 394: */
	/* 395: */public static String convertDateToMinute(java.util.Date d)
	/* 396: */{
		/* 397: 571 */SimpleDateFormat df = new SimpleDateFormat("mm",
				new DateFormatSymbols());
		/* 398: 572 */return df.format(d);
		/* 399: */}

	/* 400: */
	/* 401: */public static java.util.Date getCurrentDate()
	/* 402: */{
		/* 403: 581 */Calendar cal = Calendar.getInstance();
		/* 404: */
		/* 405: */
		/* 406: 584 */java.util.Date d = cal.getTime();
		/* 407: */
		/* 408: 586 */return d;
		/* 409: */}

	/* 410: */
	/* 411: */public static String getCurrentYearMonth()
	/* 412: */{
		/* 413: 596 */Calendar cal = Calendar.getInstance();
		/* 414: 597 */String currentYear = new Integer(cal.get(1)).toString();
		/* 415: 598 */String currentMonth = null;
		/* 416: 599 */if (cal.get(2) < 9) {
			/* 417: 600 */currentMonth = "0"
					+ new Integer(cal.get(2) + 1).toString();
			/* 418: */} else {
			/* 419: 602 */currentMonth = new Integer(cal.get(2) + 1).toString();
			/* 420: */}
		/* 421: 603 */return currentYear + currentMonth;
		/* 422: */}

	/* 423: */
	/* 424: */public static int getCurrentYear()
	/* 425: */{
		/* 426: 613 */Calendar cal = Calendar.getInstance();
		/* 427: 614 */int currentYear = cal.get(1);
		/* 428: 615 */return currentYear;
		/* 429: */}

	/* 430: */
	/* 431: */public static String formatDateTime(String _dateTime,
			String _format)
	/* 432: */throws Exception
	/* 433: */{
		/* 434: 627 */String returnValue = "";
		/* 435: 628 */String formatString = _format.toUpperCase();
		/* 436: 629 */String strYear = "";
		/* 437: 630 */String strMonth = "";
		/* 438: 631 */String strDay = "";
		/* 439: 632 */String strHour = "";
		/* 440: 633 */String strMinu = "";
		/* 441: 634 */String strSec = "";
		/* 442: 635 */int hourType = 12;
		/* 443: 636 */int yearType = 1;
		/* 444: */try
		/* 445: */{
			/* 446: 639 */if (formatString.indexOf("YYYY") >= 0)
			/* 447: */{
				/* 448: 641 */int tempBeginPlace = formatString.indexOf("YYYY");
				/* 449: 642 */int temEndPlace = tempBeginPlace + 4;
				/* 450: 643 */strYear = _dateTime.substring(tempBeginPlace,
						temEndPlace);
				/* 451: */}
			/* 452: 645 */if (formatString.indexOf("MM") >= 0)
			/* 453: */{
				/* 454: 647 */int tempBeginPlace = formatString.indexOf("MM");
				/* 455: 648 */int temEndPlace = tempBeginPlace + 2;
				/* 456: 649 */strMonth = _dateTime.substring(tempBeginPlace,
						temEndPlace);
				/* 457: */}
			/* 458: 651 */if (formatString.indexOf("DD") >= 0)
			/* 459: */{
				/* 460: 653 */int tempBeginPlace = formatString.indexOf("DD");
				/* 461: 654 */int temEndPlace = tempBeginPlace + 2;
				/* 462: 655 */strDay = _dateTime.substring(tempBeginPlace,
						temEndPlace);
				/* 463: */}
			/* 464: 657 */if (formatString.indexOf("HH24") >= 0)
			/* 465: */{
				/* 466: 659 */int tempBeginPlace = formatString.indexOf("HH24");
				/* 467: 660 */int temEndPlace = tempBeginPlace + 2;
				/* 468: 661 */strHour = _dateTime.substring(tempBeginPlace,
						temEndPlace);
				/* 469: 662 */formatString = formatString.replaceAll("24", "");
				/* 470: */
				/* 471: 664 */hourType = 24;
				/* 472: */}
			/* 473: 666 */else if (formatString.indexOf("HH12") >= 0)
			/* 474: */{
				/* 475: 668 */int tempBeginPlace = formatString.indexOf("HH12");
				/* 476: 669 */int temEndPlace = tempBeginPlace + 2;
				/* 477: 670 */strHour = _dateTime.substring(tempBeginPlace,
						temEndPlace);
				/* 478: 671 */formatString = formatString.replaceAll("12", "");
				/* 479: */
				/* 480: 673 */hourType = 12;
				/* 481: */}
			/* 482: 675 */else if (formatString.indexOf("HH") >= 0)
			/* 483: */{
				/* 484: 677 */int tempBeginPlace = formatString.indexOf("HH");
				/* 485: 678 */int temEndPlace = tempBeginPlace + 2;
				/* 486: 679 */strHour = _dateTime.substring(tempBeginPlace,
						temEndPlace);
				/* 487: 680 */hourType = 12;
				/* 488: */}
			/* 489: 682 */if (formatString.indexOf("MI") >= 0)
			/* 490: */{
				/* 491: 684 */int tempBeginPlace = formatString.indexOf("MI");
				/* 492: 685 */int temEndPlace = tempBeginPlace + 2;
				/* 493: 686 */strMinu = _dateTime.substring(tempBeginPlace,
						temEndPlace);
				/* 494: */}
			/* 495: 688 */if (formatString.indexOf("SS") >= 0)
			/* 496: */{
				/* 497: 690 */int tempBeginPlace = formatString.indexOf("SS");
				/* 498: 691 */int temEndPlace = tempBeginPlace + 2;
				/* 499: 692 */strSec = _dateTime.substring(tempBeginPlace,
						temEndPlace);
				/* 500: */}
			/* 501: 696 */if (!strYear.equals(""))
			/* 502: */{
				/* 503: 698 */int intYear = Integer.parseInt(strYear);
				/* 504: 700 */if (intYear % 4 == 0) {
					/* 505: 702 */if (intYear % 100 != 0) {
						/* 506: 704 */yearType = 2;
						/* 507: */}
					/* 508: */}
				/* 509: 707 */if (intYear % 4 == 0) {
					/* 510: 709 */if (intYear % 400 == 0) {
						/* 511: 711 */yearType = 2;
						/* 512: */}
					/* 513: */}
				/* 514: */}
			/* 515: 716 */if (!strMonth.equals(""))
			/* 516: */{
				/* 517: 718 */int intMonth = Integer.parseInt(strMonth);
				/* 518: 719 */if (intMonth == 0)
				/* 519: */{
					/* 520: 721 */strMonth = "01";
					/* 521: 722 */intMonth = 1;
					/* 522: */}
				/* 523: 724 */if (intMonth > 12)
				/* 524: */{
					/* 525: 726 */strMonth = "12";
					/* 526: 727 */intMonth = 12;
					/* 527: */}
				/* 528: */}
			/* 529: 732 */if (!strDay.equals(""))
			/* 530: */{
				/* 531: 734 */int intDay = Integer.parseInt(strDay);
				/* 532: 735 */if (intDay == 0)
				/* 533: */{
					/* 534: 737 */strDay = "01";
					/* 535: 738 */intDay = 1;
					/* 536: */}
				/* 537: 740 */if (intDay > 31)
				/* 538: */{
					/* 539: 742 */strDay = "31";
					/* 540: 743 */intDay = 31;
					/* 541: */}
				/* 542: 745 */if ((strMonth.equals("01")) ||
				/* 543: 746 */(strMonth.equals("03")) ||
				/* 544: 747 */(strMonth.equals("05")) ||
				/* 545: 748 */(strMonth.equals("07")) ||
				/* 546: 749 */(strMonth.equals("08")) ||
				/* 547: 750 */(strMonth.equals("10")) ||
				/* 548: 751 */(strMonth.equals("12"))) {
					/* 549: 753 */if (intDay > 31)
					/* 550: */{
						/* 551: 755 */strDay = "31";
						/* 552: 756 */intDay = 31;
						/* 553: */}
					/* 554: */}
				/* 555: 759 */if ((strMonth.equals("02")) ||
				/* 556: 760 */(strMonth.equals("04")) ||
				/* 557: 761 */(strMonth.equals("06")) ||
				/* 558: 762 */(strMonth.equals("09")) ||
				/* 559: 763 */(strMonth.equals("11")))
				/* 560: */{
					/* 561: 765 */if (intDay > 30)
					/* 562: */{
						/* 563: 767 */strDay = "30";
						/* 564: 768 */intDay = 30;
						/* 565: */}
					/* 566: 770 */if (strMonth.equals("02")) {
						/* 567: 772 */if (yearType == 2)
						/* 568: */{
							/* 569: 774 */if (intDay > 29)
							/* 570: */{
								/* 571: 776 */strDay = "29";
								/* 572: 777 */intDay = 29;
								/* 573: */}
							/* 574: */}
						/* 575: 782 */else if (intDay > 28)
						/* 576: */{
							/* 577: 784 */strDay = "28";
							/* 578: 785 */intDay = 28;
							/* 579: */}
						/* 580: */}
					/* 581: */}
				/* 582: 792 */if (!strHour.equals(""))
				/* 583: */{
					/* 584: 794 */int intHour = Integer.parseInt(strHour);
					/* 585: 795 */if (intHour > 24)
					/* 586: */{
						/* 587: 797 */strHour = "24";
						/* 588: 798 */intHour = 24;
						/* 589: */}
					/* 590: 800 */if (hourType == 12)
					/* 591: */{
						/* 592: 802 */if (intHour == 0)
						/* 593: */{
							/* 594: 804 */intHour = 1;
							/* 595: 805 */strHour = "01";
							/* 596: */}
						/* 597: 807 */if (intHour > 12)
						/* 598: */{
							/* 599: 809 */intHour -= 12;
							/* 600: 810 */strHour = "0" + intHour;
							/* 601: */}
						/* 602: */}
					/* 603: 815 */else if (intHour > 23)
					/* 604: */{
						/* 605: 817 */intHour = 23;
						/* 606: 818 */strHour = "23";
						/* 607: */}
					/* 608: */}
				/* 609: 823 */if (!strMinu.equals(""))
				/* 610: */{
					/* 611: 825 */int intMinu = Integer.parseInt(strMinu);
					/* 612: 826 */if (intMinu > 59)
					/* 613: */{
						/* 614: 828 */strMinu = "59";
						/* 615: 829 */intMinu = 59;
						/* 616: */}
					/* 617: */}
				/* 618: 833 */if (!strSec.equals(""))
				/* 619: */{
					/* 620: 835 */int intSec = Integer.parseInt(strSec);
					/* 621: 836 */if (intSec > 59)
					/* 622: */{
						/* 623: 838 */strSec = "59";
						/* 624: 839 */intSec = 59;
						/* 625: */}
					/* 626: */}
				/* 627: */}
			/* 628: 843 */return strYear + strMonth + strDay + strHour
					+ strMinu + strSec;
			/* 629: */}
		/* 630: */catch (Exception e)
		/* 631: */{
			/* 632: 848 */throw e;
			/* 633: */}
		/* 634: */}

	/* 635: */
	/* 636: */public static java.util.Date stringToDate(String strDate,
			String oracleFormat)
	/* 637: */{
		/* 638: 860 */if (strDate == null) {
			/* 639: 861 */return null;
			/* 640: */}
		/* 641: 862 */Hashtable h = new Hashtable();
		/* 642: 863 */String javaFormat = new String();
		/* 643: 864 */String s = oracleFormat.toLowerCase();
		/* 644: 865 */if (s.indexOf("yyyy") != -1) {
			/* 645: 866 */h.put(new Integer(s.indexOf("yyyy")), "yyyy");
			/* 646: 867 */} else if (s.indexOf("yy") != -1) {
			/* 647: 868 */h.put(new Integer(s.indexOf("yy")), "yy");
			/* 648: */}
		/* 649: 869 */if (s.indexOf("mm") != -1) {
			/* 650: 870 */h.put(new Integer(s.indexOf("mm")), "MM");
			/* 651: */}
		/* 652: 872 */if (s.indexOf("dd") != -1) {
			/* 653: 873 */h.put(new Integer(s.indexOf("dd")), "dd");
			/* 654: */}
		/* 655: 874 */if (s.indexOf("hh24") != -1) {
			/* 656: 875 */h.put(new Integer(s.indexOf("hh24")), "HH");
			/* 657: */}
		/* 658: 876 */if (s.indexOf("mi") != -1) {
			/* 659: 877 */h.put(new Integer(s.indexOf("mi")), "mm");
			/* 660: */}
		/* 661: 878 */if (s.indexOf("ss") != -1) {
			/* 662: 879 */h.put(new Integer(s.indexOf("ss")), "ss");
			/* 663: */}
		/* 664: 881 */int intStart = 0;
		/* 665: 882 */while (s.indexOf("-", intStart) != -1)
		/* 666: */{
			/* 667: 884 */intStart = s.indexOf("-", intStart);
			/* 668: 885 */h.put(new Integer(intStart), "-");
			/* 669: 886 */intStart++;
			/* 670: */}
		/* 671: 889 */intStart = 0;
		/* 672: 890 */while (s.indexOf("/", intStart) != -1)
		/* 673: */{
			/* 674: 892 */intStart = s.indexOf("/", intStart);
			/* 675: 893 */h.put(new Integer(intStart), "/");
			/* 676: 894 */intStart++;
			/* 677: */}
		/* 678: 897 */intStart = 0;
		/* 679: 898 */while (s.indexOf(" ", intStart) != -1)
		/* 680: */{
			/* 681: 900 */intStart = s.indexOf(" ", intStart);
			/* 682: 901 */h.put(new Integer(intStart), " ");
			/* 683: 902 */intStart++;
			/* 684: */}
		/* 685: 905 */intStart = 0;
		/* 686: 906 */while (s.indexOf(":", intStart) != -1)
		/* 687: */{
			/* 688: 908 */intStart = s.indexOf(":", intStart);
			/* 689: 909 */h.put(new Integer(intStart), ":");
			/* 690: 910 */intStart++;
			/* 691: */}
		/* 692: 913 */if (s.indexOf("年") != -1) {
			/* 693: 914 */h.put(new Integer(s.indexOf("年")), "年");
			/* 694: */}
		/* 695: 915 */if (s.indexOf("月") != -1) {
			/* 696: 916 */h.put(new Integer(s.indexOf("月")), "月");
			/* 697: */}
		/* 698: 917 */if (s.indexOf("日") != -1) {
			/* 699: 918 */h.put(new Integer(s.indexOf("日")), "日");
			/* 700: */}
		/* 701: 919 */if (s.indexOf("时") != -1) {
			/* 702: 920 */h.put(new Integer(s.indexOf("时")), "时");
			/* 703: */}
		if (s.indexOf("分") != -1) {
			h.put(new Integer(s.indexOf("分")), "分");
		}
		if (s.indexOf("秒") != -1) {
			h.put(new Integer(s.indexOf("秒")), "秒");
		}
		int i = 0;
		/* 711: 927 */while (h.size() != 0)
		/* 712: */{
			/* 713: 929 */Enumeration e = h.keys();
			/* 714: 930 */int n = 0;
			/* 715: 931 */while (e.hasMoreElements())
			/* 716: */{
				/* 717: 933 */i = ((Integer) e.nextElement()).intValue();
				/* 718: 934 */if (i >= n) {
					/* 719: 935 */n = i;
					/* 720: */}
				/* 721: */}
			/* 722: 937 */String temp = (String) h.get(new Integer(n));
			/* 723: 938 */h.remove(new Integer(n));
			/* 724: */
			/* 725: 940 */javaFormat = temp + javaFormat;
			/* 726: */}
		/* 727: 942 */SimpleDateFormat df = new SimpleDateFormat(javaFormat);
		/* 728: */
		/* 729: 944 */java.util.Date myDate = new java.util.Date();
		/* 730: */try
		/* 731: */{
			/* 732: 947 */myDate = df.parse(strDate);
			/* 733: */}
		/* 734: */catch (Exception e)
		/* 735: */{
			/* 736: 952 */return null;
			/* 737: */}
		/* 738: 955 */return myDate;
		/* 739: */}

	/* 740: */
	/* 741: */public static String dateToString(java.util.Date d, String format)
	/* 742: */{
		/* 743: 967 */if (d == null) {
			/* 744: 968 */return "";
			/* 745: */}
		/* 746: 969 */Hashtable h = new Hashtable();
		/* 747: 970 */String javaFormat = new String();
		/* 748: 971 */String s = format.toLowerCase();
		/* 749: 972 */if (s.indexOf("yyyy") != -1) {
			/* 750: 973 */h.put(new Integer(s.indexOf("yyyy")), "yyyy");
			/* 751: 974 */} else if (s.indexOf("yy") != -1) {
			/* 752: 975 */h.put(new Integer(s.indexOf("yy")), "yy");
			/* 753: */}
		/* 754: 976 */if (s.indexOf("mm") != -1) {
			/* 755: 977 */h.put(new Integer(s.indexOf("mm")), "MM");
			/* 756: */}
		/* 757: 979 */if (s.indexOf("dd") != -1) {
			/* 758: 980 */h.put(new Integer(s.indexOf("dd")), "dd");
			/* 759: */}
		/* 760: 981 */if (s.indexOf("hh24") != -1) {
			/* 761: 982 */h.put(new Integer(s.indexOf("hh24")), "HH");
			/* 762: */}
		/* 763: 983 */if (s.indexOf("mi") != -1) {
			/* 764: 984 */h.put(new Integer(s.indexOf("mi")), "mm");
			/* 765: */}
		/* 766: 985 */if (s.indexOf("ss") != -1) {
			/* 767: 986 */h.put(new Integer(s.indexOf("ss")), "ss");
			/* 768: */}
		/* 769: 988 */int intStart = 0;
		/* 770: 989 */while (s.indexOf("-", intStart) != -1)
		/* 771: */{
			/* 772: 991 */intStart = s.indexOf("-", intStart);
			/* 773: 992 */h.put(new Integer(intStart), "-");
			/* 774: 993 */intStart++;
			/* 775: */}
		/* 776: 996 */intStart = 0;
		/* 777: 997 */while (s.indexOf("/", intStart) != -1)
		/* 778: */{
			/* 779: 999 */intStart = s.indexOf("/", intStart);
			/* 780:1000 */h.put(new Integer(intStart), "/");
			/* 781:1001 */intStart++;
			/* 782: */}
		/* 783:1004 */intStart = 0;
		/* 784:1005 */while (s.indexOf(" ", intStart) != -1)
		/* 785: */{
			/* 786:1007 */intStart = s.indexOf(" ", intStart);
			/* 787:1008 */h.put(new Integer(intStart), " ");
			/* 788:1009 */intStart++;
			/* 789: */}
		/* 790:1012 */intStart = 0;
		/* 791:1013 */while (s.indexOf(":", intStart) != -1)
		/* 792: */{
			/* 793:1015 */intStart = s.indexOf(":", intStart);
			/* 794:1016 */h.put(new Integer(intStart), ":");
			/* 795:1017 */intStart++;
			/* 796: */}
		/* 797:1020 */if (s.indexOf("年") != -1) {
			/* 798:1021 */h.put(new Integer(s.indexOf("年")), "年");
			/* 799: */}
		/* 800:1022 */if (s.indexOf("月") != -1) {
			/* 801:1023 */h.put(new Integer(s.indexOf("月")), "月");
			/* 802: */}
		/* 803:1024 */if (s.indexOf("日") != -1) {
			/* 804:1025 */h.put(new Integer(s.indexOf("日")), "日");
			/* 805: */}
		/* 806:1026 */if (s.indexOf("时") != -1) {
			/* 807:1027 */h.put(new Integer(s.indexOf("时")), "时");
			/* 808: */}
		/* 809:1028 */if (s.indexOf("分") != -1) {
			/* 810:1029 */h.put(new Integer(s.indexOf("分")), "分");
			/* 811: */}
		/* 812:1030 */if (s.indexOf("秒") != -1) {
			/* 813:1031 */h.put(new Integer(s.indexOf("秒")), "秒");
			/* 814: */}
		/* 815:1033 */int i = 0;
		/* 816:1034 */while (h.size() != 0)
		/* 817: */{
			/* 818:1036 */Enumeration e = h.keys();
			/* 819:1037 */int n = 0;
			/* 820:1038 */while (e.hasMoreElements())
			/* 821: */{
				/* 822:1040 */i = ((Integer) e.nextElement()).intValue();
				/* 823:1041 */if (i >= n) {
					/* 824:1042 */n = i;
					/* 825: */}
				/* 826: */}
			/* 827:1044 */String temp = (String) h.get(new Integer(n));
			/* 828:1045 */h.remove(new Integer(n));
			/* 829: */
			/* 830:1047 */javaFormat = temp + javaFormat;
			/* 831: */}
		/* 832:1049 */SimpleDateFormat df = new SimpleDateFormat(javaFormat,
				new DateFormatSymbols());
		/* 833: */
		/* 834:1051 */return df.format(d);
		/* 835: */}

	/* 836: */
	/* 837: */public static String getDate(java.util.Date d, String format)
	/* 838: */{
		/* 839:1063 */if (d == null) {
			/* 840:1064 */return "";
			/* 841: */}
		/* 842:1065 */Hashtable h = new Hashtable();
		/* 843:1066 */String javaFormat = new String();
		/* 844:1067 */String s = format.toLowerCase();
		/* 845:1068 */if (s.indexOf("yyyy") != -1) {
			/* 846:1069 */h.put(new Integer(s.indexOf("yyyy")), "yyyy");
			/* 847:1070 */} else if (s.indexOf("yy") != -1) {
			/* 848:1071 */h.put(new Integer(s.indexOf("yy")), "yy");
			/* 849: */}
		/* 850:1072 */if (s.indexOf("mm") != -1) {
			/* 851:1073 */h.put(new Integer(s.indexOf("mm")), "MM");
			/* 852: */}
		/* 853:1075 */if (s.indexOf("dd") != -1) {
			/* 854:1076 */h.put(new Integer(s.indexOf("dd")), "dd");
			/* 855: */}
		/* 856:1077 */if (s.indexOf("hh24") != -1) {
			/* 857:1078 */h.put(new Integer(s.indexOf("hh24")), "HH");
			/* 858: */}
		/* 859:1079 */if (s.indexOf("mi") != -1) {
			/* 860:1080 */h.put(new Integer(s.indexOf("mi")), "mm");
			/* 861: */}
		/* 862:1081 */if (s.indexOf("ss") != -1) {
			/* 863:1082 */h.put(new Integer(s.indexOf("ss")), "ss");
			/* 864: */}
		/* 865:1084 */int intStart = 0;
		/* 866:1085 */while (s.indexOf("-", intStart) != -1)
		/* 867: */{
			/* 868:1087 */intStart = s.indexOf("-", intStart);
			/* 869:1088 */h.put(new Integer(intStart), "-");
			/* 870:1089 */intStart++;
			/* 871: */}
		/* 872:1092 */intStart = 0;
		/* 873:1093 */while (s.indexOf("/", intStart) != -1)
		/* 874: */{
			/* 875:1095 */intStart = s.indexOf("/", intStart);
			/* 876:1096 */h.put(new Integer(intStart), "/");
			/* 877:1097 */intStart++;
			/* 878: */}
		/* 879:1100 */intStart = 0;
		/* 880:1101 */while (s.indexOf(" ", intStart) != -1)
		/* 881: */{
			/* 882:1103 */intStart = s.indexOf(" ", intStart);
			/* 883:1104 */h.put(new Integer(intStart), " ");
			/* 884:1105 */intStart++;
			/* 885: */}
		/* 886:1108 */intStart = 0;
		/* 887:1109 */while (s.indexOf(":", intStart) != -1)
		/* 888: */{
			/* 889:1111 */intStart = s.indexOf(":", intStart);
			/* 890:1112 */h.put(new Integer(intStart), ":");
			/* 891:1113 */intStart++;
			/* 892: */}
		/* 893:1116 */if (s.indexOf("年") != -1) {
			/* 894:1117 */h.put(new Integer(s.indexOf("年")), "年");
			/* 895: */}
		/* 896:1118 */if (s.indexOf("月") != -1) {
			/* 897:1119 */h.put(new Integer(s.indexOf("月")), "月");
			/* 898: */}
		/* 899:1120 */if (s.indexOf("日") != -1) {
			/* 900:1121 */h.put(new Integer(s.indexOf("日")), "日");
			/* 901: */}
		/* 902:1122 */if (s.indexOf("时") != -1) {
			/* 903:1123 */h.put(new Integer(s.indexOf("时")), "时");
			/* 904: */}
		/* 905:1124 */if (s.indexOf("分") != -1) {
			/* 906:1125 */h.put(new Integer(s.indexOf("分")), "分");
			/* 907: */}
		/* 908:1126 */if (s.indexOf("秒") != -1) {
			/* 909:1127 */h.put(new Integer(s.indexOf("秒")), "秒");
			/* 910: */}
		/* 911:1129 */int i = 0;
		/* 912:1130 */while (h.size() != 0)
		/* 913: */{
			/* 914:1132 */Enumeration e = h.keys();
			/* 915:1133 */int n = 0;
			/* 916:1134 */while (e.hasMoreElements())
			/* 917: */{
				/* 918:1136 */i = ((Integer) e.nextElement()).intValue();
				/* 919:1137 */if (i >= n) {
					/* 920:1138 */n = i;
					/* 921: */}
				/* 922: */}
			/* 923:1140 */String temp = (String) h.get(new Integer(n));
			/* 924:1141 */h.remove(new Integer(n));
			/* 925: */
			/* 926:1143 */javaFormat = temp + javaFormat;
			/* 927: */}
		/* 928:1145 */SimpleDateFormat df = new SimpleDateFormat(javaFormat,
				new DateFormatSymbols());
		/* 929: */
		/* 930:1147 */return df.format(d);
		/* 931: */}

	/* 932: */
	/* 933: */public static int getAge(String id)
	/* 934: */throws Exception
	/* 935: */{
		/* 936:1158 */int age = -1;
		/* 937:1159 */int length = id.length();
		/* 938:1160 */String birthday = "";
		/* 939:1161 */if (length == 15)
		/* 940: */{
			/* 941:1163 */birthday = id.substring(6, 8);
			/* 942:1164 */birthday = "19" + birthday;
			/* 943: */}
		/* 944:1166 */else if (length == 18)
		/* 945: */{
			/* 946:1168 */birthday = id.substring(6, 10);
			/* 947: */}
		/* 948: */else
		/* 949: */{
			/* 950:1172 */throw new Exception("错误的身份证号");
			/* 951: */}
		/* 952:1174 */int currentYear = Calendar.getInstance().get(1);
		/* 953:1175 */age = currentYear - new Integer(birthday).intValue();
		/* 954:1176 */return age;
		/* 955: */}

	/* 956: */
	/* 957: */public static java.sql.Date getDateByAge(int age)
	/* 958: */{
		/* 959:1187 */Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		/* 960:1188 */long current = calendar.getTimeInMillis();
		/* 961:1189 */calendar.set(calendar.get(1) - age, calendar.get(2),
				calendar.get(5));
		/* 962:1190 */return new java.sql.Date(calendar.getTimeInMillis());
		/* 963: */}

	/* 964: */
	/* 965: */public static int calBetweenTwoMonth(String dealMonth,
			String alterMonth)
	/* 966: */{
		/* 967:1202 */int length = 0;
		/* 968:1203 */if ((dealMonth.length() != 6)
				|| (alterMonth.length() != 6))
		/* 969: */{
			/* 970:1206 */length = -1;
			/* 971: */}
		/* 972: */else
		/* 973: */{
			/* 974:1211 */int dealInt = Integer.parseInt(dealMonth);
			/* 975:1212 */int alterInt = Integer.parseInt(alterMonth);
			/* 976:1213 */if (dealInt < alterInt)
			/* 977: */{
				/* 978:1216 */length = -2;
				/* 979: */}
			/* 980: */else
			/* 981: */{
				/* 982:1220 */int dealYearInt = Integer.parseInt(dealMonth
						.substring(0, 4));
				/* 983:1221 */int dealMonthInt = Integer.parseInt(dealMonth
						.substring(4, 6));
				/* 984:1222 */int alterYearInt = Integer.parseInt(alterMonth
						.substring(0, 4));
				/* 985:1223 */int alterMonthInt = Integer.parseInt(alterMonth
						.substring(4, 6));
				/* 986:1224 */length = (dealYearInt - alterYearInt) * 12
						+ (dealMonthInt - alterMonthInt);
				/* 987: */}
			/* 988: */}
		/* 989:1228 */return length;
		/* 990: */}

	/* 991: */
	/* 992: */public static int daysBetweenDates(java.util.Date newDate,
			java.util.Date oldDate)
	/* 993: */{
		/* 994:1240 */int days = 0;
		/* 995:1241 */Calendar calo = Calendar.getInstance();
		/* 996:1242 */Calendar caln = Calendar.getInstance();
		/* 997:1243 */calo.setTime(oldDate);
		/* 998:1244 */caln.setTime(newDate);
		/* 999:1245 */int oday = calo.get(6);
		/* 1000:1246 */int nyear = caln.get(1);
		/* 1001:1247 */int oyear = calo.get(1);
		/* 1002:1248 */while (nyear > oyear)
		/* 1003: */{
			/* 1004:1250 */calo.set(2, 11);
			/* 1005:1251 */calo.set(5, 31);
			/* 1006:1252 */days += calo.get(6);
			/* 1007:1253 */oyear++;
			/* 1008:1254 */calo.set(1, oyear);
			/* 1009: */}
		/* 1010:1256 */int nday = caln.get(6);
		/* 1011:1257 */days = days + nday - oday;
		/* 1012: */
		/* 1013:1259 */return days;
		/* 1014: */}

	/* 1015: */
	/* 1016: */public static java.util.Date getDateBetween(java.util.Date date,
			int intBetween)
	/* 1017: */{
		/* 1018:1271 */Calendar calo = Calendar.getInstance();
		/* 1019:1272 */calo.setTime(date);
		/* 1020:1273 */calo.add(5, intBetween);
		/* 1021:1274 */return calo.getTime();
		/* 1022: */}

	/* 1023: */
	/* 1024: */public static String getDateBetween_String(java.util.Date date,
			int intBetween, String strFromat)
	/* 1025: */{
		/* 1026:1287 */java.util.Date dateOld = getDateBetween(date, intBetween);
		/* 1027:1288 */return getDate(dateOld, strFromat);
		/* 1028: */}

	/* 1029: */
	/* 1030: */public static String increaseYearMonth(String yearMonth)
	/* 1031: */{
		/* 1032:1299 */int year = new Integer(yearMonth.substring(0, 4))
				.intValue();
		/* 1033:1300 */int month = new Integer(yearMonth.substring(4, 6))
				.intValue();
		/* 1034:1301 */month++;
		/* 1035:1302 */if ((month <= 12) && (month >= 10)) {
			/* 1036:1303 */return yearMonth.substring(0, 4)
					+ new Integer(month).toString();
			/* 1037: */}
		/* 1038:1304 */if (month < 10) {
			/* 1039:1305 */return yearMonth.substring(0, 4) + "0"
					+ new Integer(month).toString();
			/* 1040: */}
		/* 1041:1308 */return new Integer(year + 1).toString() + "0"
				+ new Integer(month - 12).toString();
		/* 1042: */}

	/* 1043: */
	/* 1044: */public static String increaseYearMonth(String yearMonth,
			int addMonth)
	/* 1045: */{
		/* 1046:1321 */int year = new Integer(yearMonth.substring(0, 4))
				.intValue();
		/* 1047:1322 */int month = new Integer(yearMonth.substring(4, 6))
				.intValue();
		/* 1048:1323 */month += addMonth;
		/* 1049:1324 */year += month / 12;
		/* 1050:1325 */month %= 12;
		/* 1051:1326 */if ((month <= 12) && (month >= 10)) {
			/* 1052:1327 */return year + new Integer(month).toString();
			/* 1053: */}
		/* 1054:1329 */return year + "0" + new Integer(month).toString();
		/* 1055: */}

	/* 1056: */
	/* 1057: */public static String descreaseYearMonth(String yearMonth)
	/* 1058: */{
		/* 1059:1341 */int year = new Integer(yearMonth.substring(0, 4))
				.intValue();
		/* 1060:1342 */int month = new Integer(yearMonth.substring(4, 6))
				.intValue();
		/* 1061:1343 */month--;
		/* 1062:1344 */if (month >= 10) {
			/* 1063:1345 */return yearMonth.substring(0, 4)
					+ new Integer(month).toString();
			/* 1064: */}
		/* 1065:1346 */if ((month > 0) && (month < 10)) {
			/* 1066:1347 */return yearMonth.substring(0, 4) + "0"
					+ new Integer(month).toString();
			/* 1067: */}
		/* 1068:1350 */return new Integer(year - 1).toString()
				+ new Integer(month + 12).toString();
		/* 1069: */}

	/* 1070: */
	/* 1071: */public static boolean yearMonthGreatEqual(String s1, String s2)
	/* 1072: */{
		/* 1073:1363 */String temp1 = s1.substring(0, 4);
		/* 1074:1364 */String temp2 = s2.substring(0, 4);
		/* 1075:1365 */String temp3 = s1.substring(4, 6);
		/* 1076:1366 */String temp4 = s2.substring(4, 6);
		/* 1077:1368 */if (Integer.parseInt(temp1) > Integer.parseInt(temp2)) {
			/* 1078:1369 */return true;
			/* 1079: */}
		/* 1080:1370 */if (Integer.parseInt(temp1) == Integer.parseInt(temp2))
		/* 1081: */{
			/* 1082:1372 */if (Integer.parseInt(temp3) >= Integer
					.parseInt(temp4)) {
				/* 1083:1373 */return true;
				/* 1084: */}
			/* 1085:1375 */return false;
			/* 1086: */}
		/* 1087:1378 */return false;
		/* 1088: */}

	/* 1089: */
	/* 1090: */public static boolean yearMonthGreater(String s1, String s2)
	/* 1091: */{
		/* 1092:1391 */String temp1 = s1.substring(0, 4);
		/* 1093:1392 */String temp2 = s2.substring(0, 4);
		/* 1094:1393 */String temp3 = s1.substring(4, 6);
		/* 1095:1394 */String temp4 = s2.substring(4, 6);
		/* 1096:1396 */if (Integer.parseInt(temp1) > Integer.parseInt(temp2)) {
			/* 1097:1397 */return true;
			/* 1098: */}
		/* 1099:1398 */if (Integer.parseInt(temp1) == Integer.parseInt(temp2))
		/* 1100: */{
			/* 1101:1400 */if (Integer.parseInt(temp3) > Integer
					.parseInt(temp4)) {
				/* 1102:1401 */return true;
				/* 1103: */}
			/* 1104:1403 */return false;
			/* 1105: */}
		/* 1106:1406 */return false;
		/* 1107: */}

	/* 1108: */
	/* 1109: */public static String getOracleFormatDateStr(java.util.Date date)
	/* 1110: */{
		/* 1111:1416 */return getDate(date, "YYYY-MM-DD HH24:MI:SS");
		/* 1112: */}

	/* 1113: */
	/* 1114: */public static String getLastDay(String term)
	/* 1115: */{
		/* 1116:1429 */int getYear = Integer.parseInt(term.substring(0, 4));
		/* 1117:1430 */int getMonth = Integer.parseInt(term.substring(4, 6));
		/* 1118: */
		/* 1119:1432 */String getLastDay = "";
		/* 1120:1434 */if (getMonth == 2)
		/* 1121: */{
			/* 1122:1436 */if (((getYear % 4 == 0) && (getYear % 100 != 0))
					|| (getYear % 400 == 0)) {
				/* 1123:1438 */getLastDay = "29";
				/* 1124: */} else {
				/* 1125:1442 */getLastDay = "28";
				/* 1126: */}
			/* 1127: */}
		/* 1128:1445 */else if ((getMonth == 4) || (getMonth == 6)
				|| (getMonth == 9) || (getMonth == 11)) {
			/* 1129:1447 */getLastDay = "30";
			/* 1130: */} else {
			/* 1131:1451 */getLastDay = "31";
			/* 1132: */}
		/* 1133:1453 */return String.valueOf(getYear) + "年"
				+ String.valueOf(getMonth) + "月" + getLastDay + "日";
		/* 1134: */}

	/* 1135: */
	/* 1136: */public static String getMonthBetween(String strDateBegin,
			String strDateEnd)
	/* 1137: */{
		/* 1138: */try
		/* 1139: */{
			/* 1140: */String strOut;
			/* 1141: */int intMonthBegin = 0;
			/* 1142: */int intMonthEnd = 0;
			/* 1143:1470 */if ((strDateBegin.equals("")) ||
			/* 1144:1471 */(strDateEnd.equals("")) ||
			/* 1145:1472 */(strDateBegin.length() != 6) ||
			/* 1146:1473 */(strDateEnd.length() != 6))
			/* 1147: */{
				/* 1148:1474 */strOut = "";
				/* 1149: */}
			/* 1150: */else
			/* 1151: */{
				/* 1152:1477 */intMonthBegin =
				/* 1153:1478 */Integer.parseInt(strDateBegin.substring(0, 4))
						* 12
						+
						/* 1154:1479 */Integer.parseInt(strDateBegin.substring(
								4, 6));
				/* 1155:1480 */intMonthEnd =
				/* 1156:1481 */Integer.parseInt(strDateEnd.substring(0, 4))
						* 12 + Integer.parseInt(strDateEnd.substring(4, 6));
				/* 1157: */}
			/* 1158:1482 */return String.valueOf(intMonthBegin - intMonthEnd);
			/* 1159: */}
		/* 1160: */catch (Exception e) {
		}
		/* 1161:1488 */return "0";
		/* 1162: */}

	/* 1163: */
	/* 1164: */public static String getStrHaveAcross(String strDate)
	/* 1165: */{
		/* 1166: */try
		/* 1167: */{
			/* 1168:1502 */return strDate.substring(0, 4) + "-"
					+ strDate.substring(4, 6) + "-" + strDate.substring(6, 8);
			/* 1169: */}
		/* 1170: */catch (Exception e) {
		}
		/* 1171:1506 */return strDate;
		/* 1172: */}

	/* 1173: */
	/* 1174: */public static String getFirstDayOfNextMonth()
	/* 1175: */{
		/* 1176: */try
		/* 1177: */{
			/* 1178:1519 */return increaseYearMonth(getNowShortDate()
					.substring(0, 6)) + "01";
			/* 1179: */}
		/* 1180: */catch (Exception e) {
		}
		/* 1181:1523 */return "";
		/* 1182: */}

	/* 1183: */
	/* 1184: */public static String getFirstDayOfThisMonth()
	/* 1185: */{
		/* 1186: */try
		/* 1187: */{
			/* 1188:1535 */return getNowShortDate().substring(0, 6) + "01";
			/* 1189: */}
		/* 1190: */catch (Exception e) {
		}
		/* 1191:1539 */return "";
		/* 1192: */}

	/* 1193: */
	/* 1194: */public static String getYearAndMonth(String yearMonth)
	/* 1195: */{
		/* 1196:1549 */if (yearMonth == null) {
			/* 1197:1550 */return "";
			/* 1198: */}
		/* 1199:1551 */String ym = yearMonth.trim();
		/* 1200:1552 */if (6 != ym.length()) {
			/* 1201:1553 */return ym;
			/* 1202: */}
		/* 1203:1554 */String year = ym.substring(0, 4);
		/* 1204:1555 */String month = ym.substring(4);
		/* 1205:1556 */return year + "年" + month + "月";
		/* 1206: */}

	/* 1207: */
	/* 1208: */public static String month2YearMonth(String month)
	/* 1209: */{
		/* 1210:1566 */String yearMonth = "";
		/* 1211:1567 */int smonth = 0;
		/* 1212:1568 */int year = 0;
		/* 1213:1569 */int rmonth = 0;
		/* 1214:1571 */if ((month == null) || ("0".equals(month))
				|| ("".equals(month.trim()))) {
			/* 1215:1573 */return "0月";
			/* 1216: */}
		/* 1217:1576 */smonth = Integer.parseInt(month);
		/* 1218:1577 */year = smonth / 12;
		/* 1219:1578 */rmonth = smonth % 12;
		/* 1220:1580 */if (year > 0) {
			/* 1221:1582 */yearMonth = year + "年";
			/* 1222: */}
		/* 1223:1584 */if (rmonth > 0) {
			/* 1224:1586 */yearMonth = yearMonth + rmonth + "个月";
			/* 1225: */}
		/* 1226:1589 */return yearMonth;
		/* 1227: */}

	/* 1228: */
	/* 1229: */public static String getYearMonthByMonth(String month)
	/* 1230: */{
		/* 1231:1599 */if (month == null) {
			/* 1232:1600 */return null;
			/* 1233: */}
		/* 1234:1601 */String ym = month.trim();
		/* 1235:1602 */if (6 != ym.length()) {
			/* 1236:1603 */return ym;
			/* 1237: */}
		/* 1238:1604 */String year = ym.substring(0, 4);
		/* 1239:1605 */String month1 = ym.substring(4);
		/* 1240:1606 */return year + "年" + month + "月";
		/* 1241: */}

	/* 1242: */
	/* 1243: */public static java.util.Date increaseMonth(java.util.Date date,
			int intBetween)
	/* 1244: */{
		/* 1245:1618 */Calendar calo = Calendar.getInstance();
		/* 1246:1619 */calo.setTime(date);
		/* 1247:1620 */calo.add(2, intBetween);
		/* 1248:1621 */return calo.getTime();
		/* 1249: */}

	/* 1250: */
	/* 1251: */public static java.util.Date increaseDay(java.util.Date date,
			int intBetween)
	/* 1252: */{
		/* 1253:1633 */Calendar calo = Calendar.getInstance();
		/* 1254:1634 */calo.setTime(date);
		/* 1255:1635 */calo.add(5, intBetween);
		/* 1256:1636 */return calo.getTime();
		/* 1257: */}

	/* 1258: */
	/* 1259: */public static java.util.Date increaseYear(java.util.Date date,
			int intBetween)
	/* 1260: */{
		/* 1261:1646 */Calendar calo = Calendar.getInstance();
		/* 1262:1647 */calo.setTime(date);
		/* 1263:1648 */calo.add(1, intBetween);
		/* 1264:1649 */return calo.getTime();
		/* 1265: */}

	/* 1266: */
	/* 1267: */public static int compareDate(String str1, String str2)
	/* 1268: */{
		/* 1269:1659 */SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		/* 1270:1660 */java.util.Date date1 = null;
		java.util.Date date2 = null;
		/* 1271: */try
		/* 1272: */{
			/* 1273:1663 */date1 = formatter.parse(str1);
			/* 1274:1664 */date2 = formatter.parse(str2);
			/* 1275: */}
		/* 1276: */catch (ParseException ex)
		/* 1277: */{
			/* 1278:1668 */ex.printStackTrace();
			/* 1279: */}
		/* 1280:1670 */return date1.compareTo(date2);
		/* 1281: */}

	/* 1282: */
	/* 1283: */public static int compareDate(String str1, java.util.Date date2)
	/* 1284: */{
		/* 1285:1675 */java.util.Date date1 = getDateByString(str1);
		/* 1286:1676 */return date1.compareTo(date2);
		/* 1287: */}

	/* 1288: */
	/* 1289: */public static int compareDate(String format, String str1,
			java.util.Date date2)
	/* 1290: */{
		/* 1291:1682 */java.util.Date date1 = null;
		/* 1292: */try
		/* 1293: */{
			/* 1294:1684 */date1 = fromStringToDate(format, str1);
			/* 1295: */}
		/* 1296: */catch (ParseException e)
		/* 1297: */{
			/* 1298:1686 */e.printStackTrace();
			/* 1299: */}
		/* 1300:1688 */return date1.compareTo(date2);
		/* 1301: */}

	/* 1302: */
	/* 1303: */public static Timestamp getDateByString(String strDate)
	/* 1304: */{
		/* 1305:1699 */if (strDate.trim().equals("")) {
			/* 1306:1701 */return new Timestamp(System.currentTimeMillis());
			/* 1307: */}
		/* 1308: */try
		/* 1309: */{
			/* 1310:1705 */strDate = getFormattedDate(strDate,
					"yyyy-MM-dd HH:mm:ss") + ".000000000";
			/* 1311:1706 */return Timestamp.valueOf(strDate);
			/* 1312: */}
		/* 1313: */catch (Exception ex) {
		}
		/* 1314:1710 */return new Timestamp(System.currentTimeMillis());
		/* 1315: */}

	/* 1316: */
	/* 1317: */public static Timestamp getNextMonyDate(String strDate)
	/* 1318: */{
		/* 1319: */try
		/* 1320: */{
			/* 1321:1718 */int iYear = StringUtil.getStrToInt(getFormattedDate(
					strDate, "yyyy"));
			/* 1322:1719 */int iMonth = StringUtil
					.getStrToInt(getFormattedDate(strDate, "MM"));
			/* 1323:1720 */if (iMonth == 12) {
				/* 1324:1722 */iYear++;
				/* 1325: */} else {
				/* 1326:1726 */iMonth++;
				/* 1327: */}
			/* 1328:1728 */String strMonth = Integer.toString(iMonth);
			/* 1329:1729 */if (strMonth.length() == 1) {
				/* 1330:1731 */strMonth = "0" + strMonth;
				/* 1331: */}
			/* 1332:1733 */strDate = Integer.toString(iYear) + "/" + strMonth
					+ "/01";
			/* 1333:1734 */return getDateByString(strDate);
			/* 1334: */}
		/* 1335: */catch (Exception ex) {
		}
		/* 1336:1738 */return getDateByString(strDate);
		/* 1337: */}

	/* 1338: */
	/* 1339: */public static String getCurrDate()
	/* 1340: */{
		/* 1341:1758 */return getFormattedDate(getDateByString(""));
		/* 1342: */}

	/* 1343: */
	/* 1344: */public static String getToday()
	/* 1345: */{
		/* 1346:1767 */java.util.Date cDate = new java.util.Date();
		/* 1347:1768 */SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		/* 1348:1769 */return cSimpleDateFormat.format(cDate);
		/* 1349: */}

	/* 1350: */
	/* 1351: */public static String getYesterday()
	/* 1352: */{
		/* 1353:1778 */java.util.Date cDate = new java.util.Date();
		/* 1354:1779 */cDate.setTime(cDate.getTime() - 86400000L);
		/* 1355:1780 */SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		/* 1356:1781 */return cSimpleDateFormat.format(cDate);
		/* 1357: */}

	/* 1358: */
	/* 1359: */public static String getTomorrow()
	/* 1360: */{
		/* 1361:1790 */java.util.Date cDate = new java.util.Date();
		/* 1362:1791 */cDate.setTime(cDate.getTime() + 86400000L);
		/* 1363:1792 */SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		/* 1364:1793 */return cSimpleDateFormat.format(cDate);
		/* 1365: */}

	/* 1366: */
	/* 1367: */public static String getDefaultValidDate()
	/* 1368: */{
		/* 1369:1801 */return "1900-01-01";
		/* 1370: */}

	/* 1371: */
	/* 1372: */public static String getDefaultExpireDate()
	/* 1373: */{
		/* 1374:1809 */return "2099-12-31";
		/* 1375: */}

	/* 1376: */
	/* 1377: */public static String getCurrDateTime()
	/* 1378: */{
		/* 1379:1817 */Timestamp date = new Timestamp(
				System.currentTimeMillis());
		/* 1380:1818 */SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		/* 1381:1819 */return formatter.format(date);
		/* 1382: */}

	/* 1383: */
	/* 1384: */public static String getSpecDate(String strFormat, int iYear,
			int iMonth, int iDate)
	/* 1385: */{
		/* 1386:1832 */Calendar rightNow = Calendar.getInstance();
		/* 1387:1833 */rightNow.set(1, rightNow.get(1) + iYear);
		/* 1388:1834 */rightNow.set(2, rightNow.get(2) + iMonth);
		/* 1389:1835 */rightNow.set(5, rightNow.get(5) + iDate);
		/* 1390:1836 */SimpleDateFormat df = new SimpleDateFormat(strFormat);
		/* 1391:1837 */return df.format(rightNow.getTime());
		/* 1392: */}

	/* 1393: */
	/* 1394: */public static String getDefaultFormattedDate(String strDate)
	/* 1395: */{
		/* 1396:1846 */return getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss");
		/* 1397: */}

	/* 1398: */
	/* 1399: */public static String getDefaultFormattedDate(Timestamp dtDate)
	/* 1400: */{
		/* 1401:1856 */return getFormattedDate(dtDate, "yyyy-MM-dd HH:mm:ss");
		/* 1402: */}

	/* 1403: */
	/* 1404: */public static Timestamp getNullBirthDay()
	/* 1405: */{
		/* 1406:1861 */return new Timestamp(0L);
		/* 1407: */}

	/* 1408: */
	/* 1409: */public static String getFormattedDate(String strDate)
	/* 1410: */{
		/* 1411:1871 */return getFormattedDate(strDate, "yyyy-MM-dd");
		/* 1412: */}

	/* 1413: */
	/* 1414: */public static String getFormattedDate(String strDate,
			String strFormatTo)
	/* 1415: */{
		/* 1416:1882 */if ((strDate == null) || (strDate.trim().equals(""))) {
			/* 1417:1884 */return "";
			/* 1418: */}
		/* 1419:1886 */strDate = strDate.replace('/', '-');
		/* 1420:1887 */strFormatTo = strFormatTo.replace('/', '-');
		/* 1421:1888 */if ((strDate.equals("0000-00-00 00:00:00"))
				|| (strDate.equals("1800-01-01 00:00:00"))) {
			/* 1422:1890 */return "";
			/* 1423: */}
		/* 1424:1892 */String formatStr = strFormatTo;
		/* 1425:1893 */if ((strDate == null) || (strDate.trim().equals(""))) {
			/* 1426:1895 */return "";
			/* 1427: */}
		/* 1428:1897 */switch (strDate.trim().length())
		/* 1429: */{
		/* 1430: */case 6:
			/* 1431:1900 */if (strDate.substring(0, 1).equals("0")) {
				/* 1432:1902 */formatStr = "yyMMdd";
				/* 1433: */} else {
				/* 1434:1906 */formatStr = "yyyyMM";
				/* 1435: */}
			/* 1436:1908 */break;
		/* 1437: */case 8:
			/* 1438:1910 */formatStr = "yyyyMMdd";
			/* 1439:1911 */break;
		/* 1440: */case 10:
			/* 1441:1913 */if (strDate.indexOf("-") == -1) {
				/* 1442:1915 */formatStr = "yyyy/MM/dd";
				/* 1443: */} else {
				/* 1444:1919 */formatStr = "yyyy-MM-dd";
				/* 1445: */}
			/* 1446:1921 */break;
		/* 1447: */case 11:
			/* 1448:1923 */if (strDate.getBytes().length == 14) {
				/* 1449:1925 */formatStr = "yyyy年MM月dd日";
				/* 1450: */} else {
				/* 1451:1929 */return "";
				/* 1452: */}
			/* 1453: */case 14:
			/* 1454:1932 */formatStr = "yyyyMMddHHmmss";
			/* 1455:1933 */break;
		/* 1456: */case 19:
			/* 1457:1935 */if (strDate.indexOf("-") == -1) {
				/* 1458:1937 */formatStr = "yyyy/MM/dd HH:mm:ss";
				/* 1459: */} else {
				/* 1460:1941 */formatStr = "yyyy-MM-dd HH:mm:ss";
				/* 1461: */}
			/* 1462:1943 */break;
		/* 1463: */case 21:
			/* 1464:1945 */if (strDate.indexOf("-") == -1) {
				/* 1465:1947 */formatStr = "yyyy/MM/dd HH:mm:ss.S";
				/* 1466: */} else {
				/* 1467:1951 */formatStr = "yyyy-MM-dd HH:mm:ss.S";
				/* 1468: */}
			break;
		/* 1470: */}

		try {
			SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(formatter.parse(strDate));
			formatter = new SimpleDateFormat(strFormatTo);
			return formatter.format(calendar.getTime());
		} catch (Exception e) {
		}
		return strDate.trim();
	}

	/* 1483: */
	/* 1484: */public static String getFormattedDate(Timestamp dtDate)
	/* 1485: */{
		/* 1486:1979 */return getFormattedDate(dtDate, "yyyy-MM-dd");
		/* 1487: */}

	/* 1488: */
	/* 1489: */public static String getFormattedDate(Timestamp dtDate,
			String strFormatTo)
	/* 1490: */{
		/* 1491:1990 */if (dtDate == null) {
			/* 1492:1992 */return "";
			/* 1493: */}
		/* 1494:1994 */if (dtDate.equals(new Timestamp(0L))) {
			/* 1495:1996 */return "";
			/* 1496: */}
		/* 1497:1998 */strFormatTo = strFormatTo.replace('/', '-');
		/* 1498: */try
		/* 1499: */{
			/* 1500:2001 */SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy");
			/* 1501:2002 */if (Integer.parseInt(formatter.format(dtDate)) < 1900) {
				/* 1502:2004 */return "";
				/* 1503: */}
			/* 1504:2008 */formatter = new SimpleDateFormat(strFormatTo);
			/* 1505:2009 */return formatter.format(dtDate);
			/* 1506: */}
		/* 1507: */catch (Exception e) {
		}
		/* 1508:2015 */return "";
		/* 1509: */}

	/* 1510: */
	/* 1511: */public static String getTimeFormat(long lSecond)
	/* 1512: */{
		/* 1513:2025 */String szTime = new String();
		/* 1514:2027 */if (lSecond <= 0L)
		/* 1515: */{
			/* 1516:2029 */szTime = "00:00:00";
			/* 1517: */}
		/* 1518: */else
		/* 1519: */{
			/* 1520:2033 */long hour = lSecond / 3600L;
			/* 1521:2034 */long minute = (lSecond - hour * 3600L) / 60L;
			/* 1522:2035 */long second = lSecond - hour * 3600L - minute * 60L;
			/* 1523:2037 */if (hour <= 0L) {
				/* 1524:2039 */szTime = "00";
				/* 1525:2041 */} else if (hour < 10L) {
				/* 1526:2043 */szTime = "0" + String.valueOf(hour);
				/* 1527: */} else {
				/* 1528:2047 */szTime = String.valueOf(hour);
				/* 1529: */}
			/* 1530:2049 */szTime = szTime + ":";
			/* 1531:2051 */if (minute <= 0L) {
				/* 1532:2053 */szTime = szTime + "00";
				/* 1533:2055 */} else if (minute < 10L) {
				/* 1534:2057 */szTime = szTime + "0" + String.valueOf(minute);
				/* 1535: */} else {
				/* 1536:2061 */szTime = szTime + String.valueOf(minute);
				/* 1537: */}
			/* 1538:2063 */szTime = szTime + ":";
			/* 1539:2065 */if (second <= 0L) {
				/* 1540:2067 */szTime = szTime + "00";
				/* 1541:2069 */} else if (second < 10L) {
				/* 1542:2071 */szTime = szTime + "0" + String.valueOf(second);
				/* 1543: */} else {
				/* 1544:2075 */szTime = szTime + String.valueOf(second);
				/* 1545: */}
			/* 1546: */}
		/* 1547:2079 */return szTime;
		/* 1548: */}

	/* 1549: */
	/* 1550: */public static String getFormattedDateUtil(java.util.Date dtDate,
			String strFormatTo)
	/* 1551: */{
		/* 1552:2083 */if (dtDate == null) {
			/* 1553:2085 */return "";
			/* 1554: */}
		/* 1555:2087 */strFormatTo = strFormatTo.replace('/', '-');
		/* 1556: */try
		/* 1557: */{
			/* 1558:2090 */SimpleDateFormat formatter = new SimpleDateFormat(
					strFormatTo);
			/* 1559:2091 */return formatter.format(dtDate);
			/* 1560: */}
		/* 1561: */catch (Exception e) {
		}
		/* 1562:2096 */return "";
		/* 1563: */}

	/* 1564: */
	/* 1565: */public static int getBetweenDays(String strFromDate,
			String strToDate)
	/* 1566: */{
		/* 1567: */try
		/* 1568: */{
			/* 1569:2109 */Calendar clFrom = new GregorianCalendar();
			/* 1570:2110 */int iYear = Integer.parseInt(strFromDate.substring(
					0, 4));
			/* 1571:2111 */int iMonth = Integer.parseInt(strFromDate.substring(
					4, 6));
			/* 1572:2112 */int iDay = Integer.parseInt(strFromDate.substring(6,
					8));
			/* 1573:2113 */clFrom.set(iYear, iMonth, iDay, 0, 0, 0);
			/* 1574:2114 */Calendar clTo = new GregorianCalendar();
			/* 1575:2115 */iYear = Integer.parseInt(strToDate.substring(0, 4));
			/* 1576:2116 */iMonth = Integer.parseInt(strToDate.substring(4, 6));
			/* 1577:2117 */iDay = Integer.parseInt(strToDate.substring(6, 8));
			/* 1578:2118 */clTo.set(iYear, iMonth, iDay, 0, 0, 0);
			/* 1579:2119 */long llTmp = clTo.getTime().getTime()
					- clFrom.getTime().getTime();
			/* 1580:2120 */return new Long(llTmp / 1000L / 3600L / 24L)
					.intValue();
			/* 1581: */}
		/* 1582: */catch (Exception e) {
		}
		/* 1583:2124 */return -2147483648;
		/* 1584: */}

	/* 1585: */
	/* 1586:2129 */private static DateUtil instance = null;
	/* 1587:2131 */private static final Locale local = Locale.ENGLISH;
	/* 1588: */public static final long millisInDay = 86400000L;

	/* 1589: */
	/* 1590: */public static synchronized DateUtil getInstance()
	/* 1591: */{
		/* 1592:2135 */if (instance == null) {
			/* 1593:2137 */instance = new DateUtil();
			/* 1594: */}
		/* 1595:2139 */return instance;
		/* 1596: */}

	/* 1597: */
	/* 1598:2144 */private static SimpleDateFormat[] mDateFormats = loadDateFormats();
	/* 1599:2146 */private static final SimpleDateFormat mFormat8chars = new SimpleDateFormat(
			"yyyyMMdd");
	/* 1600:2148 */private static final SimpleDateFormat mFormatIso8601Day = new SimpleDateFormat(
			"yyyy-MM-dd");
	/* 1601:2150 */private static final SimpleDateFormat mFormatIso8601 = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssZ");
	/* 1602:2153 */private static final SimpleDateFormat mFormatRfc822 = new SimpleDateFormat(
			"EEE, d MMM yyyy HH:mm:ss z");
	/* 1603:2155 */private static final SimpleDateFormat mFormatTradeEasy = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm");
	/* 1604:2157 */private static final SimpleDateFormat mFormatTradeEasyMMddyyyy = new SimpleDateFormat(
			"MM/dd/yyyy");
	/* 1605:2160 */private static final SimpleDateFormat mFormatTradeEasyProduct = new SimpleDateFormat(
			"dd/MM/yyyy");
	/* 1606:2163 */private static final SimpleDateFormat mFormatExpire = new SimpleDateFormat(
			"MMMM dd, yyyy", local);

	/* 1607: */
	/* 1608: */private static SimpleDateFormat[] loadDateFormats()
	/* 1609: */{
		/* 1610:2167 */SimpleDateFormat[] temp = {
		/* 1611: */
		/* 1612:2169 */new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy"),
		/* 1613: */
		/* 1614:2171 */new SimpleDateFormat("M/d/yy hh:mm:ss"),
		/* 1615:2172 */new SimpleDateFormat("M/d/yyyy hh:mm:ss"),
		/* 1616:2173 */new SimpleDateFormat("M/d/yy hh:mm a"),
		/* 1617:2174 */new SimpleDateFormat("M/d/yyyy hh:mm a"),
		/* 1618:2175 */new SimpleDateFormat("M/d/yy HH:mm"),
		/* 1619:2176 */new SimpleDateFormat("M/d/yyyy HH:mm"),
		/* 1620:2177 */new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"),
		/* 1621:2178 */new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS"),
		/* 1622:2179 */new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"),
		/* 1623: */
		/* 1624:2181 */new SimpleDateFormat("M-d-yy HH:mm"),
		/* 1625:2182 */new SimpleDateFormat("M-d-yyyy HH:mm"),
		/* 1626:2183 */new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS"),
		/* 1627:2184 */new SimpleDateFormat("M/d/yy"),
		/* 1628:2185 */new SimpleDateFormat("M/d/yyyy"),
		/* 1629:2186 */new SimpleDateFormat("M-d-yy"),
		/* 1630:2187 */new SimpleDateFormat("M-d-yyyy"),
		/* 1631:2188 */new SimpleDateFormat("MMMM d, yyyyy"),
		/* 1632:2189 */new SimpleDateFormat("MMM d, yyyyy") };
		/* 1633: */
		/* 1634:2191 */return temp;
		/* 1635: */}

	/* 1636: */
	/* 1637: */private static SimpleDateFormat[] getFormats()
	/* 1638: */{
		/* 1639:2199 */return mDateFormats;
		/* 1640: */}

	/* 1641: */
	/* 1642: */public static java.util.Date getEndOfDay(java.util.Date day)
	/* 1643: */{
		/* 1644:2210 */return getEndOfDay(day, Calendar.getInstance());
		/* 1645: */}

	/* 1646: */
	/* 1647: */public static java.util.Date getEndOfDay(java.util.Date day,
			Calendar cal)
	/* 1648: */{
		/* 1649:2214 */if (day == null) {
			/* 1650:2215 */day = new java.util.Date();
			/* 1651: */}
		/* 1652:2216 */cal.setTime(day);
		/* 1653:2217 */cal.set(11, cal.getMaximum(11));
		/* 1654:2218 */cal.set(12, cal.getMaximum(12));
		/* 1655:2219 */cal.set(13, cal.getMaximum(13));
		/* 1656:2220 */cal.set(14, cal.getMaximum(14));
		/* 1657:2221 */return cal.getTime();
		/* 1658: */}

	/* 1659: */
	/* 1660: */public static java.util.Date getStartOfDay(java.util.Date day)
	/* 1661: */{
		/* 1662:2232 */return getStartOfDay(day, Calendar.getInstance());
		/* 1663: */}

	/* 1664: */
	/* 1665: */public static java.util.Date getStartOfDay(java.util.Date day,
			Calendar cal)
	/* 1666: */{
		/* 1667:2241 */if (day == null) {
			/* 1668:2242 */day = new java.util.Date();
			/* 1669: */}
		/* 1670:2243 */cal.setTime(day);
		/* 1671:2244 */cal.set(11, cal.getMinimum(11));
		/* 1672:2245 */cal.set(12, cal.getMinimum(12));
		/* 1673:2246 */cal.set(13, cal.getMinimum(13));
		/* 1674:2247 */cal.set(14, cal.getMinimum(14));
		/* 1675:2248 */return cal.getTime();
		/* 1676: */}

	/* 1677: */
	/* 1678: */public static java.util.Date getNoonOfDay(java.util.Date day,
			Calendar cal)
	/* 1679: */{
		/* 1680:2258 */if (day == null) {
			/* 1681:2259 */day = new java.util.Date();
			/* 1682: */}
		/* 1683:2260 */cal.setTime(day);
		/* 1684:2261 */cal.set(11, 12);
		/* 1685:2262 */cal.set(12, cal.getMinimum(12));
		/* 1686:2263 */cal.set(13, cal.getMinimum(13));
		/* 1687:2264 */cal.set(14, cal.getMinimum(14));
		/* 1688:2265 */return cal.getTime();
		/* 1689: */}

	/* 1690: */
	/* 1691: */public static java.util.Date getDateFromString(String strDate)
	/* 1692: */{
		/* 1693:2276 */if (StringUtils.isEmpty(strDate)) {
			/* 1694:2278 */return new java.util.Date(System.currentTimeMillis());
			/* 1695: */}
		/* 1696: */try
		/* 1697: */{
			/* 1698:2282 */return sdfLongTimePlus.parse(strDate);
			/* 1699: */}
		/* 1700: */catch (Exception ex) {
		}
		/* 1701:2286 */return new Timestamp(System.currentTimeMillis());
		/* 1702: */}

	/* 1703: */
	/* 1704: */public static java.util.Date parseFromFormats(String aValue)
	/* 1705: */{
		/* 1706:2293 */if (StringUtil.isEmpty(aValue)) {
			/* 1707:2294 */return null;
			/* 1708: */}
		/* 1709:2297 */SimpleDateFormat[] formats = getFormats();
		/* 1710:2298 */if (formats == null) {
			/* 1711:2299 */return null;
			/* 1712: */}
		/* 1713:2302 */java.util.Date myDate = null;
		/* 1714:2303 */for (int i = 0; i < formats.length; i++) {
			/* 1715: */try
			/* 1716: */{
				/* 1717:2307 */return parse(aValue, formats[i]);
				/* 1718: */}
			/* 1719: */catch (Exception localException) {
			}
			/* 1720: */}
		/* 1721:2318 */return null;
		/* 1722: */}

	/* 1723: */
	/* 1724: */public static Timestamp parseTimestampFromFormats(String aValue)
	/* 1725: */{
		/* 1726:2324 */if (StringUtil.isEmpty(aValue)) {
			/* 1727:2325 */return null;
			/* 1728: */}
		/* 1729:2328 */java.util.Date myDate = parseFromFormats(aValue);
		/* 1730:2329 */if (myDate != null) {
			/* 1731:2330 */return new Timestamp(myDate.getTime());
			/* 1732: */}
		/* 1733:2331 */return null;
		/* 1734: */}

	/* 1735: */
	/* 1736: */public static Timestamp now()
	/* 1737: */{
		/* 1738:2339 */return new Timestamp(new java.util.Date().getTime());
		/* 1739: */}

	/* 1740: */
	/* 1741: */public static String format(java.util.Date aDate,
			SimpleDateFormat aFormat)
	/* 1742: */{
		/* 1743:2350 */if ((aDate == null) || (aFormat == null)) {
			/* 1744:2352 */return "";
			/* 1745: */}
		/* 1746:2354 */synchronized (aFormat)
		/* 1747: */{
			/* 1748:2356 */return aFormat.format(aDate);
			/* 1749: */}
		/* 1750: */}

	/* 1751: */
	/* 1752: */public static String formatDateString(String aString,
			SimpleDateFormat aFormat)
	/* 1753: */{
		/* 1754:2367 */if ((StringUtil.isEmpty(aString)) || (aFormat == null)) {
			/* 1755:2368 */return "";
			/* 1756: */}
		/* 1757: */try
		/* 1758: */{
			/* 1759:2371 */Timestamp aDate = parseTimestampFromFormats(aString);
			/* 1760:2372 */if (aDate != null) {
				/* 1761:2374 */return format(aDate, aFormat);
				/* 1762: */}
			/* 1763: */}
		/* 1764: */catch (Exception localException) {
		}
		/* 1765:2381 */return "";
		/* 1766: */}

	/* 1767: */
	/* 1768: */public static java.util.Date parse(String aValue,
			SimpleDateFormat aFormat)
	/* 1769: */throws ParseException
	/* 1770: */{
		/* 1771:2391 */if ((StringUtil.isEmpty(aValue)) || (aFormat == null)) {
			/* 1772:2393 */return null;
			/* 1773: */}
		/* 1774:2396 */return aFormat.parse(aValue);
		/* 1775: */}

	/* 1776: */
	/* 1777: */public static boolean isValidDateRange(java.util.Date startDate,
			java.util.Date endDate)
	/* 1778: */{
		/* 1779:2406 */return isValidDateRange(startDate, endDate, true);
		/* 1780: */}

	/* 1781: */
	/* 1782: */public static boolean isValidDateRange(java.util.Date startDate,
			java.util.Date endDate, boolean equalOK)
	/* 1783: */{
		/* 1784:2418 */if ((startDate == null) || (endDate == null)) {
			/* 1785:2420 */return false;
			/* 1786: */}
		/* 1787:2423 */if (equalOK) {
			/* 1788:2426 */if (startDate.equals(endDate)) {
				/* 1789:2428 */return true;
				/* 1790: */}
			/* 1791: */}
		/* 1792:2433 */if (endDate.after(startDate)) {
			/* 1793:2435 */return true;
			/* 1794: */}
		/* 1795:2438 */return false;
		/* 1796: */}

	/* 1797: */
	/* 1798: */public static SimpleDateFormat defaultTimestampFormat()
	/* 1799: */{
		/* 1800:2445 */return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		/* 1801: */}

	/* 1802: */
	/* 1803: */public static SimpleDateFormat get8charDateFormat()
	/* 1804: */{
		/* 1805:2452 */return mFormat8chars;
		/* 1806: */}

	/* 1807: */
	/* 1808: */public static SimpleDateFormat defaultDateFormat()
	/* 1809: */{
		/* 1810:2459 */return friendlyDateFormat(true);
		/* 1811: */}

	/* 1812: */
	/* 1813: */public static String defaultTimestamp(java.util.Date date)
	/* 1814: */{
		/* 1815:2466 */return format(date, defaultTimestampFormat());
		/* 1816: */}

	/* 1817: */
	/* 1818: */public static String defaultDate(java.util.Date date)
	/* 1819: */{
		/* 1820:2473 */return format(date, defaultDateFormat());
		/* 1821: */}

	/* 1822: */
	/* 1823: */public static SimpleDateFormat friendlyTimestampFormat()
	/* 1824: */{
		/* 1825:2480 */return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		/* 1826: */}

	/* 1827: */
	/* 1828: */public static String friendlyTimestamp(java.util.Date date)
	/* 1829: */{
		/* 1830:2487 */return format(date, friendlyTimestampFormat());
		/* 1831: */}

	/* 1832: */
	/* 1833: */public static String format8chars(java.util.Date date)
	/* 1834: */{
		/* 1835:2494 */return format(date, mFormat8chars);
		/* 1836: */}

	/* 1837: */
	/* 1838: */public static String formatIso8601Day(java.util.Date date)
	/* 1839: */{
		/* 1840:2501 */return format(date, mFormatIso8601Day);
		/* 1841: */}

	/* 1842: */
	/* 1843: */public static String formatIso8601Day(Timestamp timestamp)
	/* 1844: */{
		/* 1845:2505 */Calendar calendar = Calendar.getInstance();
		/* 1846:2506 */calendar.setTimeInMillis(timestamp.getTime());
		/* 1847:2507 */return format(calendar.getTime(), mFormatIso8601Day);
		/* 1848: */}

	/* 1849: */
	/* 1850: */public static String formatTradeEasy(java.util.Date date)
	/* 1851: */{
		/* 1852:2511 */return format(date, mFormatTradeEasy);
		/* 1853: */}

	/* 1854: */
	/* 1855: */public static String formatTradeEasyProduct(java.util.Date date)
	/* 1856: */{
		/* 1857:2516 */return format(date, mFormatTradeEasyProduct);
		/* 1858: */}

	/* 1859: */
	/* 1860: */public static String formatFormatTradeEasyMMddyyyy(
			java.util.Date date)
	/* 1861: */{
		/* 1862:2522 */return format(date, mFormatTradeEasyMMddyyyy);
		/* 1863: */}

	/* 1864: */
	/* 1865: */public static String formatTradeEasy(Timestamp timestamp)
	/* 1866: */{
		/* 1867:2527 */Calendar calendar = Calendar.getInstance();
		/* 1868:2528 */calendar.setTimeInMillis(timestamp.getTime());
		/* 1869:2529 */return format(calendar.getTime(), mFormatTradeEasy);
		/* 1870: */}

	/* 1871: */
	/* 1872: */public static String formatRfc822(java.util.Date date)
	/* 1873: */{
		/* 1874:2534 */return format(date, mFormatRfc822);
		/* 1875: */}

	/* 1876: */
	/* 1877: */public static String formatExpire(java.util.Date date)
	/* 1878: */{
		/* 1879:2539 */return format(date, mFormatExpire);
		/* 1880: */}

	/* 1881: */
	/* 1882: */public static String formatIso8601(java.util.Date date)
	/* 1883: */{
		/* 1884:2546 */if (date == null) {
			/* 1885:2547 */return "";
			/* 1886: */}
		/* 1887:2552 */String str = format(date, mFormatIso8601);
		/* 1888:2553 */StringBuffer sb = new StringBuffer();
		/* 1889:2554 */sb.append(str.substring(0, str.length() - 2));
		/* 1890:2555 */sb.append(":");
		/* 1891:2556 */sb.append(str.substring(str.length() - 2));
		/* 1892:2557 */return sb.toString();
		/* 1893: */}

	/* 1894: */
	/* 1895: */public static SimpleDateFormat minimalDateFormat()
	/* 1896: */{
		/* 1897:2564 */return friendlyDateFormat(true);
		/* 1898: */}

	/* 1899: */
	/* 1900: */public static String minimalDate(java.util.Date date)
	/* 1901: */{
		/* 1902:2571 */return format(date, minimalDateFormat());
		/* 1903: */}

	/* 1904: */
	/* 1905: */public static SimpleDateFormat fullDateFormat()
	/* 1906: */{
		/* 1907:2579 */return friendlyDateFormat(false);
		/* 1908: */}

	/* 1909: */
	/* 1910: */public static String fullDate(java.util.Date date)
	/* 1911: */{
		/* 1912:2585 */return format(date, fullDateFormat());
		/* 1913: */}

	/* 1914: */
	/* 1915: */public static SimpleDateFormat friendlyDateFormat(
			boolean minimalFormat)
	/* 1916: */{
		/* 1917:2594 */if (minimalFormat) {
			/* 1918:2596 */return new SimpleDateFormat("d.M.yy");
			/* 1919: */}
		/* 1920:2599 */return new SimpleDateFormat("dd.MM.yyyy");
		/* 1921: */}

	/* 1922: */
	/* 1923: */public static String friendlyDate(java.util.Date date,
			boolean minimalFormat)
	/* 1924: */{
		/* 1925:2608 */return format(date, friendlyDateFormat(minimalFormat));
		/* 1926: */}

	/* 1927: */
	/* 1928: */public static String friendlyDate(java.util.Date date)
	/* 1929: */{
		/* 1930:2615 */return format(date, friendlyDateFormat(true));
		/* 1931: */}

	/* 1932: */
	/* 1933: */public static java.util.Date parseFormatIso8601Date(String date)
	/* 1934: */throws Exception
	/* 1935: */{
		/* 1936:2620 */java.util.Date returnDate = null;
		/* 1937: */try
		/* 1938: */{
			/* 1939:2623 */returnDate = mFormatIso8601Day.parse(date);
			/* 1940: */}
		/* 1941: */catch (Exception e)
		/* 1942: */{
			/* 1943:2627 */throw e;
			/* 1944: */}
		/* 1945:2629 */return returnDate;
		/* 1946: */}

	/* 1947: */
	/* 1948: */public static String addDate(String date, String type, int into)
	/* 1949: */throws Exception
	/* 1950: */{
		/* 1951:2634 */String Sdate = "";
		/* 1952: */try
		/* 1953: */{
			/* 1954:2637 */GregorianCalendar grc = new GregorianCalendar();
			/* 1955:2638 */grc.setTime(new java.util.Date(date));
			/* 1956:2639 */if (type.equals("D")) {
				/* 1957:2641 */grc.add(5, into);
				/* 1958:2643 */} else if (type.equals("M")) {
				/* 1959:2645 */grc.add(2, into);
				/* 1960:2647 */} else if (type.equals("Y")) {
				/* 1961:2649 */grc.add(1, into);
				/* 1962: */}
			/* 1963:2651 */SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd");
			/* 1964:2652 */Sdate = new String(formatter.format(grc.getTime()));
			/* 1965: */}
		/* 1966: */catch (Exception e)
		/* 1967: */{
			/* 1968:2656 */throw e;
			/* 1969: */}
		/* 1970:2658 */return Sdate;
		/* 1971: */}

	/* 1972: */
	/* 1973: */public static String addDate(String date, String into)
	/* 1974: */throws Exception
	/* 1975: */{
		/* 1976:2662 */String Sdate = "";
		/* 1977: */try
		/* 1978: */{
			/* 1979:2665 */date = date.replaceAll("-", "/");
			/* 1980:2666 */date = date.substring(0, date.length() - 2);
			/* 1981:2667 */GregorianCalendar grc = new GregorianCalendar();
			/* 1982:2668 */grc.setTime(new java.util.Date(date));
			/* 1983:2669 */grc.add(5, Integer.parseInt(into));
			/* 1984:2670 */SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd");
			/* 1985:2671 */Sdate = new String(formatter.format(grc.getTime()));
			/* 1986: */}
		/* 1987: */catch (Exception e)
		/* 1988: */{
			/* 1989:2675 */throw e;
			/* 1990: */}
		/* 1991:2677 */return Sdate;
		/* 1992: */}

	/* 1993: */
	/* 1994: */public static String formatDate(java.util.Date date,
			String pattern)
	/* 1995: */{
		/* 1996:2681 */if ((pattern == null) || (pattern.equals(""))
				|| (pattern.equals("null"))) {
			/* 1997:2683 */pattern = "yyyy-MM-dd";
			/* 1998: */}
		/* 1999:2685 */SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		/* 2000:2686 */return sdf.format(date);
		/* 2001: */}

	/* 2002: */
	/* 2003: */public static String addValidateDate(String date, String into)
	/* 2004: */throws Exception
	/* 2005: */{
		/* 2006:2692 */String Sdate = "";
		/* 2007: */try
		/* 2008: */{
			/* 2009:2695 */date = date.replaceAll("-", "/");
			/* 2010:2696 */date = date.substring(0, date.length() - 2);
			/* 2011:2697 */GregorianCalendar grc = new GregorianCalendar();
			/* 2012:2698 */grc.setTime(new java.util.Date(date));
			/* 2013:2699 */grc.add(5, Integer.parseInt(into));
			/* 2014:2700 */Sdate = new String(mFormatExpire.format(grc
					.getTime()));
			/* 2015: */}
		/* 2016: */catch (Exception e)
		/* 2017: */{
			/* 2018:2704 */throw e;
			/* 2019: */}
		/* 2020:2706 */return Sdate;
		/* 2021: */}

	/* 2022: */
	/* 2023: */public static String addDayToStringDate(String formate,
			String strDate, String days)
	/* 2024: */{
		/* 2025:2710 */String stringDate = null;
		/* 2026: */try
		/* 2027: */{
			/* 2028:2712 */java.util.Date date = fromStringToDate(formate,
					strDate);
			/* 2029:2713 */long now = date.getTime() + Integer.parseInt(days)
					* DAY_IN_MILLISECOND;
			/* 2030: */
			/* 2031:2715 */stringDate = getFomartDate(new java.util.Date(now),
					formate);
			/* 2032: */}
		/* 2033: */catch (ParseException e)
		/* 2034: */{
			/* 2035:2719 */e.printStackTrace();
			/* 2036: */}
		/* 2037:2722 */return stringDate;
		/* 2038: */}

	/* 2039: */
	/* 2040: */public static java.util.Date addDayToStringDate2(String formate,
			String strDate, String days)
	/* 2041: */{
		/* 2042:2726 */java.util.Date date = null;
		/* 2043: */try
		/* 2044: */{
			/* 2045:2728 */date = fromStringToDate(formate, strDate);
			/* 2046:2729 */long now = date.getTime() + Integer.parseInt(days)
					* DAY_IN_MILLISECOND;
			/* 2047: */
			/* 2048:2731 */date = new java.util.Date(now);
			/* 2049: */}
		/* 2050: */catch (ParseException e)
		/* 2051: */{
			/* 2052:2735 */e.printStackTrace();
			/* 2053: */}
		/* 2054:2738 */return date;
		/* 2055: */}

	/* 2056: */
	/* 2057: */public static java.util.Date dateDayAdd(java.util.Date date,
			int days)
	/* 2058: */{
		/* 2059:2742 */long now = date.getTime() + days * DAY_IN_MILLISECOND;
		/* 2060:2743 */return new java.util.Date(now);
		/* 2061: */}

	/* 2062: */
	/* 2063: */public static java.util.Date fromStringToDate(String format,
			String dateTime)
	/* 2064: */throws ParseException
	/* 2065: */{
		/* 2066:2751 */java.util.Date date = null;
		/* 2067:2752 */SimpleDateFormat sdf = new SimpleDateFormat(format);
		/* 2068:2753 */date = sdf.parse(dateTime);
		/* 2069:2754 */return date;
		/* 2070: */}

	/* 2071: */
	/* 2072: */public static java.util.Date fromStringToDate(java.util.Date date)
	/* 2073: */throws ParseException
	/* 2074: */{
		/* 2075:2764 */return sdfLongTimePlus.parse(sdfLongTimePlus
				.format(date));
		/* 2076: */}

	/* 2077: */
	/* 2078: */public static void main(String[] args)
	/* 2079: */{
		/* 2080: */try
		/* 2081: */{
			/* 2082:2771 */System.out.println(toDayToStr("yyyy年MM月dd日"));
			/* 2083: */
			/* 2084: */
			/* 2085: */
			/* 2086: */
			/* 2087: */
			/* 2088: */
			/* 2089: */
			/* 2090: */
			/* 2091: */
			/* 2092: */
			/* 2093: */
			/* 2094: */
			/* 2095: */
			/* 2096: */
			/* 2097: */
			/* 2098: */
			/* 2099: */
			/* 2100: */
			/* 2101: */
			/* 2102: */
			/* 2103: */
			/* 2104: */
			/* 2105: */
			/* 2106: */
			/* 2107: */
			/* 2108: */
			/* 2109: */
			/* 2110: */
			/* 2111: */
			/* 2112: */
			/* 2113: */
			/* 2114: */
			/* 2115: */
			/* 2116: */
			/* 2117: */
			/* 2118:2807 */System.out
					.println(getDateShortLongTimeCn(new java.util.Date()));
			/* 2119:2808 */System.out.println(null + "1");
			/* 2120:2809 */System.out
					.println(convertDateToDay(new java.util.Date()));
			/* 2121:2810 */java.util.Date nows = new java.util.Date();
			/* 2122:2811 */System.out.println("============"
					+ getDateLongCn(nows));
			/* 2123:2812 */System.out
					.println("============0000000000000000000000000000000");
			/* 2124:2813 */System.out.println("============stringToDate="
					+ stringToDate("2009-11-18 19:14:31",
							"yyyy-MM-dd h24:mi:ss"));
			/* 2125: */
			/* 2126: */
			/* 2127:2816 */System.out.println("============getDateFromString="
					+ getDateFromString("2009-11-18 19:14:31"));
			/* 2128: */}
		/* 2129: */catch (Exception ex)
		/* 2130: */{
			/* 2131:2823 */ex.printStackTrace();
			/* 2132: */}
		/* 2133: */}

	/* 2134: */
	/* 2135: */public static Integer getTimeFormatIntger(java.util.Date date)
	/* 2136: */{
		/* 2137:2837 */if (date == null) {
			/* 2138:2838 */return Integer.valueOf(0);
			/* 2139: */}
		/* 2140:2840 */String strTemp = getFomartDate(date, "yyyyMMddHHmmss");
		/* 2141:2841 */String nowTime = strTemp.substring(8, 14);
		/* 2142:2842 */return Integer.valueOf(nowTime);
		/* 2143: */}

	/* 2144: */
	/* 2145: */public static String getNowDayStr(java.util.Date date)
	/* 2146: */{
		/* 2147:2846 */if (date == null) {
			/* 2148:2847 */return "";
			/* 2149: */}
		/* 2150:2850 */Calendar c = Calendar.getInstance();
		/* 2151:2851 */int i = c.get(7);
		/* 2152:2852 */System.out.println(i);
		/* 2153: */
		/* 2154: */
		/* 2155:2855 */return "";
		/* 2156: */}

	/* 2157: */
	/* 2158: */public static String toDayToStr(String format)
	/* 2159: */{
		/* 2160: */try
		/* 2161: */{
			/* 2162:2868 */java.util.Date now = new java.util.Date();
			/* 2163:2869 */return DateToStr(now, format) + " "
					+ getWeekOfDate(now);
			/* 2164: */}
		/* 2165: */catch (Exception e)
		/* 2166: */{
			/* 2167:2871 */System.out.println("Date 转 String 类型失败: " + e);
			/* 2168: */}
		/* 2169:2872 */return null;
		/* 2170: */}

	/* 2171: */
	/* 2172: */public static String DateToStr(java.util.Date date, String format)
	/* 2173: */{
		/* 2174: */try
		/* 2175: */{
			/* 2176:2885 */SimpleDateFormat sdf = new SimpleDateFormat(format);
			/* 2177:2886 */return sdf.format(date);
			/* 2178: */}
		/* 2179: */catch (Exception e)
		/* 2180: */{
			/* 2181:2888 */System.out.println("Date 转 String 类型失败: " + e);
			/* 2182: */}
		/* 2183:2889 */return null;
		/* 2184: */}

	/* 2185: */
	/* 2186: */public static java.util.Date dateAddDays(java.util.Date date,
			int days)
	/* 2187: */{
		/* 2188:2904 */long now = date.getTime() + days * DAY_IN_MILLISECOND;
		/* 2189:2905 */return new java.util.Date(now);
		/* 2190: */}

	/* 2191: */
	/* 2192: */public static String dateTypeToString(java.util.Date date,
			String fFormatStr)
	/* 2193: */{
		/* 2194:2915 */SimpleDateFormat dateformat = new SimpleDateFormat(
				fFormatStr);
		/* 2195:2916 */String strDate = dateformat.format(date);
		/* 2196:2917 */return strDate;
		/* 2197: */}

	/* 2198: */
	/* 2199: */public static String getStringOfNowDate(String fFormatStr)
	/* 2200: */{
		/* 2201:2924 */String nowDateString = dateTypeToString(
				new java.util.Date(), fFormatStr);
		/* 2202:2925 */return nowDateString;
		/* 2203: */}

	/* 2204: */
	/* 2205: */public static String getStringOfFirstDayInMonth()
	/* 2206: */{
		/* 2207:2933 */java.util.Date date = new java.util.Date();
		/* 2208:2934 */SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		/* 2209:2935 */String temp = sdf.format(date);
		/* 2210:2936 */String firstDayInMoth = "";
		/* 2211:2937 */firstDayInMoth = temp + "-01";
		/* 2212: */
		/* 2213:2939 */return firstDayInMoth;
		/* 2214: */}

	/* 2215: */
	/* 2216: */public static String getWeekOfDate(java.util.Date dt)
	/* 2217: */{
		/* 2218:2952 */String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四",
				"星期五", "星期六" };
		/* 2219:2953 */Calendar cal = Calendar.getInstance();
		/* 2220:2954 */cal.setTime(dt);
		/* 2221: */
		/* 2222: */
		/* 2223:2957 */int w = cal.get(7) - 1;
		/* 2224:2958 */if (w < 0) {
			/* 2225:2959 */w = 0;
			/* 2226: */}
		/* 2227:2961 */return weekDays[w];
		/* 2228: */}
	/* 2229: */
}

/*
 * Location: C:\Users\congxiaofan\Desktop\
 * 
 * Qualified Name: com.base.utils.DateUtil
 * 
 * JD-Core Version: 0.7.0.1
 */
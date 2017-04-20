/*  1:   */package com.base.utils;

/*  2:   */
/*  3:   */import java.util.Random;

/*  4:   */
/*  5:   */public class RandomUtils
/* 6: */{
	/* 7: */public static int getRandom(int max)
	/* 8: */{
		/* 9:17 */return new Random().nextInt(max);
		/* 10: */}

	/* 11: */
	/* 12: */public static int getRandom(int min, int max)
	/* 13: */{
		/* 14:28 */int r = getRandom(max - min);
		/* 15:29 */return r + min;
		/* 16: */}

	/* 17: */
	/* 18: */public static long getRandomLong(long max)
	/* 19: */{
		/* 20:42 */long randNum = (long) (Math.random() * max);
		/* 21:43 */return randNum;
		/* 22: */}

	/* 23: */
	/* 24: */public static long getRandomLong(long min, long max)
	/* 25: */{
		/* 26:54 */long r = getRandomLong(max - min);
		/* 27:55 */return r + min;
		/* 28: */}

	/* 29: */
	/* 30: */public static long getSQLRandom(Long num)
	/* 31: */{
		/* 32:65 */Long newNum = Long.valueOf(getRandomLong(num.longValue()));
		/* 33:66 */String numStr = newNum + "";
		/* 34:67 */if (numStr.length() < 8) {
			/* 35:68 */return newNum.longValue();
			/* 36: */}
		/* 37:70 */int randLen = getRandom(8, numStr.length());
		/* 38:71 */return Long.valueOf(numStr.substring(0, randLen))
				.longValue();
		/* 39: */}
	/* 40: */
}

/*
 * Location: C:\Users\congxiaofan\Desktop\
 * 
 * Qualified Name: com.base.utils.RandomUtils
 * 
 * JD-Core Version: 0.7.0.1
 */
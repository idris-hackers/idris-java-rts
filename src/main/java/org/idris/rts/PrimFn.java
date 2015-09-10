/*
 */
package org.idris.rts;

import java.lang.RuntimeException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Primitive backend functions.
 * 
 * @author Jan Bessai
 */
public class PrimFn {
    public static byte LPlus(byte x, byte y) {
        return (byte)(x + y);
    }
    public static char LPlus(char x, char y) {
        return (char)(x + y);
    }
    public static short LPlus(short x, short y) {
        return (short)(x + y);
    }
    public static int LPlus(int x, int y) {
        return x + y;
    }
    public static long LPlus(long x, long y) {
        return x + y;
    }
    public static double LPlus(double x, double y) {
        return x + y;
    }
    public static BigInteger LPlus(BigInteger x, BigInteger y) {
        return x.add(y);
    }
    public static byte[] LPlus(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] += y[i];
        }
        return res;
    }
    public static short[] LPlus(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] += y[i];
        }
        return res;
    }
    public static int[] LPlus(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] += y[i];
        }
        return res;
    }
    public static long[] LPlus(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] += y[i];
        }
        return res;
    }
    
    public static byte LMinus(byte x, byte y) {
        return (byte)(x - y);
    }
    public static char LMinus(char x, char y) {
        return (char)(x - y);
    }
    public static short LMinus(short x, short y) {
        return (short)(x - y);
    }
    public static int LMinus(int x, int y) {
        return x - y;
    }
    public static long LMinus(long x, long y) {
        return x - y;
    }
    public static double LMinus(double x, double y) {
        return x - y;
    }
    public static BigInteger LMinus(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }
    public static byte[] LMinus(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] -= y[i];
        }
        return res;
    }
    public static short[] LMinus(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] -= y[i];
        }
        return res;
    }
    public static int[] LMinus(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] -= y[i];
        }
        return res;
    }
    public static long[] LMinus(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] -= y[i];
        }
        return res;
    }
    
    public static byte LTimes(byte x, byte y) {
        return (byte)(x * y);
    }
    public static char LTimes(char x, char y) {
        return (char)(x * y);
    }
    public static short LTimes(short x, short y) {
        return (short)(x * y);
    }
    public static int LTimes(int x, int y) {
        return x * y;
    }
    public static long LTimes(long x, long y) {
        return x * y;
    }
    public static double LTimes(double x, double y) {
        return x * y;
    }
    public static BigInteger LTimes(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }
    public static byte[] LTimes(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] *= y[i];
        }
        return res;
    }
    public static short[] LTimes(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] *= y[i];
        }
        return res;
    }
    public static int[] LTimes(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] *= y[i];
        }
        return res;
    }
    public static long[] LTimes(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] *= y[i];
        }
        return res;
    }
    
    public static byte LUDiv(byte x, byte y) {
        return (byte)(((short)x) / ((short)y));
    }  
    public static char LUDiv(char x, char y) {
        return (char)(((long)x) / ((long)y));
    } 
    public static short LUDiv(short x, short y) {
        return (short)(((int)x) / ((int)y));
    }
    public static int LUDiv(int x, int y) {
        return (int)(((long)x) / ((long)y));
    }
    public static long LUDiv(long x, long y) {
        return BigInteger.valueOf(x).divide(BigInteger.valueOf(y)).longValue();
    }
    public static byte[] LUDiv(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] = (byte)(((short)res[i]) / ((short)y[i]));
        }
        return res;
    }
    public static short[] LUDiv(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] = (short)(((int)res[i]) / ((int)y[i]));
        }
        return res;
    }
    public static int[] LUDiv(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] = (int)(((long)res[i]) / ((long)y[i]));
        }
        return res;
    }
    public static long[] LUDiv(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] = BigInteger.valueOf(res[i]).divide(BigInteger.valueOf(y[i])).longValue();
        }
        return res;
    }
    
    public static byte LSDiv(byte x, byte y) {
        return (byte)(x / y);
    }
    public static char LSDiv(char x, char y) {
        return (char)(x / y);
    }
    public static short LSDiv(short x, short y) {
        return (short)(x / y);
    }
    public static int LSDiv(int x, int y) {
        return x / y;
    }
    public static long LSDiv(long x, long y) {
        return x / y;
    }
    public static double LSDiv(double x, double y) {
        return x / y;
    }
    public static BigInteger LSDiv(BigInteger x, BigInteger y) {
        return x.divide(y);
    }
    public static byte[] LSDiv(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] /= y[i];
        }
        return res;
    }
    public static short[] LSDiv(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] /= y[i];
        }
        return res;
    }
    public static int[] LSDiv(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] /= y[i];
        }
        return res;
    }
    public static long[] LSDiv(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] /= y[i];
        }
        return res;
    }
    
    public static byte LURem(byte x, byte y) {
        return (byte)(((short)x) % ((short)y));
    }    
    public static char LURem(char x, char y) {
        return (char)(((long)x) % ((long)y));
    }   
    public static short LURem(short x, short y) {
        return (short)(((int)x) % ((int)y));
    }
    public static int LURem(int x, int y) {
        return (int)(((long)x) % ((long)y));
    }
    public static long LURem(long x, long y) {
        return BigInteger.valueOf(x).divide(BigInteger.valueOf(y)).longValue();
    }
    public static byte[] LURem(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] = (byte)(((short)res[i]) % ((short)y[i]));
        }
        return res;
    }
    public static short[] LURem(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] = (short)(((int)res[i]) % ((int)y[i]));
        }
        return res;
    }
    public static int[] LURem(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] = (int)(((long)res[i]) % ((long)y[i]));
        }
        return res;
    }
    public static long[] LURem(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] = BigInteger.valueOf(res[i]).divide(BigInteger.valueOf(y[i])).longValue();
        }
        return res;
    }
    
    public static byte LSRem(byte x, byte y) {
        return (byte)(x % y);
    }
    public static char LSRem(char x, char y) {
        return (char)(x % y);
    }  
    public static short LSRem(short x, short y) {
        return (short)(x % y);
    }
    public static int LSRem(int x, int y) {
        return x % y;
    }
    public static long LSRem(long x, long y) {
        return x % y;
    }
    public static double LSRem(double x, double y) {
        return x % y;
    }
    public static BigInteger LSRem(BigInteger x, BigInteger y) {
        return x.remainder(y);
    }
    public static byte[] LSRem(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] %= y[i];
        }
        return res;
    }
    public static short[] LSRem(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] %= y[i];
        }
        return res;
    }
    public static int[] LSRem(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] %= y[i];
        }
        return res;
    }
    public static long[] LSRem(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] %= y[i];
        }
        return res;
    }
    
    
    public static byte LAnd(byte x, byte y) {
        return (byte)(x & y);
    }
    public static char LAnd(char x, char y) {
        return (char)(x & y);
    }
    public static short LAnd(short x, short y) {
        return (short)(x & y);
    }
    public static int LAnd(int x, int y) {
        return x & y;
    }
    public static long LAnd(long x, long y) {
        return x & y;
    }
    public static BigInteger LAnd(BigInteger x, BigInteger y) {
        return x.and(y);
    }
    public static byte[] LAnd(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] &= y[i];
        }
        return res;
    }
    public static short[] LAnd(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] &= y[i];
        }
        return res;
    }
    public static int[] LAnd(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] &= y[i];
        }
        return res;
    }
    public static long[] LAnd(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] &= y[i];
        }
        return res;
    }
    
    public static byte LOr(byte x, byte y) {
        return (byte)(x | y);
    }
    public static char LOr(char x, char y) {
        return (char)(x | y);
    }
    public static short LOr(short x, short y) {
        return (short)(x | y);
    }
    public static int LOr(int x, int y) {
        return x | y;
    }
    public static long LOr(long x, long y) {
        return x | y;
    }
    public static BigInteger LOr(BigInteger x, BigInteger y) {
        return x.or(y);
    }
    public static byte[] LOr(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] |= y[i];
        }
        return res;
    }
    public static short[] LOr(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] |= y[i];
        }
        return res;
    }
    public static int[] LOr(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] |= y[i];
        }
        return res;
    }
    public static long[] LOr(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] |= y[i];
        }
        return res;
    }
    
    public static byte LXOr(byte x, byte y) {
        return (byte)(x ^ y);
    }
    public static char LXOr(char x, char y) {
        return (char)(x ^ y);
    }
    public static short LXOr(short x, short y) {
        return (short)(x ^ y);
    }
    public static int LXOr(int x, int y) {
        return x ^ y;
    }
    public static long LXOr(long x, long y) {
        return x ^ y;
    }
    public static BigInteger LXOr(BigInteger x, BigInteger y) {
        return x.xor(y);
    }
    public static byte[] LXOr(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] ^= y[i];
        }
        return res;
    }
    public static short[] LXOr(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] ^= y[i];
        }
        return res;
    }
    public static int[] LXOr(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] ^= y[i];
        }
        return res;
    }
    public static long[] LXOr(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] ^= y[i];
        }
        return res;
    }
    
    public static byte LCompl(byte x) {
        return (byte)~x;
    }
    public static char LCompl(char x) {
        return (char)~x;
    }
    public static short LCompl(short x) {
        return (short)(~x);
    }
    public static int LCompl(int x) {
        return ~x;
    }
    public static long LCompl(long x) {
        return ~x;
    }
    public static BigInteger LCompl(BigInteger x) {
        return x.not();
    }
    public static byte[] LCompl(byte[] x) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (byte)~(x[i]);
        }
        return res;
    }
    public static short[] LCompl(short[] x) {
        short[] res = new short[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (short)~(x[i]);
        }
        return res;
    }
    public static int[] LCompl(int[] x) {
        int[] res = new int[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (int)~(x[i]);
        }
        return res;
    }
    public static long[] LCompl(long[] x) {
        long[] res = new long[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (long)~(x[i]);
        }
        return res;
    }
    
    public static byte LSHL(byte x, byte y) {
        return (byte)(x << y);
    }
    public static char LSHL(char x, char y) {
        return (char)(x << y);
    }
    public static short LSHL(short x, short y) {
        return (short)(x << y);
    }
    public static int LSHL(int x, int y) {
        return x << y;
    }
    public static long LSHL(long x, long y) {
        return x << y;
    }
    public static BigInteger LSHL(BigInteger x, BigInteger y) {
        return x.shiftLeft(y.intValue());
    }
    public static byte[] LSHL(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] <<= y[i];
        }
        return res;
    }
    public static short[] LSHL(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] <<= y[i];
        }
        return res;
    }
    public static int[] LSHL(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] <<= y[i];
        }
        return res;
    }
    public static long[] LSHL(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] <<= y[i];
        }
        return res;
    }
    
    public static byte LLSHR(byte x, byte y) {
        return (byte)(x >>> y);
    }    
    public static char LLSHR(char x, char y) {
        return (char)(x >>> y);
    }    
    public static short LLSHR(short x, short y) {
        return (short)(x >>> y);
    }
    public static int LLSHR(int x, int y) {
        return x >>> y;
    }
    public static long LLSHR(long x, long y) {
        return x >>> y;
    }
    public static byte[] LLSHR(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] >>>= y[i];
        }
        return res;
    }
    public static short[] LLSHR(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] >>>= y[i];
        }
        return res;
    }
    public static int[] LLSHR(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] >>>= y[i];
        }
        return res;
    }
    public static long[] LLSHR(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] >>>= y[i];
        }
        return res;
    }
    
    public static byte LASHR(byte x, byte y) {
        return (byte)(x >> y);
    }
    public static char LASHR(char x, char y) {
        return (char)(x >> y);
    }
    public static short LASHR(short x, short y) {
        return (short)(x >> y);
    }
    public static int LASHR(int x, int y) {
        return x >> y;
    }
    public static long LASHR(long x, long y) {
        return x >> y;
    }
    public static BigInteger LASHR(BigInteger x, BigInteger y) {
        return x.shiftRight(y.intValue());
    }
    public static byte[] LASHR(byte[] x, byte[] y) {
        byte[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] >>= y[i];
        }
        return res;
    }
    public static short[] LASHR(short[] x, short[] y) {
        short[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] >>= y[i];
        }
        return res;
    }
    public static int[] LASHR(int[] x, int[] y) {
        int[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] >>= y[i];
        }
        return res;
    }
    public static long[] LASHR(long[] x, long[] y) {
        long[] res = x.clone();
        for (int i = 0; i < res.length; ++i) {
            res[i] >>= y[i];
        }
        return res;
    }
    
    public static int LEq(byte x, byte y) {
        return (x == y ? 1 : 0);
    }
    public static int LEq(char x, char y) {
        return (x == y ? 1 : 0);
    }
    public static int LEq(short x, short y) {
        return (x == y ? 1 : 0);
    }
    public static int LEq(int x, int y) {
        return (x == y ? 1 : 0);
    }
    public static int LEq(long x, long y) {
        return (x == y ? 1 : 0);
    }
    public static int LEq(double x, double y) {
        return (x == y ? 1 : 0);
    }
    public static int LEq(BigInteger x, BigInteger y) {
        return (x.equals(y) ? 1 : 0);
    }
    public static byte[] LEq(byte[] x, byte[] y) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (byte)(x[i] == y[i] ? -1 : 0);
        }
        return res;
    }
    public static short[] LEq(short[] x, short[] y) {
        short[] res = new short[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (short)(x[i] == y[i] ? -1 : 0);
        }
        return res;
    }
    public static int[] LEq(int[] x, int[] y) {
        int[] res = new int[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (x[i] == y[i] ? -1 : 0);
        }
        return res;
    }
    public static long[] LEq(long[] x, long[] y) {
        long[] res = new long[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (x[i] == y[i] ? -1 : 0);
        }
        return res;
    }
    
    public static int LSLt(byte x, byte y) {
        return (x < y ? 1 : 0);
    }
    public static int LSLt(char x, char y) {
        return (x < y ? 1 : 0);
    } 
    public static int LSLt(short x, short y) {
        return (x < y ? 1 : 0);
    }
    public static int LSLt(int x, int y) {
        return (x < y ? 1 : 0);
    }
    public static int LSLt(long x, long y) {
        return (x < y ? 1 : 0);
    }
    public static double LSLt(double x, double y) {
        return (x < y ? 1 : 0);
    }
    public static int LSLt(BigInteger x, BigInteger y) {
        return (x.compareTo(y) < 0 ? 1 : 0);
    }
    public static byte[] LSLt(byte[] x, byte[] y) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (byte)(x[i] < y[i] ? -1 : 0);
        }
        return res;
    }
    public static short[] LSLt(short[] x, short[] y) {
        short[] res = new short[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (short)(x[i] < y[i] ? -1 : 0);
        }
        return res;
    }
    public static int[] LSLt(int[] x, int[] y) {
        int[] res = new int[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (x[i] < y[i] ? -1 : 0);
        }
        return res;
    }
    public static long[] LSLt(long[] x, long[] y) {
        long[] res = new long[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (x[i] < y[i] ? -1l : 0l);
        }
        return res;
    }
    public static int LLt(byte x, byte y) {
        return ((x < y) ^ (x < 0) ^ (y < 0)  ? 1 : 0);
    }
    public static int LLt(char x, char y) {
        return ((x < y) ^ (x < 0) ^ (y < 0)  ? 1 : 0);
    } 
    public static int LLt(short x, short y) {
        return ((x < y) ^ (x < 0) ^ (y < 0)  ? 1 : 0);
    }
    public static int LLt(int x, int y) {
        return ((x < y) ^ (x < 0) ^ (y < 0) ? 1 : 0);
    }
    public static int LLt(long x, long y) {
        return ((x < y) ^ (x < 0l) ^ (y < 0l) ? 1 : 0);
    }
    public static byte[] LLt(byte[] x, byte[] y) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (byte)((x[i] < y[i]) ^ (x[i] < 0) ^ (y[i] < 0) ? -1 : 0);
        }
        return res;
    }
    public static short[] LLt(short[] x, short[] y) {
        short[] res = new short[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (short)((x[i] < y[i]) ^ (x[i] < 0) ^ (y[i] < 0) ? -1 : 0);
        }
        return res;
    }
    public static int[] LLt(int[] x, int[] y) {
        int[] res = new int[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = ((x[i] < y[i]) ^ (x[i] < 0) ^ (y[i] < 0) ? -1 : 0);
        }
        return res;
    }
    public static long[] LLt(long[] x, long[] y) {
        long[] res = new long[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = ((x[i] < y[i]) ^ (x[i] < 0l) ^ (y[i] < 0l) ? -1l : 0l);
        }
        return res;
    }
    
    public static int LSLe(byte x, byte y) {
        return (x <= y ? 1 : 0);
    }
    public static int LSLe(char x, char y) {
        return (x <= y ? 1 : 0);
    }
    public static int LSLe(short x, short y) {
        return (x <= y ? 1 : 0);
    }
    public static int LSLe(int x, int y) {
        return (x <= y ? 1 : 0);
    }
    public static int LSLe(long x, long y) {
        return (x <= y ? 1 : 0);
    }
    public static double LSLe(double x, double y) {
        return (x <= y ? 1 : 0);
    }
    public static int LSLe(BigInteger x, BigInteger y) {
        return (x.compareTo(y) <= 0 ? 1 : 0);
    }
    public static byte[] LSLe(byte[] x, byte[] y) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (byte)(x[i] <= y[i] ? -1 : 0);
        }
        return res;
    }
    public static short[] LSLe(short[] x, short[] y) {
        short[] res = new short[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (short)(x[i] <= y[i] ? -1 : 0);
        }
        return res;
    }
    public static int[] LSLe(int[] x, int[] y) {
        int[] res = new int[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (x[i] <= y[i] ? -1 : 0);
        }
        return res;
    }
    public static long[] LSLe(long[] x, long[] y) {
        long[] res = new long[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (x[i] <= y[i] ? -1l : 0l);
        }
        return res;
    }
    
    public static int LLe(byte x, byte y) {
        return ((x <= y) ^ (x < 0) ^ (y < 0) ? 1 : 0);
    }
    public static int LLe(char x, char y) {
        return ((x <= y) ^ (x < 0l) ^ (y < 0l) ? 1 : 0);
    }
    public static int LLe(short x, short y) {
        return ((x <= y) ^ (x < 0) ^ (y < 0) ? 1 : 0);
    }
    public static int LLe(int x, int y) {
        return ((x <= y) ^ (x < 0) ^ (y < 0) ? 1 : 0);
    }
    public static int LLe(long x, long y) {
        return ((x <= y) ^ (x < 0l) ^ (y < 0l) ? 1 : 0);
    }
    public static byte[] LLe(byte[] x, byte[] y) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (byte)((x[i] <= y[i]) ^ (x[i] < 0) ^ (y[i] < 0) ? -1 : 0);
        }
        return res;
    }
    public static short[] LLe(short[] x, short[] y) {
        short[] res = new short[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (short)((x[i] <= y[i]) ^ (x[i] < 0) ^ (y[i] < 0) ? -1 : 0);
        }
        return res;
    }
    public static int[] LLe(int[] x, int[] y) {
        int[] res = new int[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = ((x[i] <= y[i]) ^ (x[i] < 0) ^ (y[i] < 0) ? -1 : 0);
        }
        return res;
    }
    public static long[] LLe(long[] x, long[] y) {
        long[] res = new long[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = ((x[i] <= y[i]) ^ (x[i] < 0) ^ (y[i] < 0l) ? -1l : 0l);
        }
        return res;
    }
    
    public static int LSGt(byte x, byte y) {
        return (x > y ? 1 : 0);
    }    
    public static int LSGt(char x, char y) {
        return (x > y ? 1 : 0);
    }  
    public static int LSGt(short x, short y) {
        return (x > y ? 1 : 0);
    }
    public static int LSGt(int x, int y) {
        return (x > y ? 1 : 0);
    }
    public static int LSGt(long x, long y) {
        return (x > y ? 1 : 0);
    }
    public static double LSGt(double x, double y) {
        return (x > y ? 1 : 0);
    }
    public static int LSGt(BigInteger x, BigInteger y) {
        return (x.compareTo(y) > 0 ? 1 : 0);
    }
    public static byte[] LSGt(byte[] x, byte[] y) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (byte)(x[i] > y[i] ? -1 : 0);
        }
        return res;
    }
    public static short[] LSGt(short[] x, short[] y) {
        short[] res = new short[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (short)(x[i] > y[i] ? -1 : 0);
        }
        return res;
    }
    public static int[] LSGt(int[] x, int[] y) {
        int[] res = new int[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (x[i] > y[i] ? -1 : 0);
        }
        return res;
    }
    public static long[] LSGt(long[] x, long[] y) {
        long[] res = new long[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (x[i] > y[i] ? -1l : 0l);
        }
        return res;
    }
    public static int LGt(byte x, byte y) {
        return ((x > y) ^ (x < 0) ^ (y < 0) ? 1 : 0);
    }    
    public static int LGt(char x, char y) {
        return ((x > y) ^ (x < 0) ^ (y < 0) ? 1 : 0);
    }  
    public static int LGt(short x, short y) {
        return ((x > y) ^ (x < 0) ^ (y < 0) ? 1 : 0);
    }
    public static int LGt(int x, int y) {
        return ((x > y) ^ (x < 0) ^ (y < 0) ? 1 : 0);
    }
    public static int LGt(long x, long y) {
        return ((x > y) ^ (x < 0l) ^ (y < 0l) ? 1 : 0);
    }
    public static byte[] LGt(byte[] x, byte[] y) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (byte)((x[i] > y[i]) ^ (x[i] < 0) ^ (y[i] < 0) ? -1 : 0);
        }
        return res;
    }
    public static short[] LGt(short[] x, short[] y) {
        short[] res = new short[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (short)((x[i] > y[i]) ^ (x[i] < 0) ^ (y[i] < 0) ? -1 : 0);
        }
        return res;
    }
    public static int[] LGt(int[] x, int[] y) {
        int[] res = new int[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = ((x[i] > y[i]) ^ (x[i] < 0) ^ (y[i] < 0) ? -1 : 0);
        }
        return res;
    }
    public static long[] LGt(long[] x, long[] y) {
        long[] res = new long[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = ((x[i] > y[i]) ^ (x[i] < 0l) ^ (y[i] < 0l) ? -1l : 0l);
        }
        return res;
    }
    
    public static int LSGe(byte x, byte y) {
        return (x >= y ? 1 : 0);
    }
    public static int LSGe(char x, char y) {
        return (x >= y ? 1 : 0);
    }
    public static int LSGe(short x, short y) {
        return (x >= y ? 1 : 0);
    }
    public static int LSGe(int x, int y) {
        return (x >= y ? 1 : 0);
    }
    public static int LSGe(long x, long y) {
        return (x >= y ? 1 : 0);
    }
    public static double LSGe(double x, double y) {
        return (x >= y ? 1 : 0);
    }
    public static int LSGe(BigInteger x, BigInteger y) {
        return (x.compareTo(y) >= 0 ? 1 : 0);
    }
    public static byte[] LSGe(byte[] x, byte[] y) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (byte)(x[i] >= y[i] ? -1 : 0);
        }
        return res;
    }
    public static short[] LSGe(short[] x, short[] y) {
        short[] res = new short[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (short)(x[i] >= y[i] ? -1 : 0);
        }
        return res;
    }
    public static int[] LSGe(int[] x, int[] y) {
        int[] res = new int[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (x[i] >= y[i] ? -1 : 0);
        }
        return res;
    }
    public static long[] LSGe(long[] x, long[] y) {
        long[] res = new long[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (x[i] >= y[i] ? -1l : 0l);
        }
        return res;
    }   
    public static int LGe(byte x, byte y) {
        return ((x >= y) ^ (x < 0) ^ (y < 0) ? 1 : 0);
    }
    public static int LGe(char x, char y) {
        return ((x >= y) ^ (x < 0) ^ (y < 0) ? 1 : 0);
    }
    public static int LGe(short x, short y) {
        return ((x >= y) ^ (x < 0) ^ (y < 0) ? 1 : 0);
    }
    public static int LGe(int x, int y) {
        return ((x >= y) ^ (x < 0) ^ (y < 0) ? 1 : 0);
    }
    public static int LGe(long x, long y) {
        return ((x >= y) ^ (x < 0l) ^ (y < 0l) ? 1 : 0);
    }
    public static byte[] LGe(byte[] x, byte[] y) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (byte)((x[i] >= y[i]) ^ (x[i] < 0) ^ (y[i] < 0) ? -1 : 0);
        }
        return res;
    }
    public static short[] LGe(short[] x, short[] y) {
        short[] res = new short[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = (short)((x[i] >= y[i]) ^ (x[i] < 0) ^ (y[i] < 0) ? -1 : 0);
        }
        return res;
    }
    public static int[] LGe(int[] x, int[] y) {
        int[] res = new int[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = ((x[i] >= y[i]) ^ (x[i] < 0) ^ (y[i] < 0) ? -1 : 0);
        }
        return res;
    }
    public static long[] LGe(long[] x, long[] y) {
        long[] res = new long[x.length];
        for (int i = 0; i < x.length; ++i) {
            res[i] = ((x[i] >= y[i]) ^ (x[i] < 0l) ^ (y[i] < 0l)  ? -1l : 0l);
        }
        return res;
    }
    
    
    public static short LSExtIT16(byte x) {
        return (short)x;
    }
    public static int LSExtIT32(byte x) {
        return (int)x;
    }
    public static long LSExtIT64(byte x) {
        return (long)x;
    }
    public static BigInteger LSExtITBig(byte x) {
        return BigInteger.valueOf(x);
    }
    public static short[] LSExtITVec16(byte[] x) {
        short[] res = new short[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (short)x[i];
        }
        return res;
    }
    public static int[] LSExtITVec32(byte[] x) {
        int[] res = new int[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (int)x[i];
        }
        return res;
    }
    public static long[] LSExtITVec64(byte[] x) {
        long[] res = new long[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (long)x[i];
        }
        return res;
    }
    
    public static short LSExtIT16(char x) {
        return (short)x;
    }
    public static int LSExtIT32(char x) {
        return (int)x;
    }
    public static long LSExtIT64(char x) {
        return (long)x;
    }
    public static BigInteger LSExtITBig(char x) {
        return BigInteger.valueOf(x);
    }
    
    public static int LSExtIT32(short x) {
        return (int)x;
    }
    public static long LSExtIT64(short x) {
        return (long)x;
    }
    public static BigInteger LSExtITBig(short x) {
        return BigInteger.valueOf(x);
    }
    public static int[] LSExtITVec32(short[] x) {
        int[] res = new int[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (int)x[i];
        }
        return res;
    }
    public static long[] LSExtITVec64(short[] x) {
        long[] res = new long[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (long)x[i];
        }
        return res;
    }
    
    public static long LSExtIT64(int x) {
        return (long)x;
    }
    public static BigInteger LSExtITBig(int x) {
        return BigInteger.valueOf(x);
    }
    public static long[] LSExtITVec64(int[] x) {
        long[] res = new long[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (long)x[i];
        }
        return res;
    }
    public static BigInteger LSExtITBig(long x) {
        return BigInteger.valueOf(x);
    }
        
    
    public static short LZExtIT16(byte x) {
        return (short)(x < 0 ? x + (2 << 7) : x);
    }
    public static int LZExtIT32(byte x) {
        return (int)(x < 0 ? x + (2 << 7) : x);
    }
    public static long LZExtIT64(byte x) {
        return (long)(x < 0 ? x + (2 << 7) : x);
    }
    public static BigInteger LZExtITBig(byte x) {
        return BigInteger.valueOf(x < 0 ? x + (2 << 7) : x);
    }
    public static char LZExtITChar(byte x) {
        return (char)(x < 0 ? x + (2 << 7) : x);
    }
    public static short[] LZExtITVec16(byte[] x) {
        short[] res = new short[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (short)(x[i] < 0 ? x[i] + (2 << 7) : x[i]);
        }
        return res;
    }
    public static int[] LZExtITVec32(byte[] x) {
        int[] res = new int[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (int)(x[i] < 0 ? x[i] + (2 << 7) : x[i]);
        }
        return res;
    }
    public static long[] LZExtITVec64(byte[] x) {
        long[] res = new long[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (long)(x[i] < 0 ? x[i] + (2 << 7) : x[i]);
        }
        return res;
    }
     
    public static int LZExtIT32(short x) {
        return (int)(x < 0 ? x + (2 << 15) : x);
    }
    public static long LZExtIT64(short x) {
        return (long)(x < 0 ? x + (2 << 15) : x);
    }
    public static BigInteger LZExtITBig(short x) {
        return BigInteger.valueOf(x < 0 ? x + (2 << 15) : x);
    }
    public static char LZExtITChar(short x) {
        return (char)(x < 0 ? x + (2 << 15) : x);
    }
    public static int[] LZExtITVec32(short[] x) {
        int[] res = new int[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (int)(x[i] < 0 ? x[i] + (2 << 15) : x[i]);
        }
        return res;
    }
    public static long[] LZExtITVec64(short[] x) {
        long[] res = new long[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (long)(x[i] < 0 ? x[i] + (2 << 15) : x[i]);
        }
        return res;
    }
    
    public static long LZExtIT64(int x) {
        return (long)(x < 0 ? ((long)x) + (2l << 31) : x);
    }
    public static BigInteger LZExtITBig(int x) {
        return BigInteger.valueOf(x < 0 ? ((long)x) + (2l << 31) : x);
    }
    public static char LZExtITChar(int x) {
        return (char)(x < 0 ? ((long)x) + (2l << 31) : x);
    }
    public static long[] LZExtITVec64(int[] x) {
        long[] res = new long[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (long)(((long)x[i]) < 0 ? x[i] + (2l << 31) : x[i]);
        }
        return res;
    }
    
    public static BigInteger LZExtITBig(long x) {
        return (x < 0 ? BigInteger.valueOf(x).add(BigInteger.valueOf(2).shiftLeft(61)) : BigInteger.valueOf(x));
    }
    public static char LZExtITChar(long x) {
        return (char)((x < 0 ? BigInteger.valueOf(x).add(BigInteger.valueOf(2).shiftLeft(61)) : BigInteger.valueOf(x)).intValue());
    }
    
    public static byte LTruncIT8(short x) {
        return (byte)x;
    }
    public static byte[] LTruncITVec8(short[] x) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (byte)x[i];
        }
        return res;
    }
    public static char LTruncITChar(short x) {
        return (char)x;
    }
    
    public static byte LTruncIT8(int x) {
        return (byte)x;
    }
    public static short LTruncIT16(int x) {
        return (short)x;
    }    
    public static byte[] LTruncITVec8(int[] x) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (byte)x[i];
        }
        return res;
    }
    public static short[] LTruncITVec16(int[] x) {
        short[] res = new short[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (short)x[i];
        }
        return res;
    }
    public static char LTruncITChar(int x) {
        return (char)x;
    }
    
    public static byte LTruncIT8(long x) {
        return (byte)x;
    }
    public static short LTruncIT16(long x) {
        return (short)x;
    }
    public static int LTruncIT32(long x) {
        return (int)x;
    }
    public static byte[] LTruncITVec8(long[] x) {
        byte[] res = new byte[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (byte)x[i];
        }
        return res;
    }
    public static short[] LTruncITVec16(long[] x) {
        short[] res = new short[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (short)x[i];
        }
        return res;
    }
    public static int[] LTruncITVec32(long[] x) {
        int[] res = new int[x.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (int)x[i];
        }
        return res;
    }
    public static char LTruncITChar(long x) {
        return (char)x;
    }
    
    public static byte LTruncIT8(BigInteger x) {
        return x.byteValue();
    }
    public static short LTruncIT16(BigInteger x) {
        return x.shortValue();
    }
    public static int LTruncIT32(BigInteger x) {
        return x.intValue();
    }
    public static long LTruncIT64(BigInteger x) {
        return x.longValue();
    }
    public static char LTruncITChar(BigInteger x) {
        return (char)x.intValue();
    }
    
    public static byte LTruncIT8(char x) {
        return (byte)x;
    }
    public static short LTruncIT16(char x) {
        return (short)x;
    }
    public static int LTruncIT32(char x) {
        return (int)x;
    }
    public static long LTruncIT64(char x) {
        return (int)x;
    }
    public static BigInteger LTruncITBig(char x) {
        return BigInteger.valueOf(x);
    }
    
    
    public static double LFExp(double x) {
        return Math.exp(x);
    }
    public static double LFLog(double x) {
        return Math.log(x);
    }
    public static double LFSin(double x) {
        return Math.sin(x);
    }
    public static double LFCos(double x) {
        return Math.cos(x);
    }
    public static double LFTan(double x) {
        return Math.tan(x);
    }
    public static double LFASin(double x) {
        return Math.asin(x);
    }
    public static double LFACos(double x) {
        return Math.acos(x);
    }
    public static double LFATan(double x) {
        return Math.atan(x);
    }
    public static double LFSqrt(double x) {
        return Math.sqrt(x);
    }
    public static double LFFloor(double x) {
        return Math.floor(x);
    }
    public static double LFCeil(double x) {
        return Math.ceil(x);
    }
    
    public static byte[] LMkVec(byte ... x) {
        return x;
    }
    public static short[] LMkVec(short ... x) {
        return x;
    }
    public static int[] LMKVec(int ... x) {
        return x;
    }
    public static long[] LMKVec(long ... x) {
        return x;
    }
    
    public static byte LIdxVec(byte[] x, int y) {
        return x[y];
    }
    public static short LIdxVec(short[] x, int y) {
        return x[y];
    }
    public static int LIdxVec(int[] x, int y) {
        return x[y];
    }
    public long LIdxVec(long[] x, int y) {
        return x[y];
    }
    
    public static byte[] LUpdateVec(byte[] x, int y, byte z) {
        x[y] = z;
        return x;
    }
    public static short[] LUpdateVec(short[] x, int y, short z) {
        x[y] = z;
        return x;
    }
    public static int[] LUpdateVec(int[] x, int y, int z) {
        x[y] = z;
        return x;
    }
    public long[] LUpdateVec(long[] x, int y, long z) {
        x[y] = z;
        return x;
    }
    
    public static String LStrConcat(String ... args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg);
        }
        return builder.toString();
    }
    
    public static int LStrLt(String x, String y) {
        return (x.compareTo(y) < 0 ? 1 : 0);
    }
    
    public static int LStrEq(String x, String y) {
        return (x.equals(y) ? 1 : 0);
    }
    
    public static int LStrLen(String x) {
        return x.length();
    }
    
    public static double LIntFloat(byte x) {
        return (double)x;
    }
    public static double LIntFloat(char x) {
        return (double)x;
    }
    public static double LIntFloat(short x) {
        return (double)x;
    }
    public static double LIntFloat(int x) {
        return (double)x;
    }
    public static double LIntFloat(long x) {
        return (double)x;
    }
    public static double LIntFloat(BigInteger x) {
        return x.doubleValue();
    }
    
    public static byte LFloatIntIT8(double x) {
        return (byte)x;
    }
    public static short LFloatIntIT16(double x) {
        return (short)x;
    }
    public static int LFloatIntIT32(double x) {
        return (int)x;
    }
    public static long LFloatIntIT64(double x) {
        return (long)x;
    }
    public static BigInteger LFloatIntITBig(double x) {
        return BigInteger.valueOf((long)x);
    }
    public static char LFloatIntITChar(double x) {
        return (char)x;
    }
    
    public static String LIntStr(byte x) {
        return Byte.toString(x);
    }
    public static String LIntStr(char x) {
        return Character.toString(x);
    }
    public static String LIntStr(short x) {
        return Short.toString(x);
    }
    public static String LIntStr(int x) {
        return Integer.toString(x);
    }
    public static String LIntStr(long x) {
        return Long.toString(x);
    }
    public static String LIntStr(BigInteger x) {
        return x.toString();
    }    
    public static byte LStrIntIT8(String x) {
        return Byte.parseByte(x);
    }
    public static short LStrIntIT16(String x) {
        return Short.parseShort(x);
    }
    public static int LStrIntIT32(String x) {
        return Integer.parseInt(x);
    }
    public static long LStrIntIT64(String x) {
        return Long.parseLong(x);
    }
    public static BigInteger LStrIntITBig(String x) {
        return new BigInteger(x);
    }
    public static char LStrIntITChar(String x) {
        return x.charAt(0);
    }
    
    public static String LFloatStr(double x) {
        return Double.toString(x);
    }
    public static double LStrFloat(String x) {
        return Double.parseDouble(x);
    }
    
    public static char LIntCh(byte x) {
        return (char)x;
    }
    public static char LIntCh(char x) {
        return x;
    }
    public static char LIntCh(short x) {
        return (char)x;
    }
    public static char LIntCh(int x) {
        return (char)x;
    }
    public static char LIntCh(long x) {
        return (char)x;
    }
    public static char LIntCh(BigInteger x) {
        return (char)(x.intValue());
    }    
    public static byte LChIntIT8(char x) {
        return (byte)x;
    }
    public static short LChIntIT16(char x) {
        return (short)x;
    }
    public static int LChIntIT32(char x) {
        return (int)x;
    }
    public static long LChIntIT64(char x) {
        return (long)x;
    }
    public static BigInteger LChIntITBig(char x) {
        return BigInteger.valueOf((long)x);
    }
    
    public static void LPrintNum(Object x) {
        System.out.print(x);
    }
    public static void LPrintStr(String x) {
        System.out.print(x);
    }

    public static Object LWriteStr(Object world, String x) {
        System.out.print(x);
	return null;
    }
    
    public static String LReadStr(Object x) {
	try {
	    return new java.io.BufferedReader(
		new java.io.InputStreamReader(System.in)).readLine();
	} catch (java.io.IOException ioEx) {
	    throw new RuntimeException(ioEx.getMessage());
	}
    }

    public static char LStrHead(String x) {
        return x.charAt(0);
    }
    public static String LStrTail(String x) {
        return x.substring(1);
    }
    public static String LStrCons(char x, String y) {
        return String.format("%c%s", x, y);
    }
    public static char LStrIndex(String x, int y) {
        return x.charAt(y);
    }
    public static String LStrRev(String x) {
        return new StringBuffer(x).reverse().toString();
    }
    
    public static InputStream LStdIn() {
        return System.in;
    }
    public static OutputStream LStdOut() {
        return System.out;
    }
    public static OutputStream LStdErr() {
        return System.err;
    }
    public static ByteBuffer LAllocate(long capacity) {
        return ByteBuffer.allocate((int)capacity);
    }
    public static ByteBuffer LAppendBuffer(ByteBuffer to, long toSize, long count, long fromSize, long fromOffset, ByteBuffer from) {
        ByteBuffer resultBuffer = ByteBuffer.allocate((int)(toSize + (fromSize - fromOffset) * count));
        
        // copy to buffer
        int oldPos = to.position();
        to.position(0);
        ByteBuffer toSlice = to.slice();
        to.position(oldPos);
        toSlice.limit((int)toSize);
        resultBuffer.put(toSlice);
                
        // copy from buffer
        oldPos = from.position();
        from.position((int)fromOffset);
        ByteBuffer fromSlice = from.slice();
        from.position(oldPos);
        fromSlice.limit((int)fromSize);
        
        for (long i = 0; i < count; ++i) {
            resultBuffer.put(fromSlice);
            fromSlice.position(0);
        }
        
        return resultBuffer;
    }
    private static ByteBuffer prepareBuffer(ByteBuffer original, long position, long elem_size, long count) {
        ByteBuffer targetBuffer;
        long newSize = position + elem_size * count;
        if (original.capacity() - position >= newSize) {
            targetBuffer = original;
            targetBuffer.limit((int)newSize);
        } else {
            targetBuffer = ByteBuffer.allocate((int)newSize);
            int oldPos = original.position();
            original.position(0);
            targetBuffer.put(original);
            original.position(oldPos);
        }
        targetBuffer.position((int)position);
        return targetBuffer;
    }
    private static ByteBuffer prepareCopyBuffer(ByteOrder endianness, int size) {
        final ByteBuffer copyBuffer = ByteBuffer.allocate(size);
        copyBuffer.order(endianness);
        return copyBuffer;
    }
    private static ByteBuffer appendCopyBuffer(ByteBuffer targetBuffer, ByteBuffer copyBuffer, long count) {
        for (long i = 0; i < count; ++i) {
            copyBuffer.position(0);
            targetBuffer.put(copyBuffer);
        }
        return targetBuffer;
    }
    
    public static ByteBuffer LAppend(ByteOrder endianness, ByteBuffer buffer, long position, long count, byte value) {
        final ByteBuffer targetBuffer = prepareBuffer(buffer, position, 1, count);
        for (long i = 0; i < count; ++i) {
            targetBuffer.put(value);
        }
        return targetBuffer;
    }
    public static ByteBuffer LAppend(ByteOrder endianness, ByteBuffer buffer, long position, long count, short value) {
        final ByteBuffer targetBuffer = prepareBuffer(buffer, position, 2, count);
        final ByteBuffer copyBuffer = prepareCopyBuffer(endianness, 2);
        copyBuffer.putShort(value);
        return appendCopyBuffer(targetBuffer, copyBuffer, count);
    }
    public static ByteBuffer LAppend(ByteOrder endianness, ByteBuffer buffer, long position, long count, int value) {
        final ByteBuffer targetBuffer = prepareBuffer(buffer, position, 4, count);
        final ByteBuffer copyBuffer = prepareCopyBuffer(endianness, 4);
        copyBuffer.putInt(value);
        return appendCopyBuffer(targetBuffer, copyBuffer, count);
    }
    public static ByteBuffer LAppend(ByteOrder endianness, ByteBuffer buffer, long position, long count, long value) {
        final ByteBuffer targetBuffer = prepareBuffer(buffer, position, 8, count);
        final ByteBuffer copyBuffer = prepareCopyBuffer(endianness, 8);
        copyBuffer.putLong(value);
        return appendCopyBuffer(targetBuffer, copyBuffer, count);
    }
    public static byte LPeekIT8(ByteOrder endianness, ByteBuffer buffer, long position) {
        return buffer.get((int)position);
    }
    public static short LPeekIT16(ByteOrder endianness, ByteBuffer buffer, long position) {
        ByteOrder oldOrder = buffer.order();
        buffer.order(endianness);
        short result = buffer.getShort((int)position);
        buffer.order(oldOrder);
        return result;
    }
    public static int LPeekIT32(ByteOrder endianness, ByteBuffer buffer, long position) {
        ByteOrder oldOrder = buffer.order();
        buffer.order(endianness);
        int result = buffer.getInt((int)position);
        buffer.order(oldOrder);
        return result;
    }
    public static long LPeekIT64(ByteOrder endianness, ByteBuffer buffer, long position) {
        ByteOrder oldOrder = buffer.order();
        buffer.order(endianness);
        long result = buffer.getLong((int)position);
        buffer.order(oldOrder);
        return result;
    }
    
    public static String LSystemInfo(int index) {
        switch (index) {
            case 0: return "java";
            case 1: return System.getProperty("os.name");
            case 2: return System.getProperties().toString();
            default: return "";
        }
    }

    public static Object LExternal() {
	//FIXME: need to correctly handle this in the code generator
	return null;
    }
    
    public static Thread LVMPtr() {
        return Thread.currentThread();
    }
}

/*
 * This file is part of "The Java Telnet Application".
 *
 * (c) Matthias L. Jugel, Marcus Mei遪er 1996-2002. All Rights Reserved.
 *
 * Please visit http://javatelnet.org/ for updates and contact.
 *
 * --LICENSE NOTICE--
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 * --LICENSE NOTICE--
 *
 * Additional NOTICE: This file uses DES (see DES.java for copyright
 * information!)
 */

package com.util;

import java.math.BigInteger;


public final class DES3 extends Cipher {
  static {
//    System.err.println("3DES Cipher.");
  }

  DES des1 = new DES();
  DES des2 = new DES();
  DES des3 = new DES();

  public synchronized void encrypt(byte[] src, int srcOff, byte[] dest, int destOff, int len) {
    des1.encrypt(src, srcOff, dest, destOff, len);
    des2.decrypt(dest, destOff, dest, destOff, len);
    des3.encrypt(dest, destOff, dest, destOff, len);
  }

  public synchronized void decrypt(byte[] src, int srcOff, byte[] dest, int destOff, int len) {
    des3.decrypt(src, srcOff, dest, destOff, len);
    des2.encrypt(dest, destOff, dest, destOff, len);
    des1.decrypt(dest, destOff, dest, destOff, len);
  }

  public void setKey(byte[] key) {
    byte[] subKey = new byte[8];
    des1.setKey(key);
    System.arraycopy(key, 8, subKey, 0, 8);
    des2.setKey(subKey);
    System.arraycopy(key, 16, subKey, 0, 8);
    des3.setKey(subKey);
  }

  static  byte[] key = {
      (byte)0x12, (byte)0x34, (byte)0x45, (byte)0x78,
      (byte)0x87, (byte)0x34, (byte)0x43, (byte)0x23,
      (byte)0x89, (byte)0x55, (byte)0x01, (byte)0x77,
      (byte)0x87, (byte)0xef, (byte)0x43, (byte)0x78,
      (byte)0xcd, (byte)0x65, (byte)0x9a, (byte)0x21,
      (byte)0x12, (byte)0xab, (byte)0x56, (byte)0x78,
    };


	static public String decrypt(String source) {
		byte[] txt = new byte[24];
		BigInteger t = new BigInteger(source, 16);	//如果首字符为>8,则解释成16进制多一个字节，第一个字节为0，可以废弃。
		byte[] b = t.toByteArray();

//		txt[0] = 0;
/*
		System.out.println(source);
		printHex(b);
		System.out.println(b[0]);
		System.out.println(b[1]);
		System.out.println(b[2]);
		System.out.println(b.length);
		System.out.println(txt.length);
*/
		if(b[0]==0){		//如果首字符为>8,则解释成16进制多一个字节，第一个字节为0，可以废弃。
			System.arraycopy(b, 1, txt, 0, b.length-1);
		}else{
			System.arraycopy(b, 0, txt, 0, b.length);
		}


		byte[] dec;
		DES3 cipher = new DES3();
		cipher.setKey(key);
		dec = cipher.decrypt(txt);

/*
        String decs = printHex(dec);
		System.out.println("dec: " + decs);
*/
		byte[] dect = new byte[dec.length];
        int j=0;
        for(int i=0;i<dec.length;i++){
            if(dec[i]>0){
                dect[j++] = dec[i];
            }
        }
		String rt = (new String(dect,0,j));
/*
		System.out.println("rt.length(): " + rt.length());
		displayString(rt);
		displayString("");
*/
/*		if(rt.length()<1)
			return new String("");
*/
		return rt;//new String(dec);
	}

  static public void displayString(String source) {
        byte[] aa = source.getBytes();
        System.out.print("BEGIN:");
        for(int i=0;i<aa.length;i++){
            System.out.print(aa[i]);
            System.out.print(" ");
        }
        System.out.println(":END");
    }

  static public String encrypt(String source) {
	byte[] txt = new byte[24];
	byte[] b = source.getBytes();

    System.arraycopy(b, 0, txt, 0, b.length);

    byte[] enc;
    DES3 cipher = new DES3();
    cipher.setKey(key);

    enc = cipher.encrypt(txt);
    return printHex(enc);
  }

  static String printHex(byte[] buf) {
    byte[] out = new byte[buf.length + 1];
    out[0] = 0;
    System.arraycopy(buf, 0, out, 1, buf.length);
    BigInteger big = new BigInteger(out);
    return big.toString(16);
  }
  static String printHex(int i) {
    BigInteger b = BigInteger.valueOf((long)i + 0x100000000L);
    BigInteger c = BigInteger.valueOf(0x100000000L);
    if(b.compareTo(c) != -1)
      b = b.subtract(c);
    return b.toString(16);
  }


	public static void main(String[] argv){
		/*
               if(argv.length > 1 ){
			System.out.println("decs:" + decrypt(argv[0]));
		}else
		{
        */
			String encs = encrypt("ibnms123!@#");
			System.out.println("ends:" + encs);
			System.out.println("decs:" + decrypt(encs));

	}

//

}

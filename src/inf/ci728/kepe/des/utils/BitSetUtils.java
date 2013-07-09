package inf.ci728.kepe.des.utils;

import java.util.BitSet;

public class BitSetUtils
{
	public static BitSet getBitSetFromInt(int value, int size)
	{
		BitSet bits = new BitSet(size);
		boolean bitValue;
		int index = 0;
		while(value > 0)
		{
			if((value % 2) == 0)
				bitValue = false;
			else
				bitValue = true;
			bits.set(index++, bitValue);
			value /= 2;
		}
		return bits;
	}

	public static int bitSetToInt(BitSet bitSet)
	{
	    int result = 0 ;
	    for(int i = 0 ; i < bitSet.length() ; i++){
	        if(bitSet.get(i)){
	            result |= (1 << i);
	        }
	    }
	    result &= Integer.MAX_VALUE;
	    return result;
	}
	
	public static BitSet getBitSetFromByte(byte value, int size)
	{
		BitSet bitset = new BitSet(size);  
		for (int i=0; i<size; i++)   
		{  
		    if ((value & (1 << i)) > 0)  
		    {  
		        bitset.set(i);  
		    }  
		} 
		return bitset;
	}
	
	public static byte bitSetToByte(BitSet bitSet)
	{
	    byte[] b = new byte[1];  
	    
	    for(int i = 0; i<bitSet.length(); i++)  
	    {
	    	if(bitSet.get(i))
	    		b[0] |= 1 <<i ;              
	    }  
	    return b[0]; 
	}
	
	public static BitSet getBitSetFromChar(char value, int size)
	{
		BitSet bits = new BitSet(size);
		boolean bitValue;
		int index = 0;
		while(value > 0)
		{
			if((value % 2) == 0)
			{
				bitValue = false;
			}
			else
			{
				bitValue = true;
			}
			bits.set(index++, bitValue);
			value /= 2;
		}
		return bits;
	}
	
	public static char bitSetToChar(BitSet bitSet)
	{
	    char result = 0 ;
	    for(int i = 0 ; i < bitSet.length() ; i++){
	        if(bitSet.get(i)){
	            result |= (1 << i);
	        }
	    }
	    result &= Character.MAX_VALUE;
	    return result;
	}
	
	public static void printBitSet(BitSet bits)
	{
		boolean bit;
		for(int i=0; i<bits.length(); i++)
		{
			bit = bits.get(i);
			if(bit)
				System.out.print(1);
			else
				System.out.print(0);
		}
		System.out.println();
	}

}
package inf.ci728.kepe.des.utils;

import java.util.BitSet;

public class BitSetUtils
{
	public static BitSet getBitSetFromInt(int value)
	{
		BitSet bits = new BitSet();
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

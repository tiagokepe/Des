package inf.ci728.kepe.des;

import java.util.BitSet;
import inf.ci728.kepe.des.Des;

public class Test {
	
	Des des = new Des();

	public static void main(String[] args) {
		BitSet bits = new BitSet();
		bits.set(0, true);
		bits.set(1, false);

		System.out.println("bitSetToInt = "+bitSetToInt(bits));

		
		System.out.println("bits = "+bits);
		
		BitSet bits2 = new BitSet();
		bits2.set(0, false);
		bits2.set(1, true);

		System.out.println("Antes Bits = "+bits.get(0));
		System.out.println("Antes Bits = "+bits.get(1));
		
		bits.and(bits2);
		System.out.println("bitSetToInt = "+bitSetToInt(bits));
		
		System.out.println("Depois Bits = "+bits.get(0));
		System.out.println("Depois Bits = "+bits.get(1));
		
		printBitSet(Test.getBitSetFromInt(10));
		System.out.println("10 = "+Test.getBitSetFromInt(10));

		//System.out.println("10 = "+Test.getBitSetFromIng(10).toString());
	}
	
	private static int bitSetToInt(BitSet bitSet)
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
	
	private static BitSet getBitSetFromInt(int value)
	{
		BitSet bits = new BitSet();
		boolean bitValue;
		int index = 0;
		while(value > 0)
		{
			if((value % 2) == 0)
			{
				bitValue = false;
				//System.out.print(0);
			}
			else
			{
				bitValue = true;
				//System.out.print(1);
			}
			
			
			bits.set(index++, bitValue);
			value /= 2;
		}
		System.out.println();
		return bits;
	}

	private static void printBitSet(BitSet bits)
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

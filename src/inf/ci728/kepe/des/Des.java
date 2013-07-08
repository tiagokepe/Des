package inf.ci728.kepe.des;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import inf.ci728.kepe.des.utils.BitSetUtils;

/**
 * This class use the BitSet class as base.
 * -> True = 1
 * -> False = 0
 * The BitSet class represents the bits in litle endian order
 * i.e., the number 1 = 10000000, 2 = 01000000, 3 = 00100000 ... 
 * @author kepe
 *
 */
public class Des
{
	private static int BLOCK_SIZE, SUB_KEY_SIZE = 8;
	private static int KEY_SIZE = 10;
	private static int MATRIX_SIZE = 4;
	
	private static List<BitSet> S0_MATRIX = 
			new ArrayList<BitSet>(MATRIX_SIZE*MATRIX_SIZE);  

	private static List<BitSet> S1_MATRIX = 
			new ArrayList<BitSet>(MATRIX_SIZE*MATRIX_SIZE);  
	
	private BitSet K1 = new BitSet(SUB_KEY_SIZE);
	private BitSet K2 = new BitSet(SUB_KEY_SIZE);

	public Des()
	{
		buildS0Matrix();
		buildS1Matrix();
	}
	
	private static void buildS0Matrix()
	{
		S0_MATRIX.add(0, BitSetUtils.getBitSetFromInt(1));
		S0_MATRIX.add(1, BitSetUtils.getBitSetFromInt(0));
		S0_MATRIX.add(2, BitSetUtils.getBitSetFromInt(3));
		S0_MATRIX.add(3, BitSetUtils.getBitSetFromInt(2));
		S0_MATRIX.add(4, BitSetUtils.getBitSetFromInt(3));
		S0_MATRIX.add(5, BitSetUtils.getBitSetFromInt(2));
		S0_MATRIX.add(6, BitSetUtils.getBitSetFromInt(1));
		S0_MATRIX.add(7, BitSetUtils.getBitSetFromInt(0));
		S0_MATRIX.add(8, BitSetUtils.getBitSetFromInt(0));
		S0_MATRIX.add(9, BitSetUtils.getBitSetFromInt(2));
		S0_MATRIX.add(10, BitSetUtils.getBitSetFromInt(1));
		S0_MATRIX.add(11, BitSetUtils.getBitSetFromInt(3));
		S0_MATRIX.add(12, BitSetUtils.getBitSetFromInt(3));
		S0_MATRIX.add(13, BitSetUtils.getBitSetFromInt(1));
		S0_MATRIX.add(14, BitSetUtils.getBitSetFromInt(3));
		S0_MATRIX.add(15, BitSetUtils.getBitSetFromInt(2));
	}
	
	private static void buildS1Matrix()
	{
		S1_MATRIX.add(0, BitSetUtils.getBitSetFromInt(0));
		S1_MATRIX.add(1, BitSetUtils.getBitSetFromInt(1));
		S1_MATRIX.add(2, BitSetUtils.getBitSetFromInt(2));
		S1_MATRIX.add(3, BitSetUtils.getBitSetFromInt(3));
		S1_MATRIX.add(4, BitSetUtils.getBitSetFromInt(2));
		S1_MATRIX.add(5, BitSetUtils.getBitSetFromInt(0));
		S1_MATRIX.add(6, BitSetUtils.getBitSetFromInt(1));
		S1_MATRIX.add(7, BitSetUtils.getBitSetFromInt(3));
		S1_MATRIX.add(8, BitSetUtils.getBitSetFromInt(3));
		S1_MATRIX.add(9, BitSetUtils.getBitSetFromInt(0));
		S1_MATRIX.add(10, BitSetUtils.getBitSetFromInt(1));
		S1_MATRIX.add(11, BitSetUtils.getBitSetFromInt(0));
		S1_MATRIX.add(12, BitSetUtils.getBitSetFromInt(2));
		S1_MATRIX.add(13, BitSetUtils.getBitSetFromInt(1));
		S1_MATRIX.add(14, BitSetUtils.getBitSetFromInt(0));
		S1_MATRIX.add(15, BitSetUtils.getBitSetFromInt(3));
	}
	
	/**
	 * 
	 * @param text of 8 bits
	 * @return permutation text of 8 bits
	 */
	public BitSet IP(BitSet text)
	{
		BitSet ret = new BitSet(BLOCK_SIZE);
		ret.set(0, text.get(3));
		ret.set(1, text.get(0));
		ret.set(2, text.get(2));
		ret.set(3, text.get(4));
		ret.set(4, text.get(6));
		ret.set(5, text.get(1));
		ret.set(6, text.get(7));
		ret.set(7, text.get(5));
		return ret;
	}
	
	/**
	 * 
	 * @param text of 8 bits
	 * @return inverse permutation text of 8 bits
	 */
	public BitSet IP_1(BitSet text)
	{
		BitSet ret = new BitSet(BLOCK_SIZE);
		ret.set(0, text.get(1));
		ret.set(1, text.get(5));
		ret.set(2, text.get(2));
		ret.set(3, text.get(0));
		ret.set(4, text.get(3));
		ret.set(5, text.get(7));
		ret.set(6, text.get(4));
		ret.set(7, text.get(6));
		return ret;
	}
	
	/**
	 * 
	 * @param bitIn of 4 bits
	 * @return bit set expanded and permuted of 8 bits
	 */
	public BitSet EP(BitSet bitIn)
	{
		BitSet ret = new BitSet(BLOCK_SIZE);
		ret.set(0, bitIn.get(3));
		ret.set(1, bitIn.get(0));
		ret.set(2, bitIn.get(1));
		ret.set(3, bitIn.get(2));
		ret.set(4, bitIn.get(1));
		ret.set(5, bitIn.get(2));
		ret.set(6, bitIn.get(3));
		ret.set(7, bitIn.get(0));
		return ret;
	}
	
	/**
	 * 
	 * @param bitIn of 10 bits
	 * @return bit set permuted of 8 bits
	 */
	public BitSet P10(BitSet bitIn)
	{
		BitSet ret = new BitSet(KEY_SIZE);
		ret.set(0, bitIn.get(2));
		ret.set(1, bitIn.get(4));
		ret.set(2, bitIn.get(1));
		ret.set(3, bitIn.get(6));
		ret.set(4, bitIn.get(3));
		ret.set(5, bitIn.get(9));
		ret.set(6, bitIn.get(0));
		ret.set(7, bitIn.get(8));
		ret.set(8, bitIn.get(7));
		ret.set(9, bitIn.get(5));
		return ret;
	}
	
	/**
	 * 
	 * @param bitsIn of 10 bits
	 * @return bit set permuted of 8 bits
	 */
	public BitSet P8(BitSet bitsIn)
	{
		BitSet ret = new BitSet(BLOCK_SIZE);
		ret.set(0, bitsIn.get(5));
		ret.set(1, bitsIn.get(2));
		ret.set(2, bitsIn.get(6));
		ret.set(3, bitsIn.get(3));
		ret.set(4, bitsIn.get(7));
		ret.set(5, bitsIn.get(4));
		ret.set(6, bitsIn.get(9));
		ret.set(7, bitsIn.get(8));
		return ret;
	}
	
	/**
	 * Performs left circular shift of 1
	 * @param bitIn of 5 bits
	 * @return bit set of 5 bits
	 */
	public BitSet LS_1(BitSet bitIn)
	{
		BitSet ret = new BitSet(BLOCK_SIZE-3);
		ret.set(0, bitIn.get(1));
		ret.set(1, bitIn.get(2));
		ret.set(2, bitIn.get(3));
		ret.set(3, bitIn.get(4));
		ret.set(4, bitIn.get(0));
		return ret;
	}
	
	/**
	 * Performs left circular shift of 2
	 * @param bitIn of 5 bits
	 * @return bit set of 5 bits
	 */
	public BitSet LS_2(BitSet bitIn)
	{
		BitSet ret = new BitSet(BLOCK_SIZE-3);
		ret.set(0, bitIn.get(2));
		ret.set(1, bitIn.get(3));
		ret.set(2, bitIn.get(4));
		ret.set(3, bitIn.get(0));
		ret.set(4, bitIn.get(1));
		return ret;
	}
	
	public BitSet S0(BitSet bitIn)
	{
		return S0_MATRIX.get(this.getIndexToBoxes(bitIn));
	}
	
	public BitSet S1(BitSet bitIn)
	{
		return S1_MATRIX.get(this.getIndexToBoxes(bitIn));
	}
	
	public int getIndexToBoxes(BitSet bitIn)
	{
		BitSet bitRow = new BitSet(2);
		bitRow.set(0, bitIn.get(0));
		bitRow.set(1, bitIn.get(3));
		
		BitSet bitCol = new BitSet(2);
		bitCol.set(0, bitIn.get(1));
		bitCol.set(1, bitIn.get(2));
		
		int row = BitSetUtils.bitSetToInt(bitRow);
		int col = BitSetUtils.bitSetToInt(bitCol);
		int index = row * MATRIX_SIZE + col;
		return index;
	}
	
	private void keyGeneration(BitSet key)
	{
		key = this.P10(key);
		BitSet leftKey = this.buildLeftKey(key);
		BitSet rightKey = this.buildRightKey(key);
		leftKey = this.LS_1(leftKey);
		rightKey = this.LS_1(rightKey);
		this.K1 = this.P8(concatLeftAndRight(leftKey, rightKey));
		
		leftKey = LS_2(leftKey);
		rightKey = LS_2(rightKey);
		this.K2 = this.P8(concatLeftAndRight(leftKey, rightKey));
	}

	private BitSet concatLeftAndRight(BitSet left, BitSet right)
	{
		BitSet concat = new BitSet(KEY_SIZE);
		int i;
		for(i=0; i < (KEY_SIZE / 2); i++)
			concat.set(i, left.get(i));
		
		for(; i < KEY_SIZE; i++)
			concat.set(i, right.get(i));
		
		return concat;
	}
	
	private BitSet buildLeftKey(BitSet key)
	{
		BitSet leftKey = new BitSet(5); 
		leftKey.set(0, key.get(0));
		leftKey.set(1, key.get(1));
		leftKey.set(2, key.get(2));
		
		leftKey.set(3, key.get(3));
		leftKey.set(4, key.get(4));
		return leftKey;
	}

	private BitSet buildRightKey(BitSet key)
	{
		BitSet rightKey = new BitSet(5); 
		rightKey.set(0, key.get(5));
		rightKey.set(1, key.get(6));
		rightKey.set(2, key.get(7));
		rightKey.set(3, key.get(8));
		rightKey.set(4, key.get(9));
		return rightKey;
	}
	
	private BitSet encode(BitSet plainText)
	{
		BitSet encoded = new BitSet(BLOCK_SIZE);
		encoded = IP(encoded);
		
		return encoded;
	}
	
	public static void main(String[] args)
	{
		Des.testS1();
	}
	
	public static void testS0()
	{
		BitSet text = new BitSet(MATRIX_SIZE);
		text = BitSetUtils.getBitSetFromInt(14);
		System.out.println("14 = "+text);
		BitSetUtils.printBitSet(text);
		
		Des des = new Des();
		System.out.println("Result = "+BitSetUtils.bitSetToInt(text));
		BitSet s0 = des.S0(text);
		System.out.println("S0 = "+s0);
		BitSetUtils.printBitSet(s0);
	}
	
	public static void testS1()
	{
		BitSet text = new BitSet(MATRIX_SIZE);
		text = BitSetUtils.getBitSetFromInt(15);
		System.out.println("15 = "+text);
		BitSetUtils.printBitSet(text);
		
		Des des = new Des();
		BitSet s1 = des.S1(text);
		System.out.println("S1 = "+s1);
		BitSetUtils.printBitSet(s1);
		System.out.println("Result = "+BitSetUtils.bitSetToInt(s1));
	}
}

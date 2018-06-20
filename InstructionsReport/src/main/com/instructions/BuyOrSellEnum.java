package com.instructions;

/**
 * Enum to represent whether an instruction is to buy or sell.
 * 
 * @author Mark Johnstone
 *
 */
public enum BuyOrSellEnum {

	BUY('b'), SELL('s');

	private char buyOrSell;

	BuyOrSellEnum(char buyorsell) {
		if (buyorsell != 'b' && buyorsell != 's') {
			System.out.println("*** comparing b and " +buyorsell + (buyorsell=='b'));
			throw new IllegalArgumentException("Invalid character supplied. Expected b or s, got:"+buyorsell);
		}
		this.buyOrSell = buyorsell;
	}
	
	public char getBuyOrSell(){
		return buyOrSell;
	}
	
	public static BuyOrSellEnum fromValue(char c){
		for(BuyOrSellEnum buyOrSellEnums : BuyOrSellEnum.values()){
			if(buyOrSellEnums.getBuyOrSell() == c){
				return buyOrSellEnums;
			}
		}
	    throw new IllegalArgumentException("Invalid character supplied. Expected b or s, got:" +c);

	}
}

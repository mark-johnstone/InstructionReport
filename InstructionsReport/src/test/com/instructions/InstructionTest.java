package com.instructions;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Test;

public class InstructionTest {
	private static final BigDecimal PRICE = new BigDecimal("1.10");
	private static final EntityEnum ENTITY_FOO = EntityEnum.FOO;
	private static final char BUY = 'b';

	private static final BigDecimal AGREED_FX = new BigDecimal("1.76");
	private static final CurrencyEnum CURRENCY_AED = CurrencyEnum.AED;

	private static final DateTime INSTRUCTION_DATE_MONDAY = new DateTime(2018, 6, 22, 0, 0, 0);
	private static final DateTime SETTLEMENT_DATE = new DateTime(2018,6,24,0,0,0);
	private static final int UNITS = 100;
	private static final BigDecimal TOTAL_USD= new BigDecimal("193.6000");

	@Test
	public void test() {
		Instruction instruction = createInstruction(ENTITY_FOO, BUY, AGREED_FX, CURRENCY_AED, INSTRUCTION_DATE_MONDAY,
				UNITS, PRICE);

		assertEquals(ENTITY_FOO, instruction.entity);
		assertEquals(BUY, instruction.buyOrSell.getBuyOrSell());
		assertEquals(AGREED_FX, instruction.getAgreedFx());
		assertEquals(CURRENCY_AED, instruction.getCurrency());
		assertEquals(INSTRUCTION_DATE_MONDAY, instruction.getInstructionDate());
		assertEquals(SETTLEMENT_DATE, instruction.getSettlementDate());
		assertEquals(PRICE, instruction.getPrice());
		assertEquals(TOTAL_USD, instruction.getTradeTotalInUSD());
	}

	private Instruction createInstruction(EntityEnum entity, char buyOrSell, BigDecimal agreedFx, CurrencyEnum currency,
			DateTime instructionDate, int units, BigDecimal price) {
		Instruction instruction = new Instruction(entity, buyOrSell, agreedFx, currency, instructionDate, units, price);

		return instruction;
	}
}

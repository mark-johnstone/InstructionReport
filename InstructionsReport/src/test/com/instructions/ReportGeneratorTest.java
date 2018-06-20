package com.instructions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

public class ReportGeneratorTest {
	private static final BigDecimal PRICE = new BigDecimal(1.00);
	private static final EntityEnum ENTITY_FOO = EntityEnum.FOO;
	private static final EntityEnum ENTITY_BAR = EntityEnum.BAR;
	private static final char BUY = 'b';
	private static final char SELL = 's';

	private static final BigDecimal AGREED_FX = new BigDecimal(1.00);
	private static final CurrencyEnum CURRENCY_AED = CurrencyEnum.AED;

	private static final DateTime INSTRUCTION_DATE_MONDAY = new DateTime().withDayOfWeek(1).withWeekOfWeekyear(24);
	private static final DateTime INSTRUCTION_DATE_SATURDAY = new DateTime().withDayOfWeek(6).withWeekOfWeekyear(24);

	private static final int UNITS = 100;

	@Test
	public void test() {
		ReportGenerator reportGenerator = new ReportGenerator(createInstructionList());
		reportGenerator.createReport();
	}

	private List<Instruction> createInstructionList() {
		List<Instruction> instructionList = new ArrayList<>();
		instructionList
				.add(new Instruction(ENTITY_FOO, BUY, AGREED_FX, CURRENCY_AED, INSTRUCTION_DATE_MONDAY, UNITS, PRICE));
		instructionList
				.add(new Instruction(ENTITY_FOO, BUY, AGREED_FX, CURRENCY_AED, INSTRUCTION_DATE_MONDAY, UNITS, PRICE));
		instructionList
				.add(new Instruction(ENTITY_BAR, BUY, AGREED_FX, CURRENCY_AED, INSTRUCTION_DATE_MONDAY, UNITS, PRICE));
		instructionList.add(
				new Instruction(ENTITY_BAR, BUY, AGREED_FX, CURRENCY_AED, INSTRUCTION_DATE_SATURDAY, UNITS, PRICE));
		instructionList.add(
				new Instruction(ENTITY_BAR, BUY, AGREED_FX, CURRENCY_AED, INSTRUCTION_DATE_SATURDAY, UNITS, PRICE));
		instructionList.add(
				new Instruction(ENTITY_FOO, BUY, AGREED_FX, CURRENCY_AED, INSTRUCTION_DATE_SATURDAY, UNITS, PRICE));
		instructionList.add(
				new Instruction(ENTITY_BAR, BUY, AGREED_FX, CURRENCY_AED, INSTRUCTION_DATE_SATURDAY, UNITS, PRICE));
		instructionList.add(
				new Instruction(ENTITY_BAR, SELL, AGREED_FX, CURRENCY_AED, INSTRUCTION_DATE_SATURDAY, UNITS, PRICE));
		return instructionList;
	}

}
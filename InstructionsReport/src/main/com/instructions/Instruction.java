package com.instructions;

import java.math.BigDecimal;

import org.joda.time.DateTime;

/**
 * Class to represent an Instruction
 * 
 * @author Mark Johnstone
 *
 */
public class Instruction {
	EntityEnum entity;
	BuyOrSellEnum buyOrSell;
	BigDecimal agreedFx;
	CurrencyEnum currency; 
	DateTime instructionDate;
	DateTime settlementDate;
	int units;
	BigDecimal price;
	BigDecimal tradeTotalInUSD;

	public Instruction(EntityEnum entity, char buyOrSell, BigDecimal agreedFx, CurrencyEnum currency,
			DateTime instructionDate, int units, BigDecimal price) {
		this.entity = entity;
		this.buyOrSell = BuyOrSellEnum.fromValue(buyOrSell);
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.units = units;
		this.price = price;

		/*
		 * Calculates the settlement date based on currency and days of week
		 * Note DateTime uses ISO definitions where 1 is Monday, 7 is Sunday.
		 */
		if ((currency.equals(CurrencyEnum.AED) || currency.equals(CurrencyEnum.SAR))) {
			if (instructionDate.getDayOfWeek() > 4 && instructionDate.getDayOfWeek() < 7) {
				this.settlementDate = instructionDate.withDayOfWeek(7);
			} else {
				this.settlementDate = instructionDate;
			}
		} else {
			if (instructionDate.getDayOfWeek() > 5) {
				this.settlementDate = instructionDate.withWeekOfWeekyear(instructionDate.weekOfWeekyear().get() + 1)
						.withDayOfWeek(1);
			}
		}

		/*
		 * Calculates the trade total in USD by multiplying agreedFx, units and
		 * price per unit
		 */
		this.tradeTotalInUSD = price.multiply(agreedFx).multiply(new BigDecimal(units));
	}

	public EntityEnum getEntity() {
		return entity;
	}

	public void setEntity(EntityEnum entity) {
		this.entity = entity;
	}

	public BuyOrSellEnum getBuyOrSell() {
		return buyOrSell;
	}

	public void setBuyOrSell(BuyOrSellEnum buyOrSell) {
		this.buyOrSell = buyOrSell;
	}

	public BigDecimal getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(BigDecimal agreedFx) {
		this.agreedFx = agreedFx;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public DateTime getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(DateTime instructionDate) {
		this.instructionDate = instructionDate;
	}

	public DateTime getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(DateTime settlementDate) {
		this.settlementDate = settlementDate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTradeTotalInUSD() {
		return tradeTotalInUSD;
	}

	public void setTradeTotalInUSD(BigDecimal tradeTotalInUSD) {
		this.tradeTotalInUSD = tradeTotalInUSD;
	}
}

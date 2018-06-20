package com.instructions;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

/**
 * Class which stores instructions within a list and prints a report of instructions per day 
 * 
 * @author Mark Johnstone
 *
 */
public class ReportGenerator {
	List<Instruction> instructionList;

	public ReportGenerator(List<Instruction> instructionList) {
		this.instructionList = instructionList;
	}

	public void createReport() {

		HashMap<EntityEnum, BigDecimal> incomingInstructions = new HashMap<>();
		HashMap<EntityEnum, BigDecimal> outgoingInstructions = new HashMap<>();

		BigDecimal totalIncoming = new BigDecimal(0);
		BigDecimal totalOutgoing = new BigDecimal(0);

		// Sort instruction list by date
		instructionList.sort(new Comparator<Instruction>() {
			@Override
			public int compare(Instruction o1, Instruction o2) {
				return (o1.getSettlementDate()).compareTo(o2.getSettlementDate());
			}
		});
		DateTime currentDateTime = instructionList.get(0).getSettlementDate();
		for (int i = 0; i < instructionList.size(); i++) {
			Instruction instructionAtIndex = instructionList.get(i);

			if (instructionAtIndex.getSettlementDate().toDate().after(currentDateTime.toDate())) {
				System.out.println("For Date " + currentDateTime.toLocalDate());
				System.out.println("Amount in USD settled incoming by entity");
				sortHashMapAndPrintOrdered(incomingInstructions);
				System.out.println("Total amount in USD settled incoming:" + totalIncoming);
				System.out.println("");
				System.out.println("Amount in USD settled outgoing:");
				sortHashMapAndPrintOrdered(outgoingInstructions);
				System.out.println("Total amount in USD settled outgoing:" + totalOutgoing);
				System.out.println("");
				
				currentDateTime = instructionAtIndex.getSettlementDate();
				incomingInstructions.clear();
				outgoingInstructions.clear();
				totalIncoming = new BigDecimal(0);
				totalOutgoing = new BigDecimal(0);
			}

			// If the entity is already in the associated incoming or outgoing
			// Hashmap then add the new
			// instruction trade total to the running total for that entity
			// otherwise add it. Update the total incoming for all entities.
			if (instructionAtIndex.getBuyOrSell().getBuyOrSell() == 'b') {
				updateInstructionMap(outgoingInstructions, instructionAtIndex);
				totalOutgoing = totalOutgoing.add(instructionAtIndex.getTradeTotalInUSD());
			} else {
				updateInstructionMap(incomingInstructions, instructionAtIndex);
				totalIncoming = totalIncoming.add(instructionAtIndex.getTradeTotalInUSD());
			}
		}

		System.out.println("For Date " + currentDateTime.toLocalDate());
		System.out.println("Amount in USD settled incoming by entity");
		sortHashMapAndPrintOrdered(incomingInstructions);
		System.out.println("Total amount in USD settled incoming:" + totalIncoming);
		System.out.println("");
		System.out.println("Amount in USD settled outgoing:");
		sortHashMapAndPrintOrdered(outgoingInstructions);
		System.out.println("Total amount in USD settled outgoing:" + totalOutgoing);
		System.out.println("");
	}

	/*
	 * Adds instruction entity and total trade in USD to hash map. If key
	 * already exists for that instruction the total trade in USD is added to
	 * the existing value for the pair.
	 */
	private HashMap<EntityEnum, BigDecimal> updateInstructionMap(HashMap<EntityEnum, BigDecimal> instructionMap,
			Instruction instruction) {
		EntityEnum entityAtIndex = instruction.getEntity();

		if (instructionMap.containsKey(entityAtIndex)) {
			instructionMap.put(entityAtIndex, instructionMap.get(entityAtIndex).add(instruction.getTradeTotalInUSD()));
		} else {
			instructionMap.put(entityAtIndex, instruction.getTradeTotalInUSD());
		}

		return instructionMap;
	}

	/*
	 * Takes unsorted hash map which is converted to a List and sorted based on
	 * map pair value. The Sorted List is then printed to console.
	 */
	private void sortHashMapAndPrintOrdered(HashMap<EntityEnum, BigDecimal> unsortedMap) {

		List<Map.Entry<EntityEnum, BigDecimal>> list = new LinkedList<Map.Entry<EntityEnum, BigDecimal>>(
				unsortedMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<EntityEnum, BigDecimal>>() {
			@Override
			public int compare(Map.Entry<EntityEnum, BigDecimal> o1, Map.Entry<EntityEnum, BigDecimal> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		for (Map.Entry<EntityEnum, BigDecimal> entry : list) {
			System.out.println("Entity:" + entry.getKey() + " settled " + entry.getValue() + " USD");
		}
	}
}

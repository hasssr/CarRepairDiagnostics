package com.ubiquisoft.evaluation.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

	private String year;
	private String make;
	private String model;

	private List<Part> parts;

	public Map<PartType, Integer> getMissingPartsMap() {

		final Map<PartType, Integer> missingParts = getAllParts();

		parts.stream().forEach(part -> {
			if(missingParts.containsKey(part.getType())) {
				Integer oldValue = missingParts.get(part.getType());
				if(oldValue == 1) {
					missingParts.remove(part.getType());
				} else {
					Integer newValue = oldValue - 1;
					missingParts.replace(part.getType(), oldValue, newValue);
				}
			}
		});

		return Collections.unmodifiableMap(missingParts);
	}

	private Map<PartType, Integer> getAllParts() {
		final Map<PartType, Integer> allParts = new HashMap<>();
		allParts.put(PartType.ENGINE, 1);
		allParts.put(PartType.ELECTRICAL, 1);
		allParts.put(PartType.FUEL_FILTER, 1);
		allParts.put(PartType.OIL_FILTER, 1);
		allParts.put(PartType.TIRE, 4);

		return allParts;
	}

	@Override
	public String toString() {
		return "Car{" +
				       "year='" + year + '\'' +
				       ", make='" + make + '\'' +
				       ", model='" + model + '\'' +
				       ", parts=" + parts +
				       '}';
	}

	/* --------------------------------------------------------------------------------------------------------------- */
	/*  Getters and Setters *///region
	/* --------------------------------------------------------------------------------------------------------------- */

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	/* --------------------------------------------------------------------------------------------------------------- */
	/*  Getters and Setters End *///endregion
	/* --------------------------------------------------------------------------------------------------------------- */

}

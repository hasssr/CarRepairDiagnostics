package com.ubiquisoft.evaluation.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

	private final Map<PartType, Integer> missingParts = Stream.of(new Object[][] {
			{PartType.ENGINE, 1},
			{PartType.ELECTRICAL, 1},
			{PartType.FUEL_FILTER, 1},
			{PartType.OIL_FILTER, 1},
			{PartType.TIRE, 4}
	}).collect(Collectors.toMap(data -> (PartType) data[0], data -> (Integer) data[1]));

	private String year;
	private String make;
	private String model;
	private List<Part> parts;

	public Map<PartType, Integer> getMissingPartsMap() {

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

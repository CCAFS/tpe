/*****************************************************************
 * This file is part of CCAFS Target Population Environments Identification Platform.
 * CCAFS TPE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * CCAFS TPE Identification Platform is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with CCAFS TPE Identification Platform. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/
package org.cgiar.dapa.ccafs.tpe.jqgrid;

public class SoilGrid implements Grid {
	private String country;
	private String state;
	private String municipality;
	private String station;
	private Float ph;
	private Float clay;
	private Float sand;
	private Float silt;
	private Float bulkyDensity;
	/**
	 * Available Soil Moiture
	 */
	private Float asm;
	/**
	 * Available Soil Moiture at Wilting Point
	 */
	private Float asmWiltPoit;
	/**
	 * Available Soil Moiture at Field Capacity
	 */
	private Float asmFieldCapacity;
	private Float organicCarbon;
	private Float depth;
	private Integer taxonomy;
	private Float cationExchange;
	private Double longitude;
	private Double latitude;

	// private int id;

	public SoilGrid() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SoilGrid(String country, String state, String municipality,
			String station, Float ph, Float clay, Float sand, Float silt,
			Float bulkyDensity, Float asm, Float asmWiltPoit,
			Float asmFieldCapacity, Float organicCarbon, Float depth,
			Integer taxonomy, Float cationExchange, Double longitude,
			Double latitude) {
		super();
		this.country = country;
		this.state = state;
		this.municipality = municipality;
		this.station = station;
		this.ph = ph;
		this.clay = clay;
		this.sand = sand;
		this.silt = silt;
		this.bulkyDensity = bulkyDensity;
		this.asm = asm;
		this.asmWiltPoit = asmWiltPoit;
		this.asmFieldCapacity = asmFieldCapacity;
		this.organicCarbon = organicCarbon;
		this.depth = depth;
		this.taxonomy = taxonomy;
		this.cationExchange = cationExchange;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public Float getPh() {
		return ph;
	}

	public void setPh(Float ph) {
		this.ph = ph;
	}

	public Float getClay() {
		return clay;
	}

	public void setClay(Float clay) {
		this.clay = clay;
	}

	public Float getSand() {
		return sand;
	}

	public void setSand(Float sand) {
		this.sand = sand;
	}

	public Float getSilt() {
		return silt;
	}

	public void setSilt(Float silt) {
		this.silt = silt;
	}

	public Float getBulkyDensity() {
		return bulkyDensity;
	}

	public void setBulkyDensity(Float bulkyDensity) {
		this.bulkyDensity = bulkyDensity;
	}

	public Float getAsm() {
		return asm;
	}

	public void setAsm(Float asm) {
		this.asm = asm;
	}

	public Float getAsmWiltPoit() {
		return asmWiltPoit;
	}

	public void setAsmWiltPoit(Float asmWiltPoit) {
		this.asmWiltPoit = asmWiltPoit;
	}

	public Float getAsmFieldCapacity() {
		return asmFieldCapacity;
	}

	public void setAsmFieldCapacity(Float asmFieldCapacity) {
		this.asmFieldCapacity = asmFieldCapacity;
	}

	public Float getOrganicCarbon() {
		return organicCarbon;
	}

	public void setOrganicCarbon(Float organicCarbon) {
		this.organicCarbon = organicCarbon;
	}

	public Float getDepth() {
		return depth;
	}

	public void setDepth(Float depth) {
		this.depth = depth;
	}

	public Integer getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(Integer taxonomy) {
		this.taxonomy = taxonomy;
	}

	public Float getCationExchange() {
		return cationExchange;
	}

	public void setCationExchange(Float cationExchange) {
		this.cationExchange = cationExchange;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {

		return "Country: " + getCountry() + ", State: " + getState()
				+ ", Municipio: " + getMunicipality() + ", Station: " + getStation()
				+ ", Sand: " + getSand() + ", PH: " + getPh()
				+ ", Bulky Density: " + getBulkyDensity() + ", Silt: "
				+ getSilt() + ", Clay: " + getClay() + ", Caation Exchange: "
				+ getCationExchange() + ", Taxonomy: " + getTaxonomy()
				+ ", Depth: " + getDepth() + ", ASM: " + getAsm() + ", ASMFC: "
				+ getAsmFieldCapacity() + ", ASMWP: " + getAsmWiltPoit();
	}

}

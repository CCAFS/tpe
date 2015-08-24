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

import java.util.regex.Pattern;

public class ClimateGrid implements Grid {
	private String country;
	private String state;
	private String municipality;
	private String station;
	private Float jan;
	private Float feb;
	private Float mar;
	private Float apr;
	private Float may;
	private Float jun;
	private Float jul;
	private Float aug;
	private Float sep;
	private Float oct;
	private Float nov;
	private Float dec;
	private Double lon;
	private Double lat;

	// private int id;

	public ClimateGrid() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClimateGrid(String country, String state, String municipality,
			String station, Float jan, Float feb, Float mar, Float apr,
			Float may, Float jun, Float jul, Float aug, Float sep, Float oct,
			Float nov, Float dec, Double lon, Double lat) {
		super();
		this.country = country;
		this.state = state;
		this.municipality = municipality;
		this.station = station;
		this.jan = jan;
		this.feb = feb;
		this.mar = mar;
		this.apr = apr;
		this.may = may;
		this.jun = jun;
		this.jul = jul;
		this.aug = aug;
		this.sep = sep;
		this.oct = oct;
		this.nov = nov;
		this.dec = dec;
		this.lon = lon;
		this.lat = lat;
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
		if (station != null)
			return station.replaceAll(Pattern.quote("_"), " ");
		return null;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public Float getJan() {
		return jan;
	}

	public void setJan(Float jan) {
		this.jan = jan;
	}

	public Float getFeb() {
		return feb;
	}

	public void setFeb(Float feb) {
		this.feb = feb;
	}

	public Float getMar() {
		return mar;
	}

	public void setMar(Float mar) {
		this.mar = mar;
	}

	public Float getApr() {
		return apr;
	}

	public void setApr(Float apr) {
		this.apr = apr;
	}

	public Float getMay() {
		return may;
	}

	public void setMay(Float may) {
		this.may = may;
	}

	public Float getJun() {
		return jun;
	}

	public void setJun(Float jun) {
		this.jun = jun;
	}

	public Float getJul() {
		return jul;
	}

	public void setJul(Float jul) {
		this.jul = jul;
	}

	public Float getAug() {
		return aug;
	}

	public void setAug(Float aug) {
		this.aug = aug;
	}

	public Float getSep() {
		return sep;
	}

	public void setSep(Float sep) {
		this.sep = sep;
	}

	public Float getOct() {
		return oct;
	}

	public void setOct(Float oct) {
		this.oct = oct;
	}

	public Float getNov() {
		return nov;
	}

	public void setNov(Float nov) {
		this.nov = nov;
	}

	public Float getDec() {
		return dec;
	}

	public void setDec(Float dec) {
		this.dec = dec;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {

		return "Country: " + getCountry() + ", State: " + getState()
				+ ", Mun: " + getMunicipality() + ", Station: " + getStation()
				+ ", Jan: " + getJan() + ", Feb: " + getFeb() + ", Mar: "
				+ getMar() + ", Apr: " + getApr() + ", May: " + getMay()
				+ ", Jun: " + getJun() + ", Jul: " + getJul() + ", Aug: "
				+ getAug() + ", Sep: " + getSep() + ", Oct: " + getOct()
				+ ", Nov: " + getNov() + ", Dec: " + getDec();
	}

}

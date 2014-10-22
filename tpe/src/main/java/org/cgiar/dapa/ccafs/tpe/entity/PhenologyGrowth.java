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
package org.cgiar.dapa.ccafs.tpe.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class represents the crop cultivar phenology and growth properties in th
 * ecrop model simulation
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "phenology_growth")
@AttributeOverride(name = "id", column = @Column(name = "result_id"))
public class PhenologyGrowth extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5524382042651219445L;

	/**
	 * The crop cultivar that relates to this record
	 */
	private Cultivar cultivar;
	/**
	 * The crop model that is attached to this record
	 */
	private Model model;
	/**
	 * The region that relates to this record
	 */
	private Region region;
	/**
	 * The station that relates to this record.
	 */
	private Station station;
	/**
	 * Number of days between Emergency to Panicle initiation
	 */
	private Integer totalDaysEmeIpa;
	/**
	 * Number of days between Emergency to Flowering
	 */
	private Integer totalDaysEmeFlo;

	/**
	 * Number of days between Emergency to Maturation
	 */
	private Integer totalDaysEmeMad;
	/**
	 * Number of days between Emergency to Panicle Initiation
	 */
	private Integer numDaysEmeIpa;
	/**
	 * Number of days between Panicle Initiation to Flowering
	 */
	private Integer numDaysIPaFlo;
	/**
	 * Number of days between Flowering to Maduration
	 */
	private Integer numDaysFloMad;
	/**
	 * Sum of minimum temperature between Emergency to Panicle Initiation
	 */
	private Float sumTminEmeIpa;
	/**
	 * Sum of minimum temperature between Panicle Initiation to Flowering
	 */
	private Float sumTminIPaFlo;
	/**
	 * Sum of minimum temperature between Flowering to Maturation
	 */
	private Float sumTminFloMad;
	/**
	 * Higer_ Minimum temperature between Emergency to Panicle Initiation
	 */
	private Float maxTminEmeIpa;

	/**
	 * Higer_ Minimum temperature between Panicle Initiation to Flowering
	 */
	private Float maxTminIPaFlo;
	/**
	 * Higer_ Minimum temperature between Flowering to Maturation
	 */
	private Float maxTminFloMad;
	/**
	 * Sum of maximum temperature between Emergency to Panicle Initiation
	 */
	private Float sumTmaxEmeIpa;
	/**
	 * Sum of maximum temperature between Panicle Initiation to Flowering
	 */
	private Float sumTmaxIPaFlo;

	/**
	 * Sum of maximum temperature between Flowering to Maturation
	 */
	private Float sumTmaxFloMad;
	/**
	 * Higer_ Maximum temperature between Emergency to Panicle Initiation
	 */
	private Float maxTmaxEmeIpa;

	/**
	 * Higer_ Maximum temperature between Panicle Initiation to Flowering
	 */
	private Float maxTmaxIPaFlo;

	/**
	 * Higer_ Maximum temperature between Flowering to Maturation
	 */
	private Float maxTmaxFloMad;

	/**
	 * Total rainfall between Emergency to Panicle Initiation
	 */
	private Float sumRainEmeIpa;

	/**
	 * Total rainfall between Panicle Initiation to Flowering
	 */
	private Float sumRainIPaFlo;
	/**
	 * Total rainfall between Flowering to Maturation
	 */
	private Float sumRainFloMad;

	/**
	 * Nitrogen fraction in leaves between Emergency to Panicle Initiation
	 */
	private Float nflvEmeIpa;
	/**
	 * Nitrogen fraction in leaves between Panicle Initiation to Flowering
	 */
	private Float nflvIPaFlo;
	/**
	 * Nitrogen fraction in leaves between Flowering to Maturation
	 */
	private Float nflvFloMad;

	/**
	 * Maximum specific leaf area between Emergency to Panicle Initiation
	 */
	private Float maxSlaEmeIpa;

	/**
	 * Maximum specific leaf area between Panicle Initiation to Flowering
	 */
	private Float maxSlaIPaFlo;

	/**
	 * Maximum specific leaf area between Flowering to Maturation
	 */
	private Float maxSlaFloMad;

	/**
	 * Maximum number of spikelets between Emergency to Panicle Initiation
	 */
	private Integer maxNspEmeIpa;
	/**
	 * Maximum number of spikelets between Panicle Initiation to Flowering
	 */
	private Integer maxNspIPaFlo;
	/**
	 * Maximum number of spikelets between Flowering to Maturation
	 */
	private Integer maxNspFloMad;
	/**
	 * Maximum leaf area index between Emergency to Panicle Initiation
	 */
	private Float maxLaiEmeIpa;
	/**
	 * Maximum leaf area index between Panicle Initiation to Flowering
	 */
	private Float maxLaiIPaFlo;
	/**
	 * Maximum leaf area index between Flowering to Maturation
	 */
	private Float maxLaiFloMad;
	/**
	 * Total dry matter between Emergency to Panicle Initiation
	 */
	private Float maxWagtEmeIpa;
	/**
	 * Total dry matter between Panicle Initiation to Flowering
	 */
	private Float maxWagtIPaFlo;

	/**
	 * Total dry matter between Flowering to Maturation
	 */
	private Float maxWagtFloMad;
	/**
	 * Total dry weight of stem between Emergency to Panicle Initiation
	 */
	private Float maxWstEmeIpa;
	/**
	 * Total dry weight of stem between Panicle Initiation to Flowering
	 */
	private Float maxWstIPaFlo;
	/**
	 * Total dry weight of stem between Flowering to Maturation
	 */
	private Float maxWstFloMad;
	/**
	 * Total dry weight of green leaves between Emergency to Panicle Initiation
	 */
	private Float maxWlvgEmeIpa;
	/**
	 * Total dry weight of green leaves between Panicle Initiation to Flowering
	 */
	private Float maxWlvgIPaFlo;
	/**
	 * Total dry weight of green leaves between Flowering to Maturation
	 */
	private Float maxWlvgFloMad;
	/**
	 * Total dry weight storage organs between Emergency to Panicle Initiation
	 */
	private Float maxWsoEmeIpa;
	/**
	 * Total dry weight storage organs between Panicle Initiation to Flowering
	 */
	private Float maxWsoIPaFlo;
	/**
	 * Total dry weight storage organs between Flowering to Maturation
	 */
	private Float maxWsoFloMad;
	/**
	 * Total actual transpiration between Emergency to Panicle Initiation
	 */
	private Float sumTrwEmeIpa;
	/**
	 * Total actual transpiration between Panicle Initiation to Flowering
	 */
	private Float sumTrwIPaFlo;
	/**
	 * Total actual transpiration between Flowering to Maturation
	 */
	private Float sumTrwFloMad;
	/**
	 * Cumulated reference evapotranspiration
	 */
	private Float etdcum;
	/**
	 * Cumulated potential evaporation
	 */
	private Float evsccum;
	/**
	 * Cumulated potential transpiration
	 */
	private Float trccum;
	/**
	 * Dry weight of rough rice
	 */
	private Float wrr14;
	/**
	 * Cumulated dry weight storage organs
	 */
	private Float wso;
	/**
	 * Cumulated dry matter
	 */
	private Float wagt;
	/**
	 * Float Cumulative amount of radiation absorbed
	 */
	private Float PARCUM;
	/**
	 * Temp Float erature sum for phenological development
	 */
	private Float TS;

	/**
	 * Maximum temperature sum
	 */
	private Float TMAXC;
	/**
	 * Minimum temperature sum
	 */
	private Float TMINC;
	/**
	 * Average temperature (Total avera temperature)
	 */
	private Float TAVERC;
	/**
	 * Cumulative amount of rainfall
	 */
	private Float raincum;
	/**
	 * Cumulative amount of irrigation
	 */
	private Float ircum;
	/**
	 * Cumulative runoff
	 */
	private Float runofcum;

	/**
	 * Cumulative actual transpiration
	 */
	private Float trwcum;

	/**
	 * Cumulative actual evaporation
	 */
	private Float evswcum;

	/**
	 * Cumulative outflow from deepest soil layer
	 */
	private Float draicum;

	/**
	 * Emergency day
	 */
	private Float emd;

	/**
	 * Days after emergency
	 */
	private Float dae;
	/**
	 * The texture that relates to this record
	 */
	private Soil soil;
	/**
	 * The window sowing that relates to this record
	 */
	private WindowSowing window;
	/**
	 * The scenario (rainfed, irrigated, etc)
	 */
	private Scenario scenario;

	@ManyToOne(targetEntity = Cultivar.class)
	@JoinColumn(name = "cultivar_id", referencedColumnName = "cultivar_id")
	public Cultivar getCultivar() {
		return cultivar;
	}

	public void setCultivar(Cultivar cultivar) {
		this.cultivar = cultivar;
	}

	@ManyToOne(targetEntity = Model.class)
	@JoinColumn(name = "model_id", referencedColumnName = "model_id")
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	@ManyToOne(targetEntity = Region.class)
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@ManyToOne(targetEntity = Station.class)
	@JoinColumn(name = "station_id", referencedColumnName = "station_id")
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@Column(name = "total_days_eme_ipa")
	public Integer getTotalDaysEmeIpa() {
		return totalDaysEmeIpa;
	}

	public void setTotalDaysEmeIpa(Integer totalDaysEmeIpa) {
		this.totalDaysEmeIpa = totalDaysEmeIpa;
	}

	@Column(name = "total_days_eme_flo")
	public Integer getTotalDaysEmeFlo() {
		return totalDaysEmeFlo;
	}

	public void setTotalDaysEmeFlo(Integer totalDaysEmeFlo) {
		this.totalDaysEmeFlo = totalDaysEmeFlo;
	}

	@Column(name = "total_days_eme_mad")
	public Integer getTotalDaysEmeMad() {
		return totalDaysEmeMad;
	}

	public void setTotalDaysEmeMad(Integer totalDaysEmeMad) {
		this.totalDaysEmeMad = totalDaysEmeMad;
	}

	@Column(name = "num_days_eme_ipa")
	public Integer getNumDaysEmeIpa() {
		return numDaysEmeIpa;
	}

	public void setNumDaysEmeIpa(Integer numDaysEmeIpa) {
		this.numDaysEmeIpa = numDaysEmeIpa;
	}

	@Column(name = "num_days_iPa_flo")
	public Integer getNumDaysIPaFlo() {
		return numDaysIPaFlo;
	}

	public void setNumDaysIPaFlo(Integer numDaysIPaFlo) {
		this.numDaysIPaFlo = numDaysIPaFlo;
	}

	@Column(name = "num_days_flo_mad")
	public Integer getNumDaysFloMad() {
		return numDaysFloMad;
	}

	public void setNumDaysFloMad(Integer numDaysFloMad) {
		this.numDaysFloMad = numDaysFloMad;
	}

	@Column(name = "sum_tmin_eme_ipa")
	public Float getSumTminEmeIpa() {
		return sumTminEmeIpa;
	}

	public void setSumTminEmeIpa(Float sumTminEmeIpa) {
		this.sumTminEmeIpa = sumTminEmeIpa;
	}

	@Column(name = "sum_tmin_ipa_flo")
	public Float getSumTminIPaFlo() {
		return sumTminIPaFlo;
	}

	public void setSumTminIPaFlo(Float sumTminIPaFlo) {
		this.sumTminIPaFlo = sumTminIPaFlo;
	}

	@Column(name = "sum_tmin_flo_mad")
	public Float getSumTminFloMad() {
		return sumTminFloMad;
	}

	public void setSumTminFloMad(Float sumTminFloMad) {
		this.sumTminFloMad = sumTminFloMad;
	}

	@Column(name = "max_tmin_eme_ipa")
	public Float getMaxTminEmeIpa() {
		return maxTminEmeIpa;
	}

	public void setMaxTminEmeIpa(Float maxTminEmeIpa) {
		this.maxTminEmeIpa = maxTminEmeIpa;
	}

	@Column(name = "max_tmin_ipa_flo")
	public Float getMaxTminIPaFlo() {
		return maxTminIPaFlo;
	}

	public void setMaxTminIPaFlo(Float maxTminIPaFlo) {
		this.maxTminIPaFlo = maxTminIPaFlo;
	}

	@Column(name = "max_tmin_flo_mad")
	public Float getMaxTminFloMad() {
		return maxTminFloMad;
	}

	public void setMaxTminFloMad(Float maxTminFloMad) {
		this.maxTminFloMad = maxTminFloMad;
	}

	@Column(name = "sum_tmax_eme_ipa")
	public Float getSumTmaxEmeIpa() {
		return sumTmaxEmeIpa;
	}

	public void setSumTmaxEmeIpa(Float sumTmaxEmeIpa) {
		this.sumTmaxEmeIpa = sumTmaxEmeIpa;
	}

	@Column(name = "sum_tmax_ipa_flo")
	public Float getSumTmaxIPaFlo() {
		return sumTmaxIPaFlo;
	}

	public void setSumTmaxIPaFlo(Float sumTmaxIPaFlo) {
		this.sumTmaxIPaFlo = sumTmaxIPaFlo;
	}

	@Column(name = "sum_tmax_flo_mad")
	public Float getSumTmaxFloMad() {
		return sumTmaxFloMad;
	}

	public void setSumTmaxFloMad(Float sumTmaxFloMad) {
		this.sumTmaxFloMad = sumTmaxFloMad;
	}

	@Column(name = "max_tmax_eme_ipa")
	public Float getMaxTmaxEmeIpa() {
		return maxTmaxEmeIpa;
	}

	public void setMaxTmaxEmeIpa(Float maxTmaxEmeIpa) {
		this.maxTmaxEmeIpa = maxTmaxEmeIpa;
	}

	@Column(name = "max_tmax_ipa_flo")
	public Float getMaxTmaxIPaFlo() {
		return maxTmaxIPaFlo;
	}

	public void setMaxTmaxIPaFlo(Float maxTmaxIPaFlo) {
		this.maxTmaxIPaFlo = maxTmaxIPaFlo;
	}

	@Column(name = "max_tmax_flo_mad")
	public Float getMaxTmaxFloMad() {
		return maxTmaxFloMad;
	}

	public void setMaxTmaxFloMad(Float maxTmaxFloMad) {
		this.maxTmaxFloMad = maxTmaxFloMad;
	}

	@Column(name = "sum_rain_eme_ipa")
	public Float getSumRainEmeIpa() {
		return sumRainEmeIpa;
	}

	public void setSumRainEmeIpa(Float sumRainEmeIpa) {
		this.sumRainEmeIpa = sumRainEmeIpa;
	}

	@Column(name = "sum_rain_ipa_flo")
	public Float getSumRainIPaFlo() {
		return sumRainIPaFlo;
	}

	public void setSumRainIPaFlo(Float sumRainIPaFlo) {
		this.sumRainIPaFlo = sumRainIPaFlo;
	}

	@Column(name = "sum_rain_flo_mad")
	public Float getSumRainFloMad() {
		return sumRainFloMad;
	}

	public void setSumRainFloMad(Float sumRainFloMad) {
		this.sumRainFloMad = sumRainFloMad;
	}

	@Column(name = "nflv_eme_ipa")
	public Float getNflvEmeIpa() {
		return nflvEmeIpa;
	}

	public void setNflvEmeIpa(Float nflvEmeIpa) {
		this.nflvEmeIpa = nflvEmeIpa;
	}

	@Column(name = "nflv_ipa_flo")
	public Float getNflvIPaFlo() {
		return nflvIPaFlo;
	}

	public void setNflvIPaFlo(Float nflvIPaFlo) {
		this.nflvIPaFlo = nflvIPaFlo;
	}

	@Column(name = "nflv_flo_mad")
	public Float getNflvFloMad() {
		return nflvFloMad;
	}

	public void setNflvFloMad(Float nflvFloMad) {
		this.nflvFloMad = nflvFloMad;
	}

	@Column(name = "max_sla_eme_ipa")
	public Float getMaxSlaEmeIpa() {
		return maxSlaEmeIpa;
	}

	public void setMaxSlaEmeIpa(Float maxSlaEmeIpa) {
		this.maxSlaEmeIpa = maxSlaEmeIpa;
	}

	public Float getMaxSlaIPaFlo() {
		return maxSlaIPaFlo;
	}

	public void setMaxSlaIPaFlo(Float maxSlaIPaFlo) {
		this.maxSlaIPaFlo = maxSlaIPaFlo;
	}

	public Float getMaxSlaFloMad() {
		return maxSlaFloMad;
	}

	public void setMaxSlaFloMad(Float maxSlaFloMad) {
		this.maxSlaFloMad = maxSlaFloMad;
	}

	public Integer getMaxNspEmeIpa() {
		return maxNspEmeIpa;
	}

	public void setMaxNspEmeIpa(Integer maxNspEmeIpa) {
		this.maxNspEmeIpa = maxNspEmeIpa;
	}

	public Integer getMaxNspIPaFlo() {
		return maxNspIPaFlo;
	}

	public void setMaxNspIPaFlo(Integer maxNspIPaFlo) {
		this.maxNspIPaFlo = maxNspIPaFlo;
	}

	public Integer getMaxNspFloMad() {
		return maxNspFloMad;
	}

	public void setMaxNspFloMad(Integer maxNspFloMad) {
		this.maxNspFloMad = maxNspFloMad;
	}

	public Float getMaxLaiEmeIpa() {
		return maxLaiEmeIpa;
	}

	public void setMaxLaiEmeIpa(Float maxLaiEmeIpa) {
		this.maxLaiEmeIpa = maxLaiEmeIpa;
	}

	public Float getMaxLaiIPaFlo() {
		return maxLaiIPaFlo;
	}

	public void setMaxLaiIPaFlo(Float maxLaiIPaFlo) {
		this.maxLaiIPaFlo = maxLaiIPaFlo;
	}

	public Float getMaxLaiFloMad() {
		return maxLaiFloMad;
	}

	public void setMaxLaiFloMad(Float maxLaiFloMad) {
		this.maxLaiFloMad = maxLaiFloMad;
	}

	public Float getMaxWagtEmeIpa() {
		return maxWagtEmeIpa;
	}

	public void setMaxWagtEmeIpa(Float maxWagtEmeIpa) {
		this.maxWagtEmeIpa = maxWagtEmeIpa;
	}

	public Float getMaxWagtIPaFlo() {
		return maxWagtIPaFlo;
	}

	public void setMaxWagtIPaFlo(Float maxWagtIPaFlo) {
		this.maxWagtIPaFlo = maxWagtIPaFlo;
	}

	public Float getMaxWagtFloMad() {
		return maxWagtFloMad;
	}

	public void setMaxWagtFloMad(Float maxWagtFloMad) {
		this.maxWagtFloMad = maxWagtFloMad;
	}

	public Float getMaxWstEmeIpa() {
		return maxWstEmeIpa;
	}

	public void setMaxWstEmeIpa(Float maxWstEmeIpa) {
		this.maxWstEmeIpa = maxWstEmeIpa;
	}

	public Float getMaxWstIPaFlo() {
		return maxWstIPaFlo;
	}

	public void setMaxWstIPaFlo(Float maxWstIPaFlo) {
		this.maxWstIPaFlo = maxWstIPaFlo;
	}

	public Float getMaxWstFloMad() {
		return maxWstFloMad;
	}

	public void setMaxWstFloMad(Float maxWstFloMad) {
		this.maxWstFloMad = maxWstFloMad;
	}

	public Float getMaxWlvgEmeIpa() {
		return maxWlvgEmeIpa;
	}

	public void setMaxWlvgEmeIpa(Float maxWlvgEmeIpa) {
		this.maxWlvgEmeIpa = maxWlvgEmeIpa;
	}

	public Float getMaxWlvgIPaFlo() {
		return maxWlvgIPaFlo;
	}

	public void setMaxWlvgIPaFlo(Float maxWlvgIPaFlo) {
		this.maxWlvgIPaFlo = maxWlvgIPaFlo;
	}

	public Float getMaxWlvgFloMad() {
		return maxWlvgFloMad;
	}

	public void setMaxWlvgFloMad(Float maxWlvgFloMad) {
		this.maxWlvgFloMad = maxWlvgFloMad;
	}

	public Float getMaxWsoEmeIpa() {
		return maxWsoEmeIpa;
	}

	public void setMaxWsoEmeIpa(Float maxWsoEmeIpa) {
		this.maxWsoEmeIpa = maxWsoEmeIpa;
	}

	public Float getMaxWsoIPaFlo() {
		return maxWsoIPaFlo;
	}

	public void setMaxWsoIPaFlo(Float maxWsoIPaFlo) {
		this.maxWsoIPaFlo = maxWsoIPaFlo;
	}

	public Float getMaxWsoFloMad() {
		return maxWsoFloMad;
	}

	public void setMaxWsoFloMad(Float maxWsoFloMad) {
		this.maxWsoFloMad = maxWsoFloMad;
	}

	public Float getSumTrwEmeIpa() {
		return sumTrwEmeIpa;
	}

	public void setSumTrwEmeIpa(Float sumTrwEmeIpa) {
		this.sumTrwEmeIpa = sumTrwEmeIpa;
	}

	public Float getSumTrwIPaFlo() {
		return sumTrwIPaFlo;
	}

	public void setSumTrwIPaFlo(Float sumTrwIPaFlo) {
		this.sumTrwIPaFlo = sumTrwIPaFlo;
	}

	public Float getSumTrwFloMad() {
		return sumTrwFloMad;
	}

	public void setSumTrwFloMad(Float sumTrwFloMad) {
		this.sumTrwFloMad = sumTrwFloMad;
	}

	public Float getEtdcum() {
		return etdcum;
	}

	public void setEtdcum(Float etdcum) {
		this.etdcum = etdcum;
	}

	public Float getEvsccum() {
		return evsccum;
	}

	public void setEvsccum(Float evsccum) {
		this.evsccum = evsccum;
	}

	public Float getTrccum() {
		return trccum;
	}

	public void setTrccum(Float trccum) {
		this.trccum = trccum;
	}

	public Float getWrr14() {
		return wrr14;
	}

	public void setWrr14(Float wrr14) {
		this.wrr14 = wrr14;
	}

	public Float getWso() {
		return wso;
	}

	public void setWso(Float wso) {
		this.wso = wso;
	}

	public Float getWagt() {
		return wagt;
	}

	public void setWagt(Float wagt) {
		this.wagt = wagt;
	}

	public Float getPARCUM() {
		return PARCUM;
	}

	public void setPARCUM(Float pARCUM) {
		PARCUM = pARCUM;
	}

	public Float getTS() {
		return TS;
	}

	public void setTS(Float tS) {
		TS = tS;
	}

	public Float getTMAXC() {
		return TMAXC;
	}

	public void setTMAXC(Float tMAXC) {
		TMAXC = tMAXC;
	}

	public Float getTMINC() {
		return TMINC;
	}

	public void setTMINC(Float tMINC) {
		TMINC = tMINC;
	}

	public Float getTAVERC() {
		return TAVERC;
	}

	public void setTAVERC(Float tAVERC) {
		TAVERC = tAVERC;
	}

	public Float getRaincum() {
		return raincum;
	}

	public void setRaincum(Float raincum) {
		this.raincum = raincum;
	}

	public Float getIrcum() {
		return ircum;
	}

	public void setIrcum(Float ircum) {
		this.ircum = ircum;
	}

	public Float getRunofcum() {
		return runofcum;
	}

	public void setRunofcum(Float runofcum) {
		this.runofcum = runofcum;
	}

	public Float getTrwcum() {
		return trwcum;
	}

	public void setTrwcum(Float trwcum) {
		this.trwcum = trwcum;
	}

	public Float getEvswcum() {
		return evswcum;
	}

	public void setEvswcum(Float evswcum) {
		this.evswcum = evswcum;
	}

	public Float getDraicum() {
		return draicum;
	}

	public void setDraicum(Float draicum) {
		this.draicum = draicum;
	}

	public Float getEmd() {
		return emd;
	}

	public void setEmd(Float emd) {
		this.emd = emd;
	}

	public Float getDae() {
		return dae;
	}

	public void setDae(Float dae) {
		this.dae = dae;
	}

	public Soil getSoil() {
		return soil;
	}

	public void setSoil(Soil soil) {
		this.soil = soil;
	}

	public WindowSowing getWindow() {
		return window;
	}

	public void setWindow(WindowSowing window) {
		this.window = window;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

}

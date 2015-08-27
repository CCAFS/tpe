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
package org.cgiar.dapa.ccafs.tpe.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.entity.Climate;
import org.cgiar.dapa.ccafs.tpe.jqgrid.ClimateGrid;
import org.cgiar.dapa.ccafs.tpe.jqgrid.ColModel;
import org.cgiar.dapa.ccafs.tpe.jqgrid.ColNames;
import org.cgiar.dapa.ccafs.tpe.jqgrid.Grid;
import org.cgiar.dapa.ccafs.tpe.jqgrid.ModelUtils;
import org.cgiar.dapa.ccafs.tpe.jqgrid.SoilGrid;
import org.cgiar.dapa.ccafs.tpe.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

public class JQGridAction extends BaseAction {

	private static final long serialVersionUID = -2439433945086306281L;

	private static final String TMIN = "Minimum Temperature";

	private static final String TMAX = "Maximum Temperature";

	private static final String PREC = "Precipitation";

	private static final String RADI = "Radiation";

	private Log LOG = LogFactory.getLog(this.getClass());
	private List<ColModel> colModel;
	private List<String> colNames;
	private List<? extends Grid> gridModel;
	private Integer rows = 25;
	private Integer page = 1;
	private Integer total = 0;
	private Integer records = 0;
	// sorting order - asc or desc
	private String sord = "asc";

	// get index row - i.e. user click to sort.
	private String sidx;
	// Search Field
	private String searchField;

	// The Search String
	private String searchString;

	// he Search Operation
	// ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
	private String searchOper;

	// private Long rowId;
	// private Long id;
	// private String name;
	private Climate climate;
	private String id;

	// private Integer country;
	private Boolean level = true;
	// private List<Integer> params;
	private Integer selectedOutput = 0;
	private Integer selectedCountry;
	private Integer selectedParam;
	private String captionTitle;

	@SuppressWarnings("unused")
	public String execute() {
		if (getSelectedCountry() != null) {
			this.setRegion(tpeService.getRegionById(getSelectedCountry()));
			// if
			// (getRegion().getCategory().getName().equals(CATEGORY_CONTINENT))
			level = getRegion().getCategory().getName()
					.equals(CATEGORY_CONTINENT) ? true : false;

		}

		// if (selectedOutput != null)
		switch (selectedOutput) {
		case 1:// Climate
			gridModel = new ArrayList<ClimateGrid>();
			colNames = ColNames.climateNames();
			colModel = ModelUtils.climateModel();
			gridModel = tpeService.listClimate(selectedCountry, level,
					Utils.climateParam(selectedParam), page, rows);
			captionTitle = "Monthly "
					+ (selectedParam == 1 ? TMIN : (selectedParam == 2 ? TMAX
							: (selectedParam == 3 ? PREC : RADI)))
					+ " Data from " + getRegion().getName();
			break;

		case 2:// Soil
			gridModel = new ArrayList<SoilGrid>();
			colNames = ColNames.soilNames();
			colModel = ModelUtils.soilModel();
			gridModel = tpeService.listSoil(selectedCountry, level,
					new ArrayList<Integer>(), page, rows);
			captionTitle = "Soil Data from " + getRegion().getName();
			break;

		default:
//			gridModel = new ArrayList<ClimateGrid>();
//			colNames = ColNames.climateNames();
//			colModel = ModelUtils.climateModel();
//			gridModel = tpeService.listClimate(1, false, "tmin", page, rows);
//			captionTitle = "Monthly Minimum Temperature Data from Brazil";
			break;
		}

		// log.info("# of variety records: " + gridModel.size())
		// total = varieties.size(); records =
		if (gridModel == null)
			gridModel = new ArrayList<ClimateGrid>();
		records = gridModel.size();
		int to = (getRows() * getPage());
		int from = to - getRows();

		total = (int) Math.ceil((double) getRecords() / (double) getRows());
		if (to > records)
			to = records;
		return ActionSupport.SUCCESS;
	}

	public List<ColModel> getColModel() {
		return colModel;
	}

	public void setColModel(List<ColModel> colModel) {
		this.colModel = colModel;
	}

	public List<String> getColNames() {
		return colNames;
	}

	public void setColNames(List<String> colNames) {
		this.colNames = colNames;
	}

	public List<? extends Grid> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<? extends Grid> gridModel) {
		this.gridModel = gridModel;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getSearchOper() {
		return searchOper;
	}

	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}

	public Climate getClimate() {
		return climate;
	}

	public void setClimate(Climate climate) {
		this.climate = climate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getLevel() {
		return level;
	}

	public void setLevel(Boolean level) {
		this.level = level;
	}

	public Integer getSelectedOutput() {
		if (selectedOutput != 0 && selectedOutput != null)
			return selectedOutput;
		return 1;
	}

	public void setSelectedOutput(Integer selectedOutput) {
		this.selectedOutput = selectedOutput;
	}

	public Integer getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(Integer selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public Integer getSelectedParam() {
		return selectedParam;
	}

	public void setSelectedParam(Integer selectedParam) {
		this.selectedParam = selectedParam;
	}

	public String getCaptionTitle() {
		return captionTitle;
	}

	public void setCaptionTitle(String captionTitle) {
		this.captionTitle = captionTitle;
	}

}

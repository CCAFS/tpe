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
package org.cgiar.dapa.ccafs.tpe.service.impl;

import java.util.List;

import org.cgiar.dapa.ccafs.tpe.dao.ICategoryDao;
import org.cgiar.dapa.ccafs.tpe.dao.IClimateDao;
import org.cgiar.dapa.ccafs.tpe.dao.ICropDao;
import org.cgiar.dapa.ccafs.tpe.dao.ICultivarDao;
import org.cgiar.dapa.ccafs.tpe.dao.IRegionDao;
import org.cgiar.dapa.ccafs.tpe.dao.IScenarioDao;
import org.cgiar.dapa.ccafs.tpe.dao.IStationDao;
import org.cgiar.dapa.ccafs.tpe.dao.IWindowSowingDao;
import org.cgiar.dapa.ccafs.tpe.entity.Category;
import org.cgiar.dapa.ccafs.tpe.entity.Climate;
import org.cgiar.dapa.ccafs.tpe.entity.Crop;
import org.cgiar.dapa.ccafs.tpe.entity.Cultivar;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.entity.Scenario;
import org.cgiar.dapa.ccafs.tpe.entity.Station;
import org.cgiar.dapa.ccafs.tpe.entity.WindowSowing;
import org.cgiar.dapa.ccafs.tpe.service.ITPEService;

/**
 * This class implements the methods defined in the ITPEService interface
 * 
 * @author NMATOVU
 *
 */

public class TPEService implements ITPEService {
	private ICropDao cropDao;
	private IWindowSowingDao windowSowingDao;
	private ICultivarDao cultivarDao;
	private IRegionDao regionDao;
	private ICategoryDao categoryDao;
	private IStationDao stationDao;
	private IScenarioDao scenarioDao;
	private IClimateDao climateDao;

	public void setClimateDao(IClimateDao climateDao) {
		this.climateDao = climateDao;
	}

	public void setScenarioDao(IScenarioDao scenarioDao) {
		this.scenarioDao = scenarioDao;
	}

	public void setStationDao(IStationDao stationDao) {
		this.stationDao = stationDao;
	}

	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setWindowSowingDao(IWindowSowingDao windowSowingDao) {
		this.windowSowingDao = windowSowingDao;
	}

	public void setCultivarDao(ICultivarDao cultivarDao) {
		this.cultivarDao = cultivarDao;
	}

	public void setRegionDao(IRegionDao regionDao) {
		this.regionDao = regionDao;
	}

	public void setCropDao(ICropDao cropDao) {
		this.cropDao = cropDao;
	}

	@Override
	public List<Crop> getAllCrops() {

		return cropDao.getAll();
	}

	@Override
	public Crop getCropById(Integer cropId) {

		return cropDao.getById(cropId);
	}

	@Override
	public List<Cultivar> getCultivarsByCrop(Integer cropId) {

		return cultivarDao.getCultivarsByCrop(cropId);
	}

	@Override
	public List<String> getYearsByCultivar(Integer cultivarId) {

		return cultivarDao.getYearsByCultivar(cultivarId);
	}

	@Override
	public List<WindowSowing> getWindowSowingByCultivar(Integer cultivarId) {

		return windowSowingDao.getWindowSowingByCultivar(cultivarId);
	}

	@Override
	public List<Region> getCountries() {

		return regionDao.getCountries();
	}

	@Override
	public List<Region> getSubregionsByCountry(Integer countryId) {

		return regionDao.getSubregionsByCountry(countryId);
	}

	@Override
	public Region getRegionById(Integer regionId) {

		return regionDao.getById(regionId);
	}

	@Override
	public List<Category> getCategoriesByEntity(String entityName) {

		return categoryDao.getCategoriesByEntity(entityName);
	}

	@Override
	public Category getCategoryById(Integer categoryId) {

		return categoryDao.getById(categoryId);
	}

	@Override
	public List<Station> getStationsByRegion(Integer regionId) {

		return stationDao.getStationsByRegion(regionId);
	}

	@Override
	public Station getStationById(Integer stationId) {

		return stationDao.getById(stationId);
	}

	@Override
	public List<Scenario> getAllScenarios() {

		return scenarioDao.getAll();
	}

	@Override
	public Scenario getScenarioById(Integer scenarioId) {

		return scenarioDao.getById(scenarioId);
	}

	@Override
	public List<Climate> getClimateByStations(List<Integer> stationIds,
			Integer categoryId, String year) {

		return climateDao.getClimateByStations(stationIds, categoryId, year);
	}

	@Override
	public List<Climate> getClimateByRegions(List<Integer> regionIds,
			Integer categoryId, String year) {

		return climateDao.getClimateByRegions(regionIds, categoryId, year);
	}
}

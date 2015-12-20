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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.chart.Chart;
import org.cgiar.dapa.ccafs.tpe.chart.Probability;
import org.cgiar.dapa.ccafs.tpe.dao.ICategoryDao;
import org.cgiar.dapa.ccafs.tpe.dao.IClimateDao; 
import org.cgiar.dapa.ccafs.tpe.dao.ICropDao;
import org.cgiar.dapa.ccafs.tpe.dao.ICultivarDao;
import org.cgiar.dapa.ccafs.tpe.dao.IEnvironmentSoilDao;
import org.cgiar.dapa.ccafs.tpe.dao.IPhenologyGrowthDao;
import org.cgiar.dapa.ccafs.tpe.dao.IRegionDao;
import org.cgiar.dapa.ccafs.tpe.dao.ISoilDao;
import org.cgiar.dapa.ccafs.tpe.dao.ISoilPropertyDao;
import org.cgiar.dapa.ccafs.tpe.dao.IStationDao;
import org.cgiar.dapa.ccafs.tpe.dao.ITagDao;
import org.cgiar.dapa.ccafs.tpe.dao.IUserDao;
import org.cgiar.dapa.ccafs.tpe.entity.Category;
import org.cgiar.dapa.ccafs.tpe.entity.Climate;
import org.cgiar.dapa.ccafs.tpe.entity.Crop;
import org.cgiar.dapa.ccafs.tpe.entity.Cultivar;
import org.cgiar.dapa.ccafs.tpe.entity.PhenologyGrowth;
import org.cgiar.dapa.ccafs.tpe.entity.Post;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.entity.Soil;
import org.cgiar.dapa.ccafs.tpe.entity.Station;
import org.cgiar.dapa.ccafs.tpe.entity.Tag;
import org.cgiar.dapa.ccafs.tpe.entity.User;
import org.cgiar.dapa.ccafs.tpe.exception.PlatformException;
import org.cgiar.dapa.ccafs.tpe.exception.UserNotFoundException;
import org.cgiar.dapa.ccafs.tpe.jqgrid.ClimateGrid;
import org.cgiar.dapa.ccafs.tpe.jqgrid.Grid;
import org.cgiar.dapa.ccafs.tpe.projection.LatLng;
import org.cgiar.dapa.ccafs.tpe.service.ITPEService;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the methods defined in the ITPEService interface
 * 
 * @author NMATOVU
 *
 */
@Transactional
public class TPEService implements ITPEService {
	private ICropDao cropDao;
	private ICultivarDao cultivarDao;
	private IRegionDao regionDao;
	private ICategoryDao categoryDao;
	private IStationDao stationDao;
	private IClimateDao climateDao;
	private ISoilPropertyDao soilPropertyDao;
	private IPhenologyGrowthDao phenologyGrowthDao;
	private ITagDao tagDao;
	private IUserDao userDao;
	private Log LOG = LogFactory.getLog(this.getClass());

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void setTagDao(ITagDao tagDao) {
		this.tagDao = tagDao;
	}

	private IEnvironmentSoilDao environmentSoilDao;

	public void setEnvironmentSoilDao(IEnvironmentSoilDao environmentSoilDao) {
		this.environmentSoilDao = environmentSoilDao;
	}

	public void setPhenologyGrowthDao(IPhenologyGrowthDao phenologyGrowthDao) {
		this.phenologyGrowthDao = phenologyGrowthDao;
	}

	public void setSoilPropertyDao(ISoilPropertyDao soilPropertyDao) {
		this.soilPropertyDao = soilPropertyDao;
	}

	private ISoilDao soilDao;

	public void setSoilDao(ISoilDao soilDao) {
		this.soilDao = soilDao;
	}

	public void setClimateDao(IClimateDao climateDao) {
		this.climateDao = climateDao;
	}

	public void setStationDao(IStationDao stationDao) {
		this.stationDao = stationDao;
	}

	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
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
	public Soil getSoilById(Integer soilId) {

		return soilDao.getById(soilId);
	}

	@Override
	public Map<Integer, Map<Double, Double>> getSoilDistribution(
			Integer soilId, Integer regionId, Integer categoryId) {

		return soilPropertyDao
				.getSoilDistribution(soilId, regionId, categoryId);
	}

	@Override
	public Map<Integer, Map<Double, Double>> getSoilDistribution(
			List<Integer> soilIds, Integer regionId, Integer categoryId) {

		return soilPropertyDao.getSoilDistribution(soilIds, regionId,
				categoryId);
	}

	@Override
	public Map<Integer, Map<Double, Double>> getStationsPoints(Integer regionId) {

		return stationDao.getStationsPoints(regionId);
	}

	@Override
	public Map<String, Map<Double, Double>> getTPERegions(Integer cultivarId,
			Integer regionId, Integer swindowId, String year) {

		return phenologyGrowthDao.getTPERegions(cultivarId, regionId,
				swindowId, year);
	}

	@Override
	public Map<String, Map<Double, Double>> getTPERegions(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, String scenario) {

		return phenologyGrowthDao.getTPERegions(cultivarId, regionId,
				swindowId, year, scenario);
	}

	@Override
	public Map<String, Map<String, Double>> getTPESoil(Integer cultivarId,
			Integer regionId, Integer swindowId, String year) {

		return phenologyGrowthDao.getTPESoil(cultivarId, regionId, swindowId,
				year);
	}

	@Override
	public Map<String, Map<String, Double>> getTPESoil(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, String scenario) {

		return phenologyGrowthDao.getTPESoil(cultivarId, regionId, swindowId,
				year, scenario);
	}

	@Override
	public Map<String, List<Station>> getStationPerRegion(List<Integer> regions) {

		return stationDao.getStationPerRegion(regions);
	}

	@Override
	public Map<String, List<LatLng>> getStationByRegion(List<Integer> regions) {

		return stationDao.getStationByRegion(regions);
	}

	@Override
	public Map<String, Object> getSoilFeaturesByCountry(Integer propertyId,
			Integer countryId, Boolean continent) {

		return soilPropertyDao.getSoilFeaturesByCountry(propertyId, countryId,
				continent);
	}

	@Override
	public Map<String, Object> getSoilFeaturesByRegions(Integer propertyId,
			List<Integer> subregions) {

		return soilPropertyDao.getSoilFeaturesByRegions(propertyId, subregions);
	}

	@Override
	public Map<String, Object> getSoilFeaturesByRegion(Integer propertyId,
			Integer subregion) {

		return soilPropertyDao.getSoilFeaturesByRegion(propertyId, subregion);
	}

	@Override
	public Map<String, Object> getClimateGeoJSON(Integer countryId,
			List<Integer> indicators, Boolean continent) {

		return climateDao.getClimateGeoJSON(countryId, indicators, continent);
	}

	@Override
	public List<Chart> getTPEColumnSeries(Integer subregionId,
			Integer categoryId, String scenario, Integer cultivarId,
			String year, Integer swindow) {
		return phenologyGrowthDao.getTPEColumnSeries(subregionId, categoryId,
				scenario, cultivarId, year, swindow);
	}

	@Override
	public List<Soil> getSoilTextures() {

		return soilDao.getAll();
	}

	@Override
	public List<Category> getOutputs() {

		return categoryDao.getOutputs();
	}

	@Override
	public List<String> getClimateYears(Integer countryId) {

		return climateDao.getClimateYears(countryId);
	}

	@Override
	public List<String> getTPEYears(Integer countryId, Integer cultivarId) {

		return phenologyGrowthDao.getTPEYears(countryId, cultivarId);
	}

	@Override
	public Map<String, Object> getStationGeoJson(Integer countryId) {

		return stationDao.getStationGeoJson(countryId);
	}

	@Override
	public Map<String, Object> getSoilGeoJson(List<Integer> propertyIds,
			Integer countryId, Boolean continent) {

		return soilPropertyDao
				.getSoilGeoJson(propertyIds, countryId, continent);
	}

	@Override
	public List<String> getEnvSowingDates(Integer country) {

		return environmentSoilDao.getEnvSowingDates(country);
	}

	@Override
	public Map<String, List<Probability>> getEnvSoilProbabilities(
			Integer country) {

		return environmentSoilDao.getEnvSoilProbabilities(country);
	}

	@Override
	public Map<String, Object> getTPEBox(Integer country, Integer cultivar) {

		return phenologyGrowthDao.getTPEBox(country, cultivar);
	}

	@Override
	public Cultivar getCultivar(Integer cultivarId) {

		return cultivarDao.getById(cultivarId);
	}

	@Override
	public List<Object> getStressCategories(List<String> stressSeries,
			Integer cultivarId, Integer countryId) {

		return phenologyGrowthDao.getStressCategories(stressSeries, cultivarId,
				countryId);
	}

	@Override
	public Map<String, Object> getSeriesData(Integer cultivarId,
			Integer countryId) {

		return phenologyGrowthDao.getSeriesData(cultivarId, countryId);
	}

	@Override
	public Map<String, Object> getClimateSeries(Integer country,
			boolean continent) {

		return climateDao.getClimateSeries(country, continent);
	}

	@Override
	public List<Tag> getAllTags() {

		return tagDao.getAll();
	}

	@Override
	public void addTag(Tag tag) throws PlatformException {
		tagDao.addOrMerge(tag);

	}

	@Override
	public List<Climate> getClimateByStations(List<Integer> stationIds) {

		return climateDao.getClimateByStations(stationIds);
	}

	@Override
	public List<Climate> getClimateByRegions(List<Integer> regionIds) {

		return climateDao.getClimateByRegions(regionIds);
	}

	@Override
	public List<Region> getCountriesAndContinents() {

		return regionDao.getCountriesAndContinents();
	}

	@Override
	public List<Object[]> getClimateData(Integer countryId) {

		return climateDao.getClimateData(countryId);
	}

	@Override
	public List<PhenologyGrowth> getTestSeries(Integer countryId,
			Integer cultivarId) {

		return phenologyGrowthDao.getTestSeries(countryId, cultivarId);
	}

	@Override
	public List<Tag> getTags(boolean enabled) {

		return tagDao.getTags(enabled);
	}

	@Override
	public void addTag(String name, String url, Integer weight, Boolean enabled)
			throws PlatformException {
		tagDao.addTag(name, url, weight, enabled);

	}

	@Override
	public Tag findTagByName(String name) {

		return tagDao.findTagByName(name);
	}

	@Override
	public List<Post> getTagPosts(String tag) {

		return tagDao.getTagPosts(tag);
	}

	@Override
	public List<User> getUsers() {

		return userDao.getAll();
	}

	@Override
	public void addUser(User user) throws PersistenceException {
		if (userDao.findUserByUsername(user.getUsername()) != null) {
			LOG.error("User with the specified username already exists in the system");
			throw new EntityExistsException("User with the specified username("
					+ user.getUsername() + ") already exists in the system.");
		} else
			userDao.addOrMerge(user);

	}

	@Override
	public User findUserByUsername(String username)
			throws UserNotFoundException {

		return userDao.findUserByUsername(username);
	}

	@Override
	public void updateUser(User user) throws PersistenceException {

		// userDao.updateUser(user);
		userDao.addOrMerge(user);
	}

	@Override
	public List<ClimateGrid> listClimate(Integer country, Boolean level,
			String param, int start, int rows) {

		return climateDao.listClimate(country, level, param, start, rows);
	}

	@Override
	public List<? extends Grid> listSoil(Integer country, boolean level,
			ArrayList<Integer> params, int page, int rows) {

		return soilPropertyDao.listSoil(country, level, params, page, rows);
	}

	@Override
	public Map<Integer, String> getMapOptions() {

		List<Category> result = this.getOutputs();
		Map<Integer, String> options = new LinkedHashMap<Integer, String>();
		if (result != null)
			for (Category cat : result)
				options.put(cat.getId(), cat.getName());
		return options;
	}

	@Override
	public Map<Integer, String> getCrops() {
		List<Crop> list = cropDao.getAll();
		Map<Integer, String> crops = new LinkedHashMap<Integer, String>();
		if (list != null && !list.isEmpty())
			for (Crop cp : list)
				crops.put(cp.getId(), cp.getName());
		return crops;
	}

	@Override
	public Map<Integer, String> getRegions() {
		List<Region> list = this.getCountriesAndContinents();
		Map<Integer, String> regions = new LinkedHashMap<Integer, String>();
		if (list != null && !list.isEmpty())
			for (Region reg : list)
				regions.put(reg.getId(), reg.getName());
		return regions;
	}

	@Override
	public Map<Integer, Map<Integer, String>> getCultivars() {

		List<Crop> crops = cropDao.getAll();
		List<Cultivar> cultivars;
		Map<Integer, Map<Integer, String>> map = new LinkedHashMap<Integer, Map<Integer, String>>();
		Map<Integer, String> temp = new LinkedHashMap<Integer, String>();
		if (crops != null && !crops.isEmpty())
			for (Crop crop : crops) {
				temp = new LinkedHashMap<Integer, String>();
				cultivars = this.getCultivarsByCrop(crop.getId());
				if (cultivars != null && !cultivars.isEmpty())
					for (Cultivar c : cultivars)
						temp.put(c.getId(), c.getName());
				map.put(crop.getId(), temp);
			}

		return map;
	}

	 

	
}

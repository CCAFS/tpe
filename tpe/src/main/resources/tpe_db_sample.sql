insert into category (category_name, entity_class) values
('TPE','Output'),('Soil','Output'),('Climate','Output'),
('Country','Region'),('State','Region'),('Province','Region'),('District','Region'),('Municipio','Region'),
('Physical','Soil'),('Chemical','Soil'),('Hydraulic','Soil'),
('Climate','Climate');

insert into soil(soil_name) values
('Clay'),('Sand'),('Loam'),('Clay Sand'),('Clay Loam');

insert into region (region_name,category_id) values
('Brazil',4),('Colombia',4);

insert into region (region_name,category_id,region_parent) values
('RO',5,1),('GO',5,1),('MT',5,1),('TO',5,1),('MS',5,1);

insert into property(property_name,category_id,entity_class) values
('PH',10,'Soil'),('Depth',9,'Soil'),('Organic Matter',10,'Soil'),('Bulky Density',10,'Soil'),('Taxonomy',9,'Soil'),
('TMAX',12,'Climate'),('TMIN',12,'Climate'),('Precipitation',12,'Climate'),('Irradiance',12,'Climate');

insert into crop(crop_name) values
('Rice'),('Beans');

insert into cultivar(cultivar_name,crop_id) values
('Fed2000',1),('Be2012',2);
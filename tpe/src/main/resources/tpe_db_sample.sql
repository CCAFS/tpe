insert into category (category_name, entity_class) values
('TPE','Output'),('Soil','Output'),('Climate','Output'),
('Country','Region'),('State','Region'),('Province','Region'),('District','Region'),('Municipio','Region'),
('Physical','Soil'),('Chemical','Soil'),('Hydraulic','Soil'),
('Climate','Climate');

insert into soil(soil_name,soil_color) values
('Clay','green'),('Sand','red'),('Loam','blue'),('Clay Sand','orange'),('Clay Loam','purple');

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

insert into station (station_name, station_latitude, station_longitude, region_id) values
('Anapoli',-48.91,-16.3,4),('Aragarcas',-52.23,-15.9,4),('Aruana',-51,-14.9,4),('BomJesusGoias',-50.18,-18.07,4),('Parauna',-50.49,-17.51,4),
('Caiaponia',-51.82,-16.97,4),('CaldasNovas',-48.61,-17.71,4),('Catalao',-47.07,-18.12,4),('Cristalina',-47.27,-17.12,4),('Faina',-50.37,-15.43,4),
('AltaFloresta',-56.75,-10.07,5),('Aripuana',-59.45,-10.15,5),('CampoVerde',-55.08,-15.31,5),('Canarana',-52.27,-13.47,5),('Comodoro',-59.45,-13.42,5);

insert into soil_property(soil_id,property_id,property_value,station_id,longitude,latitude) values
(1,1,7.2,1,-51.82,-16.97),(1,1,4.2,1,-52.23,-15.9),(1,1,8.5,1,-47.07,-18.12),(1,1,3.6,1,-47.27,-17.12);

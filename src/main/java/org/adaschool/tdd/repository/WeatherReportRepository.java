package org.adaschool.tdd.repository;

import java.util.List;

import org.adaschool.tdd.repository.document.GeoLocation;
import org.adaschool.tdd.repository.document.WeatherReport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherReportRepository
    extends MongoRepository<WeatherReport, String>
{
    //List<WeatherReport> findByName(String name);
    //List<WeatherReport> findByGeoLocationBetween(GeoLocation g1,GeoLocation g2);
}

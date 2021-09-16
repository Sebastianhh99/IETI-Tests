package org.adaschool.tdd.service;

import org.adaschool.tdd.controller.weather.dto.WeatherReportDto;
import org.adaschool.tdd.exception.WeatherReportNotFoundException;
import org.adaschool.tdd.repository.WeatherReportRepository;
import org.adaschool.tdd.repository.document.GeoLocation;
import org.adaschool.tdd.repository.document.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MongoWeatherService
    implements WeatherService
{

    private final WeatherReportRepository repository;

    public MongoWeatherService( @Autowired WeatherReportRepository repository )
    {
        this.repository = repository;
    }

    @Override
    public WeatherReport report( WeatherReportDto weatherReportDto )
    {
        return repository.save(new WeatherReport(weatherReportDto));
    }

    @Override
    public WeatherReport findById( String id )
    {
        Optional<WeatherReport> optional = repository.findById(id);
        if( optional.isPresent()){
            return optional.get();
        }
        throw new WeatherReportNotFoundException();
    }

    @Override
    public List<WeatherReport> findNearLocation( GeoLocation geoLocation, float distanceRangeInMeters )
    {
        List<WeatherReport> ans = new ArrayList<WeatherReport>();
        double lat = geoLocation.getLat();
        double lng = geoLocation.getLng();
        List<WeatherReport> reports = repository.findAll();
        for(WeatherReport i:reports){
            GeoLocation geo = i.getGeoLocation();
            double distance = Math.pow(geo.getLat() - lat, 2) + Math.pow(geo.getLng() - lng, 2);
            if(distance <= distanceRangeInMeters){
                ans.add(i);
            }
        }
        return ans;
    }

    @Override
    public List<WeatherReport> findWeatherReportsByName( String reporter )
    {
        List<WeatherReport> ans = new ArrayList<WeatherReport>();
        List<WeatherReport> reports = repository.findAll();
        for(WeatherReport i:reports){
            if(i.getReporter().equals(reporter)){
                ans.add(i);
            }
        }
        return ans;
    }
}

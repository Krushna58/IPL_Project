package com.ipl.serviceIpl;

import com.ipl.dbRepo.SeasonRepo;
import java.util.List;

public class SeasonServiceImpl implements InterSeasonService {
	
	
		
	public int addSeason(int season){
		SeasonRepo repo = new SeasonRepo();
	
	 if (season < 2008 || season > 2025) {

		System.out.println("Invalid season!!!");
       		 return 0;
		}
	return repo.addSeason(season);

	}
	
	public int deleteSeason(int season){
	    SeasonRepo repo = new SeasonRepo();
	    
	    if (season < 2008 || season > 2025){
	        System.out.println("Season must be in between 2008 to 2025");
	        return 0;
	        
	    }
	    return repo.deleteSeason(season);
	
	}
	
	// view all season
	public List<String> viewSeason() {
	  SeasonRepo repo = new SeasonRepo();
	      return repo.viewSeason();
	}

}

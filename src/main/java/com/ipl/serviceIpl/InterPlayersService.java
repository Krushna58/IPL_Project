package com.ipl.serviceIpl;
import com.ipl.entity.PlayersEntity;
import java.util.*;

public interface InterPlayersService{
         public  List<PlayersEntity> viewPlayers();
          int insertPlayer(PlayersEntity entity);
          public int deletePlayerById(int P_ID);
           public int updatePlayer(PlayersEntity entity);
          List<PlayersEntity> viewPlayersByIdAndSeason(int t_id, int season);
  /*        List<PlayersEntity> viewPlayersByTeam();
         
          void updatePlayerName();
		*/	
		
}

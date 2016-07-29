package com.fablix.moviedb.DAO;

//import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fablix.moviedb.filter.searchFilter;
//import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Genres;
import com.fablix.moviedb.model.MovieInfo;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Pager;
import com.fablix.moviedb.model.Stars;

public class MoiveInfoDAO {
	//private Connection connection = dbConnection.getConnection();
	private MoviesDAO mDAO = new MoviesDAO();
	private GenresDAO gDAO = new GenresDAO();
	private StarsDAO sDAO = new StarsDAO();
	
	public Pager<MovieInfo> searchMovieInfo( Map<String, Object> params, String orderWord, String ascDesc, 
											boolean subMatch, int pageSize, int currentPage ) throws SQLException{
		Logger log = Logger.getLogger(searchFilter.class); 
		long startTime = System.nanoTime();
		
		Pager<Movies> movies = mDAO.searchMovies(params, orderWord, ascDesc, subMatch, pageSize, currentPage);
		
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		log.info("JDBC " + elapsedTime + " end"+"\n");
		System.out.println("Finsihed jdbc...time is " + elapsedTime );
		
		List<MovieInfo> movieInfos = new ArrayList<MovieInfo>();
		
		if (movies.getDataList()!=null){
			for (Movies m : movies.getDataList()){
				List<Genres> genres = gDAO.getGenreByMovie(m);
				List<Stars> stars = sDAO.getStarsByMovie(m);			
				MovieInfo  movieInfo = new MovieInfo(m,stars,genres);
				movieInfos.add(movieInfo);
			}
		}
		
		Pager<MovieInfo> movieInfosPage= new Pager<MovieInfo>(pageSize,currentPage, movies.getTotalRecord(), movieInfos);
		return movieInfosPage;
	}
	
	public Pager<MovieInfo> browseMovieInfo( String prefix, String orderWord, String ascDesc, int pageSize, int currentPage) throws SQLException{

		//Pager<Movie> movies = mDAO.searchMovies(params, orderWord, ascDesc, subMatch, pageSize, currentPage);
		
		MoviesDAO mDao = new MoviesDAO();
		

		Pager<MovieInfo> movieInfosPage=  mDao.browseMoviesByTitle(prefix,orderWord,ascDesc,pageSize,currentPage);
		return movieInfosPage;
	}
	public Pager<MovieInfo> browseMovieByGenre( String genre, String orderWord, String ascDesc, int pageSize, int currentPage) throws SQLException{

		//Pager<Movie> movies = mDAO.searchMovies(params, orderWord, ascDesc, subMatch, pageSize, currentPage);
		
		MoviesDAO mDao = new MoviesDAO();
		

		Pager<MovieInfo> movieInfosPage=  mDao.getMovieByGenre(genre,orderWord,ascDesc,pageSize,currentPage);
		return movieInfosPage;
	}
	

}

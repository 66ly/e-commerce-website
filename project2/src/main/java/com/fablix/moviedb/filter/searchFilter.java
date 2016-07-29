package com.fablix.moviedb.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet Filter implementation class searchFilter
 */
@WebFilter(filterName="B2_searchFilter" , urlPatterns={"/searchServlet"})
public class searchFilter implements Filter {

    /**
     * Default constructor. 
     */
    public searchFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse rp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest request  = (HttpServletRequest) req;
		HttpServletResponse response  = (HttpServletResponse) rp;
		// pass the request along the filter chain
		System.out.println("Start using search servlet...");
		 // 1. create log  
        Logger log = Logger.getLogger(searchFilter.class); 
        // 2. get log config file  
        //PropertyConfigurator.configure("log4j.properties");
        
		long startTime = System.nanoTime();
		
		chain.doFilter(request, response);
		
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		log.info("Search Servlet " + elapsedTime + " end" +"\n");
		System.out.println("Finsihed search servlet...time is " + elapsedTime );
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

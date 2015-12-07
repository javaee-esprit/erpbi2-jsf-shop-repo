package edu.esprit.shop.presentation.filters;

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

import edu.esprit.shop.presentation.mbeans.IdentityBean;

@WebFilter("/pages/admin/*")
public class AdminZoneSecurityFilter implements Filter{

	
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("admin filter has been instanciated");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		IdentityBean identity =  (IdentityBean) req.getSession().getAttribute("identity");
		
		Boolean letGo = false;
		if (
				(identity!=null)&&
				(identity.getUser()!= null)&&
				(identity.hasRole("Admin"))
				
				) {
			letGo = true;
		}
		
		
		if (letGo) {
			chain.doFilter(req, resp);
		}else{
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath()+"/pages/login.jsf");
		}
		
	}

	public void destroy() {
		System.out.println("admin filter will soon be destroyed");
	}

}

package shionn.bm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProvider provider;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and().httpBasic()
				.realmName("BreizhMaker");
		http.authorizeRequests().antMatchers("/dkp/add/**").hasRole("ADMIN").and().httpBasic()
				.realmName("BreizhMaker");
		http.authorizeRequests().antMatchers("/dkp/rm/**").hasRole("ADMIN").and().httpBasic()
				.realmName("BreizhMaker");
		http.authorizeRequests().antMatchers("/raid/add").hasRole("ADMIN").and().httpBasic()
				.realmName("BreizhMaker");
		http.authorizeRequests().antMatchers("/raid/update").hasRole("ADMIN").and().httpBasic()
				.realmName("BreizhMaker");
		http.authorizeRequests().antMatchers("/raid/edit/**").hasRole("ADMIN").and().httpBasic()
				.realmName("BreizhMaker");
	}
}
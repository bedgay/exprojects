package javaz.spring.security.encoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author SUCCESS\tungo
 *
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean matches(CharSequence inputPassword, String dbPassword) {
		if (StringUtils.isNotEmpty(inputPassword.toString()) && StringUtils.isNotEmpty(dbPassword)) {
			return MD5.encode("Spring" + inputPassword).equals(dbPassword);
		}
		return false;
	}

}

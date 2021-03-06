/**
 * 
 */
package preti.stock.fe.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * @author gsantiago
 *
 */
@Service
public class DateService {

	public Date getCurrentSystemDate() {
		//FIXME: est� retornando sempre 01/01/2015 para efeitos de testes
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = dateFormat.parse("2015-01-01");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return d;
	}
}

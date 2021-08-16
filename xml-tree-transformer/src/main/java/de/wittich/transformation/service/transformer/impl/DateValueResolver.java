package de.wittich.transformation.service.transformer.impl;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import de.wittich.transformation.persistence.ConfigurationEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of {@link AbstractValueTransformer} transforms the given value
 * into a {@link XMLGregorianCalendar} object.
 * 
 * @author Frank
 */
@Slf4j
@Service("Date")
public class DateValueResolver extends AbstractValueTransformer {

	@Override
	protected Object transformValue(String value, ConfigurationEntity configEntity) {
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar( //
					GregorianCalendar.from( //
							LocalDate.parse(value).atStartOfDay(ZoneOffset.UTC) //
					) //
			);
		} catch (DatatypeConfigurationException e) {
			log.error("Parsing of date {} failed.", value, e);
		}
		return null;

	}
}

package converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, Timestamp> {

        @Override
        public Timestamp convertToDatabaseColumn(LocalDate localDate) {
            if(localDate != null) {
                Date date = Date.from(localDate.atStartOfDay()
                        .atZone(ZoneId.systemDefault()).toInstant());
                return new Timestamp(date.getTime());
            }
            return null;
        }

        @Override
        public LocalDate convertToEntityAttribute(Timestamp timestamp) {
            return timestamp == null ? null : timestamp.toLocalDateTime().toLocalDate();
        }

}
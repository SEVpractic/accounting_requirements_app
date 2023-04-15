package sev_customs.accounting_requirements_app.util.converters;

import org.springframework.core.convert.converter.Converter;
import sev_customs.accounting_requirements_app.model.RequestStatus;

public class StringToEnumConverter implements Converter<String, RequestStatus> {
    @Override
    public RequestStatus convert(String source) {
        return RequestStatus.valueOf(source.toUpperCase());
    }
}

package yarchuk.coursework.justice.formatter;

import yarchuk.coursework.justice.model.IssueType;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import yarchuk.coursework.justice.service.IssueTypeService;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class IssueTypeFormatter implements Formatter<IssueType> {

    private final IssueTypeService issueTypeService;

    public IssueTypeFormatter(IssueTypeService issueTypeService) {
        this.issueTypeService = issueTypeService;
    }

    @Override
    public String print(IssueType issueType, Locale locale) {
        return issueType.getName();
    }

    @Override
    public IssueType parse(String text, Locale locale) throws ParseException {
        Collection<IssueType> findIssuesTypes = issueTypeService.findAll();

        for (IssueType type : findIssuesTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }

        throw new ParseException("type not found: " + text, 0);
    }

}

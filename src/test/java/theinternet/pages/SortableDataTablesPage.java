package theinternet.pages;

import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Table;
import tables.StreamTable;

import java.util.List;
import java.util.stream.Stream;

public class SortableDataTablesPage extends BasePage<SortableDataTablesPage> {

    @Visible
    @Name("Heading")
    @FindBy(css = "div.example h3")
    private WebElement heading;

    @Visible
    @Name("Table 1")
    @FindBy(css = "table#table1")
    private StreamTable table1;

    @Visible
    @Name("Table 2")
    @FindBy(css = "table#table2")
    private StreamTable table2;

    @Step("Get table 1 column {0} contents")
    public Stream<String> getTable1ColumnContents(String colHeader) {
        return table1.getColumn(colHeader).map(WebElement::getText);
    }

    @Step("Get table 2 column {0} contents")
    public Stream<String> getTable2ColumnContents(String colHeader) {
        return table2.getColumn(colHeader).map(WebElement::getText);
    }

    @Step("Sort table 2 by column with header {0}")
    public SortableDataTablesPage sortTable2ByColumnName(String colHeader) {
        sortTableByColumnName(table2, colHeader);
        return this;
    }

    @Step("Sort table {0} by column name {1}")
    private void sortTableByColumnName(StreamTable table, String colHeader) {
        table.getHeadings()
        .filter(heading -> colHeader.equals(heading.getText()))
        .findFirst()
        .orElseThrow(NotFoundException::new)
        .click();
    }

}

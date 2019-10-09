package org.oddys.timetracking.dto;

import java.util.List;
import java.util.Objects;

public class PageDto<E> {
    private List<E> elements;
    private long currentPage;
    private int rowsPerPage;
    private long numPages;

    public PageDto() {}

    public PageDto(List<E> elements, long currentPage, int rowsPerPage, long numPages) {
        this.elements = elements;
        this.currentPage = currentPage;
        this.rowsPerPage = rowsPerPage;
        this.numPages = numPages;
    }

    public List<E> getElements() {
        return elements;
    }

    public void setElements(List<E> elements) {
        this.elements = elements;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public long getNumPages() {
        return numPages;
    }

    public void setNumPages(long numPages) {
        this.numPages = numPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageDto)) return false;
        PageDto<?> pageDto = (PageDto<?>) o;
        return currentPage == pageDto.currentPage &&
                rowsPerPage == pageDto.rowsPerPage &&
                numPages == pageDto.numPages &&
                Objects.equals(elements, pageDto.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements, currentPage, rowsPerPage, numPages);
    }
}

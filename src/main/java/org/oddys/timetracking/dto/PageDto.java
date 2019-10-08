package org.oddys.timetracking.dto;

import java.util.List;

public class PageDto<E> {
    private List<E> elements;
    private int currentPage;
    private int rowsPerPage;
    private int numPages;

    public PageDto() {}

    public PageDto(List<E> elements, int currentPage, int rowsPerPage, int numPages) {
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }
}

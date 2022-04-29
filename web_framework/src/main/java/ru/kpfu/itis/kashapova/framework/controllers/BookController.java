package ru.kpfu.itis.kashapova.framework.controllers;

import ru.kpfu.itis.kashapova.framework.mav.ModelAndView;
import ru.kpfu.itis.kashapova.framework.database.BooksHashMap;

import javax.servlet.http.HttpServletRequest;

public class BookController implements Controller{
    private BooksHashMap books = new BooksHashMap();

    @Override
    public ModelAndView doAction(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView(books.getBookHashMap().getAllData(), "books_list");
        modelAndView.addData();
        return modelAndView;
    }
}

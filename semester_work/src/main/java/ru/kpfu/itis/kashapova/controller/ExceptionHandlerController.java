package ru.kpfu.itis.kashapova.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.kashapova.exception.AccessDeniedException;
import ru.kpfu.itis.kashapova.exception.NotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static ModelAndView notFound() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error_page/NOT_FOUND");
        return mav;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public static ModelAndView accessDenied() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error_page/FORBIDDEN");
        return mav;
    }

    @ExceptionHandler(ServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static ModelAndView serverError() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error_page/INTERNAL_SERVER_ERROR");
        return mav;
    }
}

package ru.kpfu.itis.kashapova.framework.controllers;

import ru.kpfu.itis.kashapova.framework.mav.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public interface Controller {
    ModelAndView doAction(HttpServletRequest req);
}
